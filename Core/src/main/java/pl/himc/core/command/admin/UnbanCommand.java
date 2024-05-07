package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.base.ban.Ban;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class UnbanCommand extends CommandAPI {

    public UnbanCommand(String permission) {
        super("unban", permission, "/unban <Gracz>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(args.length < 1 || args.length > 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        Ban ban = PluginCore.getCore().getBanManager().getBan(args[0]);
        if(ban == null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsNotBanned);
        }
        PluginCore.getCore().getBanManager().removeBan(ban, s);
        return false;
    }
}
