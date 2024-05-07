package pl.himc.core.ranking;

import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class RankKills {

    private static final Comparator<GuildUser> comparator = (rank1, rank) -> {
        int i = Integer.compare(rank.getRank().getKills(), rank1.getRank().getKills());
        if (i == 0) {
            if (rank1.getName() == null) {
                return -1;
            }
            if (rank.getName() == null) {
                return 1;
            }
            i = rank1.getName().compareTo(rank.getName());
        }
        return i;
    };

    private static final List<GuildUser> users = Collections.synchronizedList(new ArrayList<>());

    public static void update(){
        for(GuildUser user : PluginGuild.getPlugin().getUserManager().getUsers()){
            if(!users.contains(user)){
                users.add(user);
            }
        }

        synchronized (users) {
            users.sort(comparator);
        }
    }

    public static GuildUser getUser(int top){
        if (top - 1 < users.size()) {
            return users.get(top - 1);
        }
        return null;
    }
}
