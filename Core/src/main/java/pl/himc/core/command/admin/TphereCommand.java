package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class TphereCommand extends PlayerCommand {

    public TphereCommand(String permission) {
        super("tphere", permission,"/tphere <Gracz>","teleporthere", "s");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length < 1 || args.length > 1) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args[0].equalsIgnoreCase(player.getName())) {
            return ChatUtil.sendMessage(player, messageConfiguration.teleportYourselfError);
        }
        Player p2 = Bukkit.getPlayer(args[0]);
        if(p2 == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
        }
        p2.teleport(player);
        ChatUtil.sendMessage(p2, messageConfiguration.teleportHere.replace("{ADMIN}", player.getName()));
        ChatUtil.sendMessage(player, messageConfiguration.teleportHereAdmin.replace("{PLAYER}", p2.getName()));
        return false;
    }
}
