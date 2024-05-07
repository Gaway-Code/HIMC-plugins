package pl.himc.guilds.base.guild.cuboid;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.guilds.util.SpaceUtil;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

public class Cuboid {

    private Guild guild;
    private Location center;
    private int size;
    private int x1;
    private int x2;
    private int z1;
    private int z2;
    private Location holographic;

    public Cuboid(Guild guild, Location center, int size) {
        (this.guild = guild).setCuboid(this);
        this.center = center;
        this.size = size;
        this.calc();

        this.holographic = center.clone();
        this.holographic.setY(center.getBlockY() + 4);
    }

    public Guild getGuild() {
        return this.guild;
    }

    public Location getCenter() {
        return this.center;
    }

    public int getSize() {
        return this.size;
    }

    public int getX1() {
        return this.x1;
    }

    public int getX2() {
        return this.x2;
    }

    public int getZ1() {
        return this.z1;
    }

    public int getZ2() {
        return this.z2;
    }

    public Location getHolographicLocation() {
        return this.holographic;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public void setSize(int size) {
        this.size = size;
        this.guild.changes();
    }

    public void addSize(int add) {
        this.size += add;
        this.guild.changes();
    }

    public void calc() {
        this.x1 = this.center.getBlockX() + this.size;
        this.x2 = this.center.getBlockX() - this.size;
        this.z1 = this.center.getBlockZ() + this.size;
        this.z2 = this.center.getBlockZ() - this.size;
    }

    public boolean isIn(Location loc) {
        return loc.getWorld().equals(this.center.getWorld()) && loc.getBlockX() <= this.x1 && loc.getBlockX() >= this.x2 && loc.getBlockZ() <= this.z1 && loc.getBlockZ() >= this.z2;
    }

    public boolean isInCentrum(Location loc, int top, int down, int wall) {
        Location c = this.center;
        return c.getBlockY() - down <= loc.getBlockY() && c.getBlockY() + top >= loc.getBlockY() && loc.getBlockX() <= c.getBlockX() + wall && loc.getBlockX() >= c.getBlockX() - wall && loc.getBlockZ() <= c.getBlockZ() + wall && loc.getBlockZ() >= c.getBlockZ() - wall;
    }

    public void enlargeCuboid(Player player) {
        final PluginConfig pluginConfiguration = PluginGuild.getPlugin().getPluginConfiguration();
        final PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();

        if (this.size >= pluginConfiguration.guildMaxRegionSize) {
            ChatUtil.sendMessage(player, messageConfiguration.regionMaxSize.replace("{LENGHT}", Integer.toString(pluginConfiguration.guildMaxRegionSize)));
            return;
        }
        if (!ItemsUtil.hasItems(player, pluginConfiguration.guildEnlargeItems)) {
            ChatUtil.sendMessage(player, StringUtil.replaceItems(pluginConfiguration.guildEnlargeItems));
            return;
        }
        ItemsUtil.removeItems(player, pluginConfiguration.guildEnlargeItems);
        this.addSize(pluginConfiguration.guildEnlargeRegionBlocks);
        this.calc();
        this.guild.changes();

        ChatUtil.sendMessage(player, messageConfiguration.regionEnlargeSuccess.replace("{LENGHT}", Integer.toString(this.size)));
    }

    public void createChest() {
        Location loc1 = this.center;
        loc1.getBlock().setType(Material.CHEST);
        loc1.getBlock().getRelative(BlockFace.NORTH).setType(Material.CHEST);
        Chest c1 = (Chest) loc1.getBlock().getState();
        c1.getInventory().setContents(this.guild.getTreasure().getContents());
    }

    public void createRoom() {
        final Location c = this.center.clone();
        c.setY(this.center.getY() - 1);
        for (final Location loc : SpaceUtil.getSquare(c, 4, 7)) {
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
        c.setY(this.center.getY());
        c.getBlock().setType(Material.ENDER_PORTAL_FRAME);
        c.setY(this.center.getY() + 6);
        for (final Location loc : SpaceUtil.getSquare(c, 3, 0)) {
            loc.getBlock().setType(Material.COBBLESTONE); // sufit
        }
        for (final Location loc : SpaceUtil.getCorners(c, 3, 0)) {
            loc.getBlock().setType(Material.GLOWSTONE); // podloga
        }
    }
}
