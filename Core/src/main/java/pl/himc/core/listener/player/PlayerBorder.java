package pl.himc.core.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.CorePlugin;

public class PlayerBorder implements Listener {

    public PlayerBorder(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final CorePlugin plugin;

    @EventHandler(priority= EventPriority.MONITOR)
    public void onTeleport(PlayerTeleportEvent e){
        Player p = e.getPlayer();
        if(e.getCause().equals(PlayerTeleportEvent.TeleportCause.ENDER_PEARL)){
            Location to = e.getTo();
            if(!lokalizacja(-499,499,499,-499, to)){
                e.setCancelled(true);
                TitleAPI.sendTitle(p,"", this.plugin.getPluginMessages().subTitleBorderTeleportError,1,65);
            }
        }
    }
    @EventHandler
    public void onBoatPlace(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Location to = p.getLocation();
        if (!lokalizacja(-490, 490, 490, -490, to)) {
            if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (p.getItemInHand().getType() == Material.BOAT) {
                    event.setCancelled(true);
                    TitleAPI.sendTitle(p,"", this.plugin.getPluginMessages().subTitleBorderBoatError,1,65);
                }
            }
        }
    }
    @EventHandler
    public void onBreak(BlockPlaceEvent event) {
        Player p = event.getPlayer();
        Location to = p.getLocation();
        if (!lokalizacja(-495, 495, 495, -495, to)) {
            event.setCancelled(true);
            TitleAPI.sendTitle(p,"", this.plugin.getPluginMessages().subTitleBorderBlockPlaceError,1,65);
        }
    }

    private boolean lokalizacja(int minX, int maxX, int minZ, int maxZ, Location l){
        if(l.getBlockX() >= maxX || l.getBlockX() <= minX) return false;
        return l.getBlockZ() > maxZ && l.getBlockZ() < minZ;
    }
}


