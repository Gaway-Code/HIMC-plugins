package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class OwnerCommand extends PlayerCommand {

    public OwnerCommand() {
        super("owner", null, "/g lider <Nick>", "lider", "zalozyciel", "wlasciciel");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }
        GuildUser owner = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (owner == null || !guild.isMember(owner)) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotMemberInYourGuild);
        }
        if (owner.equals(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.ownerIsAlreadyOwner);
        }
        Player ownerPlayer = owner.getPlayer();
        guild.setOwner(owner);
        ChatUtil.sendMessage(player, messageConfiguration.ownerSetSuccess.replace("{PLAYER}", owner.getName()));

        if (ownerPlayer != null && ownerPlayer.isOnline()) {
            ChatUtil.sendMessage(ownerPlayer, messageConfiguration.ownerSetSuccessToNewOwner.replace("{TAG}", guild.getTag()));
        }
        return true;
    }
}
