package pl.himc.lobbycore.command.admin;

import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.lobbycore.LobbyPlugin;

public class ReloadServersCommand extends PlayerCommand {

    public ReloadServersCommand(final LobbyPlugin lobbyPlugin, String permission) {
        super("reloadservers", PluginApi.getApi().getPluginConfig().prefixPermission + "." + permission, "");
        this.register();
        this.lobbyPlugin = lobbyPlugin;

    }
    private final LobbyPlugin lobbyPlugin;

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.lobbyPlugin.getServerManager().reloadServers();
        return ChatUtil.sendMessage(player, this.lobbyPlugin.getPluginMessages().reloadServersSuccess);
    }
}
