package pl.himc.core.task;

import pl.himc.core.CorePlugin;
import org.bukkit.Bukkit;
import pl.himc.core.ranking.*;

public final class RefreshRank implements Runnable {

    public RefreshRank(CorePlugin plugin){
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 20, 20);
    }

    @Override
    public void run() {
        RankKills.update();
        RankEatKox.update();
        RankEatRefil.update();
        RankLevel.update();
        RankObsydian.update();
        RankStone.update();
    }
}
