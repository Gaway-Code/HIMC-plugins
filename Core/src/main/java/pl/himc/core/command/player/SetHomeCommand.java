package pl.himc.core.command.player;

import org.bukkit.Material;
import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class SetHomeCommand extends PlayerCommand {

    public SetHomeCommand(String permission) {
        super("sethome", permission, "/sethome <Nazwa>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(player.getLocation().subtract(0,1,0).getBlock().getType().equals(Material.BEDROCK)){
            return ChatUtil.sendMessage(player, messageConfiguration.setHomeBedrockError);
        }
        User u = PluginCore.getCore().getUserManager().getUser(player);
        if (args.length <= 0) {
            u.setHome("home", player.getLocation());
            ChatUtil.sendMessage(player, messageConfiguration.setHomeSuccess);
        } else if (args[0].contains(":") && player.hasPermission(this.getPermission() + ".other")) {
            String[] ss = args[0].split(":");
            if (ss.length == 1 || ss[1] == null) {
                ChatUtil.sendUsage(player,"/sethome <Gracz>:<Nazwa domu>");
                return false;
            }
            User user2 = PluginCore.getCore().getUserManager().getUser(ss[0]);
            if (user2 == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
            }
            user2.setHome(ss[1], player.getLocation());
            ChatUtil.sendMessage(player, messageConfiguration.setHomeOtherPlayer.replace("{PLAYER}", user2.getName()).replace("{HOME}", ss[1]));
        } else {
            if (!StringUtil.isLettersAndNumbers(args[0])) {
                return ChatUtil.sendMessage(player, messageConfiguration.homeNameOnlyLetters);
            }
            if (u.getHome(args[0]) != null) {
                u.setHome(args[0], player.getLocation());
                return true;
            }
            /*
            if (!(Config.getHomes(p) == -1)) {
                if (u.getHomeSize() >= Config.getHomes(p)) {
                    Replacer.build(Lang.setHomeMax).add("{HOMES}", Config.getHomes(p)).send(p);
                    return false;
                }
            }
             */
            u.setHome(args[0], player.getLocation());
            ChatUtil.sendMessage(player, messageConfiguration.setHomeNameSuccess.replace("{HOME}", args[0]));
        }
        return false;
    }

}
