package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class KickCommand extends PlayerCommand {

    public KickCommand() {
        super("kick", null, "/g wyrzuc <Gracz>", "wyrzuc");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        Guild guild = user.getGuild();
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        if (!user.hasGuildPermission(GuildPermission.MEMBER_KICK)) {
            return ChatUtil.sendMessage(player, messageConfiguration.noHasPermKickMembers);
        }
        GuildUser kickUser = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (kickUser == null || !guild.isMember(kickUser)) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotMemberInYourGuild);
        }
        if (guild.isOwner(kickUser)) {
            return ChatUtil.sendMessage(player, messageConfiguration.kickPlayerIsOwnerGuild);
        }
        ChatUtil.sendMessage(player, messageConfiguration.kickMemberFromGuild.replace("{PLAYER}", kickUser.getName()));

        Player kickPlayer = kickUser.getPlayer();
        if (kickPlayer != null) {
            ChatUtil.sendMessage(kickPlayer, messageConfiguration.kickFromGuildToKicked.replace("{TAG}", guild.getTag()));
        }
        PluginGuild.getPlugin().getGuildManager().removeMember(guild, kickUser);

        return ChatUtil.sendBroadcast(messageConfiguration.kickFromGuildBroadcast
                .replace("{PLAYER}", kickUser.getName())
                .replace("{GUILD}", guild.getName())
                .replace("{TAG}", guild.getTag()));
    }
}
