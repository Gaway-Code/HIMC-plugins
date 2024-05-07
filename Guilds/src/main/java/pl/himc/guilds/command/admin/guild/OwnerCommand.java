package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class OwnerCommand extends PlayerCommand {

    public OwnerCommand(String permission) {
        super("owner", permission, "/ga owner <Nick>", "lider");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            ChatUtil.sendUsage(player, this.getUsage());
            return false;
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
            return ChatUtil.sendMessage(player, messageConfiguration.gaPlayerAlreadyOwnerGuild);
        }
        guild.removeMod(user);
        guild.setOwner(user);

        return ChatUtil.sendMessage(player, messageConfiguration.gaGuildOwnerSet
                .replace("{GUILD}", guild.getName())
                .replace("{TAG}", guild.getTag())
                .replace("{PLAYER}", user.getName()));
    }
}
