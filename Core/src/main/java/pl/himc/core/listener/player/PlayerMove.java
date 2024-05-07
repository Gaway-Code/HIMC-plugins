package pl.himc.core.listener.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.regions.Region;
import pl.himc.core.manager.AntyLogout;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public final class PlayerMove implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void onMoveAnty(PlayerMoveEvent e){
        Player p = e.getPlayer();
        Location to = e.getTo();
        if (AntyLogout.isAntyLogout(p)) {
            Region region = PluginCore.getCore().getRegionData().getFirstRegion(to);
            if(region == null) return;
            if(region.getName().equalsIgnoreCase("spawn")){
                Location l = p.getLocation().subtract(p.getWorld().getSpawnLocation());
                double distance = p.getLocation().distance(p.getWorld().getSpawnLocation());
                Vector v = l.toVector().add(new Vector(0, 3, 0)).multiply(1 / distance);
                p.setVelocity(v);
            }

        }
    }

}
