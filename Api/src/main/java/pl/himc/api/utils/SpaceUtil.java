package pl.himc.api.utils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public final class SpaceUtil {

    public static List<Location> getSquare(Location center, int radius) {
        List<Location> locs = new ArrayList<>();
        int cX = center.getBlockX();
        int cZ = center.getBlockZ();
        int minX = Math.min(cX + radius, cX - radius);
        int maxX = Math.max(cX + radius, cX - radius);
        int minZ = Math.min(cZ + radius, cZ - radius);
        int maxZ = Math.max(cZ + radius, cZ - radius);
        for (int x = minX; x <= maxX; ++x) {
            for (int z = minZ; z <= maxZ; ++z) {
                locs.add(new Location(center.getWorld(), x, center.getBlockY(), z));
            }
        }
        locs.add(center);
        return locs;
    }

    public static List<Location> getWalls(Location center, int radius) {
        List<Location> locs = getSquare(center, radius);
        locs.removeAll(getSquare(center, radius - 1));
        return locs;
    }
    
    public static List<Location> getWalls(Location center, int radius, int height) {
        List<Location> locs = getWalls(center, radius);
        for (int i = 1; i <= height; ++i) {
            locs.addAll(getWalls(new Location(center.getWorld(), center.getBlockX(), (center.getBlockY() + i), center.getBlockZ()), radius));
        }
        return locs;
    }
    public static List<Location> sphere(Location loc, int radius, int height, boolean hollow, boolean sphere, int plusY) {
        List<Location> circleBlocks = new ArrayList<>();
        int explosionX = loc.getBlockX();
        int explosionY = loc.getBlockY();
        int explosionZ = loc.getBlockZ();

        for (int x = explosionX - radius; x <= explosionX + radius; x++) {
            for (int z = explosionZ - radius; z <= explosionZ + radius; z++) {
                for (int y = (sphere ? explosionY - radius : explosionY); y < (sphere ? explosionY + radius : explosionY + height); y++) {
                    double dist = (explosionX - x) * (explosionX - x) + (explosionZ - z) * (explosionZ - z) + (sphere ? (explosionY - y) * (explosionY - y) : 0);

                    if (dist < radius * radius && !(hollow && dist < (radius - 1) * (radius - 1))) {
                        Location l = new Location(loc.getWorld(), x, y + plusY, z);
                        circleBlocks.add(l);
                    }
                }
            }
        }

        return circleBlocks;
    }
}
