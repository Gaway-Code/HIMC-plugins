package pl.himc.core.base.regions;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class RegionManager {

    public static Map<Player, MarkRegion> marked = new HashMap<>();


    public static void createRegion(final Player p, final String name) {
        if (marked.containsKey(p)) {
            if (marked.get(p).pointOne != null && marked.get(p).pointTwo != null) {
                MarkRegion r = marked.get(p);
                marked.remove(p);
                final Region region = new Region(name, r.pointOne, r.pointTwo);
                PluginCore.getCore().getRegionData().addRegion(region, true);
                ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().regionCreate.replace("{REGION}", name));
            }else {
                ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().regionNotMarkedPoints);
            }
        } else {
            ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().regionNotMarkedPoints);
        }
    }

    public static void MarkPointOne(final Player p, final Location l) {
        final MarkRegion r;
        if (!marked.containsKey(p)) {
            r = new MarkRegion(l, null);
        } else {
            r = marked.get(p);
            r.pointOne = l;
            marked.remove(p);
        }
        marked.put(p, r);
        ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().regionPosOneSet);
    }

    public static void MarkPointTwo(final Player p, final Location l) {
        final MarkRegion r;
        if (!marked.containsKey(p)) {
            r = new MarkRegion(null, l);
        }else {
            r = marked.get(p);
            r.pointTwo = l;
            marked.remove(p);
        }
        marked.put(p, r);
        ChatUtil.sendMessage(p, PluginCore.getCore().getPluginMessages().regionPosTwoSet);
    }
}
