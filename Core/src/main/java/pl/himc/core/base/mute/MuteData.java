package pl.himc.core.base.mute;

import pl.himc.core.CorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.api.ApiPlugin;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class MuteData {

    private final CorePlugin plugin;
    private final ApiPlugin api;

    private final Map<String, Mute> mutes = new ConcurrentHashMap<>();

    public MuteData(final CorePlugin plugin){
        this.plugin = plugin;
        this.api = PluginApi.getApi();
    }

    public Collection<Mute> getMutes(){
        return this.mutes.values();
    }

    public void addMute(Mute mute){
        if(getMute(mute.getName()) !=null) return;
        this.mutes.put(mute.getName(), mute);
    }

    public void removeMute(Mute mute){
        if(getMute(mute.getName()) == null) return;
        this.api.getDatabaseManager().executeUpdate("DELETE FROM `" + plugin.getPluginConfig().database.tableMutes + "` WHERE name='" + mute.getName() + "'");
        this.mutes.remove(mute.getName());
    }

    public Mute getMute(String name){
        return getMutes().parallelStream().filter(user -> user.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public void addMute(String name, CommandSender admin, long time, String reason, long data, boolean broadcast){
        if(this.getMute(name) !=null){
            ChatUtil.sendMessage(admin, plugin.getPluginMessages().playerIsAlreadyMuted.replace("{PLAYER}", name));
            return;
        }
        Mute mute = new Mute(name);
        mute.setAdmin(admin.getName().equalsIgnoreCase("CONSOLE") ? "KONSOLA" : admin.getName());
        mute.setTime(time);
        mute.setReason(reason);
        mute.setData(data);
        this.mutes.put(name, mute);
        this.api.getDatabaseManager().executeUpdate(mute.getSQL());

        if(time == 0L){
            Player banned = Bukkit.getPlayer(name);
            if(banned != null){
                ChatUtil.sendMessage(banned, plugin.getPluginMessages().permMuteDesign.replace("{ADMIN}", admin.getName()).replace("{REASON}", reason));
            }
            if(broadcast){
                ChatUtil.sendBroadcast(plugin.getPluginMessages().permMuteBroadcast.replace("{ADMIN}", admin.getName())
                        .replace("{PLAYER}", name)
                        .replace("{REASON}", reason));
            }
        }else{
            Player banned = Bukkit.getPlayer(name);
            if(banned != null){
                ChatUtil.sendMessage(banned, plugin.getPluginMessages().tempMuteDesign.replace("{ADMIN}", admin.getName())
                        .replace("{REASON}", reason)
                        .replace("{DATA}", TimeUtil.getDate(time)));
            }
            if(broadcast){
                ChatUtil.sendBroadcast(plugin.getPluginMessages().tempMuteBroadcast.replace("{ADMIN}", admin.getName())
                        .replace("{PLAYER}", name)
                        .replace("{REASON}", reason)
                        .replace("{DATA}", TimeUtil.getDate(time)));
            }
        }
    }

    public void removeMute(Mute mute, CommandSender admin){
        ChatUtil.sendBroadcast(plugin.getPluginMessages().unMuteBroadcast.replace("{PLAYER}", mute.getName()).replace("{ADMIN}", admin.getName()));
        this.removeMute(mute);
    }

    public void unMuteAll(CommandSender admin){
        this.mutes.clear();
        this.api.getDatabaseManager().executeUpdate("DELETE FROM `" + plugin.getPluginConfig().database.tableMutes + "`");
        ChatUtil.sendBroadcast(plugin.getPluginMessages().unMuteAllBroadcast.replace("{ADMIN}", admin.getName()));
    }

    public void loadMutes(){
        String sb = "CREATE TABLE IF NOT EXISTS `" + plugin.getPluginConfig().database.tableMutes + "` (" +
                "name varchar(50) NOT NULL," +
                "admin varchar(50) NOT NULL," +
                "time LONG NOT NULL," +
                "reason TEXT NOT NULL," +
                "data LONG NOT NULL," +
                "PRIMARY KEY(name));";
        this.api.getDatabaseManager().executeUpdate(sb);

        this.api.getDatabaseManager().executeQuery("SELECT * FROM `" + plugin.getPluginConfig().database.tableMutes + "`", mute -> {
            int i = 0;
            try{
                while (mute.next()){
                    new Mute(mute);
                    i++;
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            this.plugin.getConsoleSender().database("Pobrano " + i + " wyciszonych graczy.");
        });
    }
}
