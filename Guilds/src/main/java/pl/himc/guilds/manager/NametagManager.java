package pl.himc.guilds.manager;

import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.guilds.api.PluginGuild;

public class NametagManager {

    private static String parse(String color, Guild g) {
        if (g == null) {
            return ChatUtil.fixColor(color);
        }
        String msg = PluginGuild.getPlugin().getPluginConfiguration().scoreboardFormat;
        msg = msg.replace("{TAG}", g.getTag());
        msg = msg.replace("{COLOR}", color);
        return ChatUtil.fixColor(msg);
    }

    public static void initPlayer(Player p) {
        if (p == null) return;
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Guild g = PluginGuild.getPlugin().getGuildManager().getPlayerGuild(p);
        for (Guild o : PluginGuild.getPlugin().getGuildManager().getGuilds()) {
            Team t = sb.getTeam(o.getTag());
            if (t == null) {
                t = sb.registerNewTeam(o.getTag());
            }
            if (g == null) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorEnemy, o));
            } else if (g.getTag().equalsIgnoreCase(o.getTag())) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorFriend, o));
            } else if (o.isAlly(g)) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorAlliance, o));
            } else {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorEnemy, o));
            }
        }
        Team noguild = sb.getTeam("noguild");
        if (noguild == null) {
            noguild = sb.registerNewTeam("noguild");
            noguild.setAllowFriendlyFire(true);
            noguild.setCanSeeFriendlyInvisibles(false);
            noguild.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorNoGuild, null));
        }

        p.setScoreboard(sb);
        for (Player online : Bukkit.getOnlinePlayers()) {
            //Util.sendInfo("Ustawiam scoreboard " + online); DEBUG
            online.getScoreboard().getTeam((g != null ? g.getTag() : "noguild")).addEntry(p.getName());
            Guild onlineguild = PluginGuild.getPlugin().getGuildManager().getPlayerGuild(online);
            p.getScoreboard().getTeam((onlineguild != null ? onlineguild.getTag() : "noguild")).addEntry(online.getName());
        }
    }

    public static void createGuild(Guild g, GuildUser u) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            Scoreboard sb = o.getScoreboard();
            Team t = sb.registerNewTeam(g.getTag());
            if (o == u.getPlayer()) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorFriend, g));
            } else {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorEnemy, g));
            }
            t.addEntry(u.getName());
        }
    }

    public static void removeGuild(Guild g) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Scoreboard sb = p.getScoreboard();
            sb.getTeam(g.getTag()).unregister();
            Team noguild = sb.getTeam("noguild");
            for (GuildUser u : g.getOnlineMembers()) {
                Player guildplayer = u.getPlayer();
                noguild.addEntry(guildplayer.getName());
            }
        }
    }

    public static void joinToGuild(Guild g, GuildUser u) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.getScoreboard().getTeam(g.getTag()).addEntry(u.getName());
        }
        u.getPlayer().getScoreboard().getTeam(g.getTag()).setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorFriend, g));
    }

    public static void leaveFromGuild(Guild g, GuildUser u) {
        for (Player o : Bukkit.getOnlinePlayers()) {
            o.getScoreboard().getTeam("noguild").addEntry(u.getName());
        }
        if (u.isOnline()) {
            u.getPlayer().getScoreboard().getTeam(g.getTag()).setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorEnemy, g));
        }
    }

    public static void createAlliance(Guild g, Guild o) {
        for (GuildUser u : g.getOnlineMembers()) {
            Player p = u.getPlayer();
            Team t = p.getScoreboard().getTeam(o.getTag());
            if (t != null) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorAlliance, o));
            }
        }
        for (GuildUser u : o.getOnlineMembers()) {
            Player p = u.getPlayer();
            Team t = p.getScoreboard().getTeam(g.getTag());
            if (t != null) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorAlliance, g));
            }
        }
    }

    public static void removeAlliance(Guild g, Guild o) {
        for (GuildUser u : g.getOnlineMembers()) {
            Player p = u.getPlayer();
            Team t = p.getScoreboard().getTeam(o.getTag());
            if (t != null) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorEnemy, o));
            }
        }
        for (GuildUser u : o.getOnlineMembers()) {
            Player p = u.getPlayer();
            Team t = p.getScoreboard().getTeam(g.getTag());
            if (t != null) {
                t.setPrefix(parse(PluginGuild.getPlugin().getPluginConfiguration().scoreboardColorEnemy, g));
            }
        }
    }
}
