package pl.himc.core.command.admin;

import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import org.bukkit.command.CommandSender;


public final class TurboDropCommand extends CommandAPI {

    public TurboDropCommand(String permission) {
        super("turbodrop", permission,"/turbodrop [Gracz/All] [Czas | np. 10s, 10min, 10h, 10d]");
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
                users.setTurboDrop(time);
                if(users.isOnline()){
                    TitleAPI.sendTitle(users.getPlayer(), "&bTURBO&dDROP", "&aAktywowano na &7" + users.getDurationTruboDrop(), 5, 60);
                }
            }
            return false;
        }
        User user = PluginCore.getCore().getUserManager().getUser(args[0]);
        if(user == null){
            return ChatUtil.sendMessage(sender, messageConfiguration.playerIsNotExists);
        }
        user.setTurboDrop(time);
        if(user.isOnline()){
            TitleAPI.sendTitle(user.getPlayer(), "&bTURBO&dDROP", "&aAktywowano na &7" + user.getDurationTruboDrop(), 5, 60);
        }

        return ChatUtil.sendMessage(sender, messageConfiguration.turboDropPlayerToAdmin.replace("{PLAYER}", args[0]).replace("{DATA}", TimeUtil.getDuration(time)));
    }
}
