package pl.himc.core.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public final class AntyReklamaManager {

    private static void ReklamaLog(String message) {
        try {
            File mainDir = PluginCore.getCore().getDataFolder();
            File saveTo = new File(mainDir, "reklama.txt");
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.flush();
            pw.close();
        } catch (IOException e) { e.printStackTrace();}
    }

    public static void checkMessage(AsyncPlayerChatEvent e){
        PluginConfig config = PluginCore.getCore().getPluginConfig();
        PluginMessages messages = PluginCore.getCore().getPluginMessages();

        String message = e.getMessage().toLowerCase();
        Player p = e.getPlayer();
        config.curseWords.forEach(curse -> {
            if(message.contains(curse)){
                e.setMessage(message.replace(curse, "***"));
            }
        });
        for(String bypass$words : config.reklamaAllowWords){
            if(message.contains(bypass$words.toLowerCase())){
                return;
            }
        }
        for(String banned$words : config.reklamaBannedWords){
            if(message.contains(banned$words.toLowerCase())){
                e.setCancelled(true);
                Bukkit.getScheduler().runTask(PluginCore.getCore(), () -> PluginCore.getCore().getBanManager().addBan(p.getName(), Bukkit.getConsoleSender(), 0L, "Wykryto probe reklamy!", System.currentTimeMillis(), true));
                ChatUtil.sendBroadcast(messages.reklamaNotifyAdmin
                        .replace("{PLAYER}", p.getName())
                        .replace("{MESSAGE}", e.getMessage()), PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.chat");

                ReklamaLog("(Data: " + TimeUtil.getDate(System.currentTimeMillis()) + ") Gracz " + p.getName() + " (IP: " + p.getAddress().getAddress().getHostAddress() + ") - Wiadomosc: " + message);
            }
        }
    }

    public static boolean checkMsg(Player p, String message){
        if(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.chat")) return true;
        PluginConfig config = PluginCore.getCore().getPluginConfig();
        PluginMessages messages = PluginCore.getCore().getPluginMessages();

        for(String bypass$words : config.reklamaAllowWords){
            if(message.toLowerCase().contains(bypass$words.toLowerCase())){
                return true;
            }
        }
        for(String banned$words : config.reklamaBannedWords){
            if(message.toLowerCase().contains(banned$words.toLowerCase())){
                Bukkit.getScheduler().runTask(PluginCore.getCore(), () -> PluginCore.getCore().getBanManager().addBan(p.getName(), Bukkit.getConsoleSender(), 0L, "Wykryto probe reklamy!", System.currentTimeMillis(), true));
                ChatUtil.sendBroadcast(messages.reklamaNotifyAdmin
                        .replace("{PLAYER}", p.getName())
                        .replace("{MESSAGE}", message), PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.chat");
                ReklamaLog("[REKLAMA MSG] (Data: " + TimeUtil.getDate(System.currentTimeMillis()) + ") Gracz " + p.getName() + " (IP: " + p.getAddress().getAddress().getHostAddress() + ") - Wiadomosc: " + message);
                return false;
            }
        }
        return true;
    }
}
