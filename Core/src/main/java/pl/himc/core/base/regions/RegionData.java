package pl.himc.core.base.regions;

import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class RegionData {

    private final List<Region> regions;
    private final CorePlugin plugin;
    private final File file;
    private final YamlConfiguration yaml;

    public RegionData(final CorePlugin plugin){
        this.regions = new ArrayList<>();
        this.plugin = plugin;
        this.file = new File(PluginCore.getCore().getDataFolder(), "regions.yml");
        this.yaml = YamlConfiguration.loadConfiguration(this.file);
    }

    public List<Region> getRegions() {
        return this.regions;
    }

    public Region getRegion(final String name) {
        return this.regions.stream().filter(region -> region.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Region getFirstRegion(final Location location){
        return this.plugin.getRegionData().getRegions().stream().filter(reg -> reg.isLocationInRegion(location)).findFirst().orElse(null);
    }
    public Region getFirstRegionFix(final Location location){
        return this.plugin.getRegionData().getRegions().stream().filter(reg -> reg.isLocationInRegionFix(location)).findFirst().orElse(null);
    }

    public void addRegion(final Region region, boolean save){
        this.regions.add(region);
        if(save) this.saveRegionToFile(region);
    }

    public void deleteRegion(final Region region, boolean save){
        this.regions.remove(region);
        if(save) this.removeRegionFromFile(region);
    }

    public void loadRegions(){
        int i = 0;
        for (final String key : this.yaml.getKeys(false)) {
            final String world = this.yaml.getString(key + ".world");
            if (Bukkit.getWorld(world) != null) {
                final Location pos1 = this.yaml.getVector(key + ".pos1").toLocation(Bukkit.getWorld(world));
                final Location pos2 = this.yaml.getVector(key + ".pos2").toLocation(Bukkit.getWorld(world));
                final Region ar = new Region(key, pos1, pos2);
                ar.flags = RegionFlags.stringListToFlagList(this.yaml.getStringList(key + ".flags"));
                this.addRegion(ar, false);
                i++;
            }else{
                this.plugin.getConsoleSender().error("Mapa o nazwie " + world + " nie istnieje i region nie zostanie zaladowany!");
            }
        }
        this.plugin.getConsoleSender().database("Zaladowano " + i + " regionow!");
    }

    public void removeRegionFromFile(final Region region){
        this.yaml.set(region.getName(), null);
        try{
            this.yaml.save(this.file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        this.plugin.getConsoleSender().database("Region " + region.getName() + " zostal usuniety!");
    }

    public void saveRegionToFile(final Region region){
        this.yaml.set(region.getName() + ".pos1", region.pointOne.toVector());
        this.yaml.set(region.getName() + ".pos2", region.pointTwo.toVector());
        this.yaml.set(region.getName() + ".world", region.pointOne.getWorld().getName());
        this.yaml.set(region.getName() + ".flags", RegionFlags.flagsListToStringList(region.flags));
        try{
            this.yaml.save(this.file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        this.plugin.getConsoleSender().database("Region " + region.getName() + " zostal zapisany!");
    }
    public void saveRegionFlagsToFile(final Region region){
        this.yaml.set(region.getName() + ".flags", RegionFlags.flagsListToStringList(region.flags));
        try{
            this.yaml.save(this.file);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
}
