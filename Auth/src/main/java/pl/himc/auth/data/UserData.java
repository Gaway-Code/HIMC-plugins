package pl.himc.auth.data;

import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class UserData {

    private Map<String, User> users;
    private AuthPlugin plugin;

    public UserData(final AuthPlugin plugin){
        this.plugin = plugin;
        this.users = new HashMap<>();

    }

    public void createUser(final String playerName, final User user){
        this.users.put(playerName, user);
    }

    public int getUsersFromIP(final String ip){
        int i = 0;
        for(User user : users.values()){
            if(user.getIp() == null || user.getIp().equals("")) continue;
            if(user.getIp().equals(ip)) i++;
        }
        return i;
    }


    public Set<User> getUsersByIP(final String ip){
        Set<User> set = new HashSet<>();
        for(User user : users.values()){
            if(user.getIp().equals(ip)) set.add(user);
        }
        return set;
    }

    public User getOrCreate(final String player){
        return this.users.computeIfAbsent(player.toLowerCase(), user -> new User(player));
    }

    public User get(final String player){
        return this.users.get(player.toLowerCase());
    }

    public void deleteUser(final User user){
        this.plugin.getDatabase().executeUpdate("DELETE FROM `" + this.plugin.getConfiguration().databaseMySQLTable + "` WHERE playerName='" + user.getPlayerName() + "';");
        this.users.remove(user.getPlayerName());
    }

    public void loadUsers(){
        String table = "CREATE TABLE IF NOT EXISTS `" + this.plugin.getConfiguration().databaseMySQLTable + "` (" +
                "playerName varchar(20) NOT NULL," +
                "realPlayerName varchar(20) NOT NULL," +
                "ip varchar(50) NOT NULL," +
                "premium varchar(10) NOT NULL," +
                "password TEXT NOT NULL," +
                "PRIMARY KEY(playerName));";
        this.plugin.getDatabase().executeUpdate(table);

        this.plugin.getDatabase().executeQuery("SELECT * FROM `" + this.plugin.getConfiguration().databaseMySQLTable + "`", user -> {
            int i = 0;
            try{
                while(user.next()){
                    new User(user);
                    i++;
                }
            }catch (SQLException ex){
                ex.printStackTrace();
            }
            System.out.println("[memekAuth] Pobrano " + i + " kont graczy.");
        });
    }

    public void saveUsers(){
        int i = 0;
        for(User users : this.users.values()){
            if(!users.isChanges()) continue;
            this.plugin.getDatabase().executeUpdate(users.getInsert());
            users.setChanges(false);
            i++;
        }
        System.out.println("[memekAuth] Zapisano " + i + " kont graczy.");
    }

}
