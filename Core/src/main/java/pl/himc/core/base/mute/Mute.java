package pl.himc.core.base.mute;

import pl.himc.core.api.PluginCore;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class Mute {

    private String name;
    private String admin;
    private long time;
    private String reason;
    private long data;

    public Mute(String name){
        this.name = name;

        PluginCore.getCore().getMuteManager().addMute(this);
    }

    public Mute(ResultSet rs){
        try{
            this.name = rs.getString("name");
            this.admin = rs.getString("admin");
            this.time = rs.getLong("time");
            this.reason = rs.getString("reason");
            this.data = rs.getLong("data");

            PluginCore.getCore().getMuteManager().addMute(this);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getSQL(){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO `" + PluginCore.getCore().getPluginConfig().database.tableMutes + "` VALUES(");
        sb.append("'%name%',");
        sb.append("'%admin%',");
        sb.append("'%time%',");
        sb.append("'%reason%',");
        sb.append("'%data%')");
        sb.append(" ON DUPLICATE KEY UPDATE ");
        sb.append("name='%name%',");
        sb.append("admin='%admin%',");
        sb.append("time='%time%',");
        sb.append("reason='%reason%',");
        sb.append("data='%data%';");

        String s = sb.toString();
        s = s.replace("%name%", this.name);
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
        if(time == 0L){
            return true;
        }else{
            return false;
        }
    }

    public boolean isExpire(){
        if(isPerm()) return false;
        if(System.currentTimeMillis() >= this.time){
            PluginCore.getCore().getMuteManager().removeMute(this);
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
