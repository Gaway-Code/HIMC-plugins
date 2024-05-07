package pl.himc.bungee.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;

import java.util.List;

public final class ChatUtil {

    public static String fixColor(String s){
        if(s == null || s.equals("")){
            return "";
        }
        return ChatColor.translateAlternateColorCodes('&', s.replace("%>", "\u00BB").replace("<%", "\u00AB").replace("*", "\u2022"));
    }

    public static void sendMessage(CommandSender sender, String message){
        sender.sendMessage(new TextComponent(fixColor(message)));
    }
    public static void sendMessage(CommandSender sender, List<String> message){
        for(String s : message){
            sendMessage(sender, s);
        }
    }
    public static String stringBuilder(String[] args, int liczOdArgumentu) {
        String msg = "";
        for (int i = liczOdArgumentu; i < args.length; i++) {
            msg = msg + args[i];
            if (i <= args.length - 2) {
                msg = msg + " ";
            }
        }
        return msg;
    }
}
