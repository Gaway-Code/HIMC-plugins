package pl.himc.api.managers;


import net.milkbowl.vault.chat.Chat;
import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;

import static org.bukkit.Bukkit.getServer;

public final class PermissionsManager {

    private static PermissionsManager instance;

    private Chat vaultChat = null;

    public PermissionsManager(){
        instance = this;
        this.refreshVault();
    }

    private void refreshVault() {
        Chat vaultChat = getServer().getServicesManager().load(Chat.class);
        if (vaultChat != this.vaultChat) {
            PluginApi.getApi().getConsoleSender().info("New Vault Chat implementation registered: " + (vaultChat == null ? "null" : vaultChat.getName()));
        }
        this.vaultChat = vaultChat;
    }

    public String getPrefix(final Player player){
        return this.vaultChat.getPlayerPrefix(player);
    }
    public String getSuffix(final Player player){
        return this.vaultChat.getPlayerSuffix(player);
    }
    public boolean playerHasPermission(Player player, String permission){
        return player.hasPermission(permission);
    }

    public static PermissionsManager getInstance() {
        if(instance == null){
            return new PermissionsManager();
        }
        return instance;
    }
}
