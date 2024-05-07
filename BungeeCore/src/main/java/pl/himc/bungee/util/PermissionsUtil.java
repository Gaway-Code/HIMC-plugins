package pl.himc.bungee.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import pl.himc.bungee.BungeePlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class PermissionsUtil {

    public static void reloadPermissions() {
        try {
            File file = new File(BungeePlugin.getPlugin().getProxy().getPluginsFolder().getParentFile(), "config.yml");
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            for (ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
                ArrayList<String> toRemove = new ArrayList<>(all.getPermissions());
                for (String permission2 : toRemove) {
                    all.setPermission(permission2, false);
                }
                List<String> groups = cfg.getStringList("groups." + all.getName());
                if (groups.size() == 0) {
                    groups.add("default");
                }
                for (String group : groups) {
                    List<String> permissions = cfg.getStringList("permissions." + group);
                    permissions.forEach(permission -> all.setPermission(permission, true));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
