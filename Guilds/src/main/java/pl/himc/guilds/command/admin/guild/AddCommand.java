package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class AddCommand extends PlayerCommand {

    public AddCommand(String permission) {
        super("add", permission, "/ga dodaj <Gracz> <Tag>", "dodaj");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 3) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (user == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
        }
        if (user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaPlayerHasGuild);
        }
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[2]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaGuildNotExists);
        }
        PluginGuild.getPlugin().getGuildManager().addMember(guild, user);
        return true;
    }
}
