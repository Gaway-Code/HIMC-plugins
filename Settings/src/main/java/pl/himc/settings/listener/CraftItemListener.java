package pl.himc.settings.listener;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.settings.SettingsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public final class CraftItemListener implements Listener {

    public CraftItemListener(final SettingsPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final SettingsPlugin plugin;


    @EventHandler
    public void onCraft(CraftItemEvent e){
        if(e.getRecipe().getResult().getType() == Material.DIAMOND_SWORD ||
                e.getRecipe().getResult().getType() == Material.DIAMOND_HELMET ||
                e.getRecipe().getResult().getType() == Material.DIAMOND_CHESTPLATE ||
                e.getRecipe().getResult().getType() == Material.DIAMOND_LEGGINGS ||
                e.getRecipe().getResult().getType() == Material.DIAMOND_BOOTS){
            if(!this.plugin.getPluginConfig().craftDiamondItems){
                e.setCancelled(true);
                ChatUtil.sendMessage(e.getWhoClicked(), this.plugin.getPluginMessage().craftDiamondItemsIsDisable);
            }
        }
    }
}
