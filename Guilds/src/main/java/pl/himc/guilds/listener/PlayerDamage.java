package pl.himc.guilds.listener;

import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import pl.himc.guilds.GuildPlugin;
import pl.himc.api.utils.bukkit.ChatUtil;

public class PlayerDamage implements Listener {

    public PlayerDamage(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private final GuildPlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player) {
            Player damaged = (Player) e.getEntity();
            Player damager = null;
            if (e.getDamager() instanceof Player) {
                damager = (Player) e.getDamager();
            } else if (e.getDamager() instanceof Projectile) {
                Projectile projectile = (Projectile) e.getDamager();
                if (projectile.getShooter() instanceof Player) {
                    damager = (Player) projectile.getShooter();
                    if (damager.equals(damaged)) {
                        return;
                    }
                }
            }
            if (!(damager instanceof Player)) return;
            GuildUser damagerUser = this.plugin.getUserManager().getUser(damager);
            GuildUser playerUser = this.plugin.getUserManager().getUser(damaged);
            playerUser.setDamage(damager.getName(), e.getDamage());
            if (!damagerUser.hasGuild()) {
                return;
            }
            if (!playerUser.hasGuild()) {
                return;
            }
            Guild damagerGuild = damagerUser.getGuild();
            if (damagerGuild.isMember(playerUser)) {
                if (damagerGuild.isPvp()) {
                    return;
                }
                e.setCancelled(true);
                ChatUtil.sendMessage(damager, this.plugin.getMessageConfiguration().pvpIsOffInGuild);
            } else {
                Guild playerGuild = playerUser.getGuild();
                if (!damagerGuild.isAlly(playerGuild)) {
                    return;
                }
                e.setCancelled(true);
                ChatUtil.sendMessage(damager, this.plugin.getMessageConfiguration().pvpIsOffInAlly);
            }
        }
    }
}
