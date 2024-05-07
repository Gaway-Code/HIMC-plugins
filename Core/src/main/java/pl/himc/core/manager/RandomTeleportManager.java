package pl.himc.core.manager;

import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.RandomUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.guilds.api.PluginGuild;

import java.util.ArrayList;
import java.util.List;

public final class RandomTeleportManager {


    public static void teleport(Player player){
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();

        int x = RandomUtil.getRandInt(pluginConfiguration.randomTpMin, pluginConfiguration.randomTpMax);
        int z = RandomUtil.getRandInt(pluginConfiguration.randomTpMin, pluginConfiguration.randomTpMax);
        double y = player.getWorld().getHighestBlockYAt(x, z) + 1.5f;
        Location loc = new Location(player.getWorld(), x, y, z);

        if(PluginGuild.getPlugin().getGuildManager().getLocation(loc) != null){
            teleport(player);
            return;
        }

        Biome biome = loc.getBlock().getBiome();
        if (biome == Biome.OCEAN || biome == Biome.DEEP_OCEAN) {
            teleport(player);
            return;
        }
        player.teleport(loc);
        ChatUtil.sendMessage(player, messageConfiguration.randomTpSuccess);
    }

    public static void grupoweTP(Player clicked, List<Player> playerList){
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        int x = RandomUtil.getRandInt(pluginConfiguration.randomTpMin, pluginConfiguration.randomTpMax);
        int z = RandomUtil.getRandInt(pluginConfiguration.randomTpMin, pluginConfiguration.randomTpMax);
        double y = clicked.getWorld().getHighestBlockYAt(x, z) + 1.5f;
        Location loc = new Location(clicked.getWorld(), x, y, z);
        if (loc.getBlock().getBiome() == Biome.OCEAN || loc.getBlock().getBiome() == Biome.DEEP_OCEAN) {
            ChatUtil.sendMessage(clicked, messageConfiguration.randomTpOcean);
            return;
        }

        if(PluginGuild.getPlugin().getGuildManager().getLocation(loc) != null){
            grupoweTP(clicked, playerList);
            return;
        }

        clicked.teleport(loc);
        Location ploc = new Location(clicked.getWorld(), clicked.getLocation().getX(), clicked.getLocation().getY(), clicked.getLocation().getZ());
        ploc.setY(clicked.getLocation().getY() + 5.0D);
        for(Player players : playerList){
            players.teleport(ploc);
            ChatUtil.sendMessage(players, messageConfiguration.randomTpSuccess);
        }
    }
    
    public static List<Player> getPlayersInRadius(Location location, int size) {
        List<Player> players = new ArrayList<>();
        for (Player p : location.getWorld().getPlayers()) {
            if (location.distance(p.getLocation()) <= size) {
                players.add(p);
            }
        }
        return players;
    }
    public static List<Player> getPlayersInRadius(Player player, int size) {
        List<Player> players = new ArrayList<>();
        for (Player p : player.getWorld().getPlayers()) {
            if(p == player) continue;
            if (player.getLocation().distance(p.getLocation()) <= size) {
                players.add(p);
            }
        }
        return players;
    }
}
