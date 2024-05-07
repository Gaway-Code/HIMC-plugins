package pl.himc.lobbycore;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.himc.api.utils.ConfigHelper;
import pl.himc.api.utils.ConsoleSender;
import pl.himc.lobbycore.base.server.ServerManager;
import pl.himc.lobbycore.command.admin.ReloadServersCommand;
import pl.himc.lobbycore.command.admin.SetSpawnCommand;
import pl.himc.lobbycore.data.PluginConfig;
import pl.himc.lobbycore.data.PluginMessages;
import pl.himc.lobbycore.listener.*;

import java.io.File;

public class LobbyPlugin extends JavaPlugin {

    private static LobbyPlugin lobbyPlugin;
    private ConsoleSender consoleSender;

    private File configFile;
    private PluginConfig pluginConfig;
    private PluginMessages pluginMessages;

    private ServerManager serverManager;

    @Override
    public void onEnable() {
        lobbyPlugin = this;
        Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.consoleSender = new ConsoleSender(this);

        this.loadConfigs();

        this.serverManager = new ServerManager(this);
        this.serverManager.loadServers();

        this.registerCommands();
        this.registerEvents();

        //new InfoServerTask(this);
    }

    private void loadConfigs(){
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.configFile = new File(this.getDataFolder(), "config.yml");
        this.pluginConfig = ConfigHelper.loadConfig(this.configFile, PluginConfig.class);
        this.pluginConfig.loadValues();
        File messagesFile = new File(this.getDataFolder(), "messages.yml");
        this.pluginMessages = ConfigHelper.loadConfig(messagesFile, PluginMessages.class);

    }

    private void registerCommands(){
        new ReloadServersCommand(this, "cmd.reloadservers");
        new SetSpawnCommand(this, "cmd.setspawn");
    }

    private void registerEvents(){
        new OtherCancelEvents(this);
        new PlayerChat(this);
        new PlayerCommand(this);
        new PlayerInteract(this);
        new PlayerJoin(this);
        new PlayerQuit(this);
        new WeatherChange(this);
    }

    public ConsoleSender getLog(){
        return this.consoleSender;
    }

    public PluginConfig getPluginConfig() {
        return pluginConfig;
    }
    public PluginMessages getPluginMessages() {
        return pluginMessages;
    }

    public void savePluginConfig(){
        ConfigHelper.saveConfig(this.configFile, this.pluginConfig);
    }

    public ServerManager getServerManager() {
        return serverManager;
    }

    public static LobbyPlugin getPlugin(){
        return lobbyPlugin;
    }
}
