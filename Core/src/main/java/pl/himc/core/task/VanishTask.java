package pl.himc.core.task;

import pl.himc.api.utils.bukkit.Actionbar;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.CorePlugin;
import pl.himc.core.manager.VanishManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class VanishTask implements Runnable {

    public VanishTask(CorePlugin plugin){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 20, 20);
    }

    @Override
    public void run() {
        for(Player player : VanishManager.getVanished()){
            new Actionbar(ChatUtil.fixColor("&cJesteś niewidoczny dla graczy")).sendToPlayer(player);
        }
    }
}
