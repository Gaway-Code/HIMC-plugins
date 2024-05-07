package pl.himc.bungee.configuration.parser.impl;

import pl.himc.bungee.configuration.parser.ConfigValueParser;
import pl.himc.bungee.util.ChatUtil;

public class ColoredStringParser implements ConfigValueParser<String, String> {

    @Override
    public String parse(String obj) {
        if (obj == null) return null;
        return ChatUtil.fixColor(obj);
    }

    @Override
    public String convert(String obj) {
        return obj.replace("\u00A7", "&");
    }
}