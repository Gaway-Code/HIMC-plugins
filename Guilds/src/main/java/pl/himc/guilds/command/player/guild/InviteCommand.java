package pl.himc.guilds.command.player.guild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class InviteCommand extends PlayerCommand {

    public InviteCommand() {
        super("invite", null, "/g zapros <Nick>", "zapros", "dodaj");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginConfig pluginConfiguration = PluginGuild.getPlugin().getPluginConfiguration();
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }

        Guild guild = user.getGuild();
        if (!user.hasGuildPermission(GuildPermission.MEMBER_INVITE)) {
            return ChatUtil.sendMessage(player, messageConfiguration.noHasPermAddMembers);
        }
        Player invited = Bukkit.getPlayer(args[1]);
        if (invited == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
        }
        if (guild.isMember(invited.getName())) {
            return ChatUtil.sendMessage(player, messageConfiguration.invitedPlayerHasGuild);
        }
        GuildUser invitedUser = PluginGuild.getPlugin().getUserManager().getUser(invited);
        if (invitedUser.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.invitedPlayerHasGuild);
        }
        if (guild.getMembers().size() >= pluginConfiguration.guildMaxMembers) {
            return ChatUtil.sendMessage(player, messageConfiguration.yourGuildMaxMembers.replace("{AMOUNT}", Integer.toString(pluginConfiguration.guildMaxMembers)));
        }
        if (guild.isInvited(invited.getName())) {
            guild.removeInvited(invited.getName());
            return ChatUtil.sendMessage(player, messageConfiguration.inviteCancelledInvite);
        }
        guild.addInvited(invited.getName());
        ChatUtil.sendMessage(player, messageConfiguration.inviteSendSuccess.replace("{PLAYER}", invited.getName()));
        messageConfiguration.invteSendToInvited.forEach(s -> ChatUtil.sendMessage(invited, s
                .replace("{TAG}", guild.getTag())
                .replace("{GUILD}", guild.getName())));

        return false;
    }
}
