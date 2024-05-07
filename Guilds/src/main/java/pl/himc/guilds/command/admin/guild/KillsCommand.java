package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;

public class KillsCommand extends PlayerCommand {

    public KillsCommand(String permission) {
        super("kills", permission, "/ga zabojstwa <Gracz> <Ilosc>", "zabojstwa");
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
        int kills = Integer.parseInt(args[2]);
        if (kills < 0) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaNumberGreaterThanZero);
        }
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(args[1]);
        if (user == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
        }
        user.getRank().setKills(kills);
        user.changes();
        return ChatUtil.sendMessage(player, messageConfiguration.gaPlayerKillsSet.replace("{PLAYER}", user.getName()).replace("{KILLS}", Integer.toString(kills)));
    }
}
