package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class AdminChatCommand extends PlayerCommand {

    public AdminChatCommand(String permission) {
        super("adminchat", permission,"/adminchat <Wiadomosc>", "achat", "a");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        String message = StringUtil.stringBuilder(args, 0);
        return ChatUtil.sendBroadcast(messageConfiguration.adminChatFormat.replace("{PLAYER}", player.getName()).replace("{MESSAGE}", message), this.getPermission());
    }
}
