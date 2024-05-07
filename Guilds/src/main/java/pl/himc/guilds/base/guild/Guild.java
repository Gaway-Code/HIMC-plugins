package pl.himc.guilds.base.guild;

import com.gmail.filoghost.holographicdisplays.api.Hologram;
import com.gmail.filoghost.holographicdisplays.api.HologramsAPI;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.scheduler.BukkitTask;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.AbstractBasic;
import pl.himc.guilds.base.BasicType;
import pl.himc.guilds.base.guild.cuboid.Cuboid;
import pl.himc.guilds.base.rank.Rank;
import pl.himc.guilds.base.rank.RankManager;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.hook.PluginHook;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;

public class Guild extends AbstractBasic {

    private String name;
    private String tag;
    private GuildUser owner;
    private Set<GuildUser> members;
    private Set<GuildUser> mods;
    private Set<Guild> alliances;
    private Location home;
    private Cuboid cuboid;
    private int lives;
    private final Map<String, BukkitTask> invited;
    private final Map<String, BukkitTask> invitedGuilds;
    private long war;
    private long validity;
    private long explode;
    private boolean pvp;
    private long ban;
    private long turbodrop;
    private long turboexp;
    private String reason;
    private Inventory treasure;
    private Rank rank;
    private Hologram hologram;

    public Guild(String name, String tag, GuildUser owner) {
        this.name = name;
        this.tag = tag;
        this.members = ConcurrentHashMap.newKeySet();
        this.mods = ConcurrentHashMap.newKeySet();
        this.alliances = ConcurrentHashMap.newKeySet();
        this.invited = new HashMap<>();
        this.invitedGuilds = new HashMap<>();
        this.war = System.currentTimeMillis() + PluginGuild.getPlugin().getPluginConfiguration().createGuildProtection;
        this.pvp = false;

        this.ban = 0L;
        this.turbodrop = System.currentTimeMillis();
        this.turboexp = System.currentTimeMillis();

        this.addMember(this.owner = owner);

        this.getRank();

        PluginGuild.getPlugin().getGuildManager().putGuild(tag, this);
    }

    public Guild(ResultSet rs) {
        this.members = ConcurrentHashMap.newKeySet();
        this.mods = ConcurrentHashMap.newKeySet();
        this.alliances = ConcurrentHashMap.newKeySet();
        this.invited = new HashMap<>();
        this.invitedGuilds = new HashMap<>();
        this.pvp = false;
        this.ban = 0L;
        try {
            this.tag = rs.getString("TAG");
            this.name = rs.getString("NAME");
            this.owner = PluginGuild.getPlugin().getUserManager().getUser(rs.getString("OWNER"));
            this.members = PluginGuild.getPlugin().getUserManager().getUsers(StringUtil.fromString(rs.getString("MEMBERS")));
            PluginGuild.getPlugin().getUserManager().setGuild(this.members, this);
            this.members.forEach(user -> user.setChanges(false));

            this.mods = PluginGuild.getPlugin().getUserManager().getUsers(StringUtil.fromString(rs.getString("MODS")));
            this.alliances = PluginGuild.getPlugin().getGuildManager().getGuildsTag(StringUtil.fromString(rs.getString("ALLIANCES")));
            for (Guild ally : this.alliances) {
                ally.addAlly(this);
                ally.setChanges(false);
            }
            this.home = StringUtil.locationFromString(rs.getString("HOME"));
            this.lives = rs.getInt("LIVES");
            this.war = rs.getLong("WAR");
            this.validity = rs.getLong("VALIDITY");
            this.turbodrop = rs.getLong("TURBODROP");
            this.turboexp = rs.getLong("TURBOEXP");
            this.pvp = rs.getBoolean("PVP");
            this.ban = rs.getLong("BAN");
            this.reason = rs.getString("REASON");
            this.treasure = StringUtil.inventoryFromString(rs.getString("TREASURE"));

            Location center = StringUtil.locationFromString(rs.getString("CENTER"));
            int size = rs.getInt("SIZE");
            new Cuboid(this, center, size).calc();

            this.getRank();

            PluginGuild.getPlugin().getGuildManager().putGuild(rs.getString("TAG"), this);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getSQL() {
        String s = "INSERT INTO `" + PluginGuild.getPlugin().getPluginConfiguration().database.tableGuilds + "` VALUES(" +
                "'%tag%'," +
                "'%name%'," +
                "'%owner%'," +
                "'%members%'," +
                "'%mods%'," +
                "'%alliances%'," +
                "'%home%'," +
                "'%center%'," +
                "'%size%'," +
                "'%lives%'," +
                "'%war%'," +
                "'%validity%'," +
                "'%turbodrop%'," +
                "'%turboexp%'," +
                "'%pvp%'," +
                "'%ban%'," +
                "%reason%," +
                "'%treasure%'" +
                ") ON DUPLICATE KEY UPDATE " +
                "NAME='%name%'," +
                "OWNER='%owner%'," +
                "MEMBERS='%members%'," +
                "MODS='%mods%'," +
                "ALLIANCES='%alliances%'," +
                "HOME='%home%'," +
                "CENTER='%center%'," +
                "SIZE='%size%'," +
                "LIVES='%lives%'," +
                "WAR='%war%'," +
                "VALIDITY='%validity%'," +
                "TURBODROP='%turbodrop%'," +
                "TURBOEXP='%turboexp%'," +
                "PVP='%pvp%'," +
                "BAN='%ban%'," +
                "REASON=%reason%," +
                "TREASURE='%treasure%';";
        s = StringUtils.replace(s, "%tag%", this.tag);
        s = StringUtils.replace(s, "%name%", this.name);
        s = StringUtils.replace(s, "%owner%", this.owner.getName());
        s = StringUtils.replace(s, "%members%", StringUtil.toString(PluginGuild.getPlugin().getUserManager().getNames(this.members), false));
        s = StringUtils.replace(s, "%mods%", StringUtil.toString(PluginGuild.getPlugin().getUserManager().getNames(this.mods), false));
        s = StringUtils.replace(s, "%alliances%", StringUtil.toString(PluginGuild.getPlugin().getGuildManager().getTags(this.alliances), false));
        s = StringUtils.replace(s, "%home%", StringUtil.locationLongToString(this.home));
        s = StringUtils.replace(s, "%center%", StringUtil.locationToString(this.cuboid.getCenter()));
        s = StringUtils.replace(s, "%size%", this.cuboid.getSize() + "");
        s = StringUtils.replace(s, "%lives%", this.lives + "");
        s = StringUtils.replace(s, "%war%", this.war + "");
        s = StringUtils.replace(s, "%validity%", this.validity + "");
        s = StringUtils.replace(s, "%turbodrop%", Long.toString(this.turbodrop));
        s = StringUtils.replace(s, "%turboexp%", Long.toString(this.turboexp));
        s = StringUtils.replace(s, "%pvp%", this.pvp + "");
        s = StringUtils.replace(s, "%ban%", this.ban + "");
        s = StringUtils.replace(s, "%reason%", (this.reason == null) ? "NULL" : ("'" + this.reason + "'"));
        s = StringUtils.replace(s, "%treasure%", StringUtil.inventoryToString(this.getTreasure()));
        return s;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public BasicType getType() {
        return BasicType.GUILD;
    }

    public Rank getRank() {
        if (this.rank != null) {
            return this.rank;
        }

        this.rank = new Rank(this);
        RankManager.getInst().update(this);
        return this.rank;
    }

    public String getTag() {
        return this.tag;
    }

    public GuildUser getOwner() {
        return this.owner;
    }

    public boolean isOwner(GuildUser user) {
        return user.equals(this.owner);
    }

    public boolean isOwner(String name) {
        return this.owner.getName().equalsIgnoreCase(name);
    }

    public Set<GuildUser> getMembers() {
        return this.members;
    }

    public Set<GuildUser> getOnlineMembers() {
        return this.members
                .stream()
                .filter(GuildUser::isOnline)
                .collect(Collectors.toSet());
    }

    public boolean isMember(GuildUser user) {
        return this.members.contains(user);
    }

    public boolean isMember(String name) {
        for (GuildUser user : this.members) {
            if (user.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void addMember(GuildUser user) {
        this.members.add(user);
        this.changes();
    }

    public void removeMember(GuildUser user) {
        this.members.remove(user);
        this.changes();
    }

    public Set<GuildUser> getMods() {
        return this.mods;
    }

    public void addMod(GuildUser user) {
        this.mods.add(user);
        this.changes();
    }

    public void removeMod(GuildUser user) {
        this.mods.remove(user);
        this.changes();
    }

    public boolean isMod(GuildUser user) {
        return this.mods.contains(user);
    }

    public boolean isTurbodrop() {
        return this.turbodrop > System.currentTimeMillis();
    }

    public boolean isTurboexp() {
        return this.turboexp > System.currentTimeMillis();
    }

    public String getDurationTruboDrop() {
        if (!isTurbodrop()) {
            return "";
        }
        return TimeUtil.getDuration(turbodrop - System.currentTimeMillis());
    }

    public String getDurationTurboExp() {
        if (!isTurbodrop()) {
            return "";
        }
        return TimeUtil.getDuration(turboexp - System.currentTimeMillis());
    }

    public Set<Guild> getAlliances() {
        return this.alliances;
    }

    public void addAlly(Guild guild) {
        if (!this.alliances.contains(guild)) {
            this.alliances.add(guild);
            this.changes();
        }
    }

    public void removeAlly(Guild guild) {
        if (this.alliances.contains(guild)) {
            this.alliances.remove(guild);
            this.changes();
        }
    }

    public boolean isAlly(Guild guild) {
        return this.alliances.contains(guild);
    }

    public boolean isAllyPlayer(String name) {
        for (Guild guild : this.alliances) {
            if (guild.isMember(name)) {
                return true;
            }
        }
        return false;
    }

    public Location getHome() {
        return this.home;
    }

    public Cuboid getCuboid() {
        return this.cuboid;
    }

    public int getLives() {
        return this.lives;
    }

    public void addInvited(String player) {
        String playerLower = player.toLowerCase();
        if (this.invited.containsKey(playerLower)) {
            return;
        }
        this.invited.put(playerLower,
                Bukkit.getScheduler().runTaskLaterAsynchronously(PluginGuild.getPlugin(), () -> {
                    invited.get(playerLower).cancel();
                    invited.remove(playerLower);
                }, 600L));
    }

    public void removeInvited(String player) {
        String playerLower = player.toLowerCase();
        if (!this.invited.containsKey(playerLower)) {
            return;
        }
        this.invited.get(playerLower).cancel();
        this.invited.remove(playerLower);
    }

    public boolean isInvited(String player) {
        String playerLower = player.toLowerCase();
        return this.invited.containsKey(playerLower);
    }

    public void addInvitedGuild(String tag) {
        String tagLower = tag.toLowerCase();
        if (this.invitedGuilds.containsKey(tagLower)) {
            return;
        }
        this.invitedGuilds.put(tagLower,
                Bukkit.getScheduler().runTaskLaterAsynchronously(PluginGuild.getPlugin(), () -> {
                    invitedGuilds.get(tagLower).cancel();
                    invitedGuilds.remove(tagLower);
                }, 600L));
    }

    public void removeInvitedGuild(String tag) {
        String tagLower = tag.toLowerCase();
        if (!this.invitedGuilds.containsKey(tagLower)) {
            return;
        }
        this.invitedGuilds.get(tagLower).cancel();
        this.invitedGuilds.remove(tagLower);
    }

    public boolean isInvitedGuild(String tag) {
        String tagLower = tag.toLowerCase();
        return this.invitedGuilds.containsKey(tagLower);
    }

    public long getWar() {
        return this.war;
    }

    public boolean isWarProtect() {
        return this.war > System.currentTimeMillis();
    }

    public long getValidity() {
        return this.validity;
    }

    public long getExplode() {
        return this.explode;
    }

    public boolean isExplode() {
        return this.explode > System.currentTimeMillis();
    }

    public boolean isPvp() {
        return this.pvp;
    }

    public long getBan() {
        return this.ban;
    }

    public boolean isBan() {
        if (this.ban <= System.currentTimeMillis() && this.ban != 1L) {
            this.ban = 0L;
            this.reason = null;
            this.changes();
            return false;
        }
        return this.ban > System.currentTimeMillis() || this.ban == -1L;
    }

    public String getReason() {
        return this.reason;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setOwner(GuildUser owner) {
        this.owner = owner;
    }

    public void setHome(Location home) {
        this.home = home;
        this.changes();
    }

    public void setCuboid(Cuboid cuboid) {
        this.cuboid = cuboid;
    }

    public void setLives(int lives) {
        this.lives = lives;
        this.changes();
    }

    public void setWar(long war) {
        this.war = war;
        this.changes();
    }

    public void addValidity(long validity) {
        this.validity += validity;
        this.changes();
    }

    public void setValidity(long validity) {
        this.validity = validity;
        this.changes();
    }

    public boolean isValid() {
        return this.validity >= System.currentTimeMillis();
    }

    public void setExplode(long explode) {
        this.explode = explode;
    }

    public void setPvp(boolean pvp) {
        this.pvp = pvp;
        this.changes();
    }

    public void setTurbodrop(long czas) {
        if (this.isTurbodrop()) {
            this.turbodrop += czas;
        } else {
            this.turbodrop = czas + System.currentTimeMillis();
        }
        this.changes();
    }

    public void setTurboexp(long czas) {
        if (this.isTurboexp()) {
            this.turboexp += czas;
        } else {
            this.turboexp = czas + System.currentTimeMillis();
        }
        this.changes();
    }

    public void setBan(long ban) {
        this.ban = ban;
        this.changes();
    }

    public void setReason(String reason) {
        this.reason = reason;
        this.changes();
    }

    public void sendInfo(CommandSender sender) {
        for (String s : PluginGuild.getPlugin().getMessageConfiguration().guildInfo) {
            ChatUtil.sendMessage(sender, s
                    .replace("{OWNER}", this.owner.getName())
                    .replace("{GUILD}", this.name)
                    .replace("{TAG}", this.tag)
                    .replace("{POINTS}", Integer.toString(this.getRank().getPoints()))
                    .replace("{KILLS}", Integer.toString(this.getRank().getKills()))
                    .replace("{DEATHS}", Integer.toString(this.getRank().getDeaths()))
                    .replace("{KDR}", Double.toString(this.getRank().getKDR()))
                    .replace("{POSITION}", Integer.toString(this.getRank().getPosition()))
                    .replace("{LIVES}", Integer.toString(this.lives))
                    .replace("{VALIDITY}", TimeUtil.getDate(this.validity))
                    //.add("{MEMBERS}", this.members.stream().map(member -> (member.isOnline() ? "&a" : "&7" + member.getName())).collect(joining("&7, ")))
                    .replace("{MEMBERS}", PluginGuild.getPlugin().getUserManager().getOnlineNames(this.members).toString().replace("[", "").replace("]", ""))
                    .replace("{ALLIANCES}", (this.alliances == null || this.alliances.isEmpty()) ? "Brak" : this.alliances.stream().map(Guild::getTag).collect(joining(", ")))
                    .replace("{PROTECTION}", this.isWarProtect() ? TimeUtil.getDuration(this.war - System.currentTimeMillis()) : "TAK"));
        }
    }

    public Inventory getTreasure() {
        if (this.treasure == null) {
            this.treasure = Bukkit.createInventory(null, 54, ChatUtil.fixColor("&eSKARBIEC GILDII"));
            this.changes();
        }
        return this.treasure;
    }

    public void openTreasure(Player p) {
        p.openInventory(this.getTreasure());
        this.changes();
    }

    public void sendMembers(String text) {
        for (GuildUser user : this.members) {
            if (user.isOnline()) {
                ChatUtil.sendMessage(user.getPlayer(), text);
            }
        }
    }

    public void sendAlliances(String text) {
        for (Guild guild : this.alliances) {
            guild.sendMembers(text);
        }
    }

    public Hologram getHologram() {
        return hologram;
    }

    public void createCenterHologram() {
        if (!PluginHook.hookHolographic()) return;
        if (this.hologram == null) {
            this.hologram = HologramsAPI.createHologram(PluginGuild.getPlugin(), this.getCuboid().getHolographicLocation());
            for (String holo : PluginGuild.getPlugin().getMessageConfiguration().guildInfoHologram) {
                this.hologram.appendTextLine(holo
                        .replace("{GUILD}", this.name)
                        .replace("{TAG}", this.tag)
                        .replace("{OWNER}", this.owner.getName())
                        .replace("{POINTS}", this.getRank().getPoints() + "")
                        .replace("{KDR}", String.format(Locale.US, "%.2f", this.getRank().getKDR()))
                        .replace("{POSITION}", this.getRank().getPosition() + "")
                        .replace("{KILLS}", this.getRank().getKills() + "")
                        .replace("{DEATHS}", this.getRank().getDeaths() + "")
                        .replace("{LIVES}", this.lives + "")
                        .replace("{VALIDITY}", TimeUtil.getDate(this.validity))
                        .replace("{PROTECTION}", this.isWarProtect() ? TimeUtil.getDuration(this.war - System.currentTimeMillis()) : "TAK")
                        .replace("{MEMBERS}", ChatUtil.fixColor(PluginGuild.getPlugin().getUserManager().getOnlineNames(this.members).toString().replace("[", "").replace("]", "")))
                        .replace("{ALLIANCES}", (this.alliances == null || this.alliances.isEmpty()) ? "Brak" : this.alliances.stream().map(Guild::getTag).collect(joining(", "))));
            }
            return;
        }
        this.hologram.delete();
        this.hologram = null;

        //Bukkit.getScheduler().runTaskLater(PluginGuild.getPlugin(), this.hologram::delete, 60 * 20);
    }
}
