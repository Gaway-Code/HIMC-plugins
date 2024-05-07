package pl.himc.auth.data;

import net.md_5.bungee.api.plugin.Plugin;
import pl.himc.auth.configuration.ConfigurationSource;
import pl.himc.auth.configuration.CustomConfiguration;

public final class PluginConfiguration extends CustomConfiguration {

    public PluginConfiguration(Plugin plugin) {
        super(plugin, "config.yml");
        this.save();
        this.load();
    }

    @ConfigurationSource(path = "database.enable")
    public boolean databaseEnable = true;


    @ConfigurationSource(path = "database.connectTimeOut")
    public int databaseTimeout = 30000;

    @ConfigurationSource(path = "database.poolsize")
    public int databasePoolSize = 10;

    @ConfigurationSource(path = "database.mysql.host")
    public String databaseMySQLHost = "localhost";

    @ConfigurationSource(path = "database.mysql.port")
    public int databaseMySQLPort = 3306;

    @ConfigurationSource(path = "database.mysql.user")
    public String databaseMySQLUser = "root";

    @ConfigurationSource(path = "database.mysql.password")
    public String databaseMySQLPass = "przemekMASNY";

    @ConfigurationSource(path = "database.mysql.database")
    public String databaseMySQLDBName = "minecraft";

    @ConfigurationSource(path = "database.mysql.table")
    public String databaseMySQLTable = "auth";

    @ConfigurationSource(path = "database.mysql.useSSL")
    public boolean databaseMySQLUseSSL = false;
}
