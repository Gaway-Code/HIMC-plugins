package pl.himc.bungee.object;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import pl.himc.bungee.BungeePlugin;

import java.util.ArrayList;
import java.util.List;

public final class Whitelist {

    private final String server;
    private boolean enable;
    private String kickMessage;
    private List<String> players;

    public Whitelist(final String server){
        this.server = server;
        this.players = new ArrayList<>();
    }

    public Whitelist(final String server, final boolean enable, final String kickMessage, final List<String> players){
        this.server = server;
        this.enable = enable;
        this.kickMessage = kickMessage;
        this.players = players;
    }

    public String getServer() {
        return server;
    }

    public boolean isEnable() {
        return enable;
    }

    public String getKickMessage() {
        return kickMessage;
    }

    public List<String> getPlayers() {
        return players;
    }

    public boolean isInWhitelist(final ProxiedPlayer player){
        return this.players.contains(player.getName().toLowerCase());
    }
    public boolean isInWhitelist(final String playerName){
        return this.players.contains(playerName.toLowerCase());
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
        BungeePlugin.getPlugin().getPluginConfiguration().getConfiguration().set("whitelist." + this.server + ".enable", enable);
        BungeePlugin.getPlugin().getPluginConfiguration().save();
    }

    public void setKickMessage(String kickMessage) {
        this.kickMessage = kickMessage;
        BungeePlugin.getPlugin().getPluginConfiguration().getConfiguration().set("whitelist." + this.server + ".kickMessage", kickMessage);
        BungeePlugin.getPlugin().getPluginConfiguration().save();
    }

    public void addPlayer(final String playerName) {
        this.players.add(playerName.toLowerCase());
        BungeePlugin.getPlugin().getPluginConfiguration().getConfiguration().set("whitelist." + this.server + ".list", players);
        BungeePlugin.getPlugin().getPluginConfiguration().save();
    }
    public void removePlayer(final String playerName) {
        this.players.remove(playerName.toLowerCase());
        BungeePlugin.getPlugin().getPluginConfiguration().getConfiguration().set("whitelist." + this.server + ".list", players);
        BungeePlugin.getPlugin().getPluginConfiguration().save();
    }
}
