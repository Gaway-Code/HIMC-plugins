package pl.himc.guilds.command.player.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class EnlargeCommand extends PlayerCommand {

    public EnlargeCommand() {
        super("enlarge", null, "/g powieksz", "powieksz");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }
        guild.getCuboid().enlargeCuboid(player);
        return false;
    }
}
