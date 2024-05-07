package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;


public final class TeczowyCommand extends PlayerCommand {

    public TeczowyCommand(String permission) {
        super("teczowy", permission, "");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        User user = PluginCore.getCore().getUserManager().getUser(player);
        String nick = StringUtil.rainbow(player.getName());
        user.setFakeName(nick);
        return ChatUtil.sendMessage(player, messageConfiguration.fakenameSet.replace("{NICK}", nick));
    }
}
