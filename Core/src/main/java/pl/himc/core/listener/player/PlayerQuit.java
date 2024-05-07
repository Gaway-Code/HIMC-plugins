package pl.himc.core.listener.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.AntyLogout;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class PlayerQuit implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        Player p = e.getPlayer();
        PluginCore.getCore().getUserManager().playerQuit(p);

        if(AntyLogout.isAntyLogout(p)){
            AntyLogout.cancelTask(p);
            p.setHealth(0D);
            ChatUtil.sendBroadcast(PluginCore.getCore().getPluginMessages().antylogoutPlayerLogout.replace("{PLAYER}", p.getName()));
        }
    }
}
