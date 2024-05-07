package pl.himc.core.command.player;

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
import pl.himc.core.base.user.User;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import pl.himc.core.command.admin.VoucherCommand;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;

import java.util.Arrays;

public final class DropCommand extends PlayerCommand {

    public DropCommand() {
        super("drop", null, "/drop","kamien", "stone");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.openStone(player);
        return false;
    }

    public void openStone(Player player){
        User user = PluginCore.getCore().getUserManager().getUser(player);
        Guild guild = PluginGuild.getPlugin().getGuildManager().getPlayerGuild(player);
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aDrop z &7kamienia"), 5);

        DropManager.getDrops().forEach(drop -> {
            boolean isDisable = drop.isDisableDrop(player);
            ItemBuilder item = new ItemBuilder(drop.getItem().getType(), 1, drop.getItem().getDurability()).setName(drop.getGuiName()).setLore(Arrays.asList("&7",
                    " &8&l%> &7Szansa: &6" + drop.getChance() + "%",
                    " &8&l%> &7Twoja szansa na drop: &6" + user.getChance(drop.getChance()) + "%",
                    " &8&l%> &7Wypada od poziomu: &6" + drop.getMaxY() + " &7w dol",
                    " &8&l%> &7Szczescie: " + (drop.isFortune() ? "&2✔" : "&4✖"),
                    " &8&l%> &7Punkty gornictwa: &6" + drop.getPoints(),
                    " &8&l%> &7Bonus &9M&6VIP&7: &a+" + PluginCore.getCore().getPluginConfig().dropMVip + "&7%",
                    " &8&l%> &7Status dropu: " + (isDisable ? "&4✖" : "&2✔"), "&7")).setClickExecutor(click -> {drop.changeStatusDrop(player); openStone(player);});
            if(!isDisable) {
                item.addEnchant(Enchantment.DURABILITY, 1337);
                item.addItemFlag(ItemFlag.HIDE_ENCHANTS);
            }
            gui.setToNextFree(item.getGuiItem());
        });

        gui.setItem(44, new ItemBuilder(Material.SKULL_ITEM).setName("&7").setLore("&7",
                "       &a&lTWOJE STATYSTYKI",
                "&7",
                " &8&l%> &7Poziom gornictwa: &a" + user.getLvl(),
                " &8&l%> &7Punkty gornictwa: &a" + user.getPointsMiner(),
                " &8&l%> &7Do nastepnego poziomu: &a" + user.getBlocksToNext(), " &7blokow").setSkullOwner(player.getName()).getGuiItem());
        gui.setItem(39, new ItemBuilder(Material.getMaterial(351), 1, (short)2).setName("&7").setLore("&7",
                "            &2&lWLACZ DROP",
                "&7",
                " &8&l%> &7Kliknij aby &awlaczyc &7drop", "&7").setClickExecutor(click -> {DropManager.enableDrops(player); openStone(player);}).getGuiItem());
        gui.setItem(40, new ItemBuilder(Material.getMaterial(351), 1, (short)1).setName("&7").setLore("&7",
                "            &4&lWYLACZ DROP",
                "&7",
                " &8&l%> &7Kliknij aby &cwylaczyc &7drop", "&7").setClickExecutor(click -> {DropManager.disableDrops(player); openStone(player);}).getGuiItem());
        gui.setItem(42, new ItemBuilder(Material.PAPER).setName("&7").setLore("&7",
                "         &e&lPOWIADOMIENIA O DROPIE     ",
                "&7",
                "   &8&l%> &7Kliknij aby " + (user.isDropMsg() ? "&cwyłączyć" : "&awłączyć") + " &7wiadomości").setClickExecutor(click -> {
            user.toggleDropMsg();
            this.openStone(player);
        }).getGuiItem());
        gui.setItem(43, new ItemBuilder(Material.COBBLESTONE).setName("&7").setLore("&7",
                "           &8&lBRUK",
                "&7",
                "    &8&l%> &7Wypadanie: " + (user.isDropCobble() ? "&2✔" : "&4✖"), "&7").setClickExecutor(click -> { user.toggleDropCobble();openStone(player); }).getGuiItem());

        gui.setItem(36, new ItemBuilder(Material.MOSSY_COBBLESTONE).addEnchant(Enchantment.DURABILITY, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&7").setLore(Arrays.asList("&7",
                "           &7Drop z &2&lCOBBLEX",
                "&7",
                "     &a&nKliknij aby zobaczyć drop&a     ",
                "&7")).setClickExecutor(event -> openCobblex(player)).getGuiItem());

        gui.setItem(27, new ItemBuilder(Material.SPONGE).addEnchant(Enchantment.DURABILITY, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&7").setLore(Arrays.asList("&7",
                "         &7Drop z &e&lLucky&6&lBox",
                "&7",
                "     &a&nKliknij aby zobaczyć drop&a     ",
                "&7")).setClickExecutor(event -> openLuckyBox(player)).getGuiItem());

        gui.setItem(37, new ItemBuilder(Material.DIAMOND_PICKAXE).addEnchant(Enchantment.DURABILITY, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&6★").setLore(Arrays.asList("&7",
                "                 &b&lTURBO&d&lDROP",
                "&7",
                "&8&m--------&7 Tryb: &6GRACZ&8 &m--------",
                "    &8%> &7Aktualny status: " + (user.isTurboDrop() ? "&aaktywny &8(&a" + user.getDurationTruboDrop() + "&8)" : "&cnieaktywny"),
                "&7",
                "&8&m--------&7 Tryb: &6GILDIA&8 &m--------",
                "&8%> &7Aktualny status: " + (guild != null ? (guild.isTurbodrop() ? "&aaktywny &8(&a" + guild.getDurationTruboDrop() + "&8)" : "&cnieaktywny") : "&cnieaktywny"),
                "&7",
                "                  &b&lTURBO&5&lEXP",
                "&7",
                "&8&m--------&7 Tryb: &6GRACZ&8 &m--------",
                "    &8%> &7Aktualny status: " + (user.isTurboExp() ? "&aaktywny &8(&a" + user.getDurationTruboExp() + "&8)" : "&cnieaktywny"),
                "&7",
                "&8&m--------&7 Tryb: &6GILDIA&8 &m--------",
                "    &8%> &7Aktualny status: " + (guild != null ? (guild.isTurboexp() ? "&aaktywny &8(&a" + guild.getDurationTurboExp() + "&8)" : "&cnieaktywny") : "&cnieaktywny"),
                "&7"
        )).setClickExecutor(click -> openStone(player)).getGuiItem());
        gui.open(player);
    }

    public void openCobblex(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aDrop z &2cobblex"), 5);

        CobblexData.getDrops().forEach(drop -> gui.setToNextFree(new GuiItem(drop.clone())));
        gui.setItem(40, new GuiItem(gui.undoPageItem(), click -> openStone(player)));
        gui.open(player);
    }

    public void openLuckyBox(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aDrop z &eLucky&6Box"), 5);

        final ItemStack turbodrop = VoucherCommand.getTruboDrop10();
        final ItemStack turboexp = VoucherCommand.getTruboExp10();

        LuckyBoxData.getDrops().forEach(drop -> gui.setToNextFree(new GuiItem(drop.clone())));
        gui.setToNextFree(new GuiItem(turbodrop));
        gui.setToNextFree(new GuiItem(turboexp));
        gui.setToNextFree(new GuiItem(new ItemBuilder(Material.LAVA_BUCKET).setName("&c&lZalanie przez lawe").setLore(" &8&l%> &7Szansa: &66%").getItem()));
        gui.setToNextFree(new GuiItem(new ItemBuilder(Material.BEACON).setName("&3&lBEACON").setLore(" &8&l%> &7Szansa: &60.4%").getItem()));
        gui.setItem(40, new GuiItem(gui.undoPageItem(), click -> openStone(player)));
        gui.open(player);
    }
}
