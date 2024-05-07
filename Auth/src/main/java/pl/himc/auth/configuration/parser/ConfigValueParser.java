package pl.himc.auth.configuration.parser;

public interface ConfigValueParser<K, T> {
    T parse(K value);

    K convert(T object);
}
