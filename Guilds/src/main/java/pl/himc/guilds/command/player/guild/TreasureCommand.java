package pl.himc.guilds.command.player.guild;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class TreasureCommand extends PlayerCommand {

    public TreasureCommand() {
        super("skarbiec", null, "/g skarbiec", "treasure");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        if (!user.hasGuildPermission(GuildPermission.OPEN_TREASURE)) {
            return ChatUtil.sendMessage(player, messageConfiguration.noHasPermOpenTreasure);
        }
        Guild guild = user.getGuild();
        guild.openTreasure(player);
        return true;
    }
}
