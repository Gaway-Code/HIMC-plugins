package pl.himc.core.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.CorePlugin;
import pl.himc.core.base.regions.Region;
import pl.himc.core.base.regions.RegionFlags;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.player.*;

public final class RegionListener implements Listener {

    public RegionListener(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final CorePlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void playerInteract(final PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            final Material type = event.getClickedBlock().getType();
            if (type.equals(Material.CHEST) || type.equals(Material.DISPENSER) || type.equals(Material.DROPPER) || type.equals(Material.WORKBENCH) || type.equals(Material.JUKEBOX) || type.equals(Material.ANVIL) || type.equals(Material.CAULDRON) || type.equals(Material.FURNACE) || type.equals(Material.ENCHANTMENT_TABLE) || type.equals(Material.STONE_BUTTON) || type.equals(Material.WOOD_BUTTON) || type.equals(Material.WOOD_DOOR) || type.equals(Material.IRON_DOOR) || type.equals(Material.TRAP_DOOR) || type.equals(Material.ENDER_CHEST) || type.equals(Material.BEACON) || type.equals(Material.WOODEN_DOOR)) {
                final Region region = this.plugin.getRegionData().getFirstRegion(event.getClickedBlock().getLocation());
                if (region == null) return;
                if (!region.flags.contains(RegionFlags.Flag.use) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.interact")) {
                    event.setCancelled(true);
                    ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyInteract.replace("{REGION}", region.getName()));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BlockBreak(final BlockBreakEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getBlock().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.blockbreak) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.blockbreak")) {
            if(region.flags.contains(RegionFlags.Flag.stoniarka)){
                Block under = event.getBlock().getLocation().subtract(0.0, 1.0, 0.0).getBlock();
                if (under.getType().equals(Material.ENDER_STONE)) return;
            }
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyBuild.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BlockPlace(final BlockPlaceEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getBlock().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.blockplace) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.blockplace")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyBuild.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void LeafDecay(final LeavesDecayEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getBlock().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.leafdecay) && region.isLocationInRegion(event.getBlock().getLocation())) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void EntityExplode(final EntityExplodeEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.explode)) {
            for (final Block b : event.blockList()) {
                if (region.isLocationInRegion(b.getLocation())) {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BucketEmpty(final PlayerBucketEmptyEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getBlockClicked().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.bucket) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.build")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyBuild.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void BucketFill(final PlayerBucketFillEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getBlockClicked().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.bucket) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.build")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyBuild.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void PaintingBreakByEntity(final HangingBreakByEntityEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getEntity().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.paintingedit)) {
            if (event.getRemover().getType().equals(EntityType.PLAYER)) {
                if ((event.getRemover()).hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.paintingedit")) {
                    return;
                }
                event.setCancelled(true);
                ChatUtil.sendMessage(event.getRemover(), this.plugin.getPluginMessages().regionDenyPaintingEdit.replace("{REGION}", region.getName()));
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void PaintingPlace(final HangingPlaceEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getEntity().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.paintingedit) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.paintingedit")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyPaintingEdit.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void PaintingEdit(final PlayerInteractEntityEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getRightClicked().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.paintingedit) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.paintingedit")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyPaintingEdit.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void CommandEvent(final PlayerCommandPreprocessEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegionFix(event.getPlayer().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.commands) && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.command")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyCommands.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void MobDamage(final EntityDamageEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getEntity().getLocation());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.pvp) && event.getEntity().getType().equals(EntityType.PLAYER)) {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void MobDamage(final EntityDamageByEntityEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegion(event.getEntity().getLocation());
        final Region region2 = this.plugin.getRegionData().getFirstRegion(event.getDamager().getLocation());
        if (region != null) {
            if (!region.flags.contains(RegionFlags.Flag.pvp) && event.getEntity().getType().equals(EntityType.PLAYER)) {
                event.setCancelled(true);
            }
        }
        if (region2 != null) {
            if (!region2.flags.contains(RegionFlags.Flag.pvp) && event.getDamager().getType().equals(EntityType.PLAYER)) {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void PlayerTeleport(final PlayerTeleportEvent event) {
        final Region region = this.plugin.getRegionData().getFirstRegionFix(event.getTo());
        if (region == null) return;
        if (!region.flags.contains(RegionFlags.Flag.enderpearl) && event.getCause() == PlayerTeleportEvent.TeleportCause.ENDER_PEARL && !event.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".region.enderpearl")) {
            event.setCancelled(true);
            ChatUtil.sendMessage(event.getPlayer(), this.plugin.getPluginMessages().regionDenyEnderPearl.replace("{REGION}", region.getName()));
        }
    }

    @EventHandler
    public void onFood(final FoodLevelChangeEvent event){
        if(!(event.getEntity() instanceof Player)) return;
        Player player = (Player)event.getEntity();
        final Region region = this.plugin.getRegionData().getFirstRegion(player.getLocation());
        if (region == null) return;
        if(region.getName().equalsIgnoreCase("spawn")){
            player.setFoodLevel(20);
            player.setHealth(player.getMaxHealth());
            event.setCancelled(true);
        }
    }
}
