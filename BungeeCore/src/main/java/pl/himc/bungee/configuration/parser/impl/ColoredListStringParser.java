package pl.himc.bungee.configuration.parser.impl;

import pl.himc.bungee.util.ChatUtil;
import pl.himc.bungee.configuration.parser.ConfigValueParser;

import java.util.List;
import java.util.stream.Collectors;

public class ColoredListStringParser implements ConfigValueParser<List<String>, List<String>> {

    @Override
    public List<String> parse(List<String> obj) {
        return obj.stream().map(ChatUtil::fixColor).collect(Collectors.toList());
    }

    @Override
    public List<String> convert(List<String> obj) {
        return obj.stream().map(str -> str.replace("\u00A7", "&")).collect(Collectors.toList());
    }
}
