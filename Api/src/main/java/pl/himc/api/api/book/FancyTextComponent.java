package pl.himc.api.api.book;

import org.bukkit.ChatColor;
import pl.himc.api.utils.bukkit.ChatUtil;

public class FancyTextComponent {

    private String text;

    private String color;

    private String[] attributes;

    private FancyClickEvent clickEvent;
    private FancyHoverEvent hoverEvent;

    public final static String bold = "bold";
    public final static String italic = "italic";
    public final static String underlined = "underlined";
    public final static String strikethrough = "strikethrough";
    public final static String obfuscated = "obfuscated";

    private FancyTextComponent(String text, String color, FancyClickEvent clickEvent, FancyHoverEvent hoverEvent, String... attributes) {
        this.text = ChatUtil.fixColor(text);
        this.color = color;
        this.clickEvent = clickEvent;
        this.hoverEvent = hoverEvent;
        this.attributes = attributes;
    }

    public static FancyTextComponent fancyTextComponent(String text, String... attributes) {
        return new FancyTextComponent(text, "white", null, null, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, String color, String... attributes) {
        return new FancyTextComponent(text, color, null, null, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, ChatColor color, String... attributes) {
        return new FancyTextComponent(text, color.name().toLowerCase(), null, null, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, String color, FancyClickEvent clickEvent, String... attributes) {
        return new FancyTextComponent(text, color, clickEvent, null, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, ChatColor color, FancyClickEvent clickEvent, String... attributes) {
        return new FancyTextComponent(text, color.name().toLowerCase(), clickEvent, null, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, String color, FancyHoverEvent hoverEvent, String... attributes) {
        return new FancyTextComponent(text, color, null, hoverEvent, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, ChatColor color, FancyHoverEvent hoverEvent, String... attributes) {
        return new FancyTextComponent(text, color.name().toLowerCase(), null, hoverEvent, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, String color, FancyClickEvent clickEvent, FancyHoverEvent hoverEvent, String... attributes) {
        return new FancyTextComponent(text, color, clickEvent, hoverEvent, attributes);
    }

    public static FancyTextComponent fancyTextComponent(String text, ChatColor color, FancyClickEvent clickEvent, FancyHoverEvent hoverEvent, String... attributes) {
        return new FancyTextComponent(text, color.name().toLowerCase(), clickEvent, hoverEvent, attributes);
    }


    public void clearEvents() {
        this.clickEvent = null;
        this.hoverEvent = null;
    }

    public String getText() {
        return text;
    }

    public String getColor() {
        return color;
    }

    public String[] getAttributes() {
        return attributes;
    }

    public FancyClickEvent getClickEvent() {
        return clickEvent;
    }

    public FancyHoverEvent getHoverEvent() {
        return hoverEvent;
    }

    public boolean hasClickEvent() {
        return clickEvent != null;
    }

    public boolean hasHoverEvent() {
        return hoverEvent != null;
    }
}