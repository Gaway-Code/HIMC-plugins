package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.AntyReklamaManager;
import pl.himc.core.manager.SocialSpyManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class MsgCommand extends PlayerCommand {

    public MsgCommand(String permission) {
        super("msg", permission, "/msg <Gracz> <Wiadomosc>", "m");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 2){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        Player p2 = Bukkit.getPlayer(args[0]);
        if(p2 == null){
            return ChatUtil.sendMessage(p, messageConfiguration.playerIsOffline);
        }
        User received = PluginCore.getCore().getUserManager().getUser(args[0]);
        if(received == null){
            return ChatUtil.sendMessage(p, messageConfiguration.playerIsNotExists);
        }
        if(!received.isEnableMsg()){
            return ChatUtil.sendMessage(p, messageConfiguration.msgPlayerHasDisable);
        }
        User sender = PluginCore.getCore().getUserManager().getUser(p);
        sender.setLastMsg(p2);
        received.setLastMsg(p);
        String message = StringUtil.newStringBuilder(args, 1);
        if(!AntyReklamaManager.checkMsg(p, message)){
            return false;
        }
        SocialSpyManager.send(p, p2, message);
        ChatUtil.sendMessage(p, messageConfiguration.msgFormat
                .replace("{PLAYER1}", "Ja")
                .replace("{PLAYER2}", p2.getName())
                .replace("{MESSAGE}", message));
        return ChatUtil.sendMessage(p2, messageConfiguration.msgFormat
                .replace("{PLAYER1}", sender.getName())
                .replace("{PLAYER2}", "Ja")
                .replace("{MESSAGE}", message));
    }
}
