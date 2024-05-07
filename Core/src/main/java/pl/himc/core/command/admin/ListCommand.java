package pl.himc.core.command.admin;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public final class ListCommand extends PlayerCommand {

    public ListCommand(String permission) {
        super("list", permission, "");
        this.onlineplayers = new ArrayList<>();
        this.onlineadmins = new ArrayList<>();
    }

    private final List<String> onlineplayers;
    private final List<String> onlineadmins;

    @Override
    public boolean onCommand(Player player, String[] args) {
        onlineplayers.clear();
        onlineadmins.clear();
        for(Player playersOnline : Bukkit.getOnlinePlayers()){
            if(playersOnline.hasPermission(this.getPermission() + ".admin")){
                onlineadmins.add(playersOnline.getName());
                continue;
            }
            onlineplayers.add(playersOnline.getName());
        }
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        messageConfiguration.onlineList.forEach(msg -> ChatUtil.sendMessage(player, msg
            .replace("{ONLINE-PLAYERS}", Integer.toString(onlineplayers.size()))
            .replace("{LIST-ONLINE-PLAYERS}", onlineplayers.toString().replace("[", "").replace("]", ""))
            .replace("{ONLINE-ADMINS}", Integer.toString(onlineadmins.size()))
            .replace("{LIST-ONLINE-ADMINS}", onlineadmins.toString().replace("[", "").replace("]", ""))));
        return false;
    }
}
