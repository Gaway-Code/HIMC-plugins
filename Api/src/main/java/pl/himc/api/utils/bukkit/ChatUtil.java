package pl.himc.api.utils.bukkit;

import pl.himc.api.api.PluginApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public final class ChatUtil {

    public static String fixColor(String s){
        if(s == null || s.equals("")){
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("%>", "\u00BB").replace("<%", "\u00AB").replace("*", "\u2022"));
    }

    public static String[] fixColor(String[] s) {
        for (int i = 0; i < s.length; i++) s[i] = fixColor(s[i]);
        return s;
    }

    public static String removeColor(String s){
        if(s == null || s.equals("")){
            return "";
        }
        return ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', s));
    }

    public static List<String> fixColor(List<String> s) {
        for (int i = 0; i < s.size(); i++) {
            String string = s.get(i);
            s.set(i, fixColor(string));
        }
        return s;
    }

    public static boolean sendMessage(CommandSender sender, String message){
        sender.sendMessage(fixColor(message));
        return true;
    }

    public static boolean sendBroadcast(String message, String permission){
        Bukkit.getOnlinePlayers().stream().filter(player -> player.hasPermission(permission)).forEach(player -> sendMessage(player, message));
        Bukkit.getConsoleSender().sendMessage("(Perm: " + permission + "): " + removeColor(message));
        return true;
    }

    public static boolean sendBroadcast(String message){
        Bukkit.broadcastMessage(fixColor(message));
        return true;
    }

    public static boolean sendUsage(CommandSender sender, String usage){
        return sendMessage(sender, PluginApi.getApi().getPluginMessages().commandUsage.replace("{USAGE}", usage));
    }
}
