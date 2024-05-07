package pl.himc.guilds.listener;

import pl.himc.guilds.base.guild.Guild;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public class PlayerCommand implements Listener {

    public PlayerCommand(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.LOW)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        if (e.isCancelled()) return;
        Player p = e.getPlayer();
        if (!p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.admin")) {
            Guild guild = this.plugin.getGuildManager().getLocation(p.getLocation());
            if (guild == null) {
                return;
            }
            if (guild.isMember(p.getName())) {
                if (e.getMessage().startsWith("/tpaccept")) {
                    if (!this.plugin.getUserManager().getUser(p).hasGuildPermission(GuildPermission.TELEPORTATION_USE)) {
                        e.setCancelled(true);
                        ChatUtil.sendMessage(p, this.plugin.getMessageConfiguration().noHasPermTpaccept);
                    }
                }
                return;
            }
            if (guild.isAllyPlayer(p.getName())) {
                return;
            }
            String[] s = e.getMessage().split(" ");
            for (String disableCommands : this.plugin.getPluginConfiguration().regionDisableCommands) {
                if (s[0].equalsIgnoreCase("/" + disableCommands)) {
                    e.setCancelled(true);
                    ChatUtil.sendMessage(p, this.plugin.getMessageConfiguration().regionDisableCommand);
                }
            }
        }
    }
}
