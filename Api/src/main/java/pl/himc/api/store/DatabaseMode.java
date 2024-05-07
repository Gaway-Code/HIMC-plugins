package pl.himc.api.store;

import pl.himc.api.data.PluginConfig;

public enum DatabaseMode {

    MYSQL("mysql"), SQLITE("sqlite");

    private final String name;

    DatabaseMode(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static DatabaseMode selectDatabase(final PluginConfig config) {
        for (DatabaseMode sm : values())
            if (sm.getName().equalsIgnoreCase(config.database.type)) return sm;
        return null;
    }

}