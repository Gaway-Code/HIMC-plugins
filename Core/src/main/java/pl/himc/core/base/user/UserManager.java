package pl.himc.core.base.user;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.adminenderchest.AdminEnderchestData;
import pl.himc.core.command.admin.SprawdzCommand;
import pl.himc.core.manager.VanishManager;
import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public final class UserManager {

    private Map<String, User> users = new HashMap<>();

    public Collection<User> getUsers(){
        return this.users.values();
    }

    public Set<User> getOnlineUsers(){
        Set<User> set = new HashSet<>();
        this.getUsers().stream().filter(User::isOnline).forEach(set::add);
        return set;
    }

    public void addUser(String name, User user){
        if(getUser(name) != null) return;
        this.users.put(name, user);
    }

    public User getUser(Player player){
        return this.users.computeIfAbsent(player.getName(), user -> new User(player));
    }

    public User getUser(String name){
        return this.getUsers().stream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void playerQuit(Player player){
        VanishManager.quitVanished(player);
        AdminEnderchestData.removeOpened(player);
        SprawdzCommand.checkLogout(player);
    }

    public void loadUsers(){
        String sb = "CREATE TABLE IF NOT EXISTS `" + PluginCore.getCore().getPluginConfig().database.tableUsers + "` (" +
                "NAME varchar(50) NOT NULL," +
                "FAKENAME varchar(50) NOT NULL," +
                "KOXY INT NOT NULL," +
                "REFILE INT NOT NULL," +
                "PERLY INT NOT NULL," +
                "EATKOXY INT NOT NULL," +
                "EATREFILE INT NOT NULL," +
                "MONEY INT NOT NULL," +
                "BLOCKSNEXT INT NOT NULL," +
                "LVL INT NOT NULL," +
                "POINTSMINER INT NOT NULL," +
                "STONEBREAK INT NOT NULL," +
                "OBSIDIANBREAK INT NOT NULL," +
                "KITS LONGTEXT NOT NULL," +
                "HOMES LONGTEXT NOT NULL," +
                "SOCIALSPY varchar(10) NOT NULL," +
                "MSG varchar(10) NOT NULL," +
                "GOD varchar(10) NOT NULL," +
                "EFFECTMSG varchar(10) NOT NULL," +
                "DROPCOBBLE varchar(10) NOT NULL," +
                "DROPMSG varchar(10) NOT NULL," +
                "HELPOP LONG NOT NULL," +
                "CHAT LONG NOT NULL," +
                "TURBODROP LONG NOT NULL," +
                "TURBOEXP LONG NOT NULL," +
                "ENDERCHEST1 LONGTEXT NOT NULL," +
                "ENDERCHEST2 LONGTEXT NOT NULL," +
                "ENDERCHEST3 LONGTEXT NOT NULL," +
                "PRIMARY KEY(NAME));";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + PluginCore.getCore().getPluginConfig().database.tableUsers + "`", user -> {
            int i = 0;
            try{
                while (user.next()){
                    new User(user);
                    i++;
                }
            }catch (SQLException | IOException e){
                e.printStackTrace();
            }
            PluginCore.getCore().getConsoleSender().database("Pobrano " + i + " graczy.");
        });
    }

    public void saveUsers(){
        int i = 0;
        for(User user : this.getUsers()){
            if(!user.isChanges()) continue;
            PluginApi.getApi().getDatabaseManager().executeUpdate(user.getInsert());
            user.setChanges(false);
            i++;
        }
        PluginCore.getCore().getConsoleSender().database("Zapisano " + i + " graczy.");
    }
}
