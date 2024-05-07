package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class TempbanCommand extends CommandAPI {

    public TempbanCommand(String permission) {
        super("tempban", permission, "/tempban <Gracz> <Czas np. 5s, 5min, 5h, 5d> <Powod>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(args.length < 2){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(PluginCore.getCore().getBanManager().getBan(args[0]) != null){
            return ChatUtil.sendMessage(s, messageConfiguration.playerIsAlreadyBanned.replace("{PLAYER}", args[0]));
        }
        if(TimeUtil.getTimeWithString(args[1]) == System.currentTimeMillis() || TimeUtil.getTimeWithString(args[1]) == 0){
            return ChatUtil.sendMessage(s, messageConfiguration.invalidTime);
        }
        String reason = "Zlamanie regulaminu.";
        if(args.length >= 3){
            reason = StringUtil.stringBuilder(args, 2);
        }
        PluginCore.getCore().getBanManager().addBan(args[0], s, TimeUtil.getTimeWithString(args[1]) + System.currentTimeMillis(), reason, System.currentTimeMillis(), true);
        return false;
    }
}
