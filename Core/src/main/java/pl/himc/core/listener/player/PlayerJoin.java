package pl.himc.core.listener.player;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.VanishManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.TitleAPI;

public final class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        e.setJoinMessage(null);
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        TitleAPI.sendTitle(player, messageConfiguration.titleJoinPlayer, messageConfiguration.subTitleJoinPlayer, 15, 60);
        VanishManager.joinVanish(player);
        if(!player.hasPlayedBefore()) {
            ChatUtil.sendMessage(player,"&7&lWitaj &2&l"+player.getDisplayName());
            ChatUtil.sendMessage(player,"&c&oPolacz swoje konto z discordem!");
            ChatUtil.sendMessage(player,"&a&o/discord link <nick#id>");
            ChatUtil.sendMessage(player,"");
            ChatUtil.sendMessage(player,"&7Przyklad:");
            ChatUtil.sendMessage(player,"&a&o/discord link Gaway#4391");
        }
    }
}
