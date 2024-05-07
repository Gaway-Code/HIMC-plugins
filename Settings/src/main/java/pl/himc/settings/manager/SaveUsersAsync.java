package pl.himc.settings.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.api.PluginCore;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.settings.SettingsPlugin;

import java.util.HashMap;
import java.util.Map;

public final class SaveUsersAsync {

    private static final Map<Player, BukkitTask> players = new HashMap<>();

    public static void startSave(Player player){
        if(players.containsKey(player)){
            players.get(player).cancel();
            players.remove(player);
        }
        players.put(player, Bukkit.getScheduler().runTaskTimerAsynchronously(SettingsPlugin.getInst(), new Runnable() {
            int seconds = 15;
            @Override
            public void run() {
                if(!player.isOnline()){
                    players.get(player).cancel();
                    players.remove(player);
                }
                if(seconds == 15){
                    TitleAPI.sendTitle(player, "&4&lUwaga", "&fTrwa zapis danych z &ecora&f.", 5, 999);
                    PluginCore.getCore().saveAll();
                }
                if(seconds == 10){
                    TitleAPI.sendTitle(player,"&4&lUwaga", "&fTrwa zapis danych z &egildii&f.", 5, 999);
                    PluginGuild.getPlugin().getGuildManager().saveGuilds();
                    PluginGuild.getPlugin().getUserManager().saveUsers();
                }
                if(seconds == 0){
                    players.get(player).cancel();
                    players.remove(player);
                    TitleAPI.sendTitle(player, "&2&lSUKCES", "&fGracze zostali &a&lzapisani&f!", 5, 70);
                }
                seconds--;
            }
        }, 20L, 20L));
    }
}
