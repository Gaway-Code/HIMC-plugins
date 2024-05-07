package pl.himc.core.manager;

import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.Actionbar;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.task.AntyLogoutTask;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class AntyLogout {

    private static Map<Player, AntyLogoutTask> tasks = new HashMap<>();


    public static void disablePlugin() {
        for (Map.Entry<Player, AntyLogoutTask> entry : tasks.entrySet()) {
            AntyLogoutTask task = entry.getValue();
            task.cancel();
        }
        tasks.clear();
    }

    public static Collection<Player> getAntyLogoutPlayers() {
        return tasks.keySet();
    }

    public static void sendTag(Player... players) {
        for (Player player : players) {
            if (isTagable(player)) {
                cancelTask(player);
                tasks.put(player, new AntyLogoutTask(player));
            }
        }
    }
    public static void sendBar(Player player, String text) {
        Actionbar actionbar = new Actionbar(ChatUtil.fixColor(text));
        actionbar.sendToPlayer(player);
    }

    public static boolean isTagable(Player player) {
        if (player == null) return false;
        if (player.getGameMode() == GameMode.CREATIVE) return false;
        if (player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".antylogout.bypass")) return false;
        if(PluginCore.getCore().getUserManager().getUser(player).isGodMode()) return false;
        return true;
    }

    public static boolean isAntyLogout(Player player){
        return tasks.containsKey(player);
    }

    public static void cancelTask(Player player) {
        AntyLogoutTask task = tasks.remove(player);
        if (task != null) {
            task.cancel();
        }
    }
}
