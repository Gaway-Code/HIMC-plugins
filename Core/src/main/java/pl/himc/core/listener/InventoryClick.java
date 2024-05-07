package pl.himc.core.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.CorePlugin;
import pl.himc.core.base.shop.ShopBazar;
import pl.himc.core.command.player.SklepCommand;

public final class InventoryClick implements Listener {

    public InventoryClick(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final CorePlugin plugin;

    @EventHandler
    public void onClick(final InventoryClickEvent event){
        if(!event.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&aWystaw przedmiot na sprzedaz"))) return;
        if(event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR) return;
        if(event.getCurrentItem().getItemMeta() == null || event.getCurrentItem().getItemMeta().getDisplayName() == null) return;
        Player player = (Player) event.getWhoClicked();
        ItemMeta im = event.getCurrentItem().getItemMeta();

        ItemStack priceItem = event.getInventory().getItem(22);
        ItemMeta priceim = priceItem.getItemMeta();
        int price = Integer.parseInt(priceim.getDisplayName().replace(ChatUtil.fixColor("&aAktualna cena: &a$"), ""));

        if(im.getDisplayName().startsWith(ChatUtil.fixColor("&e+$"))){
            event.setCancelled(true);
            if(price >= 1000000){
                ChatUtil.sendMessage(player, "&c&oMaksymalna cena to $1000000");
                return;
            }
            price += Integer.parseInt(im.getDisplayName().replace(ChatUtil.fixColor("&e+$"), ""));
            priceim.setDisplayName(ChatUtil.fixColor("&aAktualna cena: &a$" + price));
            priceItem.setItemMeta(priceim);
            player.openInventory(event.getInventory());
        }

        if(im.getDisplayName().equalsIgnoreCase(ChatUtil.fixColor("&2Wystaw oferte!"))){
            event.setCancelled(true);
            ItemStack forSell = event.getInventory().getItem(13);
            if(forSell == null || forSell.getType() == Material.AIR){
                ChatUtil.sendMessage(player, "&c&oWrzuc przedmiot ktory chcesz sprzedac!");
                return;
            }
            if(price <= 0){
                ChatUtil.sendMessage(player, "&c&oMinimalna cena to $1");
                return;
            }
            if(this.plugin.getShopData().getAmountOffer(player) >= 5){
                ChatUtil.sendMessage(player, "&c&oMozesz wystawic maksymalnie 5 ofert!");
                return;
            }

            this.plugin.getShopData().addBazar(new ShopBazar(player.getName(), forSell, price));
            ChatUtil.sendMessage(player, "&a&oPomyslnie wystawiono przedmiot &e&o" + forSell.getType() + " &a&oza cene &e&o$" + price + "&a&o!");
            SklepCommand.getSklepCommand().openBazarOffer(player);
        }
        if(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE) || event.getCurrentItem().getType().equals(Material.GOLD_NUGGET)){
            event.setCancelled(true);
        }
    }
}
