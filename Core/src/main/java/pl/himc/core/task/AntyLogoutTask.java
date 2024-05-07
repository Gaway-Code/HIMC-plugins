package pl.himc.core.task;

import pl.himc.core.manager.AntyLogout;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import pl.himc.core.api.PluginCore;

public final class AntyLogoutTask extends BukkitRunnable {
    private final Player player;
    private int time;

    public AntyLogoutTask(Player player) {
        this.player = player;
        this.time = PluginCore.getCore().getPluginConfig().antyLogoutTime;
        runTaskTimer(PluginCore.getCore(), 0, 20);
    }

    @Override
    public void run() {
        if (AntyLogout.isTagable(player)) {
            if (time > 0) {
                AntyLogout.sendBar(player, PluginCore.getCore().getPluginMessages().antyLogoutIn
                        .replace("{TIME}", String.valueOf(time)));
                time--;
            } else {
                AntyLogout.sendBar(player, PluginCore.getCore().getPluginMessages().antylogutOut);
                AntyLogout.cancelTask(player);
            }
        } else AntyLogout.cancelTask(player);
    }
}
