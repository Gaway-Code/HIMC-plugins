package pl.himc.core.base.drop.luckybox;

import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.command.admin.VoucherCommand;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.item.ItemNameUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.utils.RandomUtil;

import java.util.ArrayList;
import java.util.List;

public final class LuckyBoxData {

    private static final List<ItemStack> drops = new ArrayList<>();
    private static final ItemBuilder itemLuckybox = new ItemBuilder(Material.SPONGE).setName("&e&lLucky&6&lBox").setLore(" &8%> &7Postaw na ziemi aby &a&nwylosowac przedmiot&7!").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS);

    public static ItemStack getItem(int ilosc){
        return itemLuckybox.setAmount(ilosc).getItem();
    }

    public static ItemStack getItem(){
        return itemLuckybox.getItem();
    }

    public static List<ItemStack> getDrops(){
        return drops;
    }

    public static void changeStatus(){
        PluginCore.getCore().getPluginConfig().enableLuckyBox = !PluginCore.getCore().getPluginConfig().enableLuckyBox;
        PluginCore.getCore().savePluginConfiguration();
    }

    public static boolean isEnable(){
        return PluginCore.getCore().getPluginConfig().enableLuckyBox;
    }

    public static void getDrop(Player player){
        if(RandomUtil.getChance(6.0)){
            ChatUtil.sendMessage(player, "&4&lZalanie przez lawe!");
            player.getWorld().getBlockAt(player.getLocation()).setType(Material.LAVA);
        }else if(RandomUtil.getChance(0.4)){
            ChatUtil.sendMessage(player, "&7Wylosowales &b&lBEACONA&7!");
            ItemsUtil.giveItem(player, new ItemStack(Material.BEACON, 1));
        }else if(RandomUtil.getChance(0.8)) {
            ChatUtil.sendMessage(player, "&7Wylosowales &avoucher na &d&lTURBO&b&lDROP&7!");
            ItemsUtil.giveItem(player, VoucherCommand.getTruboDrop10());
        }else if(RandomUtil.getChance(1.0)) {
            ChatUtil.sendMessage(player, "&7Wylosowales &avoucher na &d&lTURBO&b&lEXP&7!");
            ItemsUtil.giveItem(player, VoucherCommand.getTruboExp10());
        }else{
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            ItemStack is = getDrops().get(RandomUtil.nextInt(getDrops().size())).clone();

            ItemsUtil.giveItem(player, is);
            ChatUtil.sendMessage(player, messageConfiguration.luckyBoxOpen.replace("{ITEM}", ItemNameUtil.getName(is.getType())).replace("{AMOUNT}", Integer.toString(is.getAmount())));
        }
    }
}
