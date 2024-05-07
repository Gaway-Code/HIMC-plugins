package pl.himc.core.listener;

import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.base.craft.CraftManager;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

public final class CraftItem implements Listener {

    @EventHandler(priority = EventPriority.NORMAL)
    public void onCraft(CraftItemEvent e) {
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) && e.getRecipe().getResult().getType() == Material.REDSTONE_COMPARATOR_OFF) {
            e.setCancelled(true);
        }
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) && e.getRecipe().getResult().getType() == Material.DIODE_BLOCK_OFF) {
            e.setCancelled(true);
        }
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) && e.getRecipe().getResult().getType() == Material.JUKEBOX) {
            e.setCancelled(true);
        }
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) && e.getRecipe().getResult().getType() == Material.NOTE_BLOCK) {
            e.setCancelled(true);
        }
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) && e.getRecipe().getResult().getType() == Material.SNOW_BALL && !e.getRecipe().getResult().isSimilar(CraftManager.getSwichBall())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCraft(PrepareItemCraftEvent e) {
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) &&
                e.getInventory().getResult().getType().equals(Material.TNT) &&
                e.getInventory().getResult().getItemMeta() !=null &&
                e.getInventory().getResult().getItemMeta().getDisplayName() !=null &&
                e.getInventory().getResult().getItemMeta().getDisplayName().equalsIgnoreCase(CraftManager.getRzucaneTnt().getItemMeta().getDisplayName())) {
            if (e.getInventory().getItem(1).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(2).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(3).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(4).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(5).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(6).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(7).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(8).getAmount() != 64) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(9).getAmount() != 64) {
                e.getInventory().setResult(null);
            }
        }
    }
    @EventHandler
    public void onCraftItem(CraftItemEvent e) {
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) &&
                e.getSlotType().toString().equalsIgnoreCase("RESULT") &&
                e.getInventory().getResult() != null &&
                e.getInventory().getResult().getType().equals(Material.TNT) &&
                e.getInventory().getResult().getItemMeta() != null &&
                e.getInventory().getResult().getItemMeta().getDisplayName() != null &&
                e.getInventory().getResult().getItemMeta().getDisplayName().equalsIgnoreCase(CraftManager.getRzucaneTnt().getItemMeta().getDisplayName())) {
            //Player p = (Player)e.getWhoClicked();
            int i = e.getInventory().getItem(1).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(1, new ItemStack(e.getInventory().getItem(1).getType(), i));
            }
            else {
                e.getInventory().setItem(1, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(2).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(2, new ItemStack(e.getInventory().getItem(2).getType(), i));
            }
            else {
                e.getInventory().setItem(2, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(3).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(3, new ItemStack(e.getInventory().getItem(3).getType(), i));
            }
            else {
                e.getInventory().setItem(3, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(4).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(4, new ItemStack(e.getInventory().getItem(4).getType(), i));
            }
            else {
                e.getInventory().setItem(4, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(5).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(5, new ItemStack(e.getInventory().getItem(5).getType(), i));
            }
            else {
                e.getInventory().setItem(5, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(6).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(6, new ItemStack(e.getInventory().getItem(6).getType(), i));
            }
            else {
                e.getInventory().setItem(6, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(7).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(7, new ItemStack(e.getInventory().getItem(7).getType(), i));
            }
            else {
                e.getInventory().setItem(7, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(8).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(8, new ItemStack(e.getInventory().getItem(8).getType(), i));
            }
            else {
                e.getInventory().setItem(8, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(9).getAmount() - 64;
            if (i > 0) {
                e.getInventory().setItem(9, new ItemStack(e.getInventory().getItem(9).getType(), i));
            }
            else {
                e.getInventory().setItem(9, new ItemStack(Material.AIR, 0));
            }
            e.getInventory().setResult(CraftManager.getRzucaneTnt().clone());
        }
    }

    @EventHandler
    public void onCraftSwitchBall(PrepareItemCraftEvent e) {
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) &&
                e.getInventory().getResult().getType().equals(Material.SNOW_BALL) &&
                e.getInventory().getResult().getItemMeta() !=null &&
                e.getInventory().getResult().getItemMeta().getDisplayName() !=null &&
                e.getInventory().getResult().getItemMeta().getDisplayName().equalsIgnoreCase(CraftManager.getSwichBall().getItemMeta().getDisplayName())) {
            if (e.getInventory().getItem(4).getAmount() != 32) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(5).getAmount() != 16) {
                e.getInventory().setResult(null);
                return;
            }
            if (e.getInventory().getItem(6).getAmount() != 32) {
                e.getInventory().setResult(null);
            }
        }
    }

    @EventHandler
    public void onCraftSwitchBall(CraftItemEvent e) {
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH) &&
                e.getSlotType().toString().equalsIgnoreCase("RESULT") &&
                e.getInventory().getResult() != null &&
                e.getInventory().getResult().getType().equals(Material.SNOW_BALL) &&
                e.getInventory().getResult().getItemMeta() != null &&
                e.getInventory().getResult().getItemMeta().getDisplayName() != null &&
                e.getInventory().getResult().getItemMeta().getDisplayName().equalsIgnoreCase(CraftManager.getSwichBall().getItemMeta().getDisplayName())) {
            //Player p = (Player)e.getWhoClicked();
            int i = e.getInventory().getItem(4).getAmount() - 32;
            if (i > 0) {
                e.getInventory().setItem(4, new ItemStack(e.getInventory().getItem(4).getType(), i));
            } else {
                e.getInventory().setItem(4, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(2).getAmount() - 16;
            if (i > 0) {
                e.getInventory().setItem(2, new ItemStack(e.getInventory().getItem(2).getType(), i));
            } else {
                e.getInventory().setItem(5, new ItemStack(Material.AIR, 0));
            }
            i = e.getInventory().getItem(6).getAmount() - 32;
            if (i > 0) {
                e.getInventory().setItem(6, new ItemStack(e.getInventory().getItem(6).getType(), i));
            } else {
                e.getInventory().setItem(6, new ItemStack(Material.AIR, 0));
            }
            e.getInventory().setItem(2, new ItemStack(Material.AIR, 0));
            e.getInventory().setItem(8, new ItemStack(Material.AIR, 0));
            e.getInventory().setResult(CraftManager.getSwichBall().clone());
        }
    }
    @EventHandler
    public void onCraftRatunek(CraftItemEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getInventory().getType().equals(InventoryType.WORKBENCH)) {
            if (e.getSlotType().toString().equalsIgnoreCase("RESULT")) {
                if (e.getCurrentItem().isSimilar(CraftManager.getRatunek())) {
                    if ((e.getInventory().getItem(2).getAmount() == 16)
                            && (e.getInventory().getItem(5).getAmount() == 16)
                            && (e.getInventory().getItem(8).getAmount() == 6)) {
                        e.getInventory().setItem(2, new ItemStack(Material.AIR, 0));
                        e.getInventory().setItem(5, new ItemStack(Material.AIR, 0));
                        e.getInventory().setItem(8, new ItemStack(Material.AIR, 0));
                        e.getInventory().setResult(CraftManager.getRatunek().clone());
                        return;
                    }
                    ChatUtil.sendMessage(p, "&cBrak wymaganych przedmiotow do stworzenia flary! &7Wpisz /craftingi");
                    e.getInventory().setResult(null);
                    e.setCancelled(true);
                }
            }
        }
    }
}
