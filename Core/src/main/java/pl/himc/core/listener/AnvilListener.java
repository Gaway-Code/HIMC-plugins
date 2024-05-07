package pl.himc.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.core.CorePlugin;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class AnvilListener implements Listener {


    public AnvilListener(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private CorePlugin plugin;

    @EventHandler
    public void onClick(final InventoryClickEvent event){
        if(event.getInventory().getType() == InventoryType.ANVIL){
            ItemStack item = event.getCurrentItem();
            if(item == null) return;
            ItemMeta im = item.getItemMeta();
            if(im == null) return;
            if(im.hasEnchant(Enchantment.DURABILITY) && (im.getEnchantLevel(Enchantment.DURABILITY) == 1337)){
                event.setCancelled(true);
                ChatUtil.sendMessage(event.getWhoClicked(), this.plugin.getPluginMessages().anvilEditItemError);
            }
        }
    }
}
