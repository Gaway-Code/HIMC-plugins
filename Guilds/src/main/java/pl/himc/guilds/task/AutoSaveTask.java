package pl.himc.guilds.task;

import org.bukkit.Bukkit;
import pl.himc.guilds.GuildPlugin;

public class AutoSaveTask implements Runnable {

    public AutoSaveTask(GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, plugin.getPluginConfiguration().database.autoSave * 60 * 20, plugin.getPluginConfiguration().database.autoSave * 60 * 20);
    }

    private GuildPlugin plugin;

    @Override
    public void run() {
        this.plugin.getLog().info("Automatyczny zapis gildii oraz graczy...");
        this.plugin.getUserManager().saveUsers();
        this.plugin.getGuildManager().saveGuilds();
    }
}
