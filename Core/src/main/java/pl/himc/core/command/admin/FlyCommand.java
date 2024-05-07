package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class FlyCommand extends PlayerCommand {

    public FlyCommand(String permission) {
        super("fly", permission, "/fly <Gracz>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            player.setAllowFlight(!player.getAllowFlight());
            if(player.getAllowFlight()){
                return ChatUtil.sendMessage(player, messageConfiguration.flySetEnable);
            }
            return ChatUtil.sendMessage(player, messageConfiguration.flySetDisable);
        }
        if(!player.hasPermission(this.getPermission() + ".others")){
            return ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
        }
        Player other = Bukkit.getPlayer(args[0]);
        if(other == null){
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
        }
        other.setAllowFlight(!other.getAllowFlight());
        if(other.getAllowFlight()){
            ChatUtil.sendMessage(player, messageConfiguration.flySetEnableOtherToAdmin.replace("{PLAYER}", other.getName()));
            return ChatUtil.sendMessage(other, messageConfiguration.flySetEnableOtherToPlayer.replace("{ADMIN}", player.getName()));
        }
        ChatUtil.sendMessage(player, messageConfiguration.flySetDisableOtherToAdmin.replace("{PLAYER}", other.getName()));
        return ChatUtil.sendMessage(other, messageConfiguration.flySetDisableOtherToPlayer.replace("{ADMIN}", player.getName()));
    }
}
