package pl.himc.auth.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;

public final class PlayerServerConnect implements Listener {

    public PlayerServerConnect(final AuthPlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }
    private AuthPlugin plugin;

    @EventHandler
    public void onServerConnect(final ServerConnectEvent event) {
        final User user = this.plugin.getUserData().getOrCreate(event.getPlayer().getName());
        if (user.isLoggedIn()) {
            return;
        }
        event.setTarget(ProxyServer.getInstance().getServerInfo("lobby"));
    }
}
