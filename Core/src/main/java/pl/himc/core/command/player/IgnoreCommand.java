package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class IgnoreCommand extends PlayerCommand {

    public IgnoreCommand(String permission) {
        super("ignore", permission, "/ignore");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        User u = PluginCore.getCore().getUserManager().getUser(p);
        u.toggleEnableMsg();
        if(u.isEnableMsg()){
            ChatUtil.sendMessage(p, messageConfiguration.msgEnable);
        }else{
            ChatUtil.sendMessage(p, messageConfiguration.msgDisable);
        }
        return false;
    }
}
