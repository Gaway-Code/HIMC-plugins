package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.rank.RankManager;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;

public class PointsCommand extends PlayerCommand {

    public PointsCommand(String permission) {
        super("points", permission, "/ga punkty <Gracz> <Ilosc>", "punkty");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 3) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!IntegerUtil.isInteger(args[2])) {
            return ChatUtil.sendMessage(player, messageConfiguration.invalidInteger);
        }
        int points = Integer.parseInt(args[2]);
        /*
        if (points < 0) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaNumberGreaterThanZero);
        }

         */
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (user == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
        }
        user.getRank().setPoints(points);

        RankManager.getInst().update(user);

        return ChatUtil.sendMessage(player, messageConfiguration.gaPlayerPointsSet
                .replace("{PLAYER}", user.getName())
                .replace("{POINTS}", Integer.toString(user.getRank().getPoints())));
    }
}
