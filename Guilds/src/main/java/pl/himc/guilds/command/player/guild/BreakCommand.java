package pl.himc.guilds.command.player.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.manager.NametagManager;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;


public class BreakCommand extends PlayerCommand {

    public BreakCommand() {
        super("break", null, "/g rozwiaz <Tag>", "rozwiaz", "zerwij");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }
        Guild ally = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (!guild.isAlly(ally)) {
            return ChatUtil.sendMessage(player, messageConfiguration.yourGuildNotHasAlly);
        }
        ally.removeAlly(guild);
        guild.removeAlly(ally);

        ChatUtil.sendMessage(player, messageConfiguration.allyBreakToBreaked.replace("{GUILD}", ally.getName()).replace("{TAG}", ally.getTag()));

        NametagManager.removeAlliance(guild, ally);
        return ChatUtil.sendBroadcast(messageConfiguration.allyBreakBroadcast
                .replace("{TAG}", guild.getTag())
                .replace("{GUILD}", guild.getName())
                .replace("{ALLY-TAG}", ally.getTag())
                .replace("{ALLY-GUILD}", ally.getName()));
    }
}
