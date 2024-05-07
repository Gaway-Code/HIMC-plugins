package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class KickCommand extends PlayerCommand {

    public KickCommand(String permission) {
        super("kick", permission, "/ga wyrzuc <Gracz>", "wyrzuc");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (user == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
        }
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaPlayerNotHasGuild);
        }
        Guild guild = user.getGuild();
        if (guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaKickOwnerGuild);
        }
        PluginGuild.getPlugin().getGuildManager().removeMember(guild, user);
        return true;
    }
}
