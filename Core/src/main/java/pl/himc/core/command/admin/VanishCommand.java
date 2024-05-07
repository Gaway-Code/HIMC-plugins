package pl.himc.core.command.admin;

import pl.himc.core.manager.VanishManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.CommandAPI;

public final class VanishCommand extends CommandAPI {

    public VanishCommand(String permission) {
        super("vanish", permission, "/vanish <Gracz>","v");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        Player p = (Player)s;
        if(VanishManager.isVanished(p)){
            VanishManager.showPlayer(p);
        }else{
            VanishManager.hidePlayer(p);
        }
        return false;
    }
}
