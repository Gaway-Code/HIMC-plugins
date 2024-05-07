package pl.himc.core.base.craft.stoniarka;

import pl.himc.core.api.PluginCore;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

import java.sql.SQLException;
import java.util.*;

public final class StoniarkaData {

    private static final ItemBuilder stoniarka = new ItemBuilder(Material.STONE).addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &7&lGENERATOR KAMIENIA &8*").setLore(Arrays.asList(
            "&7",
            " &8%> &7Postaw na ziemi.",
            " &8%> &7Kamien zacznie generowac sie co &a&n2 sekundy&7.",
            " &8%> &7Generator mozna zniszczyc &e&nzlotym kilofem&7!",
            "&7"));
    private static final Map<Location, Stoniarka> stoniarki = new HashMap<>();
    private static final List<String> stoniarkaToRemove = new ArrayList<>();

    public static ItemStack getStoniarka(){
        return stoniarka.getItem();
    }

    public static void addToRemove(String update){
        stoniarkaToRemove.add(update);
    }

    public static Collection<Stoniarka> getStoniarki(){
        return stoniarki.values();
    }

    public static void addStoniarka(Location loc, Stoniarka stoniarka){
        if(stoniarki.containsValue(stoniarka)){
           return;
        }
        stoniarki.put(loc, stoniarka);
    }

    public static void loadGenerators(){
        String sb = "CREATE TABLE IF NOT EXISTS `" + PluginCore.getCore().getPluginConfig().database.tableGenerators + "` (" +
                "PLAYER varchar(50) NOT NULL," +
                "CREATETIME long NOT NULL," +
                "LOCATION longtext NOT NULL);";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + PluginCore.getCore().getPluginConfig().database.tableGenerators + "`", generator -> {
            int i = 0;
            try{
                while (generator.next()){
                    new Stoniarka(generator);
                    i++;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
            PluginCore.getCore().getConsoleSender().database("Pobrano " + i + " generatorow.");
        });
        for(Stoniarka stoniarki : stoniarki.values()){
            stoniarki.getLocation().getBlock().setType(Material.STONE);
        }
    }

    public static void saveGenerators(){
        if(!stoniarkaToRemove.isEmpty()){
            stoniarkaToRemove.forEach(update -> PluginApi.getApi().getDatabaseManager().executeUpdate(update));
            stoniarkaToRemove.clear();
        }
        int i = 0;
        for(Stoniarka stoniarka : getStoniarki()){
            if(!stoniarka.isChanges()) continue;
            PluginApi.getApi().getDatabaseManager().executeUpdate(stoniarka.getInsert());
            stoniarka.setChanges(false);
            i++;
        }
        PluginCore.getCore().getConsoleSender().database("Zapisano " + i + " stoniarek.");
    }

    public static Stoniarka getGenerator(Location loc) {
        return stoniarki.get(loc);
    }
}
