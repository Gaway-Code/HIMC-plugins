package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class TpacceptCommand extends PlayerCommand {

    public TpacceptCommand(String permission) {
        super("tpaccept", permission, "/tpaccept <Gracz>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        User u = PluginCore.getCore().getUserManager().getUser(p);
        if(u.getTpa().isEmpty()){
            return ChatUtil.sendMessage(p, messageConfiguration.tpacceptNull);
        }
        Player p2 = Bukkit.getPlayer(args[0]);
        if(p2 == null){
            return ChatUtil.sendMessage(p, messageConfiguration.playerIsOffline);
        }
        if(!u.getTpa().contains(p2)){
            return ChatUtil.sendMessage(p, messageConfiguration.tpacceptPlayerNull);
        }
        if(u.getTpa().contains(p2)){
            u.getTpa().remove(p2);
            ChatUtil.sendMessage(p, messageConfiguration.tpacceptAcceptToPlayerInvited.replace("{PLAYER}", args[0]));
            ChatUtil.sendMessage(p2, messageConfiguration.tpacceptAcceptToPlayerSender.replace("{PLAYER}", p.getName()));
            PluginApi.getApi().getTeleport().sendTeleport(p2, p.getLocation(), pluginConfiguration.tpTimeTpa);
        }
        return false;
    }
}
