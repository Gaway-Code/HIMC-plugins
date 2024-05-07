package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class ClearCommand extends PlayerCommand {

    public ClearCommand(String permission) {
        super("clear", permission, "/clear <Gracz>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            player.getInventory().clear();
            player.getInventory().setHelmet(null);
            player.getInventory().setChestplate(null);
            player.getInventory().setLeggings(null);
            player.getInventory().setBoots(null);
            player.getInventory().setHeldItemSlot(0);
            player.updateInventory();
            ChatUtil.sendMessage(player, messageConfiguration.clearEq);
            return false;
        }
        if(!player.hasPermission(this.getPermission() + ".others")){
            return ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
        }
        Player other = Bukkit.getPlayer(args[0]);
        if(other == null){
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
        }
        other.getInventory().clear();
        other.getInventory().setHelmet(null);
        other.getInventory().setChestplate(null);
        other.getInventory().setLeggings(null);
        other.getInventory().setBoots(null);
        other.getInventory().setHeldItemSlot(0);
        other.updateInventory();
        ChatUtil.sendMessage(player, messageConfiguration.clearEqOtherToAdmin.replace("{PLAYER}", other.getName()));
        return ChatUtil.sendMessage(other, messageConfiguration.clearEqOtherToPlayer.replace("{ADMIN}", player.getName()));
    }
}
