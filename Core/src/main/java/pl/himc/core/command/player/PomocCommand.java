package pl.himc.core.command.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

public final class PomocCommand extends PlayerCommand {

    public PomocCommand() {
        super("pomoc", null, "");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.openPomoc(player);
        return false;
    }

    public void openPomoc(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&a&lPomoc"), 4);

        gui.setItem(1, new ItemBuilder(Material.WORKBENCH).setName("&7Lista &ecraftingów").setClickExecutor(inventoryClickEvent -> player.chat("/craftingi")).getGuiItem());
        gui.setItem(3, new ItemBuilder(Material.SKULL_ITEM).setName("&cAdministracja").setSkullOwner("Gaway69").setClickExecutor(inventoryClickEvent -> administracja(player)).getGuiItem());
        gui.setItem(5, new ItemBuilder(Material.PAPER).setName("&7Zarzadzanie &cwiadomosciami").setClickExecutor(inventoryClickEvent -> player.chat("/wiadomosci")).getGuiItem());
        gui.setItem(7, new ItemBuilder(Material.BOOK).setName("&e&lKomendy").setLore(
                "&8&l%> &a/drop &8- &7Menu dropow"
                ,"&8&l%> &a/rangi &8- &7Informacje o rangach &6PREMIUM"
                ,"&8&l%> &a/wiadomosci &8- &7Zarzadzanie wiadomosciami"
                ,"&8&l%> &a/gildie &8- &7Komendy do zarzadzania gildiami"
                ,"&8&l%> &a/gildieinfo &8- &7Informacje na temat gildii"
                ,"&8&l%> &a/craft &8- &7Lista dostepnych craftingow"
                ,"&8&l%> &a/kosz &8- &7Kosz na niepotrzebne przedmioty"
                ,"&8&l%> &a/sklep &8- &7Sklep i bazar za wirtualne monety"
                ,"&8&l%> &a/bloki &8- &7Wymiana przedmiotow na bloki"
                ,"&8&l%> &a/topki &8- &7Lista najlepszych graczy"
                ,"&8&l%> &a/cobblex &8- &7Tworzenie cobblexa z &a9x64 kamienia"
                ,"&8&l%> &a/efekty &8- &7Sklep z efektami"
                ,"&8&l%> &a/helpop &8- &7Kontakt z administracja"
                ,"&8&l%> &a/naprawitem &8- &7Szybkie naprawianie przedmiotu za EXPa"
                ,"&8&l%> &a/schowek &8- &7Depozyt na &6koxy&7, &erefile &7i &9perly"
                ,"&8&l%> &a/warp &8- &7Lista roznych lokalizacji"
                ,"&8&l%> &a/tnt &8- &7Informacje o dzialaniu TNT"
                ,"&8&l%> &a/lobby &8- &7Teleportacja na lobby"
        ).getGuiItem());
        gui.setItem(11, new ItemBuilder(Material.IRON_BLOCK).setName("&7Wymiana &aprzedmiotów na bloki").setClickExecutor(inventoryClickEvent -> player.chat("/bloki")).getGuiItem());
        gui.setItem(13, new ItemBuilder(Material.GOLDEN_APPLE).setName("&6Schowek").setClickExecutor(inventoryClickEvent -> player.chat("/schowek")).getGuiItem());
        gui.setItem(15, new ItemBuilder(Material.GOLD_NUGGET).setName("&bSklep").setClickExecutor(inventoryClickEvent -> player.chat("/sklep")).getGuiItem());
        gui.setItem(21, new ItemBuilder(Material.DIAMOND_SWORD).setName("&7Topki &anajlepszych graczy").setClickExecutor(inventoryClickEvent -> player.chat("/topki")).getGuiItem());
        gui.setItem(23, new ItemBuilder(Material.STONE).setName("&7Lista &8dropów").setClickExecutor(inventoryClickEvent -> player.chat("/drop")).getGuiItem());
        gui.setItem(31, new ItemBuilder(Material.DIAMOND_CHESTPLATE).setName("&7Dostepne &azestawy").setClickExecutor(inventoryClickEvent -> player.chat("/kit")).getGuiItem());
        gui.open(player);
    }

    public void administracja(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aAdministracja"), 1);
        gui.setItem(4, new ItemBuilder(Material.SKULL_ITEM).setName("&cGaway69").setLore("&c&lWlasciciel").setSkullOwner("Gaway69").setClickExecutor(inventoryClickEvent -> player.sendMessage(ChatUtil.fixColor("&9Discord &aGaway#4391"))).getGuiItem());
        gui.setItem(2, new ItemBuilder(Material.SKULL_ITEM).setName("&cQuerko242").setLore("&c&lWlasciciel").setSkullOwner("Querko242").setClickExecutor(inventoryClickEvent -> player.sendMessage(ChatUtil.fixColor("&9Discord &aQuerko242#8338"))).getGuiItem());
        gui.setItem(6, new ItemBuilder(Material.SKULL_ITEM).setName("&cWertos97").setLore("&c&lHeadAdmin").setSkullOwner("wertos97").setClickExecutor(inventoryClickEvent -> player.sendMessage(ChatUtil.fixColor("&9Discord &awertos#9582"))).getGuiItem());
        gui.setItem(0, new ItemBuilder(Material.SKULL_ITEM).setName("&cMacFayden").setLore("&a&lAdmin").setSkullOwner("MacFayden").setClickExecutor(inventoryClickEvent -> player.sendMessage(ChatUtil.fixColor("&9Discord &aMacFayden#4808"))).getGuiItem());
        gui.setItem(8, new ItemBuilder(Material.SKULL_ITEM).setName("&cVebTo").setLore("&9&lModerator").setSkullOwner("VebTo").setClickExecutor(inventoryClickEvent -> player.sendMessage(ChatUtil.fixColor("&9Discord &aVebTo [Ministrant]#9222"))).getGuiItem());

        gui.open(player);
    }
}
