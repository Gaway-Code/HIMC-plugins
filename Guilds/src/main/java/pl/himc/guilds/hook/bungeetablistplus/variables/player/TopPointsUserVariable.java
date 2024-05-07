package pl.himc.guilds.hook.bungeetablistplus.variables.player;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.rank.RankManager;
import pl.himc.guilds.base.user.GuildUser;
import org.bukkit.entity.Player;

public class TopPointsUserVariable extends Variable {
    private int i;

    public TopPointsUserVariable(String name, int i) {
        super(name);
        this.i = i;
    }

    @Override
    public String getReplacement(Player player) {
        String format = "";
        if (i <= RankManager.getInst().users()) {
            //User u = RankManager.getInst().getUser(i - 1);
            GuildUser u = RankManager.getInst().getUser(i);
            format = PluginGuild.getPlugin().getPluginConfiguration().tablistTopFormatPlayer;
            format = format.replace("{NAME}", (u.isOnline() ? "&a" : "&7") + u.getName());
            format = format.replace("{POINTS}", Integer.toString(u.getRank().getPoints()));
        }
        return format;
    }
}
