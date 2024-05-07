package pl.himc.settings.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.drop.cobblex.CobblexData;
import pl.himc.core.base.drop.luckybox.LuckyBoxData;
import pl.himc.core.base.drop.stone.DropManager;
import pl.himc.core.base.effect.EffectManager;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.settings.SettingsPlugin;
import pl.himc.settings.manager.ReloadFilesAsync;
import pl.himc.settings.manager.SaveUsersAsync;

import java.util.Arrays;

public final class CoreCommand extends PlayerCommand {

    public CoreCommand(final SettingsPlugin plugin, String permission) {
        super("core", permission, "core", "tools");
        this.plugin = plugin;
        this.register();
    }
    private final SettingsPlugin plugin;

    @Override
    public boolean onCommand(Player player, String[] args) {
        openMain(player);
        return false;
    }

    public void openMain(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&cZarzadzanie pluginami"), 1);

        gui.setItem(3, new GuiItem(new ItemBuilder(Material.ENDER_PORTAL_FRAME).setName("&8* &7Zarzadzaj &2gildiami &8*").setLore(" &8%> &7Kliknij aby &eprzejsc dalej").getItem(), click -> openGuild(player)));
        gui.setItem(1, new GuiItem(new ItemBuilder(Material.WOOD_PICKAXE).setName("&8* &7Zarzadzaj &2corem &8*").setLore(" &8%> &7Kliknij aby &eprzejsc dalej").getItem(), click -> openCore(player)));
        gui.setItem(7, new GuiItem(new ItemBuilder(Material.HOPPER).setName("&8* &a&lZapisz wszystkich graczy do &7&lbazy danych &8*").setLore(Arrays.asList("&7",
                " &8* &7Zapisuje wszystkich graczy do bazy danych",
                " &8* &6Zapis odbywa sie asynchronicznie",
                " &8* &7Czas trwania: &315 sekund",
                "&7")).getItem(), click -> {
            player.closeInventory();
            SaveUsersAsync.startSave(player);
        }));
        gui.setItem(5, new GuiItem(new ItemBuilder(Material.PAPER).setName("&8* &a&lPrzeladuj wszystkie pliki konfiguracyjne &8*").setLore(Arrays.asList("&7",
                " &8* &7Przeladowywuje pliki: config.yml i messages.yml we wszystkich pluginach",
                " &8* &6Przeladowywanie plikow odbywa sie asynchronicznie",
                " &8* &7Czas trwania: &315 sekund",
                "&7")).getItem(), click -> {
            player.closeInventory();
            ReloadFilesAsync.startReload(player);
        }));

        gui.open(player);
    }

    public void openGuild(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aZarzadzanie gildiami"), 1);

        gui.setItem(0, new GuiItem(new ItemBuilder(Material.HOPPER).setName("&8* &a&lZapisz graczy do &7&lbazy danych &8*").getItem(), click -> {
            long start = System.currentTimeMillis();
            PluginGuild.getPlugin().getUserManager().saveUsers();
            long time = System.currentTimeMillis() - start;
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().saveUsersSuccess.replace("{TIME}", Long.toString(time)));
        }));
        gui.setItem(2, new GuiItem(new ItemBuilder(Material.HOPPER).setName("&8* &a&lZapisz gildie do &7&lbazy danych &8*").getItem(), click -> {
            long start = System.currentTimeMillis();
            PluginGuild.getPlugin().getGuildManager().saveGuilds();
            long time = System.currentTimeMillis() - start;
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().saveGuildsSuccess.replace("{TIME}", Long.toString(time)));
        }));
        gui.setItem(4, new GuiItem(new ItemBuilder(Material.PAPER).setName("&8* &a&lPrzeladuj plik &7&lconfig.yml &8*").getItem(), click -> {
            PluginGuild.getPlugin().reloadPluginConfig();
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().reloadConfigSuccess);
        }));
        gui.setItem(6, new GuiItem(new ItemBuilder(Material.PAPER).setName("&8* &a&lPrzeladuj plik &7&lmessages.yml &8*").getItem(), click -> {
            PluginGuild.getPlugin().reloadPluginMessages();
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().reloadLangSuccess);
        }));
        gui.setItem(8, new GuiItem(new ItemBuilder(Material.BARRIER, 1, (short)14).setName("&cCofnij &8%>").getItem(), click -> openMain(player)));
        gui.open(player);
    }

    public void openCore(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aZarzadzanie corem"), 1);

        gui.setItem(0, new GuiItem(new ItemBuilder(Material.HOPPER).setName("&8* &a&lZapisz graczy do &7&lbazy danych &8*").getItem(), click -> {
            long start = System.currentTimeMillis();
            PluginCore.getCore().saveAll();
            long time = System.currentTimeMillis() - start;
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().saveUsersSuccess.replace("{TIME}", Long.toString(time)));
        }));
        gui.setItem(3, new GuiItem(new ItemBuilder(Material.PAPER).setName("&8* &a&lPrzeladuj plik &7&lconfig.yml &8*").setLore("&7Przeladowanie: ", " &8* &aconfig.yml", " &8* &aefekty").getItem(), click -> {
            PluginCore.getCore().reloadPluginConfig();
            PluginCore.getCore().getItemshopData().loadShop();
            EffectManager.getInstance().reloadEffect();
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().reloadConfigSuccess);
        }));
        gui.setItem(4, new GuiItem(new ItemBuilder(Material.PAPER).setName("&8* &a&lPrzeladuj plik &7&lmessages.yml &8*").getItem(), click -> {
            PluginCore.getCore().reloadPluginMessages();
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().reloadLangSuccess);
        }));
        gui.setItem(2, new GuiItem(new ItemBuilder(Material.PAPER).setName("&8* &a&lPrzeladuj &7&ldrop z kamienia &8*").getItem(), click -> {
            DropManager.reloadDrops();
            ChatUtil.sendMessage(player, this.plugin.getPluginMessage().reloadDropsSuccess);
        }));
        gui.setItem(5, new GuiItem(new ItemBuilder(Material.MOSSY_COBBLESTONE).setName("&8* &7&lEdytuj drop z &a&lCobbleX &8*").getItem(), click -> {
            Inventory cobblex = Bukkit.createInventory(player, 27, ChatUtil.fixColor("&cEdytowanie dropu z cobblex"));
            for(ItemStack items : CobblexData.getDrops()){
                cobblex.addItem(items.clone());
            }
            player.openInventory(cobblex);
        }));
        gui.setItem(6, new GuiItem(new ItemBuilder(Material.SPONGE).setName("&8* &7&lEdytuj drop z &e&lLucky&6&lBlocka &8*").getItem(), click -> {
            Inventory luckyblock = Bukkit.createInventory(player, 36, ChatUtil.fixColor("&cEdytowanie dropu z luckyblock"));
            for(ItemStack items : LuckyBoxData.getDrops()){
                luckyblock.addItem(items.clone());
            }
            player.openInventory(luckyblock);
        }));
        gui.setItem(8, new GuiItem(new ItemBuilder(Material.BARRIER).setName("&cCofnij &8%>").getItem(), click -> openMain(player)));
        gui.open(player);
    }
}
