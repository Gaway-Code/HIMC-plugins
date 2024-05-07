package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class SetHomeCommand extends PlayerCommand {

    public SetHomeCommand() {
        super("sethome", null, "/g ustawdom", "ustawdom", "ustawbaze", "ustawbaza", "setbaza");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }
        if (!guild.getCuboid().isIn(player.getLocation())) {
            return ChatUtil.sendMessage(player, messageConfiguration.setHomeOutSideRegion);
        }
        guild.setHome(player.getLocation());
        return ChatUtil.sendMessage(player, messageConfiguration.setHomeGuildSuccess);
    }
}
