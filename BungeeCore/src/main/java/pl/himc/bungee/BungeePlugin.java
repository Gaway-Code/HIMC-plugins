package pl.himc.bungee;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import pl.himc.bungee.command.BungeeCommands;
import pl.himc.bungee.command.WhitelistCommand;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.data.PluginConfiguration;
import pl.himc.bungee.data.WhitelistData;
import pl.himc.bungee.listener.LoginPlayer;
import pl.himc.bungee.listener.PlayerServerConnect;

public final class BungeePlugin extends Plugin {

    private static BungeePlugin plugin;

    private PluginConfiguration pluginConfiguration;
    private MessageConfiguration messageConfiguration;

    private WhitelistData whitelistData;

    @Override
    public void onEnable() {
        plugin = this;

        this.loadConfigs();

        this.whitelistData = new WhitelistData(this);
        this.whitelistData.loadWhitelist();

        ProxyServer.getInstance().getPluginManager().registerCommand(this, new BungeeCommands());
        ProxyServer.getInstance().getPluginManager().registerCommand(this,  new WhitelistCommand(this));

        new LoginPlayer(this);
        new PlayerServerConnect(this);
    }


    private void loadConfigs() {
        this.pluginConfiguration = new PluginConfiguration(this);
        this.messageConfiguration = new MessageConfiguration(this);
    }

    public PluginConfiguration getPluginConfiguration() {
        return pluginConfiguration;
    }

    public MessageConfiguration getMessageConfiguration() {
        return messageConfiguration;
    }

    public void reloadConfiguration(){
        this.pluginConfiguration = null;
        this.messageConfiguration = null;
        this.loadConfigs();
    }

    public WhitelistData getWhitelistData() {
        return whitelistData;
    }

    public static BungeePlugin getPlugin() {
        return plugin;
    }
}
