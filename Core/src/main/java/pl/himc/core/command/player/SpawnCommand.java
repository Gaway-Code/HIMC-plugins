package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class SpawnCommand extends PlayerCommand {

    public SpawnCommand(String permission) {
        super("spawn", permission, "/spawn <Gracz>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 1){
            if(!p.hasPermission(this.getPermission() + ".others")){
                return ChatUtil.sendMessage(p, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
            }
            Player other = Bukkit.getPlayer(args[0]);
            if(other == null){
                return ChatUtil.sendMessage(p, messageConfiguration.playerIsOffline);
            }
            if(other == p){
                PluginApi.getApi().getTeleport().sendTeleport(p, pluginConfiguration.cfgSpawnLocation, pluginConfiguration.tpTimeSpawn);
                return false;
            }
            other.teleport(pluginConfiguration.cfgSpawnLocation);
            ChatUtil.sendMessage(other, messageConfiguration.spawnTeleportOtherToPlayer.replace("{ADMIN}", p.getName()));
            return ChatUtil.sendMessage(p, messageConfiguration.spawnTeleportOtherToAdmin.replace("{PLAYER}", other.getName()));
        }
        PluginApi.getApi().getTeleport().sendTeleport(p, pluginConfiguration.cfgSpawnLocation, pluginConfiguration.tpTimeSpawn);
        return false;
    }
}