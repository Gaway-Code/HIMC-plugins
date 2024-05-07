package pl.himc.core.task;

import org.bukkit.Bukkit;
import pl.himc.core.CorePlugin;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;

public final class AutosaveTask implements Runnable {

    public AutosaveTask(CorePlugin plugin){
        this.plugin = plugin;

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, this, plugin.getPluginConfig().database.autoSave * 60 * 20);
    }

    private final CorePlugin plugin;

    @Override
    public void run() {
        this.plugin.getConsoleSender().info("Automatyczny zapis danych do bazy...");
        this.plugin.getUserManager().saveUsers();
        StoniarkaData.saveGenerators();
        this.plugin.getShopData().saveBazar();
    }
}
