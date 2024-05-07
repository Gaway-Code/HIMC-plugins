package pl.himc.core.base.adminenderchest;

import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;

public class AdminEnderchest {

    private Player admin;
    private User user;
    private int ender;

    public AdminEnderchest(Player admin, User user, int ender){
        this.admin = admin;
        this.user = user;
        this.ender = ender;
    }

    public void openEnderChest(){
        this.admin.openInventory(this.user.getEnderchest(this.ender));
    }

    public void saveEnderChest(){
        this.user.changes();
    }
}
