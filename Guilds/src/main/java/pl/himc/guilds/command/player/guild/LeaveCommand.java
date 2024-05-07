package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class LeaveCommand extends PlayerCommand {

    public LeaveCommand() {
        super("leave", null, "/g opusc", "opusc", "wyjdz", "odejdz");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.leaveGuildOwner);
        }

        ChatUtil.sendMessage(player, messageConfiguration.leaveFromGuild.replace("{GUILD}", guild.getName()).replace("{TAG}", guild.getTag()));

        PluginGuild.getPlugin().getGuildManager().removeMember(guild, user);
        return ChatUtil.sendBroadcast(messageConfiguration.leavePlayerFromGuildBroadcast
                .replace("{TAG}", guild.getTag())
                .replace("{GUILD}", guild.getName())
                .replace("{PLAYER}", player.getName()));
    }
}
