package pl.himc.guilds.hook.bungeetablistplus.variables.player;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.entity.Player;

import java.util.Locale;

public class KDVariable extends Variable {

    public KDVariable(String name) {
        super(name);
    }

    @Override
    public String getReplacement(Player player) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        return (user == null ? "" : String.format(Locale.US, "%.2f", user.getRank().getKDR()));
    }
}
