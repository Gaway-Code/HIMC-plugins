package pl.himc.guilds.hook.bungeetablistplus.variables.guild;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import org.bukkit.entity.Player;

public class GuildKillsVariable extends Variable {

    public GuildKillsVariable(String name) {
        super(name);
    }

    @Override
    public String getReplacement(Player player) {
        Guild guild = PluginGuild.getPlugin().getGuildManager().getPlayerGuild(player);
        return (guild == null ? "" : Integer.toString(guild.getRank().getKills()));
    }
}
