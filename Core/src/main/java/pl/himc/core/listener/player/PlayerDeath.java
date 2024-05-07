package pl.himc.core.listener.player;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.AntyLogout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public final class PlayerDeath implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDeath(PlayerDeathEvent e){
        switch (e.getEntity().getLastDamageCause().getCause()) {
            case FIRE:
            case FIRE_TICK: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6spalił się!"));
                break;
            }
            case LAVA: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6próbował pływać w lawie!"));
                break;
            }
            case DROWNING: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6utopił się!"));
                break;
            }
            case SUFFOCATION: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6utknął w ścianie!"));
                break;
            }
            case BLOCK_EXPLOSION:
            case ENTITY_EXPLOSION: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6wyleciał w powietrze!"));
                break;
            }
            case FALL: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6spadł z wysokości!"));
                break;
            }
            case FALLING_BLOCK: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6został zgnieciony przez kowadło!"));
                break;
            }
            case CONTACT: {
                e.setDeathMessage(ChatUtil.fixColor(" &4✞ &c" + e.getEntity().getName() + " &6chciał przytulić kaktusa!"));
                break;
            }
            default: {
                e.setDeathMessage(null);
                break;
            }
        }
        if(AntyLogout.isAntyLogout(e.getEntity())){
            AntyLogout.cancelTask(e.getEntity());
        }
        PluginCore.getCore().backupManager().createBackup(e.getEntity());
    }
}
