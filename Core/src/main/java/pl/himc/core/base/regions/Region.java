package pl.himc.core.base.regions;

import pl.himc.core.api.PluginCore;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public final class Region extends MarkRegion {

    public String name;
    public List<RegionFlags.Flag> flags;

    Region(final String regionName, final Location p1, final Location p2) {
        super(p1, p2);
        this.flags = new ArrayList<>();
        this.name = regionName;
    }

    public boolean isLocationInRegion(final Location l) {
        int x1 = (int)this.pointOne.getX();
        int z1 = (int)this.pointOne.getZ();
        int x2 = (int)this.pointTwo.getX();
        int z2 = (int)this.pointTwo.getZ();
        int miny = -999;
        int maxy = 999;
        int minz = Math.min(z1, z2) - 1;
        int maxz = Math.max(z1, z2) + 1;
        int minx = Math.min(x1, x2) - 1;
        int maxx = Math.max(x1, x2) + 1;
        return l.getWorld().getName().equalsIgnoreCase(this.pointOne.getWorld().getName()) && l.getY() > miny && l.getY() < maxy && l.getZ() > minz && l.getZ() < maxz && l.getX() > minx && l.getX() < maxx;
    }

    public boolean isLocationInRegionFix(final Location l) {
        int x1 = (int)this.pointOne.getX();
        int z1 = (int)this.pointOne.getZ();
        int x2 = (int)this.pointTwo.getX();
        int z2 = (int)this.pointTwo.getZ();
        int miny = -999;
        int maxy = 999;
        int minz = Math.min(z1, z2);
        int maxz = Math.max(z1, z2);
        int minx = Math.min(x1, x2);
        int maxx = Math.max(x1, x2);
        return l.getWorld().getName().equalsIgnoreCase(this.pointOne.getWorld().getName()) && l.getY() > miny && l.getY() < maxy && l.getZ() > minz && l.getZ() < maxz && l.getX() > minx && l.getX() < maxx;
    }

    public String getName() {
        return name;
    }

    public void toggleFlag(RegionFlags.Flag flag){
        if(this.flags.contains(flag)){
            this.flags.remove(flag);
        }else{
            this.flags.add(flag);
        }
        PluginCore.getCore().getRegionData().saveRegionFlagsToFile(this);
    }

    public boolean isFlagEnable(RegionFlags.Flag flag){
        return this.flags.contains(flag);
    }
}
