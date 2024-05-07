package pl.himc.settings.listener;

import pl.himc.settings.SettingsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public final class TntExplodeListener implements Listener {

    public TntExplodeListener(final SettingsPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final SettingsPlugin plugin;

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onExplode(EntityExplodeEvent e){
        if(e.getEntity() instanceof TNTPrimed){
            if(!this.plugin.getPluginConfig().tntExplode){
                e.setCancelled(true);
            }
        }
    }
}
