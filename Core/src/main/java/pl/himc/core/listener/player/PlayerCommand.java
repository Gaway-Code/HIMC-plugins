package pl.himc.core.listener.player;

import org.bukkit.Bukkit;
import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.AntyLogout;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import pl.himc.core.command.admin.SprawdzCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class PlayerCommand implements Listener {

    public PlayerCommand(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private CorePlugin plugin;

    @EventHandler(priority = EventPriority.MONITOR)
    public void customCommand(PlayerCommandPreprocessEvent e){
        if(e.isCancelled()) return;
        this.plugin.getPluginConfig().sendCustomCommand(e);
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onCommand(PlayerCommandPreprocessEvent e){
        if(e.isCancelled()) return;
        if(AntyLogout.isAntyLogout(e.getPlayer())){
            String[] s = e.getMessage().split(" ");
            for(String enabledCommands : this.plugin.getPluginConfig().antylogoutEnableCommands){
                if(s[0].equalsIgnoreCase("/" + enabledCommands)){
                    return;
                }
            }
            e.setCancelled(true);
            ChatUtil.sendMessage(e.getPlayer(), this.plugin.getPluginMessages().antylogoutDisableCommand);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void suspect(PlayerCommandPreprocessEvent e){
        if(e.isCancelled()) return;
        if(this.plugin.getPluginConfig().blockedCmdsList.contains(e.getMessage().toLowerCase().split(" ")[0]) && !e.getPlayer().isOp()) {
            e.setCancelled(true);
            ChatUtil.sendMessage(e.getPlayer(), this.plugin.getPluginConfig().blockedCmdsMessage);
            return;
        }
        if(SprawdzCommand.isSuspect(e.getPlayer())) {
            if(e.getMessage().startsWith("/helpop")) return;
            e.setCancelled(true);
            ChatUtil.sendMessage(e.getPlayer(), this.plugin.getPluginMessages().checkSuspectNotExecuteCommands);
        }
    }
}
