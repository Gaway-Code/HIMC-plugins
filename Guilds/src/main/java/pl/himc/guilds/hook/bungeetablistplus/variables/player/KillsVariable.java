package pl.himc.guilds.hook.bungeetablistplus.variables.player;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.entity.Player;

public class KillsVariable extends Variable {

    public KillsVariable(String name) {
        super(name);
    }

    @Override
    public String getReplacement(Player player) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        return (user == null ? "" : Integer.toString(user.getRank().getKills()));
    }
}
