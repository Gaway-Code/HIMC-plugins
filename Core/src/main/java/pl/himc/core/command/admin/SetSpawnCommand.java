package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class SetSpawnCommand extends PlayerCommand {

    public SetSpawnCommand(String permission) {
        super("setspawn", permission,"/setspawn");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        p.getWorld().setSpawnLocation(p.getLocation().getBlockX(), p.getLocation().getBlockY(), p.getLocation().getBlockZ());
        pluginConfiguration.spawnLocation = StringUtil.locationLongToString(p.getLocation());
        pluginConfiguration.cfgSpawnLocation = p.getLocation();
        PluginCore.getCore().savePluginConfiguration();

        return ChatUtil.sendMessage(p, messageConfiguration.spawnSetNewLocation
                .replace("{X}", Integer.toString(p.getLocation().getBlockX()))
                .replace("{Y}", Integer.toString(p.getLocation().getBlockY()))
                .replace("{Z}", Integer.toString(p.getLocation().getBlockZ())));
    }
}
