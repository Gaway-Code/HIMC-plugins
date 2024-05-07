package pl.himc.guilds.command.player.guild;

import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;

public class ResetRankCommand extends PlayerCommand {

    public ResetRankCommand() {
        super("resetrank", null, "");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser u = PluginGuild.getPlugin().getUserManager().getUser(args[0]);
        u.getRank().resetRank();
        return ChatUtil.sendMessage(player, "&a&oRanking został zresetowany!");
    }
}
