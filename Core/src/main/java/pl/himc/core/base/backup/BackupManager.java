package pl.himc.core.base.backup;

import pl.himc.api.api.PluginApi;
import pl.himc.core.CorePlugin;
import org.bukkit.entity.Player;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public final class BackupManager {

    private final CorePlugin plugin;
    private final Map<String, Backup> backups;

    public BackupManager(final CorePlugin plugin){
        this.plugin = plugin;
        this.backups = new HashMap<>();
    }

    public Map<String, Backup> getBackups() {
        return backups;
    }

    public void createBackup(final Player player){
        Backup backup = this.get(player);
        backup.createBackup();
    }

    public Backup get(final Player player){
        return this.backups.computeIfAbsent(player.getName(), backup -> new Backup(player));
    }

    public Backup get(final String player){
        return this.backups.values().stream().filter(backup -> backup.getPlayerName().equalsIgnoreCase(player)).findFirst().orElse(null);
    }
    public void loadBackups(){
        String sb = "CREATE TABLE IF NOT EXISTS `" + this.plugin.getPluginConfig().database.tableBackups + "` (" +
                "name varchar(50) NOT NULL," +
                "createDate long NOT NULL," +
                "inventoryContents LONGTEXT NOT NULL," +
                "armorContents LONGTEXT NOT NULL," +
                "PRIMARY KEY(name));";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + this.plugin.getPluginConfig().database.tableBackups + "`", backup -> {
            int i = 0;
            try{
                while (backup.next()){
                    new Backup(backup);
                    i++;
                }
            }catch (SQLException exception){
                exception.printStackTrace();
            }
            this.plugin.getConsoleSender().database("Pobrano " + i + " backupow!");
        });
    }

    public void saveBackups(){
        int i = 0;
        for(Backup backup : this.backups.values()){
            if(!backup.isChanges()) continue;
            PluginApi.getApi().getDatabaseManager().executeUpdate(backup.getSQL());
            backup.setChanges(false);
            i++;
        }
        this.plugin.getConsoleSender().database("Zapisano " + i + " backupow!");
    }
}
