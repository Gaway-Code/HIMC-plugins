package pl.himc.guilds.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.himc.guilds.GuildPlugin;

public class BlockPlace implements Listener {

    public BlockPlace(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlace2(BlockPlaceEvent e) {
        if (e.isCancelled()) return;
        if (!this.plugin.getGuildManager().canBuild(e.getPlayer(), e.getBlock().getLocation(), true)) {
            e.setCancelled(true);
        }
    }
}
