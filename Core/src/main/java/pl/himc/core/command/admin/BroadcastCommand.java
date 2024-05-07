package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.CommandAPI;

public final class BroadcastCommand extends CommandAPI {

    public BroadcastCommand(String permission) {
        super("broadcast", permission, "/broadcast <Wiadomosc>", "bc");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(sender, this.getUsage());
        }

        String message = StringUtil.stringBuilder(args, 0);
        ChatUtil.sendBroadcast(messageConfiguration.broadcastFormat.replace("{MESSAGE}", message));
        return false;
    }
}
