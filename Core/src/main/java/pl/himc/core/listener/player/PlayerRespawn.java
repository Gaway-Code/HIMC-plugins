package pl.himc.core.listener.player;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import pl.himc.core.manager.AntyLogout;

public final class PlayerRespawn implements Listener {

    @EventHandler
    public void onRespawn(final PlayerRespawnEvent event){
        if(AntyLogout.isAntyLogout(event.getPlayer())){
            AntyLogout.cancelTask(event.getPlayer());
        }
    }
}
