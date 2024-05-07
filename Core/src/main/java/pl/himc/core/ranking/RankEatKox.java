package pl.himc.core.ranking;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class RankEatKox {

    private static Comparator<User> comparator = (rank1, rank) -> {
        int i = Integer.compare(rank.getEatKox(), rank1.getEatKox());
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

    private static List<User> users = Collections.synchronizedList(new ArrayList<>());

    public static void update(){
        for(User user : PluginCore.getCore().getUserManager().getUsers()){
            if(!users.contains(user)){
                users.add(user);
            }
        }

        synchronized (users) {
            users.sort(comparator);
        }
    }

    public static User getUser(int top){
        if (top - 1 < users.size()) {
            return users.get(top - 1);
        }
        return null;
    }
}
