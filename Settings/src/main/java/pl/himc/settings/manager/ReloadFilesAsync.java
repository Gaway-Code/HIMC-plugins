package pl.himc.settings.manager;

import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.settings.SettingsPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.drop.stone.DropManager;
import pl.himc.core.base.effect.EffectManager;
import pl.himc.guilds.api.PluginGuild;

import java.util.HashMap;
import java.util.Map;

public final class ReloadFilesAsync {

    private static final Map<Player, BukkitTask> players = new HashMap<>();

    public static void startReload(Player player){
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
                    TitleAPI.sendTitle(player, "&4&lUwaga", "&fTrwa przeladowanie plikow z &ecora&f.", 5, 999);
                    PluginCore.getCore().reloadPluginConfig();
                    PluginCore.getCore().reloadPluginMessages();
                    DropManager.reloadDrops();
                    PluginCore.getCore().getItemshopData().loadShop();
                    EffectManager.getInstance().reloadEffect();
                }
                if(seconds == 10){
                    TitleAPI.sendTitle(player, "&4&lUwaga", "&fTrwa przeladowanie plikow z &egildii&f.", 5, 999);
                    PluginGuild.getPlugin().reloadPluginConfig();
                    PluginGuild.getPlugin().reloadPluginMessages();
                }
                if(seconds == 0){
                    players.get(player).cancel();
                    players.remove(player);
                    TitleAPI.sendTitle(player, "&2&lSUKCES", "&fPliki zostaly &a&lprzeladowane&f!", 5, 70);
                }
                seconds--;
            }
        }, 20L, 20L));
    }
}
