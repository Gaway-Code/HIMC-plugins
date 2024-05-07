package pl.himc.auth.data;

import com.zaxxer.hikari.HikariDataSource;
import pl.himc.auth.AuthPlugin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.function.Consumer;

public final class Database {

    private HikariDataSource dataSource;
    private AuthPlugin plugin;

    public Database(final AuthPlugin plugin) {
        this.plugin = plugin;
        PluginConfiguration config = plugin.getConfiguration();

        this.dataSource = new HikariDataSource();
        int poolSize = config.databasePoolSize;
        if(poolSize == 0){
            poolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        }

        this.dataSource.setMaximumPoolSize(poolSize);
        this.dataSource.setConnectionTimeout(config.databaseTimeout);
        this.dataSource.setJdbcUrl("jdbc:mysql://" + config.databaseMySQLHost + ":" + config.databaseMySQLPort + "/" + config.databaseMySQLDBName + "?useSSL=" + config.databaseMySQLUseSSL);
        this.dataSource.setUsername(config.databaseMySQLUser);
        if (config.databaseMySQLPass != null) {
            this.dataSource.setPassword(config.databaseMySQLPass);
        }
        this.dataSource.addDataSourceProperty("cachePrepStmts", true);
        this.dataSource.addDataSourceProperty("prepStmtCacheSize", 250);
        this.dataSource.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        this.dataSource.addDataSourceProperty("useServerPrepStmts", true);
        System.out.println("[memekAuth] Polaczenie z baza danych nawiazane!");
    }

    public void executeQuery(String query, Consumer<ResultSet> action) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet result = statement.executeQuery()) {
            action.accept(result);
        }
        catch (Exception ex) {
            //ex.printStackTrace();
            System.out.println("[memekAuth] Wystapil blad executeQuery!");
            System.out.println("Wiadomosc: " + ex.getMessage());
            System.out.println("Komenda: " + query);
            this.errorLog(ex.getMessage(), query, "QUERY");
        }
    }

    public int executeUpdate(String query) {
        try (Connection connection = this.dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            if (statement == null) {
                return 0;
            }
            return statement.executeUpdate();
        }
        catch (Exception ex) {
            System.out.println("[memekAuth] Wystapil blad executeUpdate!");
            System.out.println("Wiadomosc: " + ex.getMessage());
            System.out.println("Komenda: " + query);
            this.errorLog(ex.getMessage(), query, "UPDATE");
            return 0;
        }
    }

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

    public void shutdown() {
        if(checkConnect()){
            this.dataSource.close();
        }
    }

    private void errorLog(String message, String command, String type) {
        try {
            File mainDir = this.plugin.getDataFolder();
            File saveTo = new File(mainDir, "databaseErrors.txt");
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Database error (" + type + ") -> Komenda: " + command + " (Wiadomosc: " + message + ")");
            pw.flush();
            pw.close();
        } catch (IOException e) { e.printStackTrace();}
    }
}
