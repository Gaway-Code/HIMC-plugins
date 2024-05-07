package pl.himc.bungee.configuration.parser;

public interface ConfigValueParser<K, T> {
    T parse(K value);

    K convert(T object);
}
