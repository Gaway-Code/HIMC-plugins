package pl.himc.auth.configuration.parser;

public enum ConfigValueType {
    DEFAULT(null);

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
