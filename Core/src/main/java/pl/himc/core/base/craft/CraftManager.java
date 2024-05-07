package pl.himc.core.base.craft;

import pl.himc.core.base.craft.stoniarka.StoniarkaData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

import java.util.Arrays;

public final class CraftManager {

    private static final ItemBuilder boyfarmer = new ItemBuilder(Material.OBSIDIAN).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &9&lGENERATOR OBSYDIANU &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Postaw na ziemi.",
            " &8%> &7Generator rozpocznie tworzyc &9obsydian &7do bedrocka.",
            " &8%> &cJesli generator trafi na &c&nsrodek gildii&c - jego praca zostanie &4PRZERWANA&c!",
            "&7"));
    private static final ItemBuilder sandfarmer = new ItemBuilder(Material.SAND).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &e&lGENERATOR PIASKU &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Postaw na ziemi.",
            " &8%> &7Generator rozpocznie tworzyc &epiasek &7do bedrocka.",
            " &8%> &cJesli generator trafi na &c&nsrodek gildii&c - jego praca zostanie &4PRZERWANA&c!",
            "&7"));
    private static final ItemBuilder kopaczfosy = new ItemBuilder(Material.ENDER_PORTAL_FRAME).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &b&lKOPACZ FOSY &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Postaw na ziemi.",
            " &8%> &7Kopacz fosy zacznie &bkopac fose &7do bedrocka",
            " &8%> &cJesli generator trafi na &c&nsrodek gildii&c - jego praca zostanie &4PRZERWANA&c!",
            "&7"));
    private static final ItemBuilder rzucanetnt = new ItemBuilder(Material.TNT).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &c&lRZUCANE TNT &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Aby rzucic TNT kliknij &a&nPPM",
            " &8%> &cRzucanego tnt &4nie mozna postawic &cna ziemi!",
            "&7"));
    private static final ItemBuilder switchBall = new ItemBuilder(Material.SNOW_BALL).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &cSwitchBall &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Jesli trafisz nia gracza &8- &azamienicie sie miejscami!",
            "&7"));
    private static final ItemBuilder ratunek = new ItemBuilder(Material.STICK).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &aRatunek z nóg &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Kliknij PPM aby &ateleportowac sie &7do najblizszego gracza!",
            "&7"));

    public static void loadCrafts(){
        ShapedRecipe cStoniarka = new ShapedRecipe(StoniarkaData.getStoniarka());
        cStoniarka.shape("123", "456", "789");
        cStoniarka.setIngredient('1', Material.STONE);
        cStoniarka.setIngredient('2', Material.STONE);
        cStoniarka.setIngredient('3', Material.STONE);
        cStoniarka.setIngredient('4', Material.STONE);
        cStoniarka.setIngredient('5', Material.REDSTONE_BLOCK);
        cStoniarka.setIngredient('6', Material.STONE);
        cStoniarka.setIngredient('7', Material.STONE);
        cStoniarka.setIngredient('8', Material.STONE);
        cStoniarka.setIngredient('9', Material.STONE);
        Bukkit.getServer().addRecipe(cStoniarka);

        ShapedRecipe cBoyfarmer = new ShapedRecipe(boyfarmer.getItem());
        cBoyfarmer.shape("123", "456", "789");
        cBoyfarmer.setIngredient('1', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('2', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('3', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('4', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('5', Material.GOLD_BLOCK);
        cBoyfarmer.setIngredient('6', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('7', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('8', Material.OBSIDIAN);
        cBoyfarmer.setIngredient('9', Material.OBSIDIAN);
        Bukkit.getServer().addRecipe(cBoyfarmer);

        ShapedRecipe cSandfarmer = new ShapedRecipe(sandfarmer.getItem());
        cSandfarmer.shape("123", "456", "789");
        cSandfarmer.setIngredient('1', Material.SAND);
        cSandfarmer.setIngredient('2', Material.SAND);
        cSandfarmer.setIngredient('3', Material.SAND);
        cSandfarmer.setIngredient('4', Material.SAND);
        cSandfarmer.setIngredient('5', Material.GOLD_BLOCK);
        cSandfarmer.setIngredient('6', Material.SAND);
        cSandfarmer.setIngredient('7', Material.SAND);
        cSandfarmer.setIngredient('8', Material.SAND);
        cSandfarmer.setIngredient('9', Material.SAND);
        Bukkit.getServer().addRecipe(cSandfarmer);

        ShapedRecipe cEnderchest = new ShapedRecipe(new ItemStack(Material.ENDER_CHEST));
        cEnderchest.shape("123", "456", "789");
        cEnderchest.setIngredient('1', Material.OBSIDIAN);
        cEnderchest.setIngredient('2', Material.OBSIDIAN);
        cEnderchest.setIngredient('3', Material.OBSIDIAN);
        cEnderchest.setIngredient('4', Material.OBSIDIAN);
        cEnderchest.setIngredient('5', Material.ENDER_PEARL);
        cEnderchest.setIngredient('6', Material.OBSIDIAN);
        cEnderchest.setIngredient('7', Material.OBSIDIAN);
        cEnderchest.setIngredient('8', Material.OBSIDIAN);
        cEnderchest.setIngredient('9', Material.OBSIDIAN);
        Bukkit.getServer().addRecipe(cEnderchest);

        ShapedRecipe cKopaczFosy = new ShapedRecipe(kopaczfosy.getItem());
        cKopaczFosy.shape("123", "456", "789");
        cKopaczFosy.setIngredient('1', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('2', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('3', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('4', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('5', Material.DIAMOND_PICKAXE);
        cKopaczFosy.setIngredient('6', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('7', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('8', Material.OBSIDIAN);
        cKopaczFosy.setIngredient('9', Material.OBSIDIAN);
        Bukkit.getServer().addRecipe(cKopaczFosy);

        ShapedRecipe cRzucanetnt = new ShapedRecipe(rzucanetnt.getItem());
        cRzucanetnt.shape("123", "456", "789");
        cRzucanetnt.setIngredient('1', Material.TNT);
        cRzucanetnt.setIngredient('2', Material.TNT);
        cRzucanetnt.setIngredient('3', Material.TNT);
        cRzucanetnt.setIngredient('4', Material.TNT);
        cRzucanetnt.setIngredient('5', Material.TNT);
        cRzucanetnt.setIngredient('6', Material.TNT);
        cRzucanetnt.setIngredient('7', Material.TNT);
        cRzucanetnt.setIngredient('8', Material.TNT);
        cRzucanetnt.setIngredient('9', Material.TNT);
        Bukkit.getServer().addRecipe(cRzucanetnt);

        ShapedRecipe cSwitchball = new ShapedRecipe(switchBall.getItem());
        cSwitchball.shape("123", "456", "789");
        cSwitchball.setIngredient('2', Material.ARROW);
        cSwitchball.setIngredient('4', Material.EMERALD_BLOCK);
        cSwitchball.setIngredient('5', Material.ENDER_PEARL);
        cSwitchball.setIngredient('6', Material.EMERALD_BLOCK);
        cSwitchball.setIngredient('8', Material.REDSTONE_TORCH_ON);
        Bukkit.getServer().addRecipe(cSwitchball);

        ShapedRecipe cRatunek = new ShapedRecipe(ratunek.getItem());
        cRatunek.shape("123", "456", "789");
        cRatunek.setIngredient('2', Material.EMERALD_BLOCK);
        cRatunek.setIngredient('5', Material.DIAMOND_BLOCK);
        cRatunek.setIngredient('8', Material.FENCE);
        Bukkit.getServer().addRecipe(cRatunek);
    }

    public static ItemStack getRzucaneTnt(){
        return rzucanetnt.getItem();
    }
    public static ItemStack getBoyfarmer(){
        return boyfarmer.getItem();
    }
    public static ItemStack getSandfarmer(){
        return sandfarmer.getItem();
    }
    public static ItemStack getKopaczfosy() {
        return kopaczfosy.getItem();
    }
    public static ItemStack getSwichBall(){
        return switchBall.getItem();
    }
    public static ItemStack getRatunek(){
        return ratunek.getItem();
    }
}
