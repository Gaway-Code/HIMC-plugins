package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.data.PluginConfig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.book.FancyBook;
import pl.himc.api.api.book.FancyBookPage;
import pl.himc.api.api.book.FancyHoverEvent;
import pl.himc.api.api.book.FancyTextComponent;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.item.ItemNameUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

public class ItemsCommand extends PlayerCommand {

    public ItemsCommand() {
        super("itemy", null, "/g itemy", "items");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        FancyBook book = new FancyBook("book", player.getName());

        FancyHoverEvent pos = FancyHoverEvent.fancyHoverEvent(FancyTextComponent.fancyTextComponent("&a\u2714 &a&lPosiadasz przedmiot &a\u2714"));
        PluginConfig pluginConfiguration = PluginGuild.getPlugin().getPluginConfiguration();
        FancyBookPage page1 = new FancyBookPage();
        page1.addText(FancyTextComponent.fancyTextComponent("&7&lItemy na gildie"));
        if (player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.vip")) {
            page1.addText(FancyTextComponent.fancyTextComponent(" &a-50%"));
            for (ItemStack item : pluginConfiguration.createGuildItemsVip) {
                ItemStack is = item.clone();
                int inEq = ItemsUtil.getAmountOfItem(is.getType(), player, is.getDurability());
                if (inEq >= is.getAmount()) {
                    page1.addText(FancyTextComponent.fancyTextComponent("\n\u2714 " + ItemNameUtil.getName(is.getType()) + " x" + is.getAmount(), "green", pos));
                } else {
                    FancyHoverEvent brak = FancyHoverEvent.fancyHoverEvent(FancyTextComponent.fancyTextComponent("&c\u2716 &c&lNie posidasz przedmiotu &c\u2716"));
                    page1.addText(FancyTextComponent.fancyTextComponent("\n\u2716 " + ItemNameUtil.getName(is.getType()) + " x" + is.getAmount(), "red", brak));
                    brak.addText(FancyTextComponent.fancyTextComponent("\n&cPotrzebujesz jeszcze: " + (is.getAmount() - inEq) + " szt."));
                }
            }

        } else {
            for (ItemStack item : pluginConfiguration.createGuildItemsPlayer) {
                ItemStack is = item.clone();
                int inEq = ItemsUtil.getAmountOfItem(is.getType(), player, is.getDurability());
                if (inEq >= is.getAmount()) {
                    page1.addText(FancyTextComponent.fancyTextComponent("\n\u2714 " + ItemNameUtil.getName(is.getType()) + " x" + is.getAmount(), "green", pos));
                } else {
                    FancyHoverEvent brak = FancyHoverEvent.fancyHoverEvent(FancyTextComponent.fancyTextComponent("&c\u2716 &c&lNie posidasz przedmiotu &c\u2716"));
                    page1.addText(FancyTextComponent.fancyTextComponent("\n\u2716 " + ItemNameUtil.getName(is.getType()) + " x" + is.getAmount(), "red", brak));
                    brak.addText(FancyTextComponent.fancyTextComponent("\n&cPotrzebujesz jeszcze: " + (is.getAmount() - inEq) + " szt."));
                }
            }
        }

        book.addPage(page1);
        return false;
    }
}
