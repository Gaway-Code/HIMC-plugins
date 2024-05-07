package pl.himc.auth;

import net.md_5.bungee.api.plugin.Plugin;
import pl.himc.auth.api.PluginAuth;
import pl.himc.auth.command.AuthCommand;
import pl.himc.auth.command.ChangePasswordCommand;
import pl.himc.auth.command.LoginCommand;
import pl.himc.auth.command.RegisterCommand;
import pl.himc.auth.data.Database;
import pl.himc.auth.data.PluginConfiguration;
import pl.himc.auth.data.UserData;
import pl.himc.auth.task.AutosaveTask;
import pl.himc.auth.listener.*;

public final class AuthPlugin extends Plugin {

    private PluginConfiguration pluginConfiguration;
    private Database database;
    private UserData userData;

    @Override
    public void onDisable() {
        this.userData.saveUsers();
        this.database.shutdown();
    }

    @Override
    public void onEnable() {
        PluginAuth.setInstance(this);

        this.loadConfiguration();

        this.userData = new UserData(this);
        if(this.pluginConfiguration.databaseEnable){
            this.database = new Database(this);
            this.userData.loadUsers();
        }

        this.registerCommands();
        this.registerListeners();

        new AutosaveTask(this);
    }

    private void registerCommands(){
        new AuthCommand(this);
        new ChangePasswordCommand(this);
        new LoginCommand(this);
        new RegisterCommand(this);
    }

    private void registerListeners(){
        new PlayerChat(this);
        new PlayerDisconnect(this);
        new PlayerPostLogin(this);
        new PlayerPreLogin(this);
        new PlayerServerConnect(this);
    }

    public PluginConfiguration getConfiguration() {
        return pluginConfiguration;
    }

    public void loadConfiguration(){
        this.pluginConfiguration = null;
        this.pluginConfiguration = new PluginConfiguration(this);
    }
    public Database getDatabase() {
        return database;
    }

    public UserData getUserData() {
        return userData;
    }
}
