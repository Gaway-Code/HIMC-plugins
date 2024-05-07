package pl.himc.core.listener.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import pl.himc.core.command.admin.SprawdzCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.manager.VanishManager;

public final class PlayerDropItem implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkDropItem(PlayerDropItemEvent e) {
        if(e.isCancelled()) return;
        Player p = e.getPlayer();
        if(SprawdzCommand.isSuspect(p)) {
            e.setCancelled(true);
            ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().checkSuspectNotDropItems);
        }
        if(VanishManager.isVanished(p)){
            ChatUtil.sendMessage(p,PluginCore.getCore().getPluginMessages().vanishDropItems);
            e.setCancelled(true);
        }
    }
}
