package pl.himc.antikillaura.manager;

import org.bukkit.entity.Player;
import pl.himc.antikillaura.object.User;

import java.util.HashMap;
import java.util.Map;

public final class UserManager {

    private Map<String, User> users;

    public UserManager(){
        this.users = new HashMap<>();
    }

    public Map<String, User> getUsers() {
        return users;
    }

    public void addUser(final Player player, final User user){
        if(users.containsKey(player.getName())) return;
        users.put(player.getName(), user);
    }

    public User get(final Player player){
        return this.users.computeIfAbsent(player.getName(), user -> new User(player));
    }

    public void removeUser(final User user){
        users.remove(user.getName());
    }
}
