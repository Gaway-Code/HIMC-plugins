package pl.himc.api.managers;

import pl.himc.api.data.PluginConfig;
import pl.himc.api.data.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitTask;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;

import java.util.HashMap;
import java.util.Map;

public final class TeleportManager {

    private Map<String, BukkitTask> players;

    public TeleportManager(){
        this.players = new HashMap<>();
    }

    public boolean isTeleport(Player player) {
        return this.players.containsKey(player.getName().toLowerCase());
    }

    public void sendTeleport(Player player, Location location, int tpTime) {
        if (this.players.containsKey(player.getName().toLowerCase())) {
            this.players.get(player.getName().toLowerCase()).cancel();
        }
        PluginMessages message = PluginApi.getApi().getPluginMessages();
        PluginConfig config = PluginApi.getApi().getPluginConfig();

        if(player.hasPermission(config.prefixPermission + ".teleport.time.bypass")){
            player.teleport(location);
            TitleAPI.sendTitle(player, message.teleportSuccessTitle, message.teleportSuccessSubTitle, 15, 70);
            return;
        }
        player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, tpTime * 40, 1));
        ChatUtil.sendMessage(player, message.teleportWait.replace("{TIME}", Integer.toString(tpTime)));

        this.players.put(player.getName().toLowerCase(),
                Bukkit.getScheduler().runTaskTimer(PluginApi.getApi(), new Runnable() {
                    Location before = player.getLocation();
                    //int i = -1;
                    int i = tpTime;
                    @Override
                    public void run() {
                        --this.i;

                        if(i == 5 || i == 4 || i == 3 || i == 2 || i == 1){
                            TitleAPI.sendTitle(player, message.teleportWaitTitle, message.teleportWaitSubTitle.replace("{TIME}", Integer.toString(i)), 15, 70);
                        }
                        if (!player.isOnline()) {
                            players.get(player.getName().toLowerCase()).cancel();
                            players.remove(player.getName().toLowerCase());
                        }
                        if (!locationEqualsBlock(this.before, player.getLocation())) {
                            player.removePotionEffect(PotionEffectType.CONFUSION);
                            players.get(player.getName().toLowerCase()).cancel();
                            players.remove(player.getName().toLowerCase());
                            TitleAPI.clearTitle(player);
                            ChatUtil.sendMessage(player, message.teleportCancel);
                            return;
                        }
                        if(this.i == 0){
                            TitleAPI.sendTitle(player, message.teleportSuccessTitle, message.teleportSuccessSubTitle, 15, 70);
                            players.get(player.getName().toLowerCase()).cancel();
                            players.remove(player.getName().toLowerCase());
                            player.teleport(location);
                            player.removePotionEffect(PotionEffectType.CONFUSION);
                            ChatUtil.sendMessage(player, message.teleportSuccess);
                        }
                    }
                }, 0L, 20L));
    }

    private boolean locationEqualsBlock(Location loc, Location loc1) {
        return loc.getBlockX() == loc1.getBlockX() && loc.getBlockY() == loc1.getBlockY() && loc.getBlockZ() == loc1.getBlockZ();
    }
}
