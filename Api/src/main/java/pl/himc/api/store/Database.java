package pl.himc.api.store;

import com.zaxxer.hikari.HikariDataSource;
import pl.himc.api.api.PluginApi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.function.Consumer;

public interface Database {

    HikariDataSource getDataSource();
    void executeQuery(String query, Consumer<ResultSet> action);
    int executeUpdate(String query);
    boolean checkConnect();
    void shutdown();


    default void errorLog(String message, String command, String type) {
        try {
            File mainDir = PluginApi.getApi().getDataFolder();
            File saveTo = new File(mainDir, "databaseErrors.txt");
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println("Database error (" + type + ") -> Komenda: " + command + " (Wiadomosc: " + message + ")");
            pw.flush();
            pw.close();
        } catch (IOException e) { e.printStackTrace();}
    }
}
