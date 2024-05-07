package pl.himc.core.listener.player;

import pl.himc.core.manager.VanishManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public final class PlayerPickupItem implements Listener {

    @EventHandler
    public void onPickup(PlayerPickupItemEvent e){
        if(VanishManager.isVanished(e.getPlayer())){
            e.setCancelled(true);
        }
    }
}
