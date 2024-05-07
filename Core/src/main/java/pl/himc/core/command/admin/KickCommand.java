package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.CommandAPI;

public final class KickCommand extends CommandAPI {

    public KickCommand(String permission) {
        super("kick", permission, "/kick <Gracz> <Powod>");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(sender, this.getUsage());
        }else if(args.length == 1){
            String powod = "Zlamanie regulaminu serwera";
            Player kicked = Bukkit.getPlayer(args[0]);
            if(kicked == null){
                return ChatUtil.sendMessage(sender, messageConfiguration.playerIsOffline);
            }
            kicked.kickPlayer(ChatUtil.fixColor(messageConfiguration.kickPlayerDesign.
                    replace("{ADMIN}", sender.getName())
                    .replace("{REASON}", powod)));
            ChatUtil.sendBroadcast(messageConfiguration.kickPlayerBroadcast
                    .replace("{PLAYER}", args[0])
                    .replace("{ADMIN}", sender.getName())
                    .replace("{REASON}", powod));

        }else if(args.length >= 2){
            String powod = StringUtil.stringBuilder(args, 1);
            Player kicked = Bukkit.getPlayer(args[0]);
            if(kicked == null){
                return ChatUtil.sendMessage(sender, messageConfiguration.playerIsOffline);
            }
            kicked.kickPlayer(ChatUtil.fixColor(messageConfiguration.kickPlayerDesign.
                    replace("{ADMIN}", sender.getName())
                    .replace("{REASON}", powod)));

            ChatUtil.sendBroadcast(messageConfiguration.kickPlayerBroadcast
                    .replace("{PLAYER}", args[0])
                    .replace("{ADMIN}", sender.getName())
                    .replace("{REASON}", powod));
        }
        return false;
    }
}
