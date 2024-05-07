package pl.himc.core.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.SpaceUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.craft.CraftManager;
import pl.himc.core.base.enchant.EnchantGui;
import pl.himc.core.base.regions.RegionManager;
import pl.himc.core.base.user.User;
import pl.himc.core.command.admin.RegionCommand;
import pl.himc.core.command.admin.VoucherCommand;
import pl.himc.core.command.player.EnderchestCommand;
import pl.himc.core.manager.AntyLogout;
import pl.himc.core.manager.RandomTeleportManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class PlayerInteract implements Listener {

    private final Map<String, Long> cooldown = new HashMap<>();

    @EventHandler(priority = EventPriority.LOW)
    public void onClick(PlayerInteractEvent e){
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(e.getClickedBlock() != null || e.getClickedBlock().getType() != Material.AIR) {
                if (e.getClickedBlock().getType() == Material.ENDER_CHEST) {
                    e.setCancelled(true);
                    if(AntyLogout.isAntyLogout(e.getPlayer())){
                        ChatUtil.sendMessage(e.getPlayer(), PluginCore.getCore().getPluginMessages().antyLogoutEnderchest);
                        return;
                    }

                    EnderchestCommand.open(e.getPlayer());
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void AntyCrasher(PlayerInteractEvent e) {
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(e.getClickedBlock().getType() == Material.LEVER) {
                if(e.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".antycrash.bypass")) return;
                if(cooldown.get(e.getPlayer().getName()) !=null && System.currentTimeMillis() <= cooldown.get(e.getPlayer().getName())) {
                    e.setCancelled(true);
                    ChatUtil.sendMessage(e.getPlayer(), PluginCore.getCore().getPluginMessages().antycrashLever);
                    return;
                }
                cooldown.put(e.getPlayer().getName(), System.currentTimeMillis() + TimeUtil.getTimeWithString("5s"));
            }
        }
    }

    @EventHandler(priority = EventPriority.LOW)
    public void rzucak(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if(p.getItemInHand() == null) return;
        if(p.getItemInHand().isSimilar(CraftManager.getRzucaneTnt())){
            if (e.getAction() == Action.LEFT_CLICK_AIR ||e.getAction() == Action.LEFT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR||e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().throwRzucanetnt);
                p.getLocation().setY(p.getLocation().getY() + 1.0);
                Entity entity = p.getWorld().spawn(p.getLocation(), TNTPrimed.class);
                entity.setVelocity(p.getLocation().getDirection().multiply(1.3));
                ItemsUtil.removeItemInHand(p);
            }
        }
        if(p.getItemInHand().getType() == Material.BOOK){
            if(p.getItemInHand().isSimilar(VoucherCommand.getEvipVoucher())){
                ItemsUtil.removeItemInHand(p);
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " parent addtemp evip 1d");
                TitleAPI.sendTitle(p, PluginCore.getCore().getPluginMessages().titleVoucherUsage, PluginCore.getCore().getPluginMessages().subTitleVoucherEVipUsage, 10, 70);
                return;
            }
            if(p.getItemInHand().isSimilar(VoucherCommand.getTruboDrop5())){
                ItemsUtil.removeItemInHand(p);
                User user = PluginCore.getCore().getUserManager().getUser(p);
                user.setTurboDrop(TimeUtil.getTimeWithString("5min"));
                TitleAPI.sendTitle(p, PluginCore.getCore().getPluginMessages().titleVoucherUsage, PluginCore.getCore().getPluginMessages().subTitleVoucherTurboDropUsage, 10, 70);
                return;
            }
            if(p.getItemInHand().isSimilar(VoucherCommand.getTruboDrop10())){
                ItemsUtil.removeItemInHand(p);
                User user = PluginCore.getCore().getUserManager().getUser(p);
                user.setTurboDrop(TimeUtil.getTimeWithString("10min"));
                TitleAPI.sendTitle(p, PluginCore.getCore().getPluginMessages().titleVoucherUsage, PluginCore.getCore().getPluginMessages().subTitleVoucherTurboDropUsage, 10, 70);
                return;
            }
            if(p.getItemInHand().isSimilar(VoucherCommand.getTruboDrop30s())){
                ItemsUtil.removeItemInHand(p);
                User user = PluginCore.getCore().getUserManager().getUser(p);
                user.setTurboDrop(TimeUtil.getTimeWithString("30s"));
                TitleAPI.sendTitle(p, PluginCore.getCore().getPluginMessages().titleVoucherUsage, PluginCore.getCore().getPluginMessages().subTitleVoucherTurboDropUsage, 10, 70);
                return;
            }
            if(p.getItemInHand().isSimilar(VoucherCommand.getTruboExp5())){
                ItemsUtil.removeItemInHand(p);
                User user = PluginCore.getCore().getUserManager().getUser(p);
                user.setTurboExp(TimeUtil.getTimeWithString("5min"));
                TitleAPI.sendTitle(p, PluginCore.getCore().getPluginMessages().titleVoucherUsage, PluginCore.getCore().getPluginMessages().subTitleVoucherTurboExpUsage, 10, 70);
                return;
            }
            if(p.getItemInHand().isSimilar(VoucherCommand.getTruboExp10())){
                ItemsUtil.removeItemInHand(p);
                User user = PluginCore.getCore().getUserManager().getUser(p);
                user.setTurboExp(TimeUtil.getTimeWithString("10min"));
                TitleAPI.sendTitle(p, PluginCore.getCore().getPluginMessages().titleVoucherUsage, PluginCore.getCore().getPluginMessages().subTitleVoucherTurboExpUsage, 10, 70);
            }

        }
    }

    @EventHandler
    public void randomTP(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.STONE_BUTTON)) {
            Location c1 = e.getClickedBlock().getLocation().add(1.0D, 0.0D, 0.0D);
            Location c2 = e.getClickedBlock().getLocation().add(-1.0D, 0.0D, 0.0D);
            Location c3 = e.getClickedBlock().getLocation().add(0.0D, 0.0D, 1.0D);
            Location c4 = e.getClickedBlock().getLocation().add(0.0D, 0.0D, -1.0D);
            if (c1.getBlock().getType() == Material.JUKEBOX || c2.getBlock().getType() == Material.JUKEBOX || c3.getBlock().getType() == Material.JUKEBOX || c4.getBlock().getType() == Material.JUKEBOX) {
                RandomTeleportManager.teleport(p);
            }
        }

        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock().getType() == Material.WOOD_BUTTON)) {
            Location c1 = e.getClickedBlock().getLocation().add(1.0D, 0.0D, 0.0D);
            Location c2 = e.getClickedBlock().getLocation().add(-1.0D, 0.0D, 0.0D);
            Location c3 = e.getClickedBlock().getLocation().add(0.0D, 0.0D, 1.0D);
            Location c4 = e.getClickedBlock().getLocation().add(0.0D, 0.0D, -1.0D);
            if (c1.getBlock().getType() == Material.JUKEBOX || c2.getBlock().getType() == Material.JUKEBOX || c3.getBlock().getType() == Material.JUKEBOX || c4.getBlock().getType() == Material.JUKEBOX) {
                e.setCancelled(true);
                RandomTeleportManager.grupoweTP(p, RandomTeleportManager.getPlayersInRadius(e.getClickedBlock().getLocation(), 3));
            }
        }
    }
    @EventHandler
    public void teleportStick(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if ((p.getItemInHand().getType().equals(Material.STICK)) && (p.getItemInHand().isSimilar(CraftManager.getRatunek())) && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            List<Player> players = RandomTeleportManager.getPlayersInRadius(p, 4);
            if(players.isEmpty()){
                TitleAPI.sendTitle(p, "&7", "&c&oNie ma nikogo obok Ciebie!", 1, 45);
            } else {
                Player nearPlayer = players.get(0);
                p.teleport(nearPlayer.getLocation());
                TitleAPI.sendTitle(p,"&7","&a&oZostales przeniesiony do &6&o" + nearPlayer.getName(),1, 60);
                ItemsUtil.removeItemInHand(p);
            }
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerEnchant(PlayerInteractEvent e) {
        if(e.isCancelled()) return;
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
            e.setCancelled(true);
            if (e.getPlayer().getItemInHand() != null) {
                int books = 0;
                for (Location loc : SpaceUtil.getWalls(e.getClickedBlock().getLocation(), 2, 2)) {
                    if (loc.getBlock().getType() == Material.BOOKSHELF) {
                        ++books;
                    }
                }
                EnchantGui.open(e.getPlayer(), e.getPlayer().getItemInHand(), books);
                return;
            }
            ChatUtil.sendMessage(e.getPlayer(), PluginCore.getCore().getPluginMessages().enchantErrorNullItem);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onRegion(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getPlayer().getItemInHand().isSimilar(RegionCommand.getStick())) {
            event.setCancelled(true);
            RegionManager.MarkPointTwo(event.getPlayer(), event.getClickedBlock().getLocation());
        }
        if (event.getAction().equals(Action.LEFT_CLICK_BLOCK) && event.getPlayer().getItemInHand().isSimilar(RegionCommand.getStick())) {
            event.setCancelled(true);
            RegionManager.MarkPointOne(event.getPlayer(), event.getClickedBlock().getLocation());
        }
    }
}
