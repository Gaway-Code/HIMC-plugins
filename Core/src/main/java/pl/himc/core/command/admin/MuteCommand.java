package pl.himc.core.command.admin;

import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class MuteCommand extends CommandAPI {

    public MuteCommand(String permission) {
        super("mute", permission, "/mute <Gracz> <Powod>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        CorePlugin plugin = PluginCore.getCore();

        if(plugin.getMuteManager().getMute(args[0]) != null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsAlreadyMuted.replace("{PLAYER}", args[0]));
        }
        String reason = "Zlamanie regulaminu";
        if(args.length >= 2){
            reason = StringUtil.stringBuilder(args, 1);
        }
        plugin.getMuteManager().addMute(args[0], s, 0L, reason, System.currentTimeMillis(), true);
        return false;
    }
}
