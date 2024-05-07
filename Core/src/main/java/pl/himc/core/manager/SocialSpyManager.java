package pl.himc.core.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;

public final class SocialSpyManager {

    public static void send(Player p1, Player p2, String message){
        for(Player players : Bukkit.getOnlinePlayers()){
            User user = PluginCore.getCore().getUserManager().getUser(players);
            if(user.isEnableSocialspy()){
                ChatUtil.sendMessage(players, PluginCore.getCore().getPluginMessages().socialspyFormat
                        .replace("{PLAYER1}", p1.getName())
                        .replace("{PLAYER2}", p2.getName())
                        .replace("{MESSAGE}", message));
            }
        }
    }
}
