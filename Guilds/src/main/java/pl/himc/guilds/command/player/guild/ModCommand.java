package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class ModCommand extends PlayerCommand {

    public ModCommand() {
        super("mod", null, "/g mod <Gracz>", "moderator", "zastepca");
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
        GuildUser modUser = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (!guild.isMember(modUser)) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotMemberInYourGuild);
        }
        if (guild.isOwner(modUser)) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOwnerGuild);
        }
        if (guild.isMod(modUser)) {
            guild.removeMod(modUser);
            ChatUtil.sendMessage(player, messageConfiguration.modRemoveSuccess.replace("{PLAYER}", modUser.getName()));
            Player modPlayer = modUser.getPlayer();
            if (modPlayer != null) {
                ChatUtil.sendMessage(modPlayer, messageConfiguration.modRemoveSuccessToDegraded.replace("{TAG}", guild.getTag()));
            }
            return true;
        }
        if (guild.getMods().size() >= pluginConfiguration.guildMaxMods) {
            return ChatUtil.sendMessage(player, messageConfiguration.maxMods.replace("{LENGHT}", Integer.toString(pluginConfiguration.guildMaxMods)));
        }
        guild.addMod(modUser);
        ChatUtil.sendMessage(player, messageConfiguration.modAddSuccess.replace("{PLAYER}", modUser.getName()));
        Player modPlayer = modUser.getPlayer();
        if (modPlayer != null) {
            ChatUtil.sendMessage(modPlayer, messageConfiguration.modAddSuccessToNewMod.replace("{TAG}", guild.getTag()));
        }
        return false;
    }
}
