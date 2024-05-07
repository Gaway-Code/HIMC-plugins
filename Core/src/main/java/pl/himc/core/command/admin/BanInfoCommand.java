package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.base.ban.Ban;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class BanInfoCommand extends CommandAPI {

    public BanInfoCommand(String permission) {
        super("baninfo", permission, "/baninfo <Gracz>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1 || args.length > 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        if(PluginCore.getCore().getBanManager().getBan(args[0]) == null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsNotBanned);
        }
        Ban ban = PluginCore.getCore().getBanManager().getBan(args[0]);
        ChatUtil.sendMessage(s, "&8%> &7Nick: &d" + ban.getName());
        if(s.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".ban.checkip")){
            ChatUtil.sendMessage(s, "&8%> &7Adres IP: &d" + ban.getIP());
        }
        ChatUtil.sendMessage(s, "&8%> &7Administrator banujacy: &d" + ban.getAdmin());
        ChatUtil.sendMessage(s, ban.isPerm() ? "&8%> &7Ban jest &4&lNA ZAWSZE" : "&8%> &7Ban wygasa: &d" + TimeUtil.getDate(ban.getTime()) + " &8(&d" + TimeUtil.getDuration(ban.getTime() - System.currentTimeMillis()) + "&8)");
        ChatUtil.sendMessage(s, "&8%> &7Powod: &d" + ban.getReason());
        return ChatUtil.sendMessage(s, "&8%> &7Data nadania bana: &d" + TimeUtil.getDate(ban.getData()));
    }
}
