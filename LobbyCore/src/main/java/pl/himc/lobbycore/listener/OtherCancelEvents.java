package pl.himc.lobbycore.listener;

import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.lobbycore.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class OtherCancelEvents implements Listener {

    public OtherCancelEvents(final LobbyPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final LobbyPlugin plugin;

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if(!(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".lobbycore.blockbreak"))) {
            e.setCancelled(true);
            ChatUtil.sendMessage(p, this.plugin.getPluginMessages().denyBlockBreak);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if(!(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".lobbycore.blockplace"))) {
            e.setCancelled(true);
            ChatUtil.sendMessage(p, this.plugin.getPluginMessages().denyBlockPlace);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void FoodLevel(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player p = e.getPlayer();
        if(!(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".lobbycore.dropitem"))) {
            e.setCancelled(true);
            ChatUtil.sendMessage(p, this.plugin.getPluginMessages().denyDropItem);
        }
    }
}
