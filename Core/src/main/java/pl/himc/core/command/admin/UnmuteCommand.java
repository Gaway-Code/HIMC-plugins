package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.base.mute.Mute;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class UnmuteCommand extends CommandAPI {

    public UnmuteCommand(String permission) {
        super("unmute", permission,"/unmute <Gracz>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(args.length < 1 || args.length > 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        Mute ban = PluginCore.getCore().getMuteManager().getMute(args[0]);
        if(ban == null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsNotMuted);
        }
        PluginCore.getCore().getMuteManager().removeMute(ban, s);
        return false;
    }
}
