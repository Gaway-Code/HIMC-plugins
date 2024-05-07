package pl.himc.core.base.itemshop;

import org.bukkit.configuration.ConfigurationSection;
import pl.himc.core.CorePlugin;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public final class ItemshopData {

    private final CorePlugin plugin;
    private final List<ItemShop> itemshops;

    public ItemshopData(final CorePlugin plugin){
        this.plugin = plugin;
        this.itemshops = new ArrayList<>();
    }
    public void addShop(ItemShop shop){
        if(this.itemshops.contains(shop)) return;
        this.itemshops.add(shop);
    }

    public String getProducts(){
        return this.itemshops.stream().map(shop ->
                "" + shop.getName())
                .collect(joining(", "));
    }

    public List<ItemShop> itemShopsList(){
        return this.itemshops;
    }

    public ItemShop get(String produkt){
        return this.itemshops.stream()
                .filter(shop -> shop.getName().equalsIgnoreCase(produkt))
                .findAny()
                .orElse(null);
    }

    public void loadShop(){
        itemShopsList().clear();
        ConfigurationSection cs1 = this.plugin.getShopData().getYamlConfiguration().getConfigurationSection("itemShop");
        for(String s : cs1.getKeys(false)){
            ConfigurationSection cs = cs1.getConfigurationSection(s);
            new ItemShop(s, cs.getString("command"), cs.getStringList("broadcast"));
        }
    }
}
