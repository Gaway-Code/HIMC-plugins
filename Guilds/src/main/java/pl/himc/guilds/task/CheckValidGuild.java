package pl.himc.guilds.task;

import org.bukkit.Bukkit;
import pl.himc.guilds.GuildPlugin;

public class CheckValidGuild implements Runnable {

    public CheckValidGuild(GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 0, 5 * 60 * 20);
    }

    private GuildPlugin plugin;

    @Override
    public void run() {
        this.plugin.getGuildManager().checkValid();
    }
}
