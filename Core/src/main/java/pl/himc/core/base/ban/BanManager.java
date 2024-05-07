package pl.himc.core.base.ban;

import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class BanManager {

    private final CorePlugin plugin;

    private final Map<String, Ban> bans = new ConcurrentHashMap<>();


    public BanManager(final CorePlugin plugin){
        this.plugin = plugin;
    }

    public Collection<Ban> getBans(){
        return this.bans.values();
    }

    public void addBan(Ban ban){
        if(getBan(ban.getName()) !=null) return;
        this.bans.put(ban.getName(), ban);
    }

    public void removeBan(Ban ban){
        if(getBan(ban.getName()) == null) return;
        PluginApi.getApi().getDatabaseManager().executeUpdate("DELETE FROM `" + plugin.getPluginConfig().database.tableBans + "` WHERE name='" + ban.getName() + "'");
        this.bans.remove(ban.getName());
    }

    public Ban getBan(String name){
        return getBans().parallelStream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void addBan(String name, CommandSender admin, long time, String reason, long data, boolean broadcast){
        if(getBan(name) !=null){
            ChatUtil.sendMessage(admin, plugin.getPluginMessages().playerIsAlreadyBanned.replace("{PLAYER}", name));
            return;
        }
        Ban ban = new Ban(name);
        ban.setIP(Bukkit.getPlayer(name) !=null ? Bukkit.getPlayer(name).getAddress().getAddress().getHostAddress() : "-");
        ban.setAdmin(admin.getName().equalsIgnoreCase("CONSOLE") ? "KONSOLA" : admin.getName());
        ban.setTime(time);
        ban.setReason(reason);
        ban.setData(data);
        bans.put(name, ban);
        PluginApi.getApi().getDatabaseManager().executeUpdate(ban.getSQL());

        if(time == 0L){
            Player banned = Bukkit.getPlayer(name);
            if(banned != null){
                banned.kickPlayer(ChatUtil.fixColor(plugin.getPluginMessages().permBanDesign.replace("{ADMIN}", admin.getName()).replace("{REASON}", reason)));
            }
            if(broadcast){
                ChatUtil.sendBroadcast(plugin.getPluginMessages().permBanBroadcast.replace("{PLAYER}", name).replace("{ADMIN}", admin.getName()).replace("{REASON}", reason));
            }
        }else{
            Player banned = Bukkit.getPlayer(name);
            if(banned != null){
                ChatUtil.sendMessage(admin, plugin.getPluginMessages().banPlayerOnline);
                banned.kickPlayer(ChatUtil.fixColor(plugin.getPluginMessages().tempBanDesign.replace("{ADMIN}", admin.getName()).replace("{DATA}", TimeUtil.getDate(time)).replace("{REASON}", reason)));
            }else{
                ChatUtil.sendMessage(admin, plugin.getPluginMessages().banPlayerOffline);
            }
            if(broadcast){
                ChatUtil.sendBroadcast(plugin.getPluginMessages().tempBanBroadcast.replace("{PLAYER}", name).replace("{ADMIN}", admin.getName()).replace("{DATA}", TimeUtil.getDate(time)).replace("{REASON}", reason));
            }
        }
    }

    public void removeBan(Ban ban, CommandSender admin){
        ChatUtil.sendBroadcast(plugin.getPluginMessages().unBanBroadcast.replace("{PLAYER}", ban.getName()).replace("{ADMIN}", admin.getName()));
        removeBan(ban);
    }

    public void unBanAll(CommandSender admin){
        this.bans.clear();
        PluginApi.getApi().getDatabaseManager().executeUpdate("DELETE FROM `" + plugin.getPluginConfig().database.tableBans + "`");
        ChatUtil.sendBroadcast(plugin.getPluginMessages().unBanAllBroadcast.replace("{ADMIN}", admin.getName()));
    }

    public void loadBans(){
        String sb = "CREATE TABLE IF NOT EXISTS `" + plugin.getPluginConfig().database.tableBans + "` (" +
                "name varchar(50) NOT NULL," +
                "ip varchar(50) NOT NULL," +
                "admin varchar(50) NOT NULL," +
                "time LONG NOT NULL," +
                "reason TEXT NOT NULL," +
                "data LONG NOT NULL," +
                "PRIMARY KEY(name));";
        PluginApi.getApi().getDatabaseManager().executeUpdate(sb);

        PluginApi.getApi().getDatabaseManager().executeQuery("SELECT * FROM `" + plugin.getPluginConfig().database.tableBans + "`", ban -> {
            int i = 0;
            try{
                while (ban.next()){
                    new Ban(ban);
                    i++;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            PluginCore.getCore().getConsoleSender().database("Pobrano " + i + " zbanowanych graczy.");
        });
    }
}
