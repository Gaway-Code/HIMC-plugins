package pl.himc.bungee.data;

import net.md_5.bungee.config.Configuration;
import pl.himc.bungee.BungeePlugin;
import pl.himc.bungee.object.Whitelist;

import java.util.HashMap;
import java.util.Map;

public final class WhitelistData {

    private Map<String, Whitelist> servers;
    private BungeePlugin plugin;

    public WhitelistData(final BungeePlugin plugin){
        this.plugin = plugin;
        this.servers = new HashMap<>();
    }

    public void addWhitelist(final Whitelist whitelist){
        this.servers.put(whitelist.getServer(), whitelist);
    }

    public Whitelist getWhitelist(final String server){
        return this.servers.get(server.toLowerCase());
    }

    public Whitelist createWhitelist(final String server){
        Whitelist whitelist = new Whitelist(server);
        whitelist.setEnable(false);
        whitelist.setKickMessage("&cNie masz dostepu do tego serwera.");
        this.addWhitelist(whitelist);
        return whitelist;
    }
    public void loadWhitelist(){
        this.servers.clear();
        Configuration cs1 = BungeePlugin.getPlugin().getPluginConfiguration().getConfiguration().getSection("whitelist");

        for(String s : cs1.getKeys()){
            Whitelist whitelist = new Whitelist(s, cs1.getBoolean(s + ".enable"), cs1.getString(s + ".kickMessage"), cs1.getStringList(s + ".list"));
            this.addWhitelist(whitelist);
        }
    }
}
