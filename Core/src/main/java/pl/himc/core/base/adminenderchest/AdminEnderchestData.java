package pl.himc.core.base.adminenderchest;

import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;

import java.util.HashMap;
import java.util.Map;

public class AdminEnderchestData {

    private static Map<Player, AdminEnderchest> openedEnders = new HashMap<>();

    public static void open(Player admin, int ender, User player){
        openedEnders.remove(admin);

        AdminEnderchest openEc = new AdminEnderchest(admin, player, ender);
        openedEnders.put(admin, openEc);
        openEc.openEnderChest();
    }

    public static boolean close(Player admin){
        if(!openedEnders.containsKey(admin)) return false;
        AdminEnderchest ec = openedEnders.get(admin);
        ec.saveEnderChest();
        openedEnders.remove(admin);
        return true;
    }
    public static void removeOpened(Player p){
        openedEnders.remove(p);
    }
}
