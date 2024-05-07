package pl.himc.antikillaura.listener;

import pl.himc.api.api.PluginApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.himc.antikillaura.AntiKillauraPlugin;
import pl.himc.antikillaura.object.NpcBot;
import pl.himc.antikillaura.object.User;

public final class PlayerJoin implements Listener {

    public PlayerJoin(final AntiKillauraPlugin plugin){
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e){
        Player player = e.getPlayer();
        if(player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.antikillaura")){
            User user = AntiKillauraPlugin.getPlugin().getUserManager().get(player);
            user.setNotify(true);
        }
        new NpcBot(e.getPlayer());
    }
}
