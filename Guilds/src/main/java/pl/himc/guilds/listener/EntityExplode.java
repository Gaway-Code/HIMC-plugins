package pl.himc.guilds.listener;

import org.bukkit.block.Block;
import pl.himc.guilds.base.guild.Guild;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.api.utils.SpaceUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.RandomUtil;

import java.time.LocalTime;
import java.util.List;

public class EntityExplode implements Listener {

    public EntityExplode(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onExplode2(EntityExplodeEvent e) {
        LocalTime now = LocalTime.now();
        LocalTime start = this.plugin.getPluginConfiguration().tntProtectionStartTime;
        LocalTime end = this.plugin.getPluginConfiguration().tntProtectionEndTime;

        if (this.plugin.getPluginConfiguration().guildTNTProtectionPassingMidnight ?
                now.isAfter(start) || now.isBefore(end) : now.isAfter(start) && now.isBefore(end)) {
            e.setCancelled(true);
            return;
        }

        Location loc = e.getLocation();
        Guild guild = this.plugin.getGuildManager().getLocation(loc);
        if (guild != null) {
            if (guild.isWarProtect()) {
                e.setCancelled(true);
                return;
            }
            guild.setExplode(this.plugin.getPluginConfiguration().guildAfterExplodeBuild + System.currentTimeMillis());
            guild.sendMembers(this.plugin.getMessageConfiguration().explodeTntToMembers.replace("{TIME}", TimeUtil.getDuration(guild.getExplode() - System.currentTimeMillis())));
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onExplode(EntityExplodeEvent event){
        if(event.isCancelled()) return;
        List<Block> destroyedBlocks = event.blockList();
        PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();

        List<Location> blockSphereLocations = SpaceUtil.sphere(
                event.getLocation(),
                pluginConfiguration.guildExplodeRadius,
                pluginConfiguration.guildExplodeRadius,
                false,
                true,
                0);

        if(pluginConfiguration.guildExplosionOnlyRegionGuild){
            destroyedBlocks.removeIf(block -> this.plugin.getGuildManager().getLocation(block.getLocation()) == null);
            blockSphereLocations.removeIf(location -> {
                Guild guild = this.plugin.getGuildManager().getLocation(location);
                return guild == null || guild.isWarProtect();
            });
        }

        for (Location blockLocation : blockSphereLocations) {
            Material material = blockLocation.getBlock().getType();
            if (!pluginConfiguration.explodeMaterials.containsKey(material)) {
                continue;
            }

            double explodeChance = pluginConfiguration.explodeMaterials.get(material);
            if (material == Material.STATIONARY_WATER || material == Material.STATIONARY_LAVA) {
                if (RandomUtil.chance(explodeChance)) {
                    blockLocation.getBlock().setType(Material.AIR);
                }
            } else {
                if (RandomUtil.chance(explodeChance)) {
                    blockLocation.getBlock().breakNaturally();
                }
            }
        }
    }
}
