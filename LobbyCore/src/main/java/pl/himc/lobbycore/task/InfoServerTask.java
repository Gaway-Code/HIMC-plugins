package pl.himc.lobbycore.task;

import pl.himc.api.utils.bukkit.Actionbar;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.lobbycore.LobbyPlugin;
import org.bukkit.Bukkit;

public final class InfoServerTask implements Runnable{

    public InfoServerTask(final LobbyPlugin plugin){
        this.plugin = plugin;
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 20, 20);
    }
    private final LobbyPlugin plugin;

    @Override
    public void run() {
        Actionbar bar = new Actionbar(ChatUtil.fixColor(this.plugin.getPluginConfig().actionbarInfo));
        bar.sendToAll();
    }
}
