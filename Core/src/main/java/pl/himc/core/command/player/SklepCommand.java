package pl.himc.core.command.player;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.shop.ShopBazar;
import pl.himc.core.base.shop.ShopSell;
import pl.himc.core.base.user.User;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SklepCommand extends PlayerCommand {

    public SklepCommand(final CorePlugin plugin) {
        super("sklep", null, "");
        this.register();
        this.plugin = plugin;
        sklepCommand = this;
    }

    private final CorePlugin plugin;
    private static SklepCommand sklepCommand;

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(player.hasPermission(this.getPermission() + ".admin")){
            if(args.length <= 0){
                ChatUtil.sendMessage(player, "&b&o/sklep dodaj <nazwa> <cena> &8- &7Dodaje przedmiot do sklepu ktory trzymasz w rece");
                ChatUtil.sendMessage(player, "&b&o/sklep usun <nazwa> &8- &7Usuwa przedmiot ze sklepu");
                return ChatUtil.sendMessage(player, "&b&o/sklep open &8- &7Otwiera sklep");
            }
            if(args[0].equalsIgnoreCase("dodaj")){
                if(player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR){
                    return ChatUtil.sendMessage(player, "&c&oMusisz trzymac w rece przedmiot do sprzedazy!");
                }
                if(args.length != 3){
                    return ChatUtil.sendMessage(player, "&c/sklep dodaj <nazwa> <cena>");
                }
                if(!IntegerUtil.isInteger(args[2])){
                    return ChatUtil.sendMessage(player, "&c&oPodaj prawidlowa cene!");
                }
                int price = Integer.parseInt(args[2]);
                this.plugin.getShopData().addSell(new ShopSell(args[1], player.getItemInHand(), price));
                return ChatUtil.sendMessage(player, "&a&oPomyslnie dodano przedmiot &e&o" + player.getItemInHand().getType() + " &a&oza &e&o$" + price + "&a&o!");
            }
            if(args[0].equalsIgnoreCase("usun")){
                if(args.length != 2){
                    return ChatUtil.sendMessage(player, "&c/sklep usun <nazwa>");
                }
                ShopSell shopSell = this.plugin.getShopData().get(args[1]);
                if(shopSell == null) {
                    return ChatUtil.sendMessage(player, "&c&oNie znaleziono przedmiotu w sklepie!");
                }
                this.plugin.getShopData().removeSell(shopSell);
                return ChatUtil.sendMessage(player, "&a&oPomyslnie usunieto przedmiot ze sklepu!");
            }
            if(args[0].equalsIgnoreCase("open")){
                this.openMain(player);
                return false;
            }
        }
        this.openMain(player);
        return false;
    }

    public void openMain(final Player player){
        GuiWindow gui = new GuiWindow("&eMenu glowne", 1);
        gui.setItem(2, new ItemBuilder(Material.NETHER_STAR).setName("&aKup przedmioty").setClickExecutor(event -> this.openShop(player)).getGuiItem());
        gui.setItem(4, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).setName("&bTwoje konto").setClickExecutor(event -> this.openAccount(player)).setSkullOwner(player.getName()).getGuiItem());
        gui.setItem(6, new ItemBuilder(Material.BOOK).setName("&dHandluj z graczami").setClickExecutor(event -> this.openBazar(player, 1)).getGuiItem());
        gui.open(player);
    }

    public void openShop(final Player player){
        GuiWindow gui = new GuiWindow("&aKup przedmioty", 6);
        User user = this.plugin.getUserManager().getUser(player);

        for(ShopSell shopSell : PluginCore.getCore().getShopData().getShopSellList()){
            final ItemStack is = shopSell.getItemstack().clone();
            is.setAmount(1);
            final ItemMeta im = is.getItemMeta();
            im.setLore(ChatUtil.fixColor(Arrays.asList(" &8* &7Nazwa: &a" + shopSell.getItemName(), " &8* &7Cena: &a$" + shopSell.getPrice(), " &8* &7Ilosc: &a" + shopSell.getItemstack().getAmount() + " szt.", "&8", " &a&nKliknij aby zakupic ten przedmiot!")));
            is.setItemMeta(im);
            gui.setToNextFree(new GuiItem(is, event -> {
                if(shopSell.getPrice() > user.getMoney()){
                    ChatUtil.sendMessage(player, "&c&oNie masz tyle kasy!");
                    return;
                }
                user.removeMoney(shopSell.getPrice());
                ItemsUtil.giveItem(player, shopSell.getItemstack());
                ChatUtil.sendMessage(player, "&a&oPomyslnie zakupiles przedmiot!");
            }));
        }
        gui.setItem(49, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).setName("&bTwoje konto").setSkullOwner(player.getName()).setClickExecutor(event -> this.openAccount(player)).getGuiItem());
        gui.setItem(53, new GuiItem(gui.undoPageItem(), event -> this.openMain(player)));
        gui.open(player);
    }

    public void openAccount(final Player player){
        GuiWindow gui = new GuiWindow("&bTwoje konto", 3);
        User user = this.plugin.getUserManager().getUser(player);

        gui.setItem(13, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).setName("&a" + player.getName()).setLore(" &8* &7Stan Twojego konta: &6$" + user.getMoney()).setSkullOwner(player.getName()).getGuiItem());
        gui.setItem(3, new ItemBuilder(Material.NETHER_STAR).setName("&aKup przedmioty").setClickExecutor(event -> this.openShop(player)).getGuiItem());
        gui.setItem(5, new ItemBuilder(Material.EMERALD).setName("&aDodaj pieniadze na konto").setLore("&8", " &8* &7Aby dodac pieniadze &8- &7musisz miec &aszmaragdy", " &8* &7Aktualnie &e&ojeden szmaragd &7kosztuje &a3$", "&8", "&a&nKliknij PPM aby sprzedac!").setClickExecutor(click -> this.openEmeraldSell(player)).getGuiItem());
        gui.setItem(26, new GuiItem(gui.undoPageItem(), event -> this.openMain(player)));
        gui.open(player);
    }

    public void openEmeraldSell(final Player player){
        GuiWindow gui = new GuiWindow("&bTwoje konto", 1);
        User user = this.plugin.getUserManager().getUser(player);

        gui.setItem(3, new ItemBuilder(Material.EMERALD).setName("&7Sprzedaj &a1 szmaragda").setLore(" &a&nKliknij aby sprzedac!").setClickExecutor(event -> {
            if(ItemsUtil.getAmountOfItem(Material.EMERALD, player, (short)0) < 1){
                ChatUtil.sendMessage(player, "&c&oNie posiadasz szmaragdow!");
                return;
            }
            ItemsUtil.remove(new ItemStack(Material.EMERALD), player, 1);
            ChatUtil.sendMessage(player, "&a&oSprzedano szmaragda za &e&o$3");
            user.addMoney(3);
        }).getGuiItem());
        gui.setItem(5, new ItemBuilder(Material.EMERALD).setName("&7Sprzedaj &ewszystkie szmaragdy").setLore(" &a&nKliknij aby sprzedac!").setClickExecutor(event -> {
            final int hasEmerald = ItemsUtil.getAmountOfItem(Material.EMERALD, player, (short)0);
            if(hasEmerald < 1){
                ChatUtil.sendMessage(player, "&c&oNie posiadasz szmaragdow!");
                return;
            }
            ItemsUtil.remove(new ItemStack(Material.EMERALD), player, hasEmerald);
            final int money = hasEmerald * 3;
            ChatUtil.sendMessage(player, "&a&oSprzedano wszystkie szmaragdy za &e&o$" + money);
            user.addMoney(money);
        }).getGuiItem());
        gui.setItem(8, new GuiItem(gui.undoPageItem(), event -> this.openAccount(player)));

        gui.open(player);
    }

    public void openBazar(final Player player, int page){
        final int slotStart = (page == 1) ? 0 : (page * 35 - 34);
        final int slotEnd = (page == 1) ? 35 : (page * 35 + 1);
        GuiWindow gui = new GuiWindow("&dBazar", 6);
        User user = this.plugin.getUserManager().getUser(player);

        int slot = 0;
        AtomicBoolean nextPage = new AtomicBoolean(false);
        for(ShopBazar bazar : this.plugin.getShopData().getShopBazars()){
            if (slot >= slotStart && slot <= slotEnd) {
                final ItemStack is = bazar.getItemstack().clone();
                is.setAmount(1);
                final ItemMeta im = is.getItemMeta();
                im.setLore(ChatUtil.fixColor(Arrays.asList(" &8* &7Cena: &a$" + bazar.getPrice(), " &8* &7Ilosc: &a" + bazar.getItemstack().getAmount() + " szt.", " &8* &7Wystawil: &a" + bazar.getPlayerOffer(), "&8", " &a&nKliknij aby zakupic ten przedmiot!")));
                is.setItemMeta(im);
                gui.setToNextFree(new GuiItem(is, event -> {
                    if(player.getName().equals(bazar.getPlayerOffer())){
                        ChatUtil.sendMessage(player, "&c&oTa oferte wystawiles Ty!");
                        return;
                    }
                    if(bazar.getPrice() > user.getMoney()){
                        ChatUtil.sendMessage(player, "&c&oNie masz tyle kasy!");
                        return;
                    }
                    user.removeMoney(bazar.getPrice());
                    ItemsUtil.giveItem(player, bazar.getItemstack());
                    ChatUtil.sendMessage(player, "&a&oPomyslnie zakupiles przedmiot od &e&o" + bazar.getPlayerOffer() + "&a&o!");
                    User userSell = this.plugin.getUserManager().getUser(bazar.getPlayerOffer());
                    userSell.addMoney(bazar.getPrice());
                    if(userSell.isOnline()){
                        ChatUtil.sendMessage(userSell.getPlayer(), "&a&oGracz &e&o" + player.getName() + " &a&ozakupil od Ciebie przedmiot &e&o" + bazar.getItemstack().getType() + " &a&oza &e&o$" + bazar.getPrice() + "&a&o!");
                    }
                    this.plugin.getShopData().removeBazar(bazar);
                    this.openBazar(player, page);
                }));

                nextPage.set(true);
            }
            slot++;
        }
        gui.setItem(49, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).setName("&aTwoje oferty").setSkullOwner(player.getName()).setClickExecutor(event -> this.openBazarOffer(player)).getGuiItem());
        if(page != 1) gui.setItem(47, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)14).setName("&cCofnij strone").setClickExecutor(click -> this.openBazar(player, page - 1)).getGuiItem());
        if(nextPage.get()) gui.setItem(51, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)5).setName("&aNastepna strona").setClickExecutor(click -> this.openBazar(player, page + 1)).getGuiItem());
        gui.setItem(53, new GuiItem(gui.undoPageItem(), event -> this.openMain(player)));
        gui.open(player);
    }

    public void openBazarOffer(final Player player){
        GuiWindow gui = new GuiWindow("&5Twoje oferty", 5);
        for(ShopBazar bazar : this.plugin.getShopData().getShopBazars()){
            if(bazar.getPlayerOffer().equals(player.getName())){
                final ItemStack is = bazar.getItemstack().clone();
                is.setAmount(1);
                final ItemMeta im = is.getItemMeta();
                im.setLore(ChatUtil.fixColor(Arrays.asList(" &8* &7Cena: &a$" + bazar.getPrice(), " &8* &7Ilosc: &a" + bazar.getItemstack().getAmount() + " szt.", "&7", " &cKliknij &caby usunac oferte!")));
                is.setItemMeta(im);
                gui.setToNextFree(new GuiItem(is, click -> this.openBazarRemoveOffer(player, bazar)));
            }
        }
        gui.setItem(40, new ItemBuilder(Material.COMPASS, 1).setName("&aWystaw oferte").setClickExecutor(event -> {this.openBazarSellOffer(player);}).getGuiItem());
        gui.setItem(44, new GuiItem(gui.undoPageItem(), event -> this.openBazar(player, 1)));
        gui.open(player);
    }

    public void openBazarRemoveOffer(final Player player, ShopBazar bazar){
        GuiWindow gui = new GuiWindow("&cCzy na pewno chcesz usunac oferte?", 1);

        gui.setItem(2, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)14).setName("&4&lUSUN OFERTE").setClickExecutor(click -> {
            this.plugin.getShopData().removeBazar(bazar);
            this.openBazarOffer(player);
            ItemsUtil.giveItem(player, bazar.getItemstack());
            ChatUtil.sendMessage(player, "&a&oOferta zostala usunieta!");
        }).getGuiItem());
        gui.setItem(4, new GuiItem(bazar.getItemstack().clone()));
        gui.setItem(6, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)5).setName("&2&lANULUJ").setClickExecutor(click -> this.openBazarOffer(player)).getGuiItem());
        gui.open(player);
    }

    public void openBazarSellOffer(final Player player){
        Inventory inv = Bukkit.createInventory(player, 36, ChatUtil.fixColor("&aWystaw przedmiot na sprzedaz"));
        inv.setItem(4, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)5).setName("&8").getItem());
        inv.setItem(12, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)5).setName("&aTu dodaj przedmiot na sprzedaz &8%>").getItem());
        inv.setItem(14, new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)5).setName("&8<% &aTu dodaj przedmiot na sprzedaz").getItem());
        inv.setItem(16, new ItemBuilder(Material.matchMaterial("351"),1, (short)10).setName("&2Wystaw oferte!").getItem());
        inv.setItem(22, new ItemBuilder(Material.GOLD_NUGGET).setName("&aAktualna cena: &a$0").getItem());
        inv.setItem(29, new ItemBuilder(Material.GOLD_INGOT).setName("&e+$1").getItem());
        inv.setItem(30, new ItemBuilder(Material.GOLD_INGOT).setName("&e+$10").getItem());
        inv.setItem(31, new ItemBuilder(Material.GOLD_INGOT).setName("&e+$100").getItem());
        inv.setItem(32, new ItemBuilder(Material.GOLD_INGOT).setName("&e+$500").getItem());
        inv.setItem(33, new ItemBuilder(Material.GOLD_INGOT).setName("&e+$1000").getItem());

        player.openInventory(inv);
    }

    public static SklepCommand getSklepCommand() {
        return sklepCommand;
    }
}
