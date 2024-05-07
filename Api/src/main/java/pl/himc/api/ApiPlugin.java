package pl.himc.api;

import pl.himc.api.api.PluginApi;
import pl.himc.api.api.inventory.GuiActionHandler;
import pl.himc.api.data.PluginConfig;
import pl.himc.api.data.PluginMessages;
import pl.himc.api.managers.TeleportManager;
import pl.himc.api.store.Database;
import pl.himc.api.store.DatabaseMode;
import pl.himc.api.store.modes.MySQLMode;
import pl.himc.api.store.modes.SQLiteMode;
import pl.himc.api.utils.ConfigHelper;
import pl.himc.api.utils.ConsoleSender;
import pl.himc.api.utils.EnchantUtil;
import pl.himc.api.utils.bukkit.item.ItemNameUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ApiPlugin extends JavaPlugin {

    private ConsoleSender consoleSender;
    private TeleportManager teleportManager;

    private PluginConfig pluginConfig;
    private PluginMessages pluginMessages;

    private Database database;

    @Override
    public void onDisable() {
        if(pluginConfig.database.enable) {
            this.database.shutdown();
        }
    }

    @Override
    public void onEnable() {
        PluginApi.setInstance(this);
        this.consoleSender = new ConsoleSender(this);
        this.consoleSender.send("Plugin uruchamia sie! Wersja v" + this.getDescription().getVersion());

        this.teleportManager = new TeleportManager();

        this.loadConfigs();
        this.loadDatabase();

        new EnchantUtil();
        new ItemNameUtil();

        Bukkit.getPluginManager().registerEvents(new GuiActionHandler(), this);
    }

    public ConsoleSender getConsoleSender(){
        return this.consoleSender;
    }

    private void loadConfigs(){
        if(!this.getDataFolder().exists()) this.getDataFolder().mkdir();
        this.pluginConfig = ConfigHelper.loadConfig(new File(this.getDataFolder(), "config.yml"), PluginConfig.class);
        this.pluginMessages = ConfigHelper.loadConfig(new File(this.getDataFolder(), "messages.yml"), PluginMessages.class);
    }

    private void loadDatabase(){
        if(pluginConfig.database.enable){
            switch (DatabaseMode.selectDatabase(this.pluginConfig)) {
                case MYSQL:
                    this.database = new MySQLMode(this);
                    break;
                case SQLITE:
                    this.database = new SQLiteMode(this);
                    break;
                default:
                    this.database = new SQLiteMode(this);
                    this.consoleSender.error("Wybrano zły typ bazy danych!");
                    break;
            }
            if(!this.database.checkConnect()){
                this.consoleSender.error("Błąd polaczenia z baza danych! Serwer zostanie wyłączony.");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }
    }

    public PluginConfig getPluginConfig(){
        return this.pluginConfig;
    }

    public PluginMessages getPluginMessages(){
        return this.pluginMessages;
    }

    public TeleportManager getTeleport(){
        return this.teleportManager;
    }

    public Database getDatabaseManager(){
        return this.database;
    }
}
