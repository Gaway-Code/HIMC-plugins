package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class DajYTCommand extends PlayerCommand {

    public DajYTCommand(String permission) {
        super("dajyt", permission,"/dajyt <yt, ytplus> <Gracz>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 2){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        if(args[0].equalsIgnoreCase("yt")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[1] + " parent set yt");
            return ChatUtil.sendBroadcast(messageConfiguration.newYoutuberBroadcast.replace("{PLAYER}", args[1]));
        }else if(args[0].equalsIgnoreCase("ytplus")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + args[1] + " parent set ytplus");
            return ChatUtil.sendBroadcast(messageConfiguration.newYoutuberPlusBroadcast.replace("{PLAYER}", args[1]));
        }else{
            return ChatUtil.sendUsage(player, this.getUsage());
        }
    }
}
