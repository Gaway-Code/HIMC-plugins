package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.AntyReklamaManager;
import pl.himc.core.manager.SocialSpyManager;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class ReplyCommand extends PlayerCommand {

    public ReplyCommand(String permission) {
        super("reply", permission, "/reply <Wiadomosc>","r");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        User u = PluginCore.getCore().getUserManager().getUser(p);
        Player p2 = u.getLastMsg();
        if(p2 == null){
            return ChatUtil.sendMessage(p, messageConfiguration.replyPlayerOffile);
        }
        User u2 = PluginCore.getCore().getUserManager().getUser(p2);
        if(!u2.isEnableMsg()){
            return ChatUtil.sendMessage(p, messageConfiguration.msgPlayerHasDisable);
        }
        String message = StringUtil.newStringBuilder(args, 0);
        if(!AntyReklamaManager.checkMsg(p, message)){
            return false;
        }
        SocialSpyManager.send(p, p2, message);
        ChatUtil.sendMessage(p, messageConfiguration.msgFormat.replace("{PLAYER1}", "Ja").replace("{PLAYER2}", p2.getName()).replace("{MESSAGE}", message));
        return ChatUtil.sendMessage(p2, messageConfiguration.msgFormat.replace("{PLAYER1}", p.getName()).replace("{PLAYER2}", "Ja").replace("{MESSAGE}", message));
    }
}
