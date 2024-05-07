package pl.himc.lobbycore.listener;

import org.bukkit.Bukkit;
import pl.himc.api.utils.bukkit.TitleAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import pl.himc.lobbycore.LobbyPlugin;

public class PlayerQuit implements Listener {

    public PlayerQuit(final LobbyPlugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        e.setQuitMessage(null);
        TitleAPI.clearTitle(e.getPlayer());
    }
}
