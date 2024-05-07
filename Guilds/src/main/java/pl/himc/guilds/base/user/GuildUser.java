package pl.himc.guilds.base.user;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.Playable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.AbstractBasic;
import pl.himc.guilds.base.BasicType;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.rank.Rank;
import pl.himc.guilds.base.rank.RankManager;
import pl.himc.guilds.util.DamageUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class GuildUser extends AbstractBasic implements Playable {

    private final String name;

    private Map<String, Double> damage = new HashMap<>();

    private long lastDamage;

    private Player lastKiller;
    private Rank rank;
    private Guild guild;
    private Map<GuildPermission, Boolean> guildPermissions = new HashMap<>();
    private boolean enemyAlert;

    public GuildUser(Player player) {
        this.name = player.getName();
        this.enemyAlert = true;

        this.getRank();
        this.resetGuildPermissions();
    }

    public GuildUser(ResultSet rs) throws SQLException {
        this.name = rs.getString("NAME");

        this.rank = new Rank(this);
        this.rank.setKills(rs.getInt("KILLS"));
        this.rank.setDeaths(rs.getInt("DEATHS"));
        this.rank.setPoints(rs.getInt("POINTS"));
        RankManager.getInst().update(this);

        String permissionString = rs.getString("GUILDPERMISSIONS");
        int index = 0;
        for (GuildPermission permission : GuildPermission.values())
            this.guildPermissions.put(permission, permissionString.charAt(index++) == '1');
        this.enemyAlert = rs.getBoolean("ENEMYALERT");
        this.setChanges(false);
        PluginGuild.getPlugin().getUserManager().putUser(rs.getString("NAME"), this);
    }

    public String getSQL() {
        String update = "INSERT INTO `" + PluginGuild.getPlugin().getPluginConfiguration().database.tableUsers + "` VALUES(" +
                "'%name%'," +
                "'%kills%'," +
                "'%deaths%'," +
                "'%points%'," +
                "'%guild%'," +
                "'%guildpermissions%'," +
                "'%enemyalert%'" +
                ") ON DUPLICATE KEY UPDATE " +
                "KILLS='%kills%'," +
                "DEATHS='%deaths%'," +
                "POINTS='%points%'," +
                "GUILD='%guild%'," +
                "GUILDPERMISSIONS='%guildpermissions%'," +
                "ENEMYALERT='%enemyalert%';";
        update = update.replace("%name%", this.name);
        update = update.replace("%kills%", Integer.toString(this.getRank().getKills()));
        update = update.replace("%deaths%", Integer.toString(this.getRank().getDeaths()));
        update = update.replace("%points%", Integer.toString(this.getRank().getPoints()));
        update = update.replace("%guild%", this.hasGuild() ? this.getGuild().getTag() : "NULL");
        update = update.replace("%guildpermissions%", this.getGuildPermissionString());
        update = update.replace("%enemyalert%", Boolean.toString(this.enemyAlert));

        return update;
    }

    public String getName() {
        return this.name;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(this.name);
    }

    @Override
    public BasicType getType() {
        return BasicType.USER;
    }

    public Rank getRank() {
        if (this.rank != null) {
            return this.rank;
        }

        this.rank = new Rank(this);
        RankManager.getInst().update(this);
        this.changes();

        return this.rank;
    }

    public Guild getGuild() {
        return this.guild;
    }

    public Map<String, Double> getDamage() {
        return this.damage;
    }

    public long getLastDamage() {
        return this.lastDamage;
    }

    public Player getLastKiller() {
        return this.lastKiller;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
        this.changes();
    }

    public void setDamage(String damager, double damage) {
        if (this.lastDamage <= System.currentTimeMillis()) {
            this.damage.clear();
        }
        if (this.damage.containsKey(damager)) {
            double i = this.damage.get(damager);
            i += damage;
            this.damage.put(damager, i);
            this.lastDamage = System.currentTimeMillis() + 10000L;
            return;
        }
        this.damage.put(damager, damage);
        this.lastDamage = System.currentTimeMillis() + 10000L;
    }

    public void setLastKiller(Player killer) {
        this.lastKiller = killer;
    }

    public boolean isOnline() {
        return this.getPlayer() != null && this.getPlayer().isOnline();
    }

    public boolean hasGuild() {
        return this.guild != null;
    }

    public void resetGuildPermissions() {
        this.guildPermissions.clear();
        for (GuildPermission permission : GuildPermission.values()) {
            this.guildPermissions.put(permission, false);
        }
        this.changes();
    }

    public boolean hasGuildPermission(final GuildPermission permission) {
        if (this.getGuild().isOwner(this)) return true;
        return this.guildPermissions.get(permission);
    }

    public void toggleGuildPermission(final GuildPermission permission) {
        this.guildPermissions.computeIfPresent(permission, (a, b) -> !b);
        this.changes();
    }

    private String getGuildPermissionString() {
        return this.guildPermissions.values().stream().map(b -> b ? "1" : "0").collect(Collectors.joining());
    }

    public boolean isEnemyAlert() {
        return enemyAlert;
    }

    public void toggleEnemyAlert() {
        this.enemyAlert = !this.enemyAlert;
    }

    public void sendInfo(CommandSender sender) {
        GuildUser user2 = PluginGuild.getPlugin().getUserManager().getUser((Player)sender);
        int[] rankChanges = DamageUtil.getEloValues(this.getRank().getPoints(), user2.getRank().getPoints());
        int[] rankChanges2 = DamageUtil.getEloValues(user2.getRank().getPoints(), this.getRank().getPoints());

        for (String s : PluginGuild.getPlugin().getMessageConfiguration().playerInfo) {
            ChatUtil.sendMessage(sender, s
                    .replace("{PLAYER}", this.name)
                    .replace("{POINTS}", Integer.toString(this.getRank().getPoints()))
                    .replace("{KILLS}", Integer.toString(this.getRank().getKills()))
                    .replace("{DEATHS}", Integer.toString(this.getRank().getDeaths()))
                    .replace("{KDR}", String.format(Locale.US, "%.2f", this.getRank().getKDR()))
                    .replace("{GUILD}", this.hasGuild() ? this.getGuild().getName() : "-")
                    .replace("{KILLPOINTS}", "" + rankChanges[0])
                    .replace("{LOSEPOINTS}", "" + rankChanges2[0])
                    .replace("{TAG}", this.hasGuild() ? this.getGuild().getTag() : "-"));
        }
    }
}
