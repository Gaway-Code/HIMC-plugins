package pl.himc.guilds.listener;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.base.user.GuildUser;

public class PlayerInteract implements Listener {

    public PlayerInteract(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent e) {
        if (!e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && !e.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
            return;
        }
        Block block = e.getClickedBlock();
        if (block == null) return;
        Player player = e.getPlayer();
        this.plugin.getGuildManager().attact(player, block, e, e.getAction().equals(Action.LEFT_CLICK_BLOCK));
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(final PlayerInteractEntityEvent e) {
        if (e.getRightClicked() instanceof Player) {
            final GuildUser u = this.plugin.getUserManager().getUser(e.getRightClicked().getName());
            if (u == null) return;
            final Player p = e.getPlayer();
            //if (p.getItemInHand().getType() == Material.DIAMOND_SWORD || p.getItemInHand().getType() == Material.GOLDEN_APPLE) return;
            TitleAPI.sendTitle(p, "&c⚔", "&cRanking gracza wynosi " + u.getRank().getPoints(), 5, 40);
            u.sendInfo(p);
        }
    }
}
