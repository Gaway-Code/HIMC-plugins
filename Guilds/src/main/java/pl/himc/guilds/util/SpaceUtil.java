package pl.himc.guilds.util;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class SpaceUtil {

    public static List<Location> getSquare(Location center, int radius) {
        List<Location> locs = new ArrayList<Location>();
        int cX = center.getBlockX();
        int cZ = center.getBlockZ();
        int minX = Math.min(cX + radius, cX - radius);
        int maxX = Math.max(cX + radius, cX - radius);
        int minZ = Math.min(cZ + radius, cZ - radius);
        int maxZ = Math.max(cZ + radius, cZ - radius);

        for (int x = minX; x <= maxX; x++)
            for (int z = minZ; z <= maxZ; z++)
                locs.add(new Location(center.getWorld(), x, center.getBlockY(), z));
        locs.add(center);
        return locs;
    }

    public static List<Location> getCorners(Location center, int radius) {
        List<Location> locs = new ArrayList<Location>();
        int cX = center.getBlockX();
        int cZ = center.getBlockZ();
        int minX = Math.min(cX + radius, cX - radius);
        int maxX = Math.max(cX + radius, cX - radius);
        int minZ = Math.min(cZ + radius, cZ - radius);
        int maxZ = Math.max(cZ + radius, cZ - radius);

        locs.add(new Location(center.getWorld(), minX, center.getBlockY(), minZ));
        locs.add(new Location(center.getWorld(), maxX, center.getBlockY(), minZ));
        locs.add(new Location(center.getWorld(), minX, center.getBlockY(), maxZ));
        locs.add(new Location(center.getWorld(), maxX, center.getBlockY(), maxZ));
        return locs;
    }

    public static List<Location> getSquare(Location center, int radius, int height) {
        List<Location> locs = getSquare(center, radius);
        for (int i = 1; i <= height; i++)
            locs.addAll(getSquare(new Location(center.getWorld(), center.getBlockX(), center.getBlockY() + i, center.getBlockZ()), radius));
        return locs;
    }

    public static List<Location> getCorners(Location center, int radius, int height) {
        List<Location> locs = getCorners(center, radius);
        for (int i = 1; i <= height; i++)
            locs.addAll(getCorners(new Location(center.getWorld(), center.getBlockX(), center.getBlockY() + i, center.getBlockZ()), radius));
        return locs;
    }

    public static boolean locationEqualsBlock(Location loc, Location loc1) {
        return loc.getBlockX() == loc1.getBlockX() && loc.getBlockY() == loc1.getBlockY() && loc.getBlockZ() == loc1.getBlockZ();
    }
}
