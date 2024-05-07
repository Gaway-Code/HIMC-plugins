package pl.himc.guilds.base.guild;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.cuboid.Cuboid;
import pl.himc.guilds.base.rank.RankManager;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.manager.NametagManager;
import pl.himc.guilds.util.SpaceUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class GuildManager {

    public GuildManager(GuildPlugin plugin) {
        this.plugin = plugin;
        this.guilds = new ConcurrentHashMap<>();
    }

    private final Map<String, Guild> guilds;
    private final GuildPlugin plugin;

    public Collection<Guild> getGuilds() {
        return guilds.values();
    }

    public Guild getGuildTag(String tag) {
        return getGuilds().parallelStream().filter(guild -> guild.getTag().equalsIgnoreCase(tag)).findFirst().orElse(null);
    }

    public Guild getGuildName(String name) {
        return getGuilds().parallelStream().filter(guild -> guild.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void putGuild(String tag, Guild guild) {
        if (getGuildTag(tag) == null) {
            guilds.put(tag, guild);
        }
    }

    public Set<Guild> getGuilds(Collection<String> names) {
        return names.stream()
                .map(this::getGuildName)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Set<String> getTags(Collection<Guild> guilds) {
        return guilds.stream()
                .filter(Objects::nonNull)
                .map(Guild::getTag)
                .collect(Collectors.toSet());
    }

    public Set<Guild> getGuildsTag(Collection<String> tags) {
        return tags.stream()
                .map(this::getGuildTag)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    public Guild getPlayerGuild(Player p) {
        GuildUser u = PluginGuild.getPlugin().getUserManager().getUser(p);
        for (Guild g : getGuilds()) {
            if (g.isMember(u)) return g;
        }
        return null;
    }

    public Guild getLocation(Location loc) {
        for (Guild guild : getGuilds()) {
            if (guild.getCuboid().isIn(loc)) {
                return guild;
            }
        }
        return null;
    }

    public boolean distance(Location loc, int distance) {
        Location l = loc.clone();
        for (Guild guild : getGuilds()) {
            if (guild.getCuboid() == null) {
                continue;
            }
            Location center = guild.getCuboid().getCenter();
            l.setY(center.getY());
            if (center.distance(l) < distance) {
                return false;
            }
        }
        return true;
    }

    public void addMember(Guild g, GuildUser u) {
        final PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        g.addMember(u);
        u.setGuild(g);
        u.resetGuildPermissions();

        RankManager.getInst().update(g);
        NametagManager.joinToGuild(g, u);
        u.sendMessage(messageConfiguration.joinMember.replace("{TAG}", g.getTag()));


        ChatUtil.sendBroadcast(messageConfiguration.joinToGuildBroadcast.replace("{TAG}", g.getTag())
                .replace("{GUILD}", g.getName())
                .replace("{PLAYER}", u.getName()));

        Player owner = g.getOwner().getPlayer();
        if (owner != null) {
            ChatUtil.sendMessage(owner, messageConfiguration.joinMemberToOwner.replace("{PLAYER}", u.getName()));
        }
    }

    public void removeMember(Guild g, GuildUser u) {
        u.setGuild(null);
        g.removeMod(u);
        g.removeMember(u);
        u.resetGuildPermissions();

        NametagManager.leaveFromGuild(g, u);
        RankManager.getInst().update(g);
    }

    public void createGuild(String name, String tag, GuildUser owner) {
        final PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();
        final PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        Player player = owner.getPlayer();
        Location center;
        if (pluginConfiguration.createCenterY == 0) {
            center = player.getLocation();
            if (center.getBlockY() < 6) {
                center.setY(6.0);
            }
        } else {
            center = player.getLocation().clone();
            center.setY(pluginConfiguration.createCenterY);
        }

        Guild guild = new Guild(name, tag, owner);
        guild.setHome(center);
        guild.setLives(pluginConfiguration.createGuildLives);
        guild.setValidity(System.currentTimeMillis() + pluginConfiguration.createGuildValidity);
        guild.setWar(System.currentTimeMillis() + pluginConfiguration.createGuildProtection);

        Cuboid cub = new Cuboid(guild, center, pluginConfiguration.createRegionSize);
        cub.createRoom();

        owner.setGuild(guild);
        owner.resetGuildPermissions();
        player.teleport(guild.getCuboid().getCenter());
        NametagManager.createGuild(guild, owner);

        ChatUtil.sendBroadcast(messageConfiguration.createGuildBroadcast
                .replace("{PLAYER}", player.getName())
                .replace("{GUILD}", name)
                .replace("{TAG}", tag));

        ChatUtil.sendMessage(player, messageConfiguration.createGuildToOwner
                .replace("{GUILD}", name)
                .replace("{TAG}", tag)
                .replace("{DATE}", TimeUtil.getDate(guild.getValidity())));

        guild.changes();
    }

    public void deleteGuild(Guild guild) {
        final PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();
        guild.setChanges(false);
        Bukkit.getScheduler().runTask(PluginGuild.getPlugin(), () -> {
            Location loc = guild.getCuboid().getCenter();
            loc.getBlock().setType(Material.AIR);
            guild.getCuboid().createChest();
        });

        for (GuildUser member : guild.getMembers()) {
            member.setGuild(null);
            member.resetGuildPermissions();
        }

        if (guild.getAlliances() != null && !guild.getAlliances().isEmpty()) {
            for (Guild ally : guild.getAlliances()) {
                ally.removeAlly(guild);
                NametagManager.removeAlliance(guild, ally);
            }
        }
        guild.getAlliances().clear();
        RankManager.getInst().remove(guild);
        NametagManager.removeGuild(guild);
        PluginApi.getApi().getDatabaseManager().executeUpdate("DELETE FROM `" + pluginConfiguration.database.tableGuilds + "` WHERE TAG='" + guild.getTag() + "'");
        if(guild.getHologram() != null) guild.getHologram().delete();

        getGuilds().remove(guild);
    }

    public boolean canBuild(Player player, Location location, boolean place) {
        if (guilds.isEmpty()) {
            return true;
        }
        final PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        Guild guild = this.getLocation(location);
        if (guild == null) {
            return true;
        }
        if (SpaceUtil.locationEqualsBlock(location, guild.getCuboid().getCenter())) {
            return false;
        }
        if (player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.build")) {
            return true;
        }
        if (guild.isMember(player.getName()) && guild.getCuboid().isInCentrum(location, 6, 1, 3)) {
            ChatUtil.sendMessage(player, messageConfiguration.regionCenterBuild);
            return false;
        }
        if (!guild.isMember(player.getName())) {
            ChatUtil.sendMessage(player, messageConfiguration.regionEnemyGuildBuild);
            return false;
        }
        if (place && guild.isExplode()) {
            ChatUtil.sendMessage(player, messageConfiguration.regionExplodeBuild.replace("{TIME}", TimeUtil.getDuration(guild.getExplode() - System.currentTimeMillis())));
            return false;
        }
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (place) {
            if (location.getBlock().getType() == Material.TNT && !user.hasGuildPermission(GuildPermission.TNT_PLACE)) {
                ChatUtil.sendMessage(player, messageConfiguration.noHasPermTntPlace);
                return false;
            }
            if (!user.hasGuildPermission(GuildPermission.BLOCK_PLACE) && location.getBlock().getType() != Material.TNT) {
                ChatUtil.sendMessage(player, messageConfiguration.noHasPermBuild);
                return false;
            }
        }
        if (!place) {
            if (!user.hasGuildPermission(GuildPermission.BLOCK_BREAK)) {
                ChatUtil.sendMessage(player, messageConfiguration.noHasPermBreak);
                return false;
            }
            if (location.getBlock().getType() == Material.CHEST) {
                if (!user.hasGuildPermission(GuildPermission.OPEN_CHEST)) {
                    return false;
                }
            }
            if (location.getBlock().getType() == Material.BEACON) {
                return user.hasGuildPermission(GuildPermission.BEACON);
            }
        }
        return true;
    }

    public void attact(Player player, Block b, PlayerInteractEvent e, boolean left) {
        Location location = b.getLocation();
        Guild guild = this.getLocation(location);
        if (guild == null) {
            return;
        }
        final PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();
        final PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (!left) {
            if (guild.isMember(user)) {
                if (b.getType() == Material.CHEST) {
                    if (!user.hasGuildPermission(GuildPermission.OPEN_CHEST)) {
                        ChatUtil.sendMessage(player, messageConfiguration.noHasPermOpenChest);
                        e.setCancelled(true);
                        return;
                    }
                }
                if (b.getType() == Material.BEACON) {
                    if (!user.hasGuildPermission(GuildPermission.BEACON)) {
                        ChatUtil.sendMessage(player, messageConfiguration.noHasPermOpenBeacon);
                        e.setCancelled(true);
                        return;
                    }
                }
            }
        }
        if (!SpaceUtil.locationEqualsBlock(location, guild.getCuboid().getCenter())) {
            return;
        }
        if (!left) {
            guild.createCenterHologram();
            return;
        }
        Guild userGuild = user.getGuild();
        if (userGuild == null) {
            ChatUtil.sendMessage(player, messageConfiguration.playerNotHasGuildWar);
            return;
        }
        if (guild == userGuild) {
            guild.createCenterHologram();
            return;
        }
        if (guild.isAlly(userGuild)) {
            ChatUtil.sendMessage(player, messageConfiguration.allyGuildWar);
            return;
        }
        if (guild.isWarProtect()) {
            ChatUtil.sendMessage(player, messageConfiguration.guildHasProtectWar.replace("{TIME}", TimeUtil.getDuration(guild.getWar() - System.currentTimeMillis())));
            return;
        }
        if (guild.getLives() > 1) {
            guild.setLives(guild.getLives() - 1);
            guild.setWar(System.currentTimeMillis() + pluginConfiguration.guildProtectionAfterWar);
            guild.sendMembers(messageConfiguration.warAttackedGuildToMembers.replace("{TAG}", userGuild.getTag()));
            userGuild.sendMembers(messageConfiguration.warAttackerGuildToMembers.replace("{TAG}", guild.getTag()));


            ChatUtil.sendBroadcast(messageConfiguration.warGuildBroadcast
                    .replace("{LOSE-TAG}", guild.getTag())
                    .replace("{WIN-TAG}", userGuild.getTag()));
            return;
        }
        userGuild.setLives(userGuild.getLives() + 1);
        userGuild.sendMembers(messageConfiguration.warAttackerWinToMembers.replace("{TAG}", guild.getTag()));
        guild.sendMembers(messageConfiguration.warAttackedGuildLoseToMembers.replace("{TAG}", userGuild.getTag()));

        ChatUtil.sendBroadcast(messageConfiguration.warWinGuildBroadcast
                .replace("{LOSE-TAG}", guild.getTag())
                .replace("{WIN-TAG}", userGuild.getTag()));
        deleteGuild(guild);
    }

    public void checkValid() {
        for (Guild guild : getGuilds()) {
            if (guild.isValid()) {
                continue;
            }
            if (guild.getCuboid() == null) {
                continue;
            }
            final PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
            Location loc = guild.getCuboid().getCenter();
            ChatUtil.sendBroadcast(messageConfiguration.guildExpiredBroadcast
                    .replace("{GUILD}", guild.getName())
                    .replace("{TAG}", guild.getTag())
                    .replace("{X}", Integer.toString(loc.getBlockX()))
                    .replace("{Y}", Integer.toString(loc.getBlockY()))
                    .replace("{Z}", Integer.toString(loc.getBlockZ())));
            deleteGuild(guild);
        }
    }

    public void validityGuild(Player player, Guild guild) {
        final PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();
        final PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        long valid = guild.getValidity() - System.currentTimeMillis();
        if (valid + pluginConfiguration.guildValidityLong > pluginConfiguration.guildValidityMaxLong) {
            long time = (pluginConfiguration.guildValidityLong - (pluginConfiguration.guildValidityMaxLong - valid));

            ChatUtil.sendMessage(player, messageConfiguration.validityGuildMax.replace("{TIME}", TimeUtil.getDuration(time)));
            return;
        }
        if (!ItemsUtil.hasItems(player, pluginConfiguration.validityItems)) {
            ChatUtil.sendMessage(player, StringUtil.replaceItems(pluginConfiguration.validityItems));
            return;
        }
        ItemsUtil.removeItems(player, pluginConfiguration.validityItems);
        guild.addValidity(pluginConfiguration.guildValidityLong);

        ChatUtil.sendMessage(player, messageConfiguration.validitySuccess
                .replace("{DATA}", TimeUtil.getDate(guild.getValidity()))
                .replace("{TIME}", TimeUtil.getDuration(guild.getValidity() - System.currentTimeMillis())));
    }

    public void loadGuilds() {
        final PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();
        String sb = "CREATE TABLE IF NOT EXISTS `" + pluginConfiguration.database.tableGuilds + "` (" +
                "TAG varchar(20) not null, " +
                "NAME text not null, " +
                "OWNER varchar(20) not null, " +
                "MEMBERS text not null, " +
                "MODS text, " +
                "ALLIANCES text, " +
                "HOME text not null, " +
                "CENTER text not null, " +
                "SIZE int not null, " +
                "LIVES int not null, " +
                "WAR long not null, " +
                "VALIDITY long not null, " +
                "TURBODROP long not null, " +
                "TURBOEXP long not null, " +
                "PVP varchar(10) not null, " +
                "BAN long not null, " +
                "REASON text, " +
                "TREASURE longtext not null," +
                "primary key(TAG));";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + pluginConfiguration.database.tableGuilds + "`", guild -> {
            int i = 0;
            try {
                while (guild.next()) {
                    new Guild(guild);
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PluginGuild.getPlugin().getLog().database("[INFO] Pobrano " + i + " gildii!");
        });
        for (Guild guild : getGuilds()) {
            guild.setChanges(false);
        }
    }

    public void saveGuilds() {
        int i = 0;
        for (Guild guild : getGuilds()) {
            if (!guild.isChanges()) continue;
            PluginApi.getApi().getDatabaseManager().executeUpdate(guild.getSQL());
            guild.setChanges(false);
            i++;
        }
        PluginGuild.getPlugin().getLog().database("[INFO] Zapisano " + i + " gildii!");
    }
}
