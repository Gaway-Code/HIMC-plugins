package pl.himc.core.command.admin;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class VoucherCommand extends PlayerCommand {

    public VoucherCommand(String permission) {
        super("voucher", permission, "/voucher [evip/turbodrop5/turbodrop10/turbodrop30s/truboexp5/truboexp10]");
    }
    private static ItemStack evipVoucher = new ItemBuilder(Material.BOOK).setName("&6&lVoucher na &3E&6VIPa").setLore("&fVoucher na range &3E&6VIP", "&fCzas trwania: &a1 dzien", "&7Uzyj &8PPM&7, aby aktywowac.").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem();
    private static ItemStack truboDrop5 = new ItemBuilder(Material.BOOK).setName("&6&lVoucher &8- &d&lTURBO&b&lDROP &7na 5 minut").setLore("&fVoucher na &6turbodrop", "&7Uzyj &8PPM&7, aby aktywowac.").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem();
    private static ItemStack truboDrop10 = new ItemBuilder(Material.BOOK).setName("&6&lVoucher &8- &d&lTURBO&b&lDROP &7na 10 minut").setLore("&fVoucher na &6turbodrop", "&7Uzyj &8PPM&7, aby aktywowac.").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem();
    private static ItemStack truboDrop30s = new ItemBuilder(Material.BOOK).setName("&6&lVoucher &8- &d&lTURBO&b&lDROP &7na &630 sekund").setLore("&fVoucher na &6turbodrop", "&7Uzyj &8PPM&7, aby aktywowac.").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem();
    private static ItemStack truboExp5 = new ItemBuilder(Material.BOOK).setName("&6&lVoucher &8- &d&lTURBO&b&lEXP &7na 5 minut").setLore("&fVoucher na &6turboexp", "&7Uzyj &8PPM&7, aby aktywowac.").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem();
    private static ItemStack truboExp10 = new ItemBuilder(Material.BOOK).setName("&6&lVoucher &8- &d&lTURBO&b&lEXP &7na 10 minut").setLore("&fVoucher na &6turboexp", "&7Uzyj &8PPM&7, aby aktywowac.").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem();

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length < 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        if(args[0].equalsIgnoreCase("evip")){
            ItemsUtil.giveItem(player, evipVoucher);
        }else if(args[0].equalsIgnoreCase("turbodrop5")){
            ItemsUtil.giveItem(player, truboDrop5);
        }else if(args[0].equalsIgnoreCase("turbodrop10")){
            ItemsUtil.giveItem(player, truboDrop10);
        }else if(args[0].equalsIgnoreCase("turbodrop30s")){
            ItemsUtil.giveItem(player, truboDrop30s);
        } else if(args[0].equalsIgnoreCase("turboexp5")){
            ItemsUtil.giveItem(player, truboExp5);
        }else if(args[0].equalsIgnoreCase("turboexp10")){
            ItemsUtil.giveItem(player, truboExp10);
        }
        return false;
    }

    public static ItemStack getEvipVoucher(){
        return evipVoucher;
    }
    public static ItemStack getTruboDrop5() {
        return truboDrop5;
    }
    public static ItemStack getTruboDrop10() {
        return truboDrop10;
    }
    public static ItemStack getTruboExp5() {
        return truboExp5;
    }
    public static ItemStack getTruboExp10() {
        return truboExp10;
    }
    public static ItemStack getTruboDrop30s() {
        return truboDrop30s;
    }
}
