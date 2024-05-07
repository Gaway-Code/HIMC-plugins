package pl.himc.auth.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;

public final class PlayerDisconnect implements Listener {

    public PlayerDisconnect(final AuthPlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }
    private final AuthPlugin plugin;

    @EventHandler
    public void onLogin(final PlayerDisconnectEvent event){
        final ProxiedPlayer player = event.getPlayer();
        User user = this.plugin.getUserData().getOrCreate(player.getName());
        user.disconnectPlayer();
    }
}
