package pl.himc.core.base.warp;

import org.bukkit.configuration.ConfigurationSection;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.CorePlugin;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.util.stream.Collectors.joining;

public final class WarpData {

    private final Map<String, Location> warps;
    private final CorePlugin plugin;

    public WarpData(final CorePlugin plugin){
        this.warps = new HashMap<>();
        this.plugin = plugin;
    }

    public Location getWarp(String name){
        for (ConcurrentHashMap.Entry<String, Location> warp : this.warps.entrySet()) {
            if(warp.getKey().equalsIgnoreCase(name)) return warp.getValue();
        }
        return null;
    }

    public String getWaprs(){
        return this.warps.keySet().stream().map(location -> "" + location).collect(joining(", "));
    }

    public void addWarp(Player p, String name){
        if(this.getWarp(name) !=null){
            ChatUtil.sendMessage(p, this.plugin.getPluginMessages().warpAlreadyExists);
            return;
        }
        this.warps.put(name, p.getLocation());
        this.plugin.getPluginConfig().warps.put(name, StringUtil.locationLongToString(p.getLocation()));
        this.plugin.savePluginConfiguration();
        ChatUtil.sendMessage(p, this.plugin.getPluginMessages().warpCreateSuccess.replace("{WARP}", name));
    }

    public void removeWarp(Player p, String name){
        if(this.getWarp(name) == null){
            ChatUtil.sendMessage(p, this.plugin.getPluginMessages().warpNotExists);
            return;
        }
        this.warps.remove(name);
        this.plugin.getPluginConfig().warps.remove(name);
        this.plugin.savePluginConfiguration();
        ChatUtil.sendMessage(p, this.plugin.getPluginMessages().warpRemoveSuccess.replace("{WARP}", name));
    }

    public void loadWarps(){
        for(Map.Entry<String, String> warps :  this.plugin.getPluginConfig().warps.entrySet()){
            this.warps.put(warps.getKey(), StringUtil.locationFromString(warps.getValue()));
        }
    }
}
