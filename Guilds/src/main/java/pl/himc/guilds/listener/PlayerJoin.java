package pl.himc.guilds.listener;

import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.manager.NametagManager;

public class PlayerJoin implements Listener {

    public PlayerJoin(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        NametagManager.initPlayer(p);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent e) {
        GuildUser u = this.plugin.getUserManager().getUser(e.getPlayer());
        u.getDamage().clear();
    }
}
