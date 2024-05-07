package pl.himc.bungee.configuration.parser;

import pl.himc.bungee.configuration.parser.impl.ColoredListStringParser;
import pl.himc.bungee.configuration.parser.impl.ColoredStringParser;

public enum ConfigValueType {
    DEFAULT(null),
    COLORED_LISTSTRING(new ColoredListStringParser()),
    COLORED_STRING(new ColoredStringParser());

    private ConfigValueParser parser;

    ConfigValueType(ConfigValueParser parser) {
        this.parser = parser;
    }

    public Object parse(Object val) {
        return parser.parse(val);
    }

    public Object convert(Object obj) {
        return parser.convert(obj);
    }
}
