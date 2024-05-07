package pl.himc.settings;

import org.bukkit.plugin.java.JavaPlugin;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.ConfigHelper;
import pl.himc.settings.command.CoreCommand;
import pl.himc.settings.command.EnableCommand;
import pl.himc.settings.data.PluginConfig;
import pl.himc.settings.data.PluginMessages;
import pl.himc.settings.listener.CraftItemListener;
import pl.himc.settings.listener.InventoryCloseListener;
import pl.himc.settings.listener.TntExplodeListener;

import java.io.File;

public final class SettingsPlugin extends JavaPlugin {

    private static SettingsPlugin inst;

    private File configFile;
    private PluginConfig pluginConfig;

    private File messagesFile;
    private PluginMessages pluginMessages;

    @Override
    public void onEnable() {
        inst = this;

        this.loadConfigs();

        new CoreCommand(this,PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.core");
        new EnableCommand(this,PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.enable");

        new CraftItemListener(this);
        new InventoryCloseListener(this);
        new TntExplodeListener(this);
    }

    private void loadConfigs(){
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.configFile = new File(this.getDataFolder(), "config.yml");
        this.pluginConfig = ConfigHelper.loadConfig(this.configFile, PluginConfig.class);
        this.messagesFile = new File(this.getDataFolder(), "messages.yml");
        this.pluginMessages = ConfigHelper.loadConfig(this.messagesFile, PluginMessages.class);
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
    public void savePluginConfig(){
        ConfigHelper.saveConfig(this.configFile, this.pluginConfig);
    }

    public PluginMessages getPluginMessage() {
        return this.pluginMessages;
    }

    public static SettingsPlugin getInst(){
        return inst;
    }
}
