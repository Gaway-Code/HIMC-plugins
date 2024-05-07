package pl.himc.guilds.command.player.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.guilds.manager.NametagManager;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class AllyCommand extends PlayerCommand {

    public AllyCommand() {
        super("ally", null, "/g sojusz <Tag>", "sojusz");
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
        if (!guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }
        if (guild.getTag().equalsIgnoreCase(args[1])) {
            return ChatUtil.sendMessage(player, messageConfiguration.allySame);
        }
        Guild ally = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (ally == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.guildNotExists);
        }
        if (guild.isAlly(ally)) {
            return ChatUtil.sendMessage(player, messageConfiguration.allyIsAlready);
        }
        if (guild.getAlliances().size() >= pluginConfiguration.guildMaxAlly) {
            return ChatUtil.sendMessage(player, messageConfiguration.yourGuildMaxAlly.replace("{LENGHT}", Integer.toString(pluginConfiguration.guildMaxAlly)));
        }
        if (ally.getAlliances().size() >= pluginConfiguration.guildMaxAlly) {
            return ChatUtil.sendMessage(player, messageConfiguration.otherGuildMaxAlly.replace("{LENGHT}", Integer.toString(pluginConfiguration.guildMaxAlly)));
        }
        if (ally.isInvitedGuild(guild.getTag())) {
            ally.removeInvitedGuild(guild.getTag());
            ally.addAlly(guild);
            guild.addAlly(ally);
            ChatUtil.sendMessage(player, messageConfiguration.allySuccessToOwners.replace("{GUILD}", ally.getName()).replace("{TAG}", ally.getTag()));

            Player allyOwner = ally.getOwner().getPlayer();
            if (allyOwner != null) {
                ChatUtil.sendMessage(allyOwner, messageConfiguration.allySuccessToOwners.replace("{GUILD}", guild.getName()).replace("{TAG}", guild.getTag()));
            }
            NametagManager.createAlliance(guild, ally);

            return ChatUtil.sendBroadcast(messageConfiguration.allySuccessBroadcast
                    .replace("{GUILD}", guild.getName())
                    .replace("{TAG}", guild.getTag())
                    .replace("{ALLY-GUILD}", ally.getName())
                    .replace("{ALLY-TAG}", ally.getTag()));
        }
        Player allyOwner = ally.getOwner().getPlayer();
        if (allyOwner == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.allyOwnerGuildIsNotOnline);
        }
        guild.addInvitedGuild(ally.getTag());

        ChatUtil.sendMessage(player, messageConfiguration.allySendToInvite.replace("{ALLY-GUILD}", ally.getName()).replace("{ALLY-TAG}", ally.getTag()));
        messageConfiguration.allySendToInvited.forEach(s -> ChatUtil.sendMessage(allyOwner, s.replace("{TAG}", guild.getTag())));
        return false;
    }
}
