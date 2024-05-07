package pl.himc.guilds.listener;

import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public class PlayerMove implements Listener {

    public PlayerMove(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler
    public void onTeleport(PlayerTeleportEvent event) {
        checkPlayerMove(event);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void checkPlayerMove(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        if (to.getBlockX() == from.getBlockX() && to.getBlockZ() == from.getBlockZ()) {
            return;
        }
        Player player = e.getPlayer();
        GuildUser user = this.plugin.getUserManager().getUser(player);
        PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        if (!user.hasGuild()) {
            Guild guild = this.plugin.getGuildManager().getLocation(to);
            if (guild != null) {
                if (guild.getCuboid().isIn(from)) {
                    return;
                }
                messageConfiguration.parseTitle(player, messageConfiguration.enterToRegionEnemy.replace("{TAG}", guild.getTag()));
                if (guild.isWarProtect()) {
                    ChatUtil.sendMessage(player, messageConfiguration.enterToRegionEnemyHasProtection.replace("{TAG}", guild.getTag()).replace("{DATA}", TimeUtil.getDuration(guild.getWar() - System.currentTimeMillis())));
                }
                if (!player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.region.notify")) {
                    for (GuildUser online : guild.getOnlineMembers()) {
                        if(!online.isEnemyAlert()) continue;
                        Player mem = online.getPlayer();
                        messageConfiguration.parseTitle(mem, messageConfiguration.enterToRegionSendEnemys.replace("{PLAYER}", player.getName()));
                    }
                }
            } else {
                guild = this.plugin.getGuildManager().getLocation(from);
                if (guild == null) {
                    return;
                }
                if (guild.getCuboid().isIn(to)) {
                    return;
                }
                messageConfiguration.parseTitle(player, messageConfiguration.leaveFromRegionEnemy.replace("{TAG}", guild.getTag()));
            }
        } else {
            Guild guild = this.plugin.getGuildManager().getLocation(to);
            Guild userGuild = user.getGuild();
            if (guild != null) {
                if (guild.getCuboid().isIn(from)) {
                    return;
                }
                if (guild.isMember(user)) {
                    messageConfiguration.parseTitle(player, messageConfiguration.enterRegionMember.replace("{TAG}", guild.getTag()));
                    return;
                }
                if (guild.isAlly(userGuild)) {
                    messageConfiguration.parseTitle(player, messageConfiguration.enterToRegionAlly.replace("{TAG}", guild.getTag()));
                    for (GuildUser allyUser : guild.getOnlineMembers()) {
                        if(!allyUser.isEnemyAlert()) continue;
                        Player allyPlayer = allyUser.getPlayer();
                        messageConfiguration.parseTitle(allyPlayer, messageConfiguration.enterToRegionSendAllys.replace("{PLAYER}", player.getName()));
                    }
                    return;
                }
                messageConfiguration.parseTitle(player, messageConfiguration.enterToRegionEnemy.replace("{TAG}", guild.getTag()));
                if (guild.isWarProtect()) {
                    ChatUtil.sendMessage(player, messageConfiguration.enterToRegionEnemyHasProtection.replace("{TAG}", guild.getTag()).replace("{DATA}", TimeUtil.getDuration(guild.getWar() - System.currentTimeMillis())));
                }
                if (!player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.region.notify")) {
                    for (GuildUser online : guild.getOnlineMembers()) {
                        if(!online.isEnemyAlert()) continue;
                        Player mem = online.getPlayer();
                        messageConfiguration.parseTitle(mem, messageConfiguration.enterToRegionSendEnemys.replace("{PLAYER}", player.getName()));
                    }
                }
            } else {
                guild = this.plugin.getGuildManager().getLocation(from);
                if (guild == null) {
                    return;
                }
                if (guild.getCuboid().isIn(to)) {
                    return;
                }
                if (guild.isMember(user)) {
                    messageConfiguration.parseTitle(player, messageConfiguration.leaveFromRegionMember.replace("{TAG}", guild.getTag()));
                    return;
                }
                if (guild.isAlly(userGuild)) {
                    messageConfiguration.parseTitle(player, messageConfiguration.leaveFromRegionAlly.replace("{TAG}", guild.getTag()));
                    return;
                }
                messageConfiguration.parseTitle(player, messageConfiguration.leaveFromRegionEnemy.replace("{TAG}", guild.getTag()));
            }
        }
    }
}
