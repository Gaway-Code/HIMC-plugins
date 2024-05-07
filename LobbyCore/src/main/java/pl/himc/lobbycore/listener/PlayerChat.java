package pl.himc.lobbycore.listener;

import pl.himc.api.api.PluginApi;
import pl.himc.api.managers.PermissionsManager;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.lobbycore.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class PlayerChat implements Listener {

    public PlayerChat(final LobbyPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final LobbyPlugin plugin;

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        if(!(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".lobbycore.chat"))) {
            e.setCancelled(true);
            ChatUtil.sendMessage(p, this.plugin.getPluginMessages().denyChatWrite);
        }else {
            String format = this.plugin.getPluginConfig().chatFormat;
            format = format.replace("{NAME}", e.getPlayer().getName());
            format = format.replace("{MESSAGE}", "%2$s");
            format = format.replace("{PREFIX}", PermissionsManager.getInstance().getPrefix(p) != null ? PermissionsManager.getInstance().getPrefix(p) : "");
            format = format.replace("{SUFFIX}", PermissionsManager.getInstance().getSuffix(p) != null ? PermissionsManager.getInstance().getSuffix(p) : "");

            e.setFormat(ChatUtil.fixColor(format));
        }
    }
}
