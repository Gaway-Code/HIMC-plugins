package pl.himc.guilds.command.player.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class InfoCommand extends PlayerCommand {

    public InfoCommand() {
        super("info", null, "/g info <Tag>", "informacje", "statystyki");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
            if (!user.hasGuild()) {
                return ChatUtil.sendUsage(player, this.getUsage());
            }
            Guild guild = user.getGuild();
            guild.sendInfo(player);
            return true;
        } else {
            Guild guild2 = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
            if (guild2 == null) {
                PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
                return ChatUtil.sendMessage(player, messageConfiguration.guildNotExists);
            }
            guild2.sendInfo(player);
            return true;
        }
    }
}
