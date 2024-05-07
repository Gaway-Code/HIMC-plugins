package pl.himc.guilds.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import pl.himc.guilds.GuildPlugin;

public class BlockBreak implements Listener {

    public BlockBreak(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onBreak(BlockBreakEvent e) {
        if (e.isCancelled()) return;
        if (!this.plugin.getGuildManager().canBuild(e.getPlayer(), e.getBlock().getLocation(), false)) {
            e.setCancelled(true);
        }
    }
}
