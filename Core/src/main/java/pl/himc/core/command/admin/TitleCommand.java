package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.api.api.command.CommandAPI;

public final class TitleCommand extends CommandAPI {

    public TitleCommand(String permission) {
        super("title", permission, "/title <Wiadomosc>","allert", "alert", "tbc");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if(args.length < 1){
            return ChatUtil.sendUsage(sender, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        String message = StringUtil.stringBuilder(args, 0);
        TitleAPI.sendBroadcastTitle(messageConfiguration.titleBroadcast, messageConfiguration.subTitleBroaccast.replace("{MESSAGE}", message), 15, 60);
        return false;
    }
}
