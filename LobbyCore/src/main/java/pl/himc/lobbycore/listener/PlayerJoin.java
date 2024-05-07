package pl.himc.lobbycore.listener;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.lobbycore.LobbyPlugin;
import pl.himc.lobbycore.data.PluginConfig;
import pl.himc.lobbycore.data.PluginMessages;

public class PlayerJoin implements Listener {

    public PlayerJoin(final LobbyPlugin plugin){
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);
    }
    private final LobbyPlugin plugin;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        e.setJoinMessage(null);

        Player p = e.getPlayer();

        p.teleport(this.plugin.getPluginConfig().cfgSpawnLocation);
        p.setAllowFlight(true);

        PluginConfig pluginConfig = this.plugin.getPluginConfig();
        PluginMessages pluginMessages = this.plugin.getPluginMessages();

        TitleAPI.sendTitle(p, pluginMessages.playerJoinTitle, pluginMessages.playerJoinSubTitle, 15, 80);

        if(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".lobbycore.joinadmin")){
            Bukkit.broadcastMessage(ChatUtil.fixColor("&8&l%> &7Administrator &6" + p.getName() + " &7dolaczyl do lobby!"));
        }

        p.getInventory().clear();
        p.getInventory().setHeldItemSlot(4);
        p.getInventory().setItem(4, new ItemBuilder(Material.COMPASS).addEnchant(Enchantment.DURABILITY, 1).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName(ChatUtil.fixColor("&7Hej &a" + p.getName() + "&7! &c&lWybierz tryb gry")).setLore("&a&lDziekujemy za gre na naszym serwerze!").getItem());

        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = sb.registerNewObjective("obj", "dummy");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatUtil.fixColor(pluginConfig.sidebarName));
        int i = pluginConfig.sidebarLines.size();
        for(String str : pluginConfig.sidebarLines){
            Score score = objective.getScore(ChatUtil.fixColor(str.replace("{PLAYER}", p.getName())));
            score.setScore(i);
            i--;
        }
        p.setScoreboard(sb);
    }
}
