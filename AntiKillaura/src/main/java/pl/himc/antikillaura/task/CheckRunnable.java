package pl.himc.antikillaura.task;

import pl.himc.core.manager.AntyLogout;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.antikillaura.AntiKillauraPlugin;
import pl.himc.antikillaura.object.NpcBot;

public final class CheckRunnable implements Runnable {

    public CheckRunnable(final AntiKillauraPlugin plugin){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, plugin.getPluginConfig().checkPlayersDelay * 20, plugin.getPluginConfig().checkPlayersDelay * 20);
    }

    @Override
    public void run() {
        for(Player player : AntyLogout.getAntyLogoutPlayers()){
            if(player.isOnline()){
                new NpcBot(player);
            }
        }
    }
}
