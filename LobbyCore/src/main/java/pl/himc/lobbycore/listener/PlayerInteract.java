package pl.himc.lobbycore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import pl.himc.lobbycore.LobbyPlugin;

public class PlayerInteract implements Listener {

    public PlayerInteract(final LobbyPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final LobbyPlugin plugin;

    @EventHandler
    public void onClick2(PlayerInteractEvent e){
        if(e.getAction().equals(Action.LEFT_CLICK_AIR) ||
                e.getAction().equals(Action.LEFT_CLICK_BLOCK) ||
                e.getAction().equals(Action.RIGHT_CLICK_AIR) ||
                e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Player p = e.getPlayer();
            ItemStack is = p.getItemInHand();
            if(is !=null){
                if(is.getType() == Material.COMPASS){
                    this.plugin.getServerManager().getServersGui(p);
                }
            }
        }
    }
}