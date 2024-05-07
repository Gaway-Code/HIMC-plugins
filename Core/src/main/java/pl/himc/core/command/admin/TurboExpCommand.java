package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.api.api.command.CommandAPI;


public final class TurboExpCommand extends CommandAPI {

    public TurboExpCommand(String permission) {
        super("turboexp", permission, "/turboexp [Gracz/All] [Czas | np. 10s, 10min, 10h, 10d]");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        if(args.length < 2){
            return ChatUtil.sendUsage(sender, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        long time = TimeUtil.getTimeWithString(args[1]);
        if(time < 0L){
            return ChatUtil.sendMessage(sender, messageConfiguration.invalidTime);
        }

        if(args[0].equalsIgnoreCase("all")){
            for(User users : PluginCore.getCore().getUserManager().getUsers()){
                users.setTurboExp(time);
                if(users.isOnline()){
                    TitleAPI.sendTitle(users.getPlayer(), "&bTURBO&dEXP", "&aAktywowano na &7" + users.getDurationTruboExp(), 5, 60);
                }
            }
            return false;
        }
        User user = PluginCore.getCore().getUserManager().getUser(args[0]);
        if(user == null){
            return ChatUtil.sendMessage(sender, messageConfiguration.playerIsNotExists);
        }
        user.setTurboExp(time);
        if(user.isOnline()){
            TitleAPI.sendTitle(user.getPlayer(), "&bTURBO&dEXP", "&aAktywowano na &7" + user.getDurationTruboExp(), 5, 60);
        }
        return ChatUtil.sendMessage(sender, messageConfiguration.turboExpPlayerToAdmin.replace("{PLAYER}", args[0]).replace("{DATA}", TimeUtil.getDuration(time)));
    }
}
