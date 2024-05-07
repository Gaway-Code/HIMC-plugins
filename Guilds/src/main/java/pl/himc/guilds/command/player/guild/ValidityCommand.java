package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class ValidityCommand extends PlayerCommand {

    public ValidityCommand() {
        super("validity", null, "/g przedluz", "przedluz");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!user.hasGuildPermission(GuildPermission.GUILD_EXPIRED)) {
            return ChatUtil.sendMessage(player, messageConfiguration.noHasPermGuildValidity);
        }
        PluginGuild.getPlugin().getGuildManager().validityGuild(player, guild);
        return false;
    }
}
