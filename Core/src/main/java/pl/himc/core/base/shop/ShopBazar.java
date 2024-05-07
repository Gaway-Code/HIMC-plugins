package pl.himc.core.base.shop;

import org.bukkit.inventory.ItemStack;
import pl.himc.api.store.Entry;
import pl.himc.api.utils.StringUtil;
import pl.himc.core.api.PluginCore;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class ShopBazar extends Entry {

    private final String playerOffer;
    private final ItemStack itemstack;
    private final String itemstackHash;
    private final int price;

    public ShopBazar(final String playerOffer, final ItemStack is, int price){
        this.playerOffer = playerOffer;
        this.itemstack = is;
        this.itemstackHash = StringUtil.itemStackArrayToBase64(new ItemStack[]{is});
        this.price = price;
        this.changes();
    }

    public ShopBazar(final ResultSet rs) throws SQLException {
        this.playerOffer = rs.getString("PLAYER");
        this.itemstackHash = rs.getString("ITEM");
        this.itemstack = Arrays.stream(StringUtil.itemStackArrayFromBase64(this.itemstackHash)).findFirst().get();
        this.price = rs.getInt("PRICE");
        this.setChanges(false);
    }

    public String getInsert(){
        String s = "INSERT INTO `" + PluginCore.getCore().getPluginConfig().database.tableBazar + "` VALUES(" +
                "'%player%'," +
                "'%item%'," +
                "'%price%'" +
                ") ON DUPLICATE KEY UPDATE " +
                "PLAYER='%player%'," +
                "ITEM='%item%'," +
                "PRICE='%price%';";
        s = s.replace("%player%", this.playerOffer);
        s = s.replace("%item%", this.itemstackHash);
        s = s.replace("%price%", Integer.toString(this.price));
        return s;
    }

    public String getPlayerOffer() {
        return playerOffer;
    }

    public ItemStack getItemstack() {
        return itemstack;
    }
    public String getItemstackHash() {
        return itemstackHash;
    }
    public int getPrice() {
        return price;
    }
}
