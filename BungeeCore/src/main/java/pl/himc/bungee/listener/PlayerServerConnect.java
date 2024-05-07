package pl.himc.bungee.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import pl.himc.bungee.BungeePlugin;
import pl.himc.bungee.object.Whitelist;
import pl.himc.bungee.util.ChatUtil;

public final class PlayerServerConnect implements Listener {

    public PlayerServerConnect(final BungeePlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }
    private final BungeePlugin plugin;

    @EventHandler
    public void onServerConnect(final ServerConnectEvent event) {
        final Whitelist whitelist = this.plugin.getWhitelistData().getWhitelist(event.getTarget().getName());
        if(whitelist == null) return;
        if(!whitelist.isEnable()) return;

        if(!whitelist.isInWhitelist(event.getPlayer())){
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatUtil.fixColor(whitelist.getKickMessage()));
        }
    }
}
