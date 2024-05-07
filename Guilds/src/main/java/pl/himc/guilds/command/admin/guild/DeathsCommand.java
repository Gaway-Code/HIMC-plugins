package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;

public class DeathsCommand extends PlayerCommand {
    public DeathsCommand(String permission) {
        super("deaths", permission, "/ga zgony <Gracz> <Ilosc>", "zgony", "smierci");
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
        int deaths = Integer.parseInt(args[2]);
        if (deaths < 0) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaNumberGreaterThanZero);
        }
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (user == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
        }
        user.getRank().setDeaths(deaths);
        user.changes();
        return ChatUtil.sendMessage(player, messageConfiguration.gaPlayerDeathsSet
                .replace("{PLAYER}", user.getName())
                .replace("{DEATHS}", Integer.toString(deaths)));
    }
}
