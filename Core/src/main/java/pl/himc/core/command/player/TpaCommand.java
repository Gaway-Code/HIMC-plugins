package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class TpaCommand extends PlayerCommand {

    public TpaCommand(String permission) {
        super("tpa", permission, "/tpa <Gracz>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1 || args.length > 1){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        Player p2 = Bukkit.getPlayer(args[0]);
        if(p2 == null){
            return ChatUtil.sendMessage(p, messageConfiguration.playerIsOffline);
        }
        if(p2 == p){
            return ChatUtil.sendMessage(p, messageConfiguration.tpaSamePlayer);
        }
        User u = PluginCore.getCore().getUserManager().getUser(p2);
        if(u == null){
            return ChatUtil.sendMessage(p, messageConfiguration.playerIsNotExists);
        }
        if(u.getTpa().contains(p)){
            return ChatUtil.sendMessage(p, messageConfiguration.tpaIsSendToThisPlayer);
        }
        u.getTpa().add(p);
        Bukkit.getScheduler().runTaskLaterAsynchronously(PluginCore.getCore(), () -> {
            if (u.getTpa().contains(p)) {
                u.getTpa().remove(p);
            }}, 60 * 20L);
        ChatUtil.sendMessage(p2, messageConfiguration.tpaSendToRecipient.replace("{PLAYER}", p.getName()));
        return ChatUtil.sendMessage(p, messageConfiguration.tpaSendSuccess.replace("{PLAYER}", args[0]));
    }
}
