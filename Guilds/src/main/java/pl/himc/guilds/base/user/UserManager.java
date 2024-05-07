package pl.himc.guilds.base.user;

import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.guilds.GuildPlugin;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginConfig;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class UserManager {

    public UserManager(GuildPlugin plugin) {
        this.plugin = plugin;
        this.users = new HashMap<>();
    }

    private final Map<String, GuildUser> users;
    private final GuildPlugin plugin;

    public Collection<GuildUser> getUsers() {
        return users.values();
    }

    public void putUser(String name, GuildUser user) {
        users.put(name, user);
    }

    public GuildUser getUser(Player player) {
        return this.users.computeIfAbsent(player.getName(), user -> new GuildUser(player));
    }

    public GuildUser getUser(String name) {
        return getUsers().parallelStream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Set<GuildUser> getUsers(Collection<String> names) {
        return names.stream().map(this::getUser).collect(Collectors.toSet());
    }

    public Set<String> getNames(Collection<GuildUser> users) {
        return users.stream().map(GuildUser::getName).collect(Collectors.toSet());
    }

    public void setGuild(Collection<GuildUser> users, Guild guild) {
        for (GuildUser user : users) {
            user.setGuild(guild);
        }
    }

    public Set<String> getOnlineNames(Collection<GuildUser> users) {
        Set<String> set = new HashSet<>();

        for (GuildUser user : users) {
            set.add(user.isOnline() ? "&a" + user.getName() + "" : "&7" + user.getName());
        }

        return set;
    }

    public void loadUsers() {
        final PluginConfig pluginConfiguration = this.plugin.getPluginConfiguration();
        String sb = "CREATE TABLE IF NOT EXISTS `" + pluginConfiguration.database.tableUsers + "` (" +
                "NAME varchar(50) NOT NULL," +
                "KILLS INT NOT NULL," +
                "DEATHS INT NOT NULL," +
                "POINTS INT NOT NULL," +
                "GUILD varchar(50) NOT NULL," +
                "GUILDPERMISSIONS TEXT NOT NULL," +
                "ENEMYALERT TEXT NOT NULL," +
                "PRIMARY KEY(NAME));";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + pluginConfiguration.database.tableUsers + "`", user -> {
            int i = 0;
            try {
                while (user.next()) {
                    new GuildUser(user);
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            PluginGuild.getPlugin().getLog().database("[INFO] Pobrano " + i + " graczy!");
        });
    }

    public void saveUsers() {
        int i = 0;
        for (GuildUser user : getUsers()) {
            if (!user.isChanges()) continue;
            PluginApi.getApi().getDatabaseManager().executeUpdate(user.getSQL());
            user.setChanges(false);
            i++;
        }
        PluginGuild.getPlugin().getLog().database("[INFO] Zapisano " + i + " graczy!");
    }
}
