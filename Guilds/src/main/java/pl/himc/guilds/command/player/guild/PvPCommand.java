package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class PvPCommand extends PlayerCommand {

    public PvPCommand() {
        super("pvp", null, "/g pvp");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (user == null) {
            return false;
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.isOwner(user) && !guild.isMod(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerOrModGuild);
        }
        if (guild.isPvp()) {
            guild.setPvp(false);
            guild.sendMembers(messageConfiguration.pvpOffToMembers);
            return true;
        }
        guild.setPvp(true);
        guild.sendMembers(messageConfiguration.pvpOnToMembers);
        return true;
    }
}
