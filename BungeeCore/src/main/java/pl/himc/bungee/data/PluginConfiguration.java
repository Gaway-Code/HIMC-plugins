package pl.himc.bungee.data;

import net.md_5.bungee.api.plugin.Plugin;
import pl.himc.bungee.configuration.ConfigurationSource;
import pl.himc.bungee.configuration.CustomConfiguration;

public final class PluginConfiguration extends CustomConfiguration {

    public PluginConfiguration(Plugin plugin) {
        super(plugin, "config.yml");
        this.save();
        this.load();
    }

    @ConfigurationSource(path = "prefixPermission")
    public String prefixPermission = "himc";
}
