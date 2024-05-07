package pl.himc.core.base.craft.stoniarka;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.api.store.Entry;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Stoniarka extends Entry {

    private final String place$player;
    private final long data;
    private final Location location;

    public Stoniarka(Player player, Location location){
        this.place$player = player.getName();
        this.data = System.currentTimeMillis();
        this.location = location;
        this.setChanges(true);

        StoniarkaData.addStoniarka(location, this);

        location.getBlock().setType(Material.STONE);
        if(player.isOnline()){
            ChatUtil.sendMessage(player, PluginCore.getCore().getPluginMessages().placeStoniarka);
        }
    }

    public Stoniarka(ResultSet rs) throws SQLException {
        this.place$player = rs.getString("PLAYER");
        this.data = rs.getLong("CREATETIME");
        this.location = StringUtil.locationFromString(rs.getString("LOCATION"));
        this.setChanges(false);
        StoniarkaData.addStoniarka(StringUtil.locationFromString(rs.getString("LOCATION")), this);
        this.respawnGenerator();
    }

    public String getName() {
        return place$player;
    }
    public Location getLocation(){
        return this.location;
    }

    public String getInsert() {
        return "INSERT INTO `" + PluginCore.getCore().getPluginConfig().database.tableGenerators + "` VALUES (" +
                "'" + this.place$player + "'," +
                "'" + this.data + "'," +
                "'" + StringUtil.locationLongToString(this.location) + "')" +
                " ON DUPLICATE KEY UPDATE " +
                "PLAYER='" + this.place$player + "'," +
                "CREATETIME='" + this.data + "'," +
                "LOCATION='" + StringUtil.locationLongToString(this.location) + "';";
    }

    public void respawnGenerator(){
        Bukkit.getScheduler().runTaskLaterAsynchronously(PluginCore.getCore(), () -> Bukkit.getScheduler().runTask(PluginCore.getCore(), () -> this.location.getBlock().setType(Material.STONE)), 2 * 20L);
    }

    public void removeGenerator(){
        this.setChanges(false);
        StoniarkaData.addToRemove("DELETE FROM `" + PluginCore.getCore().getPluginConfig().database.tableGenerators + "` WHERE LOCATION='" + StringUtil.locationLongToString(this.location) + "' AND CREATETIME='" + this.data + "' AND PLAYER='" + this.place$player + "'");
        StoniarkaData.getStoniarki().remove(this);
    }
}
