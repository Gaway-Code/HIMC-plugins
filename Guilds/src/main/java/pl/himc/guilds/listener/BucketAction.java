package pl.himc.guilds.listener;

import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.api.utils.bukkit.ChatUtil;

public class BucketAction implements Listener {

    public BucketAction(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.HIGH)
    public void onFill(PlayerBucketFillEvent e) {
        if (!this.isAllowed(e.getPlayer(), e.getBlockClicked().getLocation())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onEmpty(PlayerBucketEmptyEvent e) {
        if (!this.isAllowed(e.getPlayer(), e.getBlockClicked().getLocation())) {
            e.setCancelled(true);
        }
    }

    private boolean isAllowed(Player player, Location location) {
        GuildUser user = this.plugin.getUserManager().getUser(player);
        Guild guild = this.plugin.getGuildManager().getLocation(location);
        if (guild == null) return true;
        if (!guild.isMember(user)) return false;
        if (!user.hasGuildPermission(GuildPermission.FLUID_PLACEMENT)) {
            ChatUtil.sendMessage(player, this.plugin.getMessageConfiguration().noHasPermFluid);
            return false;
        }
        return true;
    }
}
