package pl.himc.api.utils.bukkit;

import pl.himc.api.api.PluginApi;
import org.bukkit.Material;

import static org.bukkit.Material.matchMaterial;

public final class MaterialUtils {

    public static Material parseMaterial(String string, boolean allowNullReturn) {
        if (string == null) {
            PluginApi.getApi().getConsoleSender().error("Unknown material: null");
            return allowNullReturn ? null : Material.AIR;
        }

        String materialName = string.toUpperCase().replaceAll(" ", "_");
        Material material = matchMaterial(materialName);

        if (material == null) {
            PluginApi.getApi().getConsoleSender().error("Unknown material: " + string);
            return allowNullReturn ? null : Material.AIR;
        }

        return material;
    }

}
