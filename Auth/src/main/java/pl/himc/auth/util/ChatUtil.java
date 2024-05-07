package pl.himc.auth.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;

public final class ChatUtil {

    public static TextComponent fixColor(final String message) {
        return new TextComponent(ChatColor.translateAlternateColorCodes('&', message));
    }
}
