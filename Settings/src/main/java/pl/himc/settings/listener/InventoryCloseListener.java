package pl.himc.settings.listener;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.settings.SettingsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.base.drop.cobblex.CobblexData;
import pl.himc.core.base.drop.luckybox.LuckyBoxData;
import pl.himc.core.base.drop.stone.DropManager;

import java.util.ArrayList;
import java.util.List;

public final class InventoryCloseListener implements Listener {

    public InventoryCloseListener(final SettingsPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final SettingsPlugin plugin;

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if(e.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&cEdytowanie dropu z cobblex"))){
            List<ItemStack> items = new ArrayList<>();
            CobblexData.getDrops().clear();
            for(ItemStack is : e.getInventory().getContents()){
                if(is == null || is.getType() == Material.AIR) continue;
                items.add(is);
                CobblexData.getDrops().add(is);
            }
            DropManager.getYaml().set("cobblex-drops", items);
            try{
                DropManager.getYaml().save(DropManager.getFile());
            }catch(Exception ex){
                ex.printStackTrace();
            }
            ChatUtil.sendMessage(e.getPlayer(), this.plugin.getPluginMessage().saveEditedDrop);
        }
        if(e.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&cEdytowanie dropu z luckyblock"))){
            List<ItemStack> items = new ArrayList<>();
            LuckyBoxData.getDrops().clear();
            for(ItemStack is : e.getInventory().getContents()){
                if(is == null || is.getType() == Material.AIR) continue;
                items.add(is);
                LuckyBoxData.getDrops().add(is);
            }
            DropManager.getYaml().set("luckybox-drops", items);
            try{
                DropManager.getYaml().save(DropManager.getFile());
            }catch(Exception ex){
                ex.printStackTrace();
            }
            ChatUtil.sendMessage(e.getPlayer(), this.plugin.getPluginMessage().saveEditedDrop);
        }
    }
}
