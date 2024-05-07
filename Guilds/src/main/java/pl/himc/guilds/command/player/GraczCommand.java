package pl.himc.guilds.command.player;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class GraczCommand extends PlayerCommand {

    public GraczCommand() {
        super("gracz", null, "/gracz <Nick>", "staty");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 1) {
            GuildUser u = PluginGuild.getPlugin().getUserManager().getUser(player);
            u.sendInfo(player);
            return false;
        }
        GuildUser u = PluginGuild.getPlugin().getUserManager().getUser(args[0]);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (u == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsNotExists);
        }
        u.sendInfo(player);
        return true;
    }
}
