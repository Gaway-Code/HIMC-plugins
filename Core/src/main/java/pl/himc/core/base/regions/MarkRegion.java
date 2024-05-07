package pl.himc.core.base.regions;

import org.bukkit.Location;

public class MarkRegion {

    public Location pointOne;
    public Location pointTwo;

    MarkRegion(final Location p1, final Location p2) {
        this.pointOne = p1;
        this.pointTwo = p2;
    }
}
