package pl.himc.guilds;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.himc.api.utils.ConfigHelper;
import pl.himc.api.utils.ConsoleSender;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.GuildManager;
import pl.himc.guilds.base.user.UserManager;
import pl.himc.guilds.command.admin.AdminCommand;
import pl.himc.guilds.command.player.GraczCommand;
import pl.himc.guilds.command.player.GuildCommand;
import pl.himc.guilds.command.player.guild.RoomCommand;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.hook.PluginHook;
import pl.himc.guilds.listener.*;
import pl.himc.guilds.task.AutoSaveTask;
import pl.himc.guilds.task.CheckValidGuild;

import java.io.File;

public final class GuildPlugin extends JavaPlugin {

    private GuildManager guildManager;
    private UserManager userManager;

    private ConsoleSender consoleSender;

    private File configFile;
    private PluginConfig pluginConfig;
    private PluginMessages pluginMessages;

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        this.userManager.saveUsers();
        this.guildManager.saveGuilds();
    }

    @Override
    public void onEnable() {
        PluginGuild.setInstance(this);

        consoleSender = new ConsoleSender(this);

        this.loadConfigs();

        this.guildManager = new GuildManager(this);
        this.userManager = new UserManager(this);

        this.registerCommands();
        this.registerListeners();

        userManager.loadUsers();
        guildManager.loadGuilds();

        this.registerTasks();

        PluginHook.init();
    }


    private void loadConfigs() {
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.reloadPluginConfig();
        this.reloadPluginMessages();
    }

    private void registerCommands() {
        new RoomCommand("test").register();
        new AdminCommand().register();
        new GuildCommand().register();
        new GraczCommand().register();
    }

    private void registerListeners() {
        new BlockBreak(this);
        new BlockPlace(this);
        new BucketAction(this);
        new EntityExplode(this);
        new PlayerCommand(this);
        new PlayerDamage(this);
        new PlayerDeath(this);
        new PlayerInteract(this);
        new PlayerJoin(this);
        new PlayerMove(this);
    }

    private void registerTasks() {
        new AutoSaveTask(this);
        new CheckValidGuild(this);
    }

    public ConsoleSender getLog() {
        return this.consoleSender;
    }

    public PluginConfig getPluginConfiguration() {
        return this.pluginConfig;
    }

    public PluginMessages getMessageConfiguration() {
        return this.pluginMessages;
    }

    public void reloadPluginConfig() {
        this.configFile = null;
        this.configFile = new File(this.getDataFolder(), "config.yml");
        this.pluginConfig = ConfigHelper.loadConfig(this.configFile, PluginConfig.class);
        this.pluginConfig.loadValues();
    }

    public void savePluginConfig(){
        ConfigHelper.saveConfig(this.configFile, this.pluginConfig);
    }

    public void reloadPluginMessages() {
        this.pluginMessages = ConfigHelper.loadConfig(new File(this.getDataFolder(), "messages.yml"), PluginMessages.class);
    }

    public GuildManager getGuildManager() {
        return this.guildManager;
    }
    public UserManager getUserManager() {
        return this.userManager;
    }
}
