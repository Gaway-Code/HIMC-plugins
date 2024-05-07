package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class FeedCommand extends PlayerCommand {

    public FeedCommand(String permission) {
        super("feed", permission, "/feed <Gracz>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length != 1){
            player.setHealth(player.getMaxHealth());
            player.setFoodLevel(20);
            player.setFireTicks(0);
            return ChatUtil.sendMessage(player, messageConfiguration.feedSuccess);
        }
        if(!player.hasPermission(this.getPermission() + ".others")){
            return ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
        }
        Player other = Bukkit.getPlayer(args[0]);
        if(other == null){
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
        }
        other.setHealth(other.getMaxHealth());
        other.setFoodLevel(20);
        other.setFireTicks(0);
        ChatUtil.sendMessage(other, messageConfiguration.feedOtherToPlayer.replace("{ADMIN}", player.getName()));
        return ChatUtil.sendMessage(player, messageConfiguration.feedOtherToAdmin.replace("{PLAYER}", other.getName()));
    }
}
