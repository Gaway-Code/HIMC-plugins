package pl.himc.settings.command;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.drop.cobblex.CobblexData;
import pl.himc.core.base.drop.luckybox.LuckyBoxData;
import pl.himc.core.base.kit.KitData;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.settings.SettingsPlugin;

import java.util.Arrays;

public final class EnableCommand extends PlayerCommand {

    public EnableCommand(final SettingsPlugin plugin, String permission) {
        super("enable", permission,"/");
        this.register();
        this.plugin = plugin;
    }
    private final SettingsPlugin plugin;

    @Override
    public boolean onCommand(Player player, String[] args) {
        open(player);
        return false;
    }

    public void open(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&cZarzadzanie edycja"), 3);

        GuiItem blue = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)11).setName("&8*").getItem());
        GuiItem dark = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName("&8*").getItem());

        boolean createGuild = PluginGuild.getPlugin().getPluginConfiguration().enableCreateGuild;
        boolean strenght2InEffect = PluginCore.getCore().getPluginConfig().strenght2InEffect;
        boolean cobblex = PluginCore.getCore().getPluginConfig().enableCobblex;
        boolean luckybox = PluginCore.getCore().getPluginConfig().enableLuckyBox;

        boolean tnt = this.plugin.getPluginConfig().tntExplode;

        gui.setItem(0, blue);
        gui.setItem(1, new GuiItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("&8* &7&lDIAMENTOWE ITEMY &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Aktualny status: " + (this.plugin.getPluginConfig().craftDiamondItems ? "&awlaczone" : "&cwylaczone"),
                " &8%> &7Kliknij aby " + (this.plugin.getPluginConfig().craftDiamondItems ? "&cwylaczyc" : "&awlaczyc") + " &7diamentowe itemy",
                "&7")).getItem(), click -> {
                        this.plugin.getPluginConfig().craftDiamondItems = !this.plugin.getPluginConfig().craftDiamondItems;
                        this.plugin.savePluginConfig();
                        open(player);
                }));
        gui.setItem(2, blue);
        gui.setItem(3, new GuiItem(new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("&8* &7&lZAKLADANIE GILDII &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Aktualny status: " + ( createGuild ? "&awlaczone" : "&cwylaczone"),
                " &8%> &7Kliknij aby " + (createGuild ? "&cwylaczyc" : "&awlaczyc") + " &7zakladanie gildii",
                "&7")).getItem(), click -> {
            PluginGuild.getPlugin().getPluginConfiguration().enableCreateGuild = !PluginGuild.getPlugin().getPluginConfiguration().enableCreateGuild;
            PluginGuild.getPlugin().savePluginConfig();
            open(player);
        }));
        gui.setItem(4, blue);
        gui.setItem(5, new GuiItem(new ItemBuilder(Material.POTION).setName("&8* &7&lSILA II W EFEKTACH &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Aktualny status: " + (strenght2InEffect ? "&awlaczona" : "&cwylaczona"),
                " &8%> &7Kliknij aby " + (strenght2InEffect ? "&cwylaczyc" : "&awlaczyc") + " &7sile II",
                "&7")).getItem(), click -> {
            PluginCore.getCore().getPluginConfig().strenght2InEffect = !PluginCore.getCore().getPluginConfig().strenght2InEffect;
            PluginCore.getCore().savePluginConfiguration();
            open(player);
        }));
        gui.setItem(6, blue);
        gui.setItem(7, new GuiItem(new ItemBuilder(Material.MOSSY_COBBLESTONE).setName("&8* &a&lCOBBLEX &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Aktualny status: " + (cobblex ? "&awlaczone" : "&cwylaczone"),
                " &8%> &7Kliknij aby " + (cobblex ? "&cwylaczyc" : "&awlaczyc") + " &7cobblexy",
                "&7")).getItem(), click -> {
            CobblexData.changeStatus();
            open(player);
        }));
        gui.setItem(8, blue);
        gui.setItem(9, dark);
        gui.setItem(10, blue);
        gui.setItem(11, new GuiItem(new ItemBuilder(Material.SPONGE).setName("&8* &e&lLucky&6&lBox &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Aktualny status: " + (luckybox ? "&awlaczone" : "&cwylaczone"),
                " &8%> &7Kliknij aby " + (luckybox ? "&cwylaczyc" : "&awlaczyc") + " &7pandory",
                "&7")).getItem(), click -> {
            LuckyBoxData.changeStatus();
            open(player);
        }));
        gui.setItem(12, blue);
        gui.setItem(13, new GuiItem(new ItemBuilder(Material.DIAMOND_SWORD).setName("&8* &7&lZARZADZAJ &e&lZESTAWAMI &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Kliknij aby &aprzejsc dalej",
                "&7")).getItem(), click -> openKitSettings(player)));
        gui.setItem(14, blue);
        gui.setItem(15, new GuiItem(new ItemBuilder(Material.TNT).setName("&8* &c&lWybuchanie TNT &8*").setLore(Arrays.asList(
                "&7",
                " &8%> &7Aktualny status: " + (tnt ? "&awlaczone"  : "&cwylaczone"),
                " &8%> &7Kliknij aby " + (tnt ? "&cwylaczyc" : "&awlaczyc") + " &7wybuchanie tnt",
                "&7")).getItem(), click -> {
            this.plugin.getPluginConfig().tntExplode = !this.plugin.getPluginConfig().tntExplode;
            this.plugin.savePluginConfig();
            open(player);
        }));
        gui.setItem(16, blue);
        gui.setItem(17, dark);
        gui.setItem(18, blue);
        gui.setItem(19, dark);
        gui.setItem(20, blue);
        gui.setItem(21, dark);
        gui.setItem(22, blue);
        gui.setItem(23, dark);
        gui.setItem(24, blue);
        gui.setItem(25, dark);
        gui.setItem(26, blue);
        gui.open(player);
    }

    public void openKitSettings(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&cZarzadzanie zestawami"), 3);
        KitData.getInstance().getKits().forEach(kit -> gui.setToNextFree(new GuiItem(new ItemBuilder(kit.getIcon().getType()).setName(kit.getItemInInventoryName()).setLore(Arrays.asList(" &8%> &7Ten zestaw jest obecnie " + (kit.isEnable() ? "&awlaczony" : "&cwylaczony"),
                " &8%> &7Kliknij aby " + (kit.isEnable() ? "&cwylaczyc" : "&awlaczyc") + " &7ten zestaw!")).getItem(), click -> {
            kit.toggleEnable();
            openKitSettings(player);
        })));
        gui.open(player);
    }
}
