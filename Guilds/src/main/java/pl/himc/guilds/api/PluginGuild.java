package pl.himc.guilds.api;

import pl.himc.guilds.GuildPlugin;

public final class PluginGuild {

    private static GuildPlugin instance;

    public static void setInstance(GuildPlugin plugin) {
        PluginGuild.instance = plugin;
    }

    public static GuildPlugin getPlugin() {
        return instance;
    }
}
