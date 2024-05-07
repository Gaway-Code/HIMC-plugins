package pl.himc.core.base.enchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public final class EnchantManager {

    private static List<Enchant> enchants = new ArrayList<>();

    public static void addEnchant(Enchant ench){
        if(enchants.contains(ench)) return;
        enchants.add(ench);
    }

    public static Enchant get(String name) {
        for(Enchant ench : enchants) {
            if(ench.getName().equalsIgnoreCase(name)) return ench;
        }
        return null;
    }
    public static void loadEnchantments() {
        //            \\
        //            \\
        //   ZBROJA   \\
        //            \\
        //            \\
        new Enchant("prot4", Enchantment.PROTECTION_ENVIRONMENTAL, 4, 50, 25);
        new Enchant("prot3", Enchantment.PROTECTION_ENVIRONMENTAL, 3, 30, 15);
        new Enchant("prot2", Enchantment.PROTECTION_ENVIRONMENTAL, 2, 20, 10);
        new Enchant("prot1", Enchantment.PROTECTION_ENVIRONMENTAL, 1, 15, 7);

        new Enchant("fireprot4", Enchantment.PROTECTION_FIRE, 4, 20, 10);
        new Enchant("fireprot3", Enchantment.PROTECTION_FIRE, 3, 15, 7);
        new Enchant("fireprot2", Enchantment.PROTECTION_FIRE, 2, 10, 5);
        new Enchant("fireprot1", Enchantment.PROTECTION_FIRE, 1, 5, 2);

        new Enchant("projectileprot4", Enchantment.PROTECTION_PROJECTILE, 4, 20, 10);
        new Enchant("projectileprot3", Enchantment.PROTECTION_PROJECTILE, 3, 15, 7);
        new Enchant("projectileprot2", Enchantment.PROTECTION_PROJECTILE, 2, 10, 5);
        new Enchant("projectileprot1", Enchantment.PROTECTION_PROJECTILE, 1, 5, 2);

        new Enchant("featherfall4", Enchantment.PROTECTION_FALL, 4, 35, 17);
        new Enchant("featherfall3", Enchantment.PROTECTION_FALL, 3, 30, 15);
        new Enchant("featherfall2", Enchantment.PROTECTION_FALL, 2, 15, 7);
        new Enchant("featherfall1", Enchantment.PROTECTION_FALL, 1, 10, 5);

        new Enchant("unb3", Enchantment.DURABILITY, 3, 40, 20);
        new Enchant("unb2", Enchantment.DURABILITY, 2, 30, 15);
        new Enchant("unb1", Enchantment.DURABILITY, 1, 20, 10);

        new Enchant("thorns3", Enchantment.THORNS, 3, 25, 12);
        new Enchant("thorns2", Enchantment.THORNS, 2, 15, 7);
        new Enchant("thorns1", Enchantment.THORNS, 1, 10, 5);

        //            \\
        //            \\
        //   KILOFY   \\
        //            \\
        //            \\

        new Enchant("eff5", Enchantment.DIG_SPEED, 5, 40, 20);
        new Enchant("eff4", Enchantment.DIG_SPEED, 4, 30, 15);
        new Enchant("eff3", Enchantment.DIG_SPEED, 3, 20, 10);
        new Enchant("eff2", Enchantment.DIG_SPEED, 2, 10, 5);
        new Enchant("eff1", Enchantment.DIG_SPEED, 1, 5, 2);

        new Enchant("fortune3", Enchantment.LOOT_BONUS_BLOCKS, 3, 35, 17);
        new Enchant("fortune2", Enchantment.LOOT_BONUS_BLOCKS, 2, 25, 12);
        new Enchant("fortune1", Enchantment.LOOT_BONUS_BLOCKS, 1, 15, 7);

        //            \\
        //            \\
        //   MIECZE   \\
        //            \\
        //            \\

        new Enchant("sharp5", Enchantment.DAMAGE_ALL, 5, 50, 25);
        new Enchant("sharp4", Enchantment.DAMAGE_ALL, 4, 40, 20);
        new Enchant("sharp3", Enchantment.DAMAGE_ALL, 3, 30, 15);
        new Enchant("sharp2", Enchantment.DAMAGE_ALL, 2, 20, 10);
        new Enchant("sharp1", Enchantment.DAMAGE_ALL, 1, 10, 5);

        new Enchant("fireasp2", Enchantment.FIRE_ASPECT, 2, 40, 20);
        new Enchant("fireasp1", Enchantment.FIRE_ASPECT, 1, 20, 10);

        new Enchant("knock3", Enchantment.KNOCKBACK, 3, 30, 25);
        new Enchant("knock2", Enchantment.KNOCKBACK, 2, 30, 15);
        new Enchant("knock1", Enchantment.KNOCKBACK, 1, 15, 7);

        //            \\
        //            \\
        //   LUK      \\
        //            \\
        //            \\

        new Enchant("power5", Enchantment.ARROW_DAMAGE, 5, 50, 25);
        new Enchant("power4", Enchantment.ARROW_DAMAGE, 4, 40, 20);
        new Enchant("power3", Enchantment.ARROW_DAMAGE, 3, 30, 15);
        new Enchant("power2", Enchantment.ARROW_DAMAGE, 2, 20, 10);
        new Enchant("power1", Enchantment.ARROW_DAMAGE, 1, 10, 5);

        new Enchant("plomien3", Enchantment.ARROW_FIRE, 3, 30, 25);
        new Enchant("plomien2", Enchantment.ARROW_FIRE, 2, 20, 20);
        new Enchant("plomien1", Enchantment.ARROW_FIRE, 1, 10, 15);

        new Enchant("punch2", Enchantment.ARROW_KNOCKBACK, 2, 20, 20);
        new Enchant("punch1", Enchantment.ARROW_KNOCKBACK, 1, 10, 15);

        new Enchant("infinity1", Enchantment.ARROW_INFINITE, 1, 20, 15);
    }
    public static EnchantType getEnchantmentPartTypeForItemStack(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_HELMET:
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_BOOTS:
            case IRON_HELMET:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_BOOTS:
            case DIAMOND_HELMET:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_BOOTS:
            case GOLD_HELMET:
            case GOLD_CHESTPLATE:
            case GOLD_LEGGINGS:
            case GOLD_BOOTS: {
                return EnchantType.ARMOR;
            }
            case IRON_SPADE:
            case IRON_PICKAXE:
            case IRON_AXE:
            case WOOD_SPADE:
            case WOOD_PICKAXE:
            case WOOD_AXE:
            case STONE_SPADE:
            case STONE_PICKAXE:
            case STONE_AXE:
            case DIAMOND_SPADE:
            case DIAMOND_PICKAXE:
            case DIAMOND_AXE:
            case GOLD_SPADE:
            case GOLD_PICKAXE:
            case GOLD_AXE: {
                return EnchantType.TOOL;
            }
            default: {
                return EnchantType.OTHER;
            }
        }
    }

    public static EnchantType getEnchantTypeForItemStack(ItemStack item) {
        switch (item.getType()) {
            case LEATHER_HELMET:
            case IRON_HELMET:
            case DIAMOND_HELMET:
            case GOLD_HELMET: {
                return EnchantType.HEAD;
            }
            case LEATHER_CHESTPLATE:
            case IRON_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case GOLD_CHESTPLATE: {
                return EnchantType.CHEST;
            }
            case LEATHER_LEGGINGS:
            case IRON_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case GOLD_LEGGINGS: {
                return EnchantType.LEGS;
            }
            case LEATHER_BOOTS:
            case IRON_BOOTS:
            case DIAMOND_BOOTS:
            case GOLD_BOOTS: {
                return EnchantType.BOOTS;
            }
            case IRON_SWORD:
            case WOOD_SWORD:
            case STONE_SWORD:
            case DIAMOND_SWORD:
            case GOLD_SWORD: {
                return EnchantType.SWORD;
            }
            case IRON_PICKAXE:
            case WOOD_PICKAXE:
            case STONE_PICKAXE:
            case DIAMOND_PICKAXE:
            case GOLD_PICKAXE: {
                return EnchantType.PICKAXE;
            }
            case BOW: {
                return EnchantType.BOW;
            }
            default: {
                return EnchantType.OTHER;
            }
        }
    }

    public enum EnchantType {
        HEAD("HEAD", 0), CHEST("CHEST", 1), LEGS("LEGS", 2), BOOTS("BOOTS", 3), SWORD("SWORD", 4), PICKAXE("PICKAXE", 5), BOW("BOW", 6), OTHER("OTHER", 7), ARMOR("ARMOR", 8), TOOL("TOOL", 9);

        private EnchantType(String s, int n) {
        }
    }
    public static boolean isAllowedEnchant(ItemStack item, Enchantment e) {
        if(item == null || item.getType() == Material.AIR) return false;
        return e.canEnchantItem(item);
    }
}
