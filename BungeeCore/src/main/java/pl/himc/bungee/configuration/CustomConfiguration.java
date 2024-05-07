package pl.himc.bungee.configuration;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import pl.himc.bungee.configuration.parser.ConfigValueType;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;

public class CustomConfiguration {

    private File file;
    private Configuration configuration;

    public CustomConfiguration(Plugin plugin, String fileName) {
        try{
            this.file = new File(plugin.getDataFolder(), fileName);
            if (!file.exists()) {
                if (plugin.getResourceAsStream(fileName) == null) {
                    try {
                        file.getParentFile().mkdir();
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else Files.copy(plugin.getResourceAsStream(fileName), file.toPath());
            }
            configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
        }catch (IOException exception){
            exception.printStackTrace();
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public File getFile() {
        return file;
    }

    public void load() {
        for (Field f : getClass().getDeclaredFields()) {
            if (!f.isAnnotationPresent(ConfigurationSource.class)) continue;

            try {
                ConfigurationSource source = f.getAnnotation(ConfigurationSource.class);
                if (source.valueType() != ConfigValueType.DEFAULT)
                    f.set(this, source.valueType().parse(configuration.get(source.path())));
                else
                    f.set(this, configuration.get(source.path()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void save() {
        for (Field f : getClass().getDeclaredFields()) {
            if (!f.isAnnotationPresent(ConfigurationSource.class)) continue;
            try {
                ConfigurationSource source = f.getAnnotation(ConfigurationSource.class);
                if(configuration.get(source.path()) != null) continue;
                if (source.valueType() == ConfigValueType.DEFAULT)
                    configuration.set(source.path(), f.get(this));
                else
                    configuration.set(source.path(), source.valueType().convert(f.get(this)));
            } catch (IllegalAccessException | ClassCastException e) {
                e.printStackTrace();
            }
        }

        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
