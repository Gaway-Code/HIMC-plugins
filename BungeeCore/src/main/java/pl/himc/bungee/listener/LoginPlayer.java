package pl.himc.bungee.listener;

import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.himc.bungee.BungeePlugin;
import pl.himc.bungee.object.Whitelist;
import pl.himc.bungee.util.ChatUtil;

public final class LoginPlayer implements Listener {

    public LoginPlayer(final BungeePlugin plugin){
        this.plugin = plugin;
        plugin.getProxy().getPluginManager().registerListener(plugin, this);
    }
    private final BungeePlugin plugin;

    @EventHandler
    public void onLogin(final LoginEvent event){
        final Whitelist whitelist = this.plugin.getWhitelistData().getWhitelist("lobby");
        if(whitelist == null) return;
        if(!whitelist.isEnable()) return;
        if(!whitelist.isInWhitelist(event.getConnection().getName())){
            event.setCancelReason(ChatUtil.fixColor(whitelist.getKickMessage()));
            event.setCancelled(true);
        }
    }
}
