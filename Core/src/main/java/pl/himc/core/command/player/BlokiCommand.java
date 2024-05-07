package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

public final class BlokiCommand extends PlayerCommand {

    public BlokiCommand(String permission) {
        super("bloki", permission, "");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&eWymiana na bloki"), 3);
        GuiItem yellow = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)4).setName("&8*").getItem());

        gui.setItem(0, yellow);
        gui.setItem(1, new ItemBuilder(Material.DIAMOND_BLOCK).setName("&8* &7Wymiana &bdiamentow &8*").setLore(" &8%> &7Kliknij aby wymienic wszystkie &bdiamenty &7na bloki!").setClickExecutor(event -> event(player, event)).getGuiItem());
        gui.setItem(2, yellow);
        gui.setItem(3, new ItemBuilder(Material.EMERALD_BLOCK).setName("&8* &7Wymiana &aszmaragdow &8*").setLore(" &8%> &7Kliknij aby wymienic wszystkie &aszmaragdy &7na bloki!").setClickExecutor(event -> event(player, event)).getGuiItem());
        gui.setItem(4, yellow);
        gui.setItem(5, new ItemBuilder(Material.GOLD_BLOCK).setName("&8* &7Wymiana &ezlota &8*").setLore((" &8%> &7Kliknij aby wymienic wszystkie &esztabki zlota &7na bloki!")).setClickExecutor(event -> event(player, event)).getGuiItem());
        gui.setItem(6, yellow);
        gui.setItem(7, new ItemBuilder(Material.IRON_BLOCK).setName("&8* &7Wymiana &8zelaza &8*").setLore(" &8%> &7Kliknij aby wymienic wszystkie &8sztabki zelaza &7na bloki!").setClickExecutor(event -> event(player, event)).getGuiItem());
        gui.setItem(8, yellow);
        gui.setItem(21, yellow);
        gui.setItem(22, new ItemBuilder(Material.HOPPER).setName("&8* &7Wymien &awszystko &8*").setLore(" &8%> &7Kliknij aby wymienic &6wszystkie przedmioty &7na bloki!").setClickExecutor(event -> event(player, event)).getGuiItem());
        gui.setItem(23, yellow);
        gui.open(player);
        return false;
    }

    private void event(Player p, InventoryClickEvent event){
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        ItemMeta im = event.getCurrentItem().getItemMeta();
        if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &7Wymiana &bdiamentow &8*"))){
            if (ItemsUtil.getAmountOfItem(Material.DIAMOND, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.DIAMOND, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.DIAMOND), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.DIAMOND_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "diamenty"));
            }
            else {
                ChatUtil.sendMessage(p, messageConfiguration.blokiNoItems);
            }
        }
        if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &7Wymiana &aszmaragdow &8*"))){
            if (ItemsUtil.getAmountOfItem(Material.EMERALD, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.EMERALD, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.EMERALD), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.EMERALD_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "szmaragdy"));
            }
            else {
                ChatUtil.sendMessage(p, messageConfiguration.blokiNoItems);
            }
        }
        if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &7Wymiana &ezlota &8*"))){
            if (ItemsUtil.getAmountOfItem(Material.GOLD_INGOT, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.GOLD_INGOT, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.GOLD_INGOT), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.GOLD_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "zloto"));
            }
            else {
                ChatUtil.sendMessage(p, messageConfiguration.blokiNoItems);
            }
        }
        if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &7Wymiana &8zelaza &8*"))){
            if (ItemsUtil.getAmountOfItem(Material.IRON_INGOT, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.IRON_INGOT, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.IRON_INGOT), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.IRON_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "zelazo"));
            }
            else {
                ChatUtil.sendMessage(p, messageConfiguration.blokiNoItems);
            }
        }
        if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&8* &7Wymien &awszystko &8*"))){
            if (ItemsUtil.getAmountOfItem(Material.DIAMOND, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.DIAMOND, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.DIAMOND), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.DIAMOND_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "diamenty"));
            }
            if (ItemsUtil.getAmountOfItem(Material.EMERALD, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.EMERALD, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.EMERALD), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.EMERALD_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "szmaragdy"));
            }
            if (ItemsUtil.getAmountOfItem(Material.GOLD_INGOT, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.GOLD_INGOT, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.GOLD_INGOT), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.GOLD_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "zloto"));
            }
            if (ItemsUtil.getAmountOfItem(Material.IRON_INGOT, p, (short)0) >= 9) {
                while (ItemsUtil.getAmountOfItem(Material.IRON_INGOT, p, (short)0) >= 9) {
                    ItemsUtil.remove(new ItemStack(Material.IRON_INGOT), p, 9);
                    ItemsUtil.giveItem(p, new ItemStack(Material.IRON_BLOCK));
                }
                ChatUtil.sendMessage(p, messageConfiguration.blokiSuccess.replace("{ITEM}", "zelazo"));
            }
        }
    }
}
