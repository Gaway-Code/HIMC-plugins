package pl.himc.core.base.drop.cobblex;

import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

import java.util.ArrayList;
import java.util.List;

public final class CobblexData {

    private static final CorePlugin plugin = PluginCore.getCore();

    private static final List<ItemStack> drops = new ArrayList<>();
    private static final ItemBuilder itemCobblex = new ItemBuilder(Material.MOSSY_COBBLESTONE).setName("&a&lCobbleX").setLore(" &8%> &7Postaw na ziemi aby &c&nwylosowac przedmiot&7!").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS);

    public static ItemStack getItem(int ilosc){
        return itemCobblex.setAmount(ilosc).getItem();
    }

    public static ItemStack getItem(){
        return itemCobblex.getItem();
    }

    public static List<ItemStack> getDrops(){
        return drops;
    }

    public static void changeStatus(){
        PluginCore.getCore().getPluginConfig().enableCobblex = !PluginCore.getCore().getPluginConfig().enableCobblex;
        PluginCore.getCore().savePluginConfiguration();
    }

    public static boolean isEnable(){
        return plugin.getPluginConfig().enableCobblex;
    }
}
