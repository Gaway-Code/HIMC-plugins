package pl.himc.core.base.kit;

import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.List;

public final class KitConfig {

    private static File kitFile;
    private static YamlConfiguration kitYaml;

    public static File getKitFile(){
        if(kitFile == null){
            kitFile = new File(PluginCore.getCore().getDataFolder() + "/kits.yml");
            if(!kitFile.exists()){
                PluginCore.getCore().saveResource("kits.yml", false);
            }
        }
        return kitFile;
    }

    public static YamlConfiguration getKitYaml(){
        if(kitYaml == null){
            kitYaml = YamlConfiguration.loadConfiguration(getKitFile());
        }
        return kitYaml;
    }

    public static void saveKitFile(){
        try{
            getKitYaml().save(getKitFile());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void loadKits() {
        for(String s : getKitYaml().getConfigurationSection("config.kits").getKeys(false)){
            if(s == null) return;
            int delay = getKitYaml().getInt("config.kits."+s+".delay");
            boolean enable = getKitYaml().getBoolean("config.kits."+s+".enable");
            ItemStack icon = new ItemStack(Material.matchMaterial(getKitYaml().getString("config.kits."+s+".item-in-gui")));
            String kitname = s;
            @SuppressWarnings("unchecked")
            List<ItemStack> items = (List<ItemStack>) getKitYaml().get("config.kits."+s+".items");

            new Kit(icon, kitname, items, delay, enable);
        }
    }
}
