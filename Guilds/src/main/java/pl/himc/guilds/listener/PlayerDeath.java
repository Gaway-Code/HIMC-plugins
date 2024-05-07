package pl.himc.guilds.listener;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.rank.RankManager;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.util.DamageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.List;

public class PlayerDeath implements Listener {

    public PlayerDeath(final GuildPlugin plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }

    private GuildPlugin plugin;

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerDeath(PlayerDeathEvent event){
        final Player victim = event.getEntity();
        final GuildUser victimUser = this.plugin.getUserManager().getUser(victim);
        event.getDrops().add(ItemsUtil.getPlayerHead(victim.getName()));
        victimUser.getRank().addDeath();
        Player killer = victim.getKiller();

        if (victimUser.getLastDamage() < System.currentTimeMillis()) {
            victimUser.getDamage().clear();
        }
        List<DamageUtil.Damage> damage = DamageUtil.sort(victimUser.getDamage());
        if (killer == null || !(killer instanceof Player)) {
            if (damage == null || damage.isEmpty()) {
                return;
            }
            killer = Bukkit.getPlayer(damage.get(0).getPlayer());
            if (killer == null) {
                return;
            }
        }
        if (victim.equals(killer)) {
            return;
        }
        GuildUser killerUser = this.plugin.getUserManager().getUser(killer);
        Player lastKill = killerUser.getLastKiller();

        PluginMessages messageConfiguration = this.plugin.getMessageConfiguration();
        if (lastKill != null && victim.equals(lastKill)) {
            ChatUtil.sendBroadcast(this.deathsMessage(0, 0, victim, killer));
            ChatUtil.sendMessage(killer, messageConfiguration.lastKillByKiller);
            return;
        }
        if (this.isSameIP(killer, victim)) {
            ChatUtil.sendBroadcast(this.deathsMessage(0, 0, victim, killer));
            ChatUtil.sendMessage(killer, messageConfiguration.victimHasSameIpKiller);
            return;
        }


        int[] rankChanges = DamageUtil.getEloValues(victimUser.getRank().getPoints(), killerUser.getRank().getPoints());

        int losepoints = rankChanges[0];
        int winpoints = rankChanges[1];

        killerUser.setLastKiller(victim);
        killerUser.getRank().addKill();
        victimUser.getRank().removePoints(losepoints);

        String asist = DamageUtil.getAsist(damage, killer.getName());
        Player asistPlayer = (asist == null) ? null : Bukkit.getPlayer(asist);

        if (asistPlayer != null) {
            GuildUser asistUser = this.plugin.getUserManager().getUser(asistPlayer);
            int asistPoints = winpoints / 3;
            asistUser.getRank().addPoints(asistPoints);
            RankManager.getInst().update(asistUser);

            int killerPoints = winpoints - asistPoints;
            killerUser.getRank().addPoints(killerPoints);
            RankManager.getInst().update(killerUser);
            messageConfiguration.parseTitle(asistPlayer, messageConfiguration.killAsistTitle.replace("{PLAYER}", victim.getName()).replace("{+}", Integer.toString(asistPoints)));
            messageConfiguration.parseTitle(killer, messageConfiguration.killPlayerTitle.replace("{PLAYER}", victim.getName()).replace("{+}", Integer.toString(asistPoints)));
            ChatUtil.sendBroadcast(this.deathsMessage(killerPoints, winpoints, victim, killer));
            ChatUtil.sendBroadcast(this.asystaMessage(asistPoints, asistPlayer.getPlayer()));
        } else {
            killerUser.getRank().addPoints(winpoints);
            RankManager.getInst().update(killerUser);

            messageConfiguration.parseTitle(killer, messageConfiguration.killPlayerTitle.replace("{PLAYER}", victim.getName()).replace("{+}", Integer.toString(losepoints)));
            ChatUtil.sendBroadcast(this.deathsMessage(winpoints, losepoints, victim, killer));
        }
        victimUser.getDamage().clear();
        RankManager.getInst().update(victimUser);
    }


    private boolean isSameIP(final Player k, final Player p) {
        return p.getAddress().getAddress().getHostAddress().equals(k.getAddress().getAddress().getHostAddress());
    }

    private String deathsMessage(final int plus, final int minus, final Player player, final Player killer) {
        String m = this.plugin.getMessageConfiguration().killPlayerBroadcast;
        m = m.replace("{+}", (plus >= 0) ? ("" + plus) : Integer.toString(plus));
        m = m.replace("{-}", (plus >= 0) ? ("" + minus) : Integer.toString(minus));
        m = m.replace("{KILLER}", killer.getName());
        m = m.replace("{VICTIM}", player.getName());
        final Guild g = this.plugin.getGuildManager().getPlayerGuild(killer);
        final Guild o = this.plugin.getGuildManager().getPlayerGuild(player);
        m = m.replace("{KTAG}", (g == null) ? "" : ("&8[&c" + g.getTag() + "&8] "));
        m = m.replace("{VTAG}", (o == null) ? "" : ("&8[&c" + o.getTag() + "&8] "));
        return m;
    }
    private String asystaMessage(final int plus, final Player player) {
        String m = this.plugin.getMessageConfiguration().killAsistBroadcast;
        m = m.replace("{+}", (plus >= 0) ? ("+" + plus) : Integer.toString(plus));
        m = m.replace("{PLAYER}", player.getName());
        final Guild g = this.plugin.getGuildManager().getPlayerGuild(player);
        m = m.replace("{TAG}", (g == null) ? "" : ("&8[&c" + g.getTag() + "&8] "));
        return ChatUtil.fixColor(m);
    }
}