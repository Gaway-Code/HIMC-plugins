package pl.himc.guilds.hook.bungeetablistplus.variables.player;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.rank.RankManager;
import org.bukkit.entity.Player;

public class TopGuildsPointsVariable extends Variable {
    private final int i;

    public TopGuildsPointsVariable(String name, int i) {
        super(name);
        this.i = i;
    }

    @Override
    public String getReplacement(Player player) {
        String guild = "";
        if (i <= RankManager.getInst().guilds()) {
            //Guild g = RankManager.getInst().getGuild(i - 1);
            Guild g = RankManager.getInst().getGuild(i);
            guild = PluginGuild.getPlugin().getPluginConfiguration().tablistTopFormatGuild;
            guild = guild.replace("{TAG}", (g.getOwner().isOnline() ? "&a" : "&7") + g.getTag());
            guild = guild.replace("{POINTS}", Integer.toString(g.getRank().getPoints()));
        }
        return guild;
    }
}
