package pl.himc.api.utils.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public interface Playable extends Identifiable {

    default Player getPlayer() {
        return Bukkit.getPlayer(getName());
    }

    default void sendMessage(final String message) {
        if(getPlayer() == null || getPlayer().isOnline()) return;
        getPlayer().sendMessage(ChatUtil.fixColor(message));
    }

    default void sendMessage(final String[] message){
        if(getPlayer() == null || getPlayer().isOnline()) return;
        getPlayer().sendMessage(ChatUtil.fixColor(message));
    }

    default void sendActionBar(final String message){
        if(getPlayer() == null || getPlayer().isOnline()) return;
        new Actionbar(ChatUtil.fixColor(message)).sendToPlayer(getPlayer());
    }

    default void sendTitle(final String title, final String subTitle, final int stay){
        if(getPlayer() == null || getPlayer().isOnline()) return;
        TitleAPI.sendTitle(getPlayer(), title, subTitle, 15, stay);
    }
}
