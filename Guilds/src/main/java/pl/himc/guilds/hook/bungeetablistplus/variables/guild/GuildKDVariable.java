package pl.himc.guilds.hook.bungeetablistplus.variables.guild;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import org.bukkit.entity.Player;

import java.util.Locale;

public class GuildKDVariable extends Variable {

    public GuildKDVariable(String name) {
        super(name);
    }

    @Override
    public String getReplacement(Player player) {
        Guild guild = PluginGuild.getPlugin().getGuildManager().getPlayerGuild(player);
        return (guild == null ? "" : String.format(Locale.US, "%.2f", guild.getRank().getKDR()));
    }
}
