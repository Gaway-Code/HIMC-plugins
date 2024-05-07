package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.CommandAPI;

public final class DayCommand extends CommandAPI {

    public DayCommand(String permission) {
        super("day", permission, "/day");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        for(World w : Bukkit.getWorlds()){
            w.setTime(0L);
        }
        return ChatUtil.sendBroadcast(messageConfiguration.daySetBroadcast);
    }
}
