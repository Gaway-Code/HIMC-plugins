package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class BanCommand extends CommandAPI {

    public BanCommand(String permission) {
        super("ban", permission,"/ban <Gracz> <Powod>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        if(PluginCore.getCore().getBanManager().getBan(args[0]) != null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsAlreadyBanned.replace("{PLAYER}", args[0]));
        }
        String reason = "Zlamanie regulaminu";
        if(args.length >= 2){
            reason = StringUtil.stringBuilder(args, 1);
        }
        PluginCore.getCore().getBanManager().addBan(args[0], s, 0L, reason, System.currentTimeMillis(), true);
        return false;
    }
}
