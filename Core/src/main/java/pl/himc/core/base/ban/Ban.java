package pl.himc.core.base.ban;

import pl.himc.core.api.PluginCore;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Ban {

    private String name;
    private String ip;
    private String admin;
    private long time;
    private String reason;
    private long data;

    public Ban(String name){
        this.name = name;

        PluginCore.getCore().getBanManager().addBan(this);
    }

    public Ban(ResultSet rs){
        try{
            this.name = rs.getString("name");
            this.ip = rs.getString("ip");
            this.admin = rs.getString("admin");
            this.time = rs.getLong("time");
            this.reason = rs.getString("reason");
            this.data = rs.getLong("data");

            PluginCore.getCore().getBanManager().addBan(this);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getSQL(){

        String s = "INSERT INTO `" + PluginCore.getCore().getPluginConfig().database.tableBans + "` VALUES(" +
                "'%name%'," +
                "'%ip%'," +
                "'%admin%'," +
                "'%time%'," +
                "'%reason%'," +
                "'%data%')" +
                " ON DUPLICATE KEY UPDATE " +
                "name='%name%'," +
                "ip='%ip%'," +
                "admin='%admin%'," +
                "time='%time%'," +
                "reason='%reason%'," +
                "data='%data%';";
        s = s.replace("%name%", this.name);
        s = s.replace("%ip%", this.ip);
        s = s.replace("%admin%", this.admin);
        s = s.replace("%time%", this.time + "");
        s = s.replace("%reason%", this.reason);
        s = s.replace("%data%", this.data + "");

        return s;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIP(){
        return ip;
    }

    public void setIP(String ip){
        this.ip = ip;
    }

    public String getAdmin(){
        return admin;
    }

    public void setAdmin(String name){
        this.admin = name;
    }

    public long getTime() {
        return time;
    }

    public boolean isPerm(){
        return time == 0L;
    }

    public boolean isExpire(){
        if(isPerm()) return false;
        if(System.currentTimeMillis() >= this.time){
            PluginCore.getCore().getBanManager().removeBan(this);
            return true;
        }
        return false;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }
}
