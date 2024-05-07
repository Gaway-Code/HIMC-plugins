package pl.himc.guilds.hook.bungeetablistplus;

import codecrafter47.bungeetablistplus.api.bukkit.BungeeTabListPlusBukkitAPI;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.hook.bungeetablistplus.variables.guild.*;
import pl.himc.guilds.hook.bungeetablistplus.variables.player.*;

public final class BungeeTabListPlusHook {

    public static void initVariableHook() {
        final GuildPlugin plugin = PluginGuild.getPlugin();
        plugin.getLog().info("Wykryto plugin BungeeTabListPlus! Funkcje tego pluginu zostana wlaczone.");

        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new PointsVariable("points"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new PositionVariable("position"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new KillsVariable("kills"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new KDVariable("kdr"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new DeathsVariable("deaths"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildTagVariable("gtag"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildPositionVariable("gposition"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildPointsVariable("gpoints"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildOnlineVariable("gonline"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildNameVariable("gname"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildLivesVariable("glives"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildKillsVariable("gkills"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildKDVariable("gkdr"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildDeathsVariable("gdeaths"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new GuildValidityVariable("gvalidity"));
        for (int i = 1; i < 20; ++i) {
            BungeeTabListPlusBukkitAPI.registerVariable(plugin, new TopPointsUserVariable("ptop-" + i, i));
            BungeeTabListPlusBukkitAPI.registerVariable(plugin, new TopGuildsPointsVariable("gtop-" + i, i));
        }
    }
}
