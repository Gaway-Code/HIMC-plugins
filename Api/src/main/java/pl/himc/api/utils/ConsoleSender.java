package pl.himc.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class ConsoleSender {

    private JavaPlugin plugin;

    public ConsoleSender(final JavaPlugin plugin){
        this.plugin = plugin;
    }

    public void send(String message){
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "] " + message);
    }
    public void database(String message){
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "][DATABASE] " + message);
    }
    public void info(String message){
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "][INFO] " + message);
    }
    public void warning(String message){
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "][WARNING] " + message);
    }
    public void error(String message){
        Bukkit.getConsoleSender().sendMessage("[" + plugin.getName() + "][ERROR] " + message);
    }
}
