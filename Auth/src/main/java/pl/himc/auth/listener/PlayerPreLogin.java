package pl.himc.auth.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;
import pl.himc.auth.util.ChatUtil;

public final class PlayerPreLogin implements Listener {

    public PlayerPreLogin(final AuthPlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }
    private AuthPlugin plugin;

    @EventHandler
    public void onLogin(final PreLoginEvent event){
        if (event.isCancelled()) {
            return;
        }
        final PendingConnection conn = event.getConnection();
        final String nick = event.getConnection().getName();
        if (!nick.matches("[a-zA-Z0-9_]{3,16}")) {
            event.setCancelReason(ChatUtil.fixColor("&cTwoj nick zawiera niedozwolone znaki!"));
            event.setCancelled(true);
            return;
        }
        User user = this.plugin.getUserData().getOrCreate(nick);
        if(!user.checkNick(nick)){
            event.setCancelReason(ChatUtil.fixColor("&cZla wielkosc nicku (" + user.getRealPlayerName() + ")"));
            event.setCancelled(true);
            return;
        }
        conn.setOnlineMode(user.isPremium());
    }
}
