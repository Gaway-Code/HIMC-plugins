package pl.himc.api.store.modes;

import com.zaxxer.hikari.HikariDataSource;
import pl.himc.api.ApiPlugin;
import pl.himc.api.api.PluginApi;
import pl.himc.api.data.PluginConfig;
import pl.himc.api.store.Database;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public final class SQLiteMode implements Database {

    private final HikariDataSource dataSource;
    private final ApiPlugin plugin;

    public SQLiteMode(final ApiPlugin plugin){
        this.plugin = plugin;
        PluginConfig config = plugin.getPluginConfig();


        this.dataSource = new HikariDataSource();
        int poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;

        this.dataSource.setMaximumPoolSize(poolSize);
        this.dataSource.setConnectionTimeout(config.database.timeout);
        this.dataSource.setDriverClassName("org.sqlite.JDBC");
        this.dataSource.setJdbcUrl("jdbc:sqlite:" + PluginApi.getApi().getDataFolder() + File.separator + config.database.sqlite.dbName);
        this.dataSource.setConnectionTestQuery("SELECT 1");

        plugin.getConsoleSender().info("Korzystam z bazy danych: SQLITE");
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
            ex.printStackTrace();
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
            ex.printStackTrace();
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
        this.dataSource.close();
    }
}
