package pl.himc.guilds.command.player.guild;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.guilds.util.SpaceUtil;
import pl.himc.api.api.command.PlayerCommand;

public class RoomCommand extends PlayerCommand {

    public RoomCommand(String permission) {
        super("createroom", permission, "");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.createRoomGuildNew(player.getLocation());
        return false;
    }

    private void createRoomGuildNew(final Location location) {
        final Location c = location.clone();
        c.setY(38.0);
        for (final Location loc : SpaceUtil.getSquare(c, 4, 6)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.COBBLESTONE); // podloga
        }
        for (final Location loc : SpaceUtil.getCorners(c, 1, 0)) {
            loc.getBlock().setType(Material.GLOWSTONE); // podloga
        }
        for (final Location loc : SpaceUtil.getCorners(c, 3, 6)) {
            loc.getBlock().setType(Material.LOG); // Slupki
        }
        c.setY(39.0);
        c.getBlock().setType(Material.ENDER_PORTAL_FRAME);
        c.setY(44.0);
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.COBBLESTONE); // sufit
        }
        for (final Location loc : SpaceUtil.getCorners(c, 3, 0)) {
            loc.getBlock().setType(Material.GLOWSTONE); // podloga
        }
    }

    private void createRoomGuild(final Location location) {

        //final Location c = g.getCenter().clone();
        final Location c = location.clone();
        c.setY(38.0);
        for (final Location loc : SpaceUtil.getSquare(c, 4, 5)) {
            loc.getBlock().setType(Material.AIR);
        }
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.OBSIDIAN); // podloga
        }
        for (final Location loc : SpaceUtil.getCorners(c, 2, 5)) {
            loc.getBlock().setType(Material.OBSIDIAN); // Slupki
        }
        c.setY(39.0);
        for (final Location loc : SpaceUtil.getSquare(c, 1, 0)) {
            loc.getBlock().setType(Material.OBSIDIAN); // podloga 2
        }
        c.getBlock().setType(Material.BEDROCK);
        c.setY(40.0);
        c.getBlock().setType(Material.ENDER_PORTAL_FRAME);
        c.setY(43.0);
        for (final Location loc : SpaceUtil.getSquare(c, 2, 0)) {
            loc.getBlock().setType(Material.OBSIDIAN); // sufit
        }
    }
}
