package pl.himc.auth.task;

import net.md_5.bungee.api.ProxyServer;
import pl.himc.auth.AuthPlugin;

import java.util.concurrent.TimeUnit;

public final class AutosaveTask implements Runnable{

    public AutosaveTask(final AuthPlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getScheduler().schedule(plugin, this, 30, 30, TimeUnit.MINUTES);
    }
    private AuthPlugin plugin;

    @Override
    public void run() {
        System.out.println("[memekAuth] Automatyczny zapis kont graczy ...");
        this.plugin.getUserData().saveUsers();
    }
}
