package pl.himc.core.base.shop;

import org.bukkit.inventory.ItemStack;

public class ShopSell {

    private String itemName;
    private ItemStack itemstack;
    private int price;

    public ShopSell(final String itemName, final ItemStack is, int price){
        this.itemName = itemName;
        this.itemstack = is;
        this.price = price;
    }

    public String getItemName() {
        return itemName;
    }

    public ItemStack getItemstack() {
        return itemstack;
    }

    public int getPrice() {
        return price;
    }
}
