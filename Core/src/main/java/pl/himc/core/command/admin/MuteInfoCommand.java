package pl.himc.core.command.admin;

import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.base.mute.Mute;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.CommandAPI;

public final class MuteInfoCommand extends CommandAPI {

    public MuteInfoCommand(String permission) {
        super("muteinfo", permission,"/muteinfo [Nick]");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(args.length < 1 || args.length > 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        CorePlugin plugin = PluginCore.getCore();
        if(plugin.getMuteManager().getMute(args[0]) == null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsNotMuted);
        }
        Mute ban = plugin.getMuteManager().getMute(args[0]);
        ChatUtil.sendMessage(s, "&8%> &7Nick: &d" + ban.getName());
        ChatUtil.sendMessage(s, "&8%> &7Administrator banujacy: &d" + ban.getAdmin());
        ChatUtil.sendMessage(s, ban.isPerm() ? "&8%> &7Mute jest &4&lNA ZAWSZE" : "&8%> &7Ban wygasa: &d" + TimeUtil.getDate(ban.getTime()) + " &8(&d" + TimeUtil.getDuration(ban.getTime() - System.currentTimeMillis()) + "&8)");
        ChatUtil.sendMessage(s, "&8%> &7Powod: &d" + ban.getReason());
        return ChatUtil.sendMessage(s, "&8%> &7Data nadania bana: &d" + TimeUtil.getDate(ban.getData()));
    }
}
