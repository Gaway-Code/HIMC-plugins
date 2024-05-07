package pl.himc.core.base.shop;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.StringUtil;
import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShopData {

    private final File file;
    private final YamlConfiguration yamlConfiguration;
    private final List<ShopSell> shopSellList;
    private final List<ShopBazar> shopBazars;

    public ShopData(final CorePlugin plugin){
        this.shopSellList = new ArrayList<>();
        this.shopBazars = new ArrayList<>();
        this.file = new File(plugin.getDataFolder(), "shop.yml");
        if(!file.exists()) plugin.saveResource("shop.yml", false);
        this.yamlConfiguration = YamlConfiguration.loadConfiguration(this.file);
    }

    public List<ShopSell> getShopSellList() {
        return shopSellList;
    }
    public List<ShopBazar> getShopBazars(){
        return this.shopBazars;
    }

    public YamlConfiguration getYamlConfiguration() {
        return yamlConfiguration;
    }

    public ShopSell get(final String name){
        return this.shopSellList.stream().filter(shop -> shop.getItemName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void addSell(final ShopSell sell){
        this.shopSellList.add(sell);
        this.yamlConfiguration.set("itemsSell." + sell.getItemName() + ".item", StringUtil.itemStackArrayToBase64(new ItemStack[]{sell.getItemstack()}));
        this.yamlConfiguration.set("itemsSell." + sell.getItemName() + ".price", sell.getPrice());
        this.saveFile();
    }
    public void removeSell(final ShopSell sell){
        this.shopSellList.remove(sell);
        this.yamlConfiguration.set("itemsSell." + sell.getItemName(), null);
        this.saveFile();
    }

    public void addBazar(final ShopBazar shopBazar){
        this.shopBazars.add(shopBazar);
    }

    public void removeBazar(final ShopBazar shopBazar){
        PluginApi.getApi().getDatabaseManager().executeUpdate("DELETE FROM `" +PluginCore.getCore().getPluginConfig().database.tableBazar + "` WHERE ITEM='" + shopBazar.getItemstackHash() + "' AND PRICE='" + shopBazar.getPrice() + "' AND PLAYER='" + shopBazar.getPlayerOffer() + "';");
        this.shopBazars.remove(shopBazar);
    }

    public Long getAmountOffer(final Player player){
        return this.shopBazars.stream().filter(bazar -> bazar.getPlayerOffer().equalsIgnoreCase(player.getName())).count();
    }

    public void loadShop(){
        ConfigurationSection cs1 = this.yamlConfiguration.getConfigurationSection("itemsSell");
        for(String s : cs1.getKeys(false)) {
            ConfigurationSection cs = cs1.getConfigurationSection(s);
            final ItemStack is = Arrays.stream(StringUtil.itemStackArrayFromBase64(cs.getString("item"))).findFirst().get();
            if(is == null){
                PluginCore.getCore().getConsoleSender().error("Blad! Itemstack -> " + s);
                continue;
            }
            final int price = cs.getInt("price");
            this.shopSellList.add(new ShopSell(s, is, price));
        }
    }

    public void loadBazar(){
        String sb = "CREATE TABLE IF NOT EXISTS `" + PluginCore.getCore().getPluginConfig().database.tableBazar + "` (" +
                "PLAYER varchar(50) NOT NULL," +
                "ITEM LONGTEXT NOT NULL," +
                "PRICE INT NOT NULL);";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + PluginCore.getCore().getPluginConfig().database.tableBazar + "`", bazar -> {
            try{
                int i = 0;
                while (bazar.next()){
                    this.shopBazars.add(new ShopBazar(bazar));
                    i++;
                }
                PluginCore.getCore().getConsoleSender().database("Pobrano " + i + " bazarow.");
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        });
    }

    public void saveBazar(){
        int i = 0;
        for(ShopBazar bazar : this.shopBazars){
            if(!bazar.isChanges()) continue;
            PluginApi.getApi().getDatabaseManager().executeUpdate(bazar.getInsert());
            bazar.setChanges(false);
            i++;
        }
        PluginCore.getCore().getConsoleSender().database("Zapisano " + i + " bazarow.");
    }
    public void saveFile(){
        try{
            this.yamlConfiguration.save(this.file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
