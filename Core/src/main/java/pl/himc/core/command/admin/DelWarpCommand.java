package pl.himc.core.command.admin;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;

public final class DelWarpCommand extends PlayerCommand {

    public DelWarpCommand(String permission) {
        super("delwarp", permission,"/delwarp <Nazwa>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length != 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginCore.getCore().getWarpData().removeWarp(player, args[0]);
        return false;
    }
}
