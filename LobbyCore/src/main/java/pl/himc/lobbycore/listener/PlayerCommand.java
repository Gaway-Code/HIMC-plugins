package pl.himc.lobbycore.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.lobbycore.LobbyPlugin;

public class PlayerCommand implements Listener {

    public PlayerCommand(final LobbyPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final LobbyPlugin plugin;

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event){
        String[] command = event.getMessage().toLowerCase().split(" ");
        if(!this.plugin.getPluginConfig().allowCommands.contains(command[0])){
            if(!event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".lobbycore.command")){
                event.setCancelled(true);
                ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().denySendCommands);
            }
        }
    }
}
