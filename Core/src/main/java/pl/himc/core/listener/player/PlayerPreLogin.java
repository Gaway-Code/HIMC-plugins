package pl.himc.core.listener.player;

import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import pl.himc.core.base.ban.Ban;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class PlayerPreLogin implements Listener {

    private CorePlugin plugin;

    public PlayerPreLogin(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkBan(AsyncPlayerPreLoginEvent e){
        String name = e.getName();
        if(plugin.getBanManager().getBan(name) !=null){
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            Ban ban = plugin.getBanManager().getBan(name);
            if(ban.isPerm()){
                e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatUtil.fixColor(messageConfiguration.permBanDesign.replace("{ADMIN}", ban.getAdmin()).replace("{REASON}", ban.getReason())));
                ChatUtil.sendBroadcast(messageConfiguration.bannedPlayerIsLogin.replace("{PLAYER}", ban.getName()), PluginApi.getApi().getPluginConfig().prefixPermission + ".ban.playerlogininfo");
            }else{
                if(!ban.isExpire()){
                    e.disallow(AsyncPlayerPreLoginEvent.Result.KICK_OTHER, ChatUtil.fixColor(messageConfiguration.tempBanDesign.replace("{ADMIN}", ban.getAdmin()).replace("{DATA}", TimeUtil.getDate(ban.getTime())).replace("{REASON}", ban.getReason())));
                    ChatUtil.sendBroadcast(messageConfiguration.bannedPlayerIsLogin.replace("{PLAYER}", ban.getName()), PluginApi.getApi().getPluginConfig().prefixPermission + ".ban.playerlogininfo");
                }else{
                    ChatUtil.sendBroadcast(messageConfiguration.bannedPlayerLoginExpire.replace("{PLAYER}", ban.getName()), PluginApi.getApi().getPluginConfig().prefixPermission + ".ban.playerlogininfo");
                }
            }
        }
    }
}
