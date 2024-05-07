package pl.himc.core.base.backup;

import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Backup {
    
    private final String playerName;
    private long createDate;
    private ItemStack[] contents;
    private ItemStack[] armorContents;
    private boolean changes;
    
    public Backup(final Player player){
        this.playerName = player.getName();
        this.createDate = System.currentTimeMillis();
    }

    public Backup(final ResultSet rs) throws SQLException {
        this.playerName = rs.getString("name");
        this.createDate = rs.getLong("createDate");
        this.contents = StringUtil.itemStackArrayFromBase64(rs.getString("inventoryContents"));
        this.armorContents = StringUtil.itemStackArrayFromBase64(rs.getString("armorContents"));
        this.changes = false;
        PluginCore.getCore().backupManager().getBackups().put(this.playerName, this);
    }

    public String getSQL(){
        String s = "INSERT INTO `" + PluginCore.getCore().getPluginConfig().database.tableBackups + "` VALUES(" +
                "'%name%'," +
                "'%createdate%'," +
                "'%contents%'," +
                "'%armorcontents%'" +
                ") ON DUPLICATE KEY UPDATE " +
                "createDate='%createdate%'," +
                "inventoryContents='%contents%'," +
                "armorContents='%armorcontents%';";
        s = s.replace("%name%", this.playerName);
        s = s.replace("%createdate%", this.createDate + "");
        s = s.replace("%contents%", StringUtil.itemStackArrayToBase64(this.contents));
        s = s.replace("%armorcontents%", StringUtil.itemStackArrayToBase64(this.armorContents));
        return s;
    }

    public String getPlayerName(){
        return this.playerName;
    }
    public boolean isChanges() {
        return changes;
    }
    public void setChanges(boolean changes){
        this.changes = changes;
    }
    public void createBackup(){
        this.createDate = System.currentTimeMillis();
        Player player = Bukkit.getPlayer(this.playerName);
        this.contents = StringUtil.cloneItemStacks(player.getInventory().getContents());
        this.armorContents = StringUtil.cloneItemStacks(player.getInventory().getArmorContents());
        this.changes = true;
    }

    public ItemStack[] getArmorContents() {
        return armorContents;
    }

    public ItemStack[] getContents() {
        return contents;
    }

    public void restoreBackup(final Player admin){
        Player player = Bukkit.getPlayer(this.playerName);
        if(!player.isOnline()){
            ChatUtil.sendMessage(admin, PluginCore.getCore().getPluginMessages().playerIsOffline);
            return;
        }
        player.getInventory().setContents(this.contents);
        player.getInventory().setArmorContents(this.armorContents);
        ChatUtil.sendMessage(admin, PluginCore.getCore().getPluginMessages().backupRestore);
        TitleAPI.sendTitle(player, PluginCore.getCore().getPluginMessages().titleBackup, PluginCore.getCore().getPluginMessages().subTitleBackup, 3, 40);
    }
}
