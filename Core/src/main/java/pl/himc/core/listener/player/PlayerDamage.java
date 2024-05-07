package pl.himc.core.listener.player;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.core.CorePlugin;
import pl.himc.core.base.user.User;
import pl.himc.core.manager.AntyLogout;

public final class PlayerDamage implements Listener {

    public PlayerDamage(final CorePlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final CorePlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkEntityamage(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)) return;
        User userEntity = this.plugin.getUserManager().getUser((Player)e.getEntity());
        if(userEntity.isGodMode()){
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onDamage(EntityDamageByEntityEvent e){
        if(e.isCancelled()) return;
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
            if (damager != null) {
                AntyLogout.sendTag(damaged);
                AntyLogout.sendTag(damager);
            }
        }
    }

    @EventHandler
    public void onSwitchBall(EntityDamageByEntityEvent e){
        if(e.isCancelled()) return;
        if(e.getDamager() instanceof Projectile){
            Projectile  projectile = (Projectile) e.getDamager();
            if(projectile.getType().equals(EntityType.SNOWBALL)) {
                if(projectile.getShooter() instanceof Player){
                    if(e.getEntity() instanceof Player){
                        Player attacked = (Player) e.getEntity();
                        Player attacker = (Player) projectile.getShooter();
                        if(!attacked.equals(attacker)){
                            Location gracz1 = attacked.getLocation();
                            Location gracz2 = attacker.getLocation();
                            attacker.teleport(gracz1);
                            attacked.teleport(gracz2);
                            TitleAPI.sendTitle(attacker, this.plugin.getPluginMessages().titleSwitchBallToAttacer, this.plugin.getPluginMessages().subTitleSwitchBallToAttacer.replace("{PLAYER}", attacked.getName()), 5, 30);
                            TitleAPI.sendTitle(attacked, this.plugin.getPluginMessages().titleSwitchBallToAttacked, this.plugin.getPluginMessages().subTitleSwitchBallToAttacked.replace("{PLAYER}", attacker.getName()), 5, 30);
                        }
                    }
                }
            }
        }
    }
}
