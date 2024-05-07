package pl.himc.lobbycore.command.admin;

import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.lobbycore.LobbyPlugin;
import pl.himc.lobbycore.data.PluginConfig;

public class SetSpawnCommand extends PlayerCommand {

    public SetSpawnCommand(final LobbyPlugin lobbyPlugin, String permission) {
        super("setspawn", PluginApi.getApi().getPluginConfig().prefixPermission + "." + permission, "");
        this.register();
        this.lobbyPlugin = lobbyPlugin;
    }
    private final LobbyPlugin lobbyPlugin;

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginConfig pluginConfig = this.lobbyPlugin.getPluginConfig();
        pluginConfig.cfgSpawnLocation = player.getLocation();
        pluginConfig.spawnLocation = StringUtil.locationLongToString(player.getLocation());
        this.lobbyPlugin.savePluginConfig();
        return ChatUtil.sendMessage(player, LobbyPlugin.getPlugin().getPluginMessages().setSpawnSuccess);
    }
}
