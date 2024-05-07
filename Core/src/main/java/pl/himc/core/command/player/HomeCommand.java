package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class HomeCommand extends PlayerCommand {

    public HomeCommand(String permission) {
        super("home", permission,"/home <Nazwa>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if (args.length <= 0) {
            User user = PluginCore.getCore().getUserManager().getUser(player);
            if (!user.hasHome()) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeNoSet);
            }
            if (user.getHomes() > 1) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeList.replace("{HOMES}", user.getHomeList()));
            }
            Location loc = user.getHome(user.getFirstHomeName());
            PluginApi.getApi().getTeleport().sendTeleport(player, loc, pluginConfiguration.tpTimeHome);
        } else if (args[0].contains(":") && player.hasPermission(this.getPermission() + ".other")) {
            String[] ss = args[0].split(":");
            User user2 = PluginCore.getCore().getUserManager().getUser(ss[0]);
            if (user2 == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
            }
            if (ss.length == 1 || ss[1] == null || user2.getHome(ss[1]) == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeListOther.replace("{PLAYER}", user2.getName()).replace("{HOMES}", user2.getHomeList()));
            }
            Location loc = user2.getHome(ss[1]);
            player.teleport(loc);
            ChatUtil.sendMessage(player, messageConfiguration.homeTeleportToHomeOther.replace("{PLAYER}", user2.getName()));
        } else {
            User user = PluginCore.getCore().getUserManager().getUser(player);
            if (!user.hasHome()) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeNoSet);
            }
            Location loc2 = user.getHome(args[0]);
            if (loc2 == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeNotExists);
            }
            PluginApi.getApi().getTeleport().sendTeleport(player, loc2, pluginConfiguration.tpTimeHome);
        }
        return false;
    }
}
