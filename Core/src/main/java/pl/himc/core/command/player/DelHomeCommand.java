package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class DelHomeCommand extends PlayerCommand {
    public DelHomeCommand(String permission) {
        super("delhome", permission, "/delhome <Nazwa>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        User u = PluginCore.getCore().getUserManager().getUser(player);
        if (args.length <= 0) {
            User user = PluginCore.getCore().getUserManager().getUser(player);
            if (!user.hasHome()) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeNoSet);
            }
            if (user.getHomes() > 1) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeList.replace("{HOMES}", user.getHomeList()));
            }
            user.removeHome(user.getFirstHomeName());
            ChatUtil.sendMessage(player, messageConfiguration.delHomeSuccess);
        }else if (args[0].contains(":") && player.hasPermission(this.getPermission() + ".other")) {
            String[] ss = args[0].split(":");
            if (ss.length < 1) {
                ChatUtil.sendUsage(player, "/delhome <Gracz>:<Nazwa domu>");
                return false;
            }
            User user2 = PluginCore.getCore().getUserManager().getUser(ss[0]);
            if (user2 == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
            }
            if (ss.length == 1 || ss[1] == null || user2.getHome(ss[1]) == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeListOther.replace("{PLAYER}", user2.getName()).replace("{HOMES}", user2.getHomeList()));
            }
            user2.removeHome(ss[1]);
            ChatUtil.sendMessage(player, messageConfiguration.delHomeOtherPlayerSuccess.replace("{PLAYER}", user2.getName()).replace("{HOME}", ss[1]));
        }else {
            if(u.getHome(args[0]) == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeNotExists);
            }
            u.removeHome(args[0]);
            ChatUtil.sendMessage(player, messageConfiguration.delHomeSuccess);
        }
        return false;
    }
}
