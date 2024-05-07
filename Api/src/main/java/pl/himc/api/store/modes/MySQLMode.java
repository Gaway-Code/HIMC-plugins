package pl.himc.api.store.modes;

import com.zaxxer.hikari.HikariDataSource;
import pl.himc.api.ApiPlugin;
import pl.himc.api.data.PluginConfig;
import pl.himc.api.store.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public final class MySQLMode implements Database {


    private final HikariDataSource dataSource;
    private final ApiPlugin plugin;

    public MySQLMode(final ApiPlugin plugin) {
        this.plugin = plugin;
        PluginConfig config = plugin.getPluginConfig();

        this.dataSource = new HikariDataSource();
        int poolSize = config.database.poolSize;
        if(poolSize == 0){
            poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        }

        this.dataSource.setMaximumPoolSize(poolSize);
        this.dataSource.setConnectionTimeout(config.database.timeout);
        this.dataSource.setJdbcUrl("jdbc:mysql://" + config.database.mysql.host + ":" + config.database.mysql.port + "/" + config.database.mysql.dbName + "?useSSL=" + config.database.mysql.useSSL);
        this.dataSource.setUsername(config.database.mysql.user);
        if (config.database.mysql.password != null) {
            this.dataSource.setPassword(config.database.mysql.password);
        }
        this.dataSource.addDataSourceProperty("cachePrepStmts", true);
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        this.dataSource.addDataSourceProperty("useServerPrepStmts", true);
        plugin.getConsoleSender().info("Korzystam z bazy danych: MYSQL");
    }

    @Override
    public HikariDataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public void executeQuery(String query, Consumer<ResultSet> action) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            action.accept(result);
        }
        catch (Exception ex) {
            this.plugin.getConsoleSender().error("Wystapil blad executeQuery!");
            this.plugin.getConsoleSender().error("Wiadomosc: " + ex.getMessage());
            this.plugin.getConsoleSender().error("Komenda: " + query);
            this.errorLog(ex.getMessage(), query, "QUERY");
        }
    }

    @Override
    public int executeUpdate(String query) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            if (statement == null) {
                return 0;
            }
            return statement.executeUpdate();
        }
        catch (Exception ex) {
            this.plugin.getConsoleSender().error("Wystapil blad executeUpdate!");
            this.plugin.getConsoleSender().error("Wiadomosc: " + ex.getMessage());
            this.plugin.getConsoleSender().error("Komenda: " + query);
            this.errorLog(ex.getMessage(), query, "UPDATE");
            return 0;
        }
    }

    @Override
    public boolean checkConnect() {
        try {
            if (this.dataSource.getConnection() != null) {
                return true;
            }
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public void shutdown() {
        if(checkConnect()){
            this.dataSource.close();
        }
    }
}
