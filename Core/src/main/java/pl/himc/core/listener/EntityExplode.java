package pl.himc.core.listener;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.craft.stoniarka.Stoniarka;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class EntityExplode implements Listener {

    @EventHandler(priority = EventPriority.LOW)
    public void checkEntityExplode(EntityExplodeEvent e){
        if(e.isCancelled()) return;
        if(PluginCore.getCore().getPluginConfig().tntPlaceY){
            if(e.getLocation().getY() >= PluginCore.getCore().getPluginConfig().tntPlaceKoordY) {
                if (e.getEntity().getType().equals(EntityType.MINECART_TNT)) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getEntity().getType().equals(EntityType.PRIMED_TNT)) {
                    e.setCancelled(true);
                    return;
                }
                if (e.getEntity().getType().equals(EntityType.CREEPER)) {
                    e.setCancelled(true);
                    return;
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplode(EntityExplodeEvent e){
        if(e.isCancelled()) return;
        e.blockList().stream().filter(block -> StoniarkaData.getGenerator(block.getLocation()) != null).forEach(block -> {
            Stoniarka stoniarka = StoniarkaData.getGenerator(block.getLocation());
            if(e.getEntity() instanceof TNTPrimed || e.getEntity() instanceof Minecart) {
                stoniarka.removeGenerator();
                return;
            }
            stoniarka.respawnGenerator();
        });
    }
}
