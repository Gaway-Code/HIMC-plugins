package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class DeleteCommand extends PlayerCommand {
    public DeleteCommand(String permission) {
        super("delete", permission, "/ga usun <Tag>", "usun");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaGuildNotExists);
        }
        String tag = guild.getTag();
        String name = guild.getName();
        PluginGuild.getPlugin().getGuildManager().deleteGuild(guild);

        ChatUtil.sendBroadcast(messageConfiguration.guildDeleteBroadcast
                .replace("{TAG}", tag)
                .replace("{GUILD}", name)
                .replace("{PLAYER}", player.getName()));

        return ChatUtil.sendMessage(player, messageConfiguration.gaGuildDeleteSuccess.replace("{GUILD}", name).replace("{TAG}", tag));
    }
}
