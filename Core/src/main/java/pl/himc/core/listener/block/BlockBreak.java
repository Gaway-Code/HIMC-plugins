package pl.himc.core.listener.block;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.craft.stoniarka.Stoniarka;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;
import pl.himc.core.base.drop.stone.DropManager;
import pl.himc.core.base.user.User;
import pl.himc.core.command.admin.SprawdzCommand;

public final class BlockBreak implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkBlockBreak(BlockBreakEvent e){
        if(e.isCancelled()) return;
        Player p = e.getPlayer();
        if(SprawdzCommand.isSuspect(p)) {
            e.setCancelled(true);
            ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().checkSuspectNotBuild);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBreak(BlockBreakEvent e){
        if(e.isCancelled()) return;
        Player p = e.getPlayer();

        if(p.getGameMode().equals(GameMode.SURVIVAL)) {
            if(e.getBlock().getType() == Material.OBSIDIAN){
                User user = PluginCore.getCore().getUserManager().getUser(p);
                user.addObsidian(1);
            }
        }
        if(e.getBlock().getType() == Material.STONE) {
            if(p.getGameMode().equals(GameMode.SURVIVAL)) {
                DropManager.getDrop(p, e.getBlock());
            }
            ItemStack is = e.getPlayer().getItemInHand();
            Stoniarka stoniarka = StoniarkaData.getGenerator(e.getBlock().getLocation());
            if(stoniarka == null) return;
            if(is != null && is.getType() != Material.AIR && is.getType() == Material.GOLD_PICKAXE) {
                stoniarka.removeGenerator();
                ItemsUtil.giveItem(e.getPlayer(), StoniarkaData.getStoniarka().clone());
                return;
            }
            stoniarka.respawnGenerator();
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void stoniarkaSpawn(BlockBreakEvent e){
        if(e.getBlock().getType() == Material.STONE) {
            Block under = e.getBlock().getLocation().subtract(0.0, 1.0, 0.0).getBlock();
            if (under.getType().equals(Material.ENDER_STONE)) {
                new BukkitRunnable() {
                    public void run() {
                        e.getBlock().setType(Material.STONE);
                    }
                }.runTaskLater(PluginCore.getCore(), 20L);
            }
        }
        if(e.getBlock().getType() == Material.LOG) {
            Block under = e.getBlock().getLocation().subtract(0.0, 1.0, 0.0).getBlock();
            if (under.getType().equals(Material.ENDER_STONE)) {
                new BukkitRunnable() {
                    public void run() {
                        e.getBlock().setType(Material.LOG);
                    }
                }.runTaskLater(PluginCore.getCore(), 20L);
            }
        }
    }
}
