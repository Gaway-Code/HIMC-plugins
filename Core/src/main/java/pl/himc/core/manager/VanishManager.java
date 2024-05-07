package pl.himc.core.manager;

import pl.himc.api.utils.bukkit.Actionbar;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;

import java.util.ArrayList;
import java.util.List;

public final class VanishManager {

    private static List<Player> vanished = new ArrayList<>();

    public static void hidePlayer(Player player) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        for (Player online : Bukkit.getOnlinePlayers()) {
            if (online.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish") || online.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish.see")) {
                continue;
            }
            if(vanished.contains(online)){
                continue;
            }
            online.hidePlayer(player);
        }
        ChatUtil.sendMessage(player, messageConfiguration.vanishToggleOn);
        vanished.add(player);
        for(Player admins : Bukkit.getOnlinePlayers()){
            if (admins.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish") || admins.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish.see")) {
                if(admins == player) continue;
                ChatUtil.sendMessage(admins, messageConfiguration.vanishToggleOnToAdmins.replace("{PLAYER}", player.getName()));
            }
        }
    }

    public static void showPlayer(Player player) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.showPlayer(player);
            if(vanished.contains(player)) vanished.remove(player);
        }
        for(Player admins : Bukkit.getOnlinePlayers()){
            if (admins.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish") || admins.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish.see")) {
                if(admins == player) continue;
                ChatUtil.sendMessage(admins, messageConfiguration.vanishToggleOffToAdmins.replace("{PLAYER}", player.getName()));
            }
        }
        ChatUtil.sendMessage(player, messageConfiguration.vanishToggleOff);
        new Actionbar(ChatUtil.fixColor("&aJesteś widoczny dla innych graczy")).sendToPlayer(player);
    }

    public static void joinVanish(Player p){
        if(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish")){
            hidePlayer(p);
            return;
        }
        if(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.vanish.see")) return;
        for(Player vanished : vanished){
            p.hidePlayer(vanished);
        }
    }

    public static void quitVanished(Player p){
        if(!vanished.contains(p)) return;
        showPlayer(p);
    }

    public static List<Player> getVanished() {
        return vanished;
    }

    public static boolean isVanished(Player p){
        return vanished.contains(p);
    }
}
