package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.CommandAPI;

public final class NightCommand extends CommandAPI {

    public NightCommand(String permission) {
        super("night", permission,"/night");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        for(World w : Bukkit.getWorlds()){
            w.setTime(18000L);
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        return ChatUtil.sendBroadcast(messageConfiguration.nightSetBroadcast);
    }
}
