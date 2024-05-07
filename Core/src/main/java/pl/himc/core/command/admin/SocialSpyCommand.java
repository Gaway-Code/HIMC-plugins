package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class SocialSpyCommand extends PlayerCommand {

    public SocialSpyCommand(String permission) {
        super("socialspy", permission, "/socialspy");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        User user = PluginCore.getCore().getUserManager().getUser(p);
        user.toggleSocialspy();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if (user.isEnableSocialspy()) {
            return ChatUtil.sendMessage(p, messageConfiguration.socialspyEnable);
        }
        return ChatUtil.sendMessage(p, messageConfiguration.socialspyDisable);
    }
}
