package pl.himc.guilds.base.rank;

import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.Basic;
import pl.himc.guilds.base.BasicType;

public class Rank implements Comparable<Rank> {

    private BasicType type;
    private Basic basic;
    private String identityName;
    private Guild guild;
    private GuildUser user;
    private int position;
    private int points;
    private int kills;
    private int deaths;

    public Rank(Basic basic) {
        this.basic = basic;
        this.type = basic.getType();
        this.identityName = basic.getName();
        if (this.type == BasicType.GUILD) {
            this.guild = (Guild) basic;
        } else if (this.type == BasicType.USER) {
            this.user = (GuildUser) basic;
            this.points = PluginGuild.getPlugin().getPluginConfiguration().playerStartPoints;
        }
    }

    public void removePoints(int i) {
        this.points -= i;
        if (this.points < 1) {
            this.points = 0;
        }
        this.basic.changes();
    }

    public void addPoints(int i) {
        this.points += i;
        this.basic.changes();
    }

    public void addKill() {
        this.kills += 1;
        this.basic.changes();
    }

    public void addDeath() {
        this.deaths += 1;
        this.basic.changes();
    }

    @Override
    public int compareTo(Rank rank) {
        int i = Integer.compare(rank.getPoints(), getPoints());
        if (i == 0) {
            if (identityName == null) {
                return -1;
            }
            if (rank.getIdentityName() == null) {
                return 1;
            }
            i = identityName.compareTo(rank.getIdentityName());
        }
        return i;
    }

    public int getPoints() {
        if (this.type == BasicType.USER) {
            return this.points;
        } else {
            double points = 0;
            int size = guild.getMembers().size();

            if (size == 0) {
                return 0;
            }

            for (GuildUser user : guild.getMembers()) {
                points += user.getRank().getPoints();
            }

            double calc = points / size;

            if (calc != this.points) {
                this.points = (int) calc;
                this.basic.changes();
            }

            return this.points;
        }
    }

    public void setPoints(int i) {
        this.points = i;
        this.basic.changes();
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getKills() {
        if (this.type == BasicType.USER) {
            return this.kills;
        }

        int kills = 0;
        for (GuildUser user : this.guild.getMembers()) {
            kills += user.getRank().getKills();
        }

        return kills;
    }

    public void setKills(int i) {
        this.kills = i;
        this.basic.changes();
    }

    public int getDeaths() {
        if (this.type == BasicType.USER) {
            return this.deaths;
        }

        int deaths = 0;
        for (GuildUser user : this.guild.getMembers()) {
            deaths += user.getRank().getDeaths();
        }

        return deaths;
    }

    public void setDeaths(int i) {
        this.deaths = i;
        this.basic.changes();
    }

    public float getKDR() {
        if (getDeaths() == 0) {
            return getKills();
        }

        return 1.0F * getKills() / getDeaths();
    }

    public String getIdentityName() {
        return identityName;
    }

    public BasicType getType() {
        return type;
    }

    public GuildUser getUser() {
        return user;
    }

    public Guild getGuild() {
        return guild;
    }

    public Basic getBasic() {
        return basic;
    }

    public void resetRank(){
        this.points = PluginGuild.getPlugin().getPluginConfiguration().playerStartPoints;
        this.deaths = 0;
        this.kills = 0;
        this.basic.changes();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (o.getClass() != this.getClass()) {
            return false;
        }

        Rank rank = (Rank) o;

        if (rank.getType() != this.type) {
            return false;
        }

        return rank.getIdentityName().equals(this.identityName);
    }

    @Override
    public String toString() {
        return Integer.toString(getPoints());
    }
}
