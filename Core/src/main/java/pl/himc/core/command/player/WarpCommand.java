package pl.himc.core.command.player;

import pl.himc.api.api.PluginApi;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public final class WarpCommand extends PlayerCommand {

    public WarpCommand(String permission) {
        super("warp", permission, "/warp <Nazwa>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length != 1){
            return ChatUtil.sendMessage(player, messageConfiguration.warpList.replace("{WARPS}", PluginCore.getCore().getWarpData().getWaprs()));
        }
        Location warp = PluginCore.getCore().getWarpData().getWarp(args[0]);
        if(warp == null){
            return ChatUtil.sendMessage(player, messageConfiguration.warpNotExists);
        }
        PluginApi.getApi().getTeleport().sendTeleport(player, warp, pluginConfiguration.tpTimeWarp);
        return false;
    }
}
