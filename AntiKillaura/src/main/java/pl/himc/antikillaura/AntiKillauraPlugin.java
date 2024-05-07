package pl.himc.antikillaura;

import pl.himc.antikillaura.commands.AntiAuraCommand;
import pl.himc.antikillaura.configuration.PluginConfig;
import pl.himc.antikillaura.listener.PlayerJoin;
import pl.himc.antikillaura.manager.UserManager;
import pl.himc.antikillaura.object.NpcBot;
import pl.himc.antikillaura.packet.PlayerDamagePacket;
import pl.himc.antikillaura.task.CheckRunnable;
import pl.himc.api.api.PluginApi;
import org.bukkit.plugin.java.JavaPlugin;
import pl.himc.api.utils.ConfigHelper;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public final class AntiKillauraPlugin extends JavaPlugin {

    private static AntiKillauraPlugin plugin;
    private static final Map<Integer, NpcBot> npcList = new HashMap<>();

    private PluginConfig pluginConfig;

    public UserManager userManager;

    public void onEnable() {
        plugin = this;
        this.loadConfig();
        this.userManager = new UserManager();

        new AntiAuraCommand(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.antikillaura").register();
        new PlayerJoin(this);
        new CheckRunnable(this);

        new PlayerDamagePacket(this);
    }

    public void addBot(final NpcBot npcManager){
        npcList.put(npcManager.getEntityID(), npcManager);
    }
    public void removeBot(final NpcBot npcManager){
        npcList.remove(npcManager.getEntityID());
    }
    public static Map<Integer, NpcBot> getNpcList() {
        return npcList;
    }

    public UserManager getUserManager() {
        return userManager;
    }

    private void loadConfig(){
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.pluginConfig = ConfigHelper.loadConfig(new File(this.getDataFolder(), "config.yml"), PluginConfig.class);
        this.pluginConfig.loadValues();
    }

    public PluginConfig getPluginConfig(){
        return this.pluginConfig;
    }

    public static AntiKillauraPlugin getPlugin() {
        return plugin;
    }
}
