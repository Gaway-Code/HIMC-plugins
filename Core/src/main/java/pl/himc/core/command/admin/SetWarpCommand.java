package pl.himc.core.command.admin;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;

public final class SetWarpCommand extends PlayerCommand {

    public SetWarpCommand(String permission) {
        super("setwarp", permission,"/setwarp <Nazwa>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length != 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginCore.getCore().getWarpData().addWarp(player, args[0]);
        return false;
    }
}
