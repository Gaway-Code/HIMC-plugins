package pl.himc.auth.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;

public final class PlayerChat implements Listener {

    public PlayerChat(final AuthPlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }
    private final AuthPlugin plugin;

    @EventHandler
    public void onChat(ChatEvent event){
        if(!(event.getSender() instanceof ProxiedPlayer)) return;
        if (event.getMessage().toLowerCase().startsWith("/reg")
                || event.getMessage().toLowerCase().startsWith("/l")
                || event.getMessage().toLowerCase().startsWith("/login")
                || event.getMessage().toLowerCase().startsWith("/register")
                || event.getMessage().toLowerCase().startsWith("/captcha")) {
            return;
        }
        final ProxiedPlayer player = (ProxiedPlayer)event.getSender();
        final User user = this.plugin.getUserData().getOrCreate(player.getName());
        if(!user.isLoggedIn()){
            event.setCancelled(true);
        }
    }
}
