package pl.himc.guilds.base.rank;

import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RankManager {

    private static RankManager inst;

    private List<Rank> users = new ArrayList<>();
    private List<Rank> guilds = new ArrayList<>();

    public RankManager() {
        inst = this;
    }

    public void update(GuildUser user) {
        if (!this.users.contains(user.getRank())) {
            this.users.add(user.getRank());
        }

        synchronized (users) {
            Collections.sort(users);
        }

        if (user.hasGuild()) {
            update(user.getGuild());
        }

        for (int i = 0; i < users.size(); i++) {
            Rank rank = users.get(i);
            rank.setPosition(i + 1);
        }
    }

    public void update(Guild guild) {
        if (!this.guilds.contains(guild.getRank())) {
            this.guilds.add(guild.getRank());
        }

        synchronized (guilds) {
            Collections.sort(guilds);
        }

        for (int i = 0; i < guilds.size(); i++) {
            Rank rank = guilds.get(i);
            rank.setPosition(i + 1);
        }
    }

    public GuildUser getUser(int i) {
        if (i - 1 < this.users.size()) {
            return (this.users.get(i - 1)).getUser();
        }
        return null;
    }

    public Guild getGuild(int i) {
        if (i - 1 < this.guilds.size()) {
            return (this.guilds.get(i - 1)).getGuild();
        }
        return null;
    }

    public int users() {
        return this.users.size();
    }

    public int guilds() {
        return this.guilds.size();
    }

    public void remove(GuildUser user) {
        this.users.remove(user.getRank());

        synchronized (users) {
            Collections.sort(this.users);
        }
    }

    public void remove(Guild guild) {
        this.guilds.remove(guild.getRank());

        synchronized (guilds) {
            Collections.sort(this.guilds);
        }
    }

    public static RankManager getInst() {
        if (inst == null) {
            return new RankManager();
        }
        return inst;
    }
}
