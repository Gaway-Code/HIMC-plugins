package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;


public class JoinCommand extends PlayerCommand {

    public JoinCommand() {
        super("join", null, "/g dolacz <Tag>", "dolacz", "dojdz");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginConfig pluginConfiguration = PluginGuild.getPlugin().getPluginConfiguration();
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.youHaveGuild);
        }
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.guildNotExists);
        }
        if (!guild.isInvited(player.getName())) {
            return ChatUtil.sendMessage(player, messageConfiguration.joinNotInvited);
        }
        if (guild.getMembers().size() >= pluginConfiguration.guildMaxMembers) {
            return ChatUtil.sendMessage(player, messageConfiguration.guildMaxMembers.replace("{AMOUNT}", Integer.toString(pluginConfiguration.guildMaxMembers)));
        }
        if (!ItemsUtil.hasItems(player, pluginConfiguration.guildJoinItems)) {
            return ChatUtil.sendMessage(player, StringUtil.replaceItems(pluginConfiguration.guildJoinItems));
        }

        ItemsUtil.removeItems(player, pluginConfiguration.guildJoinItems);
        PluginGuild.getPlugin().getGuildManager().addMember(guild, user);
        return true;
    }
}
