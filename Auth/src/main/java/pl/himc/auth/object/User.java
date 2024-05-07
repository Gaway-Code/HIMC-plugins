package pl.himc.auth.object;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import pl.himc.auth.api.PluginAuth;

import java.sql.ResultSet;
import java.sql.SQLException;

public final class User {

    private final String playerName;
    private final String realPlayerName;
    private String ipAddress;
    private boolean premium;
    private String password;
    private boolean loggedIn;
    private ScheduledTask messageTask;
    private long kickLoginTime;
    private long sessionTime;
    private boolean changes;

    public User(final String player){
        this.playerName = player.toLowerCase();
        this.realPlayerName = player;
        this.premium = PluginAuth.isPremium(player);
        this.password = "brak";
        this.changes = true;
    }

    public User(final ResultSet rs) throws SQLException {
        this.playerName = rs.getString("playerName");
        this.realPlayerName = rs.getString("realPlayerName");
        this.ipAddress = rs.getString("ip");
        this.premium = rs.getBoolean("premium");
        this.password = rs.getString("password");
        this.changes = false;

        PluginAuth.getPlugin().getUserData().createUser(this.playerName, this);
    }

    public String getInsert(){
        String s = "INSERT INTO `" + PluginAuth.getPlugin().getConfiguration().databaseMySQLTable + "` VALUES(" +
                "'%playerName%'," +
                "'%realPlayerName%'," +
                "'%ip%'," +
                "'%premium%'," +
                "'%password%'" +
                ") ON DUPLICATE KEY UPDATE " +
                "ip='%ip%'," +
                "premium='%premium%'," +
                "password='%password%';";
        s = s.replace("%playerName%", this.playerName);
        s = s.replace("%realPlayerName%", this.realPlayerName);
        s = s.replace("%ip%", this.ipAddress == null ? "" : this.ipAddress);
        s = s.replace("%premium%", this.premium + "");
        s = s.replace("%password%", this.password);
        return s;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getRealPlayerName() {
        return realPlayerName;
    }

    public boolean checkNick(final String nick){
        return this.realPlayerName.equals(nick);
    }

    public boolean isPremium() {
        return premium;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public String getPassword() {
        return password;
    }

    public boolean isChanges() {
        return changes;
    }

    public void setPassword(String password) {
        this.password = password;
        this.changes = true;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
        this.changes = true;
    }

    public void checkIpAddress(String ipAddress) {
        if(ipAddress.equals(this.ipAddress)) return;
        this.ipAddress = ipAddress;
        this.changes = true;
    }
    public String getIp(){
        return this.ipAddress;
    }

    public void setMessageTask(final ScheduledTask task){
        this.messageTask = task;
    }

    public void setChanges(boolean changes) {
        this.changes = changes;
    }

    public void setKickLoginTime(long loginTime) {
        this.kickLoginTime = loginTime;
    }

    public boolean isSession(){
        return this.sessionTime >= System.currentTimeMillis();
    }

    public boolean isKickLoginTime(){
        return System.currentTimeMillis() >= this.kickLoginTime;
    }

    public void loggedPlayer(final ProxiedPlayer player){
        this.loggedIn = true;
        this.checkIpAddress(player.getAddress().getAddress().getHostAddress());
        if(!this.premium){
            if (this.messageTask != null) {
                this.messageTask.cancel();
                this.messageTask = null;
            }
            if(!this.isSession()){
                this.sessionTime = System.currentTimeMillis() + 1800000;
            }
        }
    }

    public void disconnectPlayer(){
        this.loggedIn = false;
        if(!this.premium){
            if (this.messageTask != null) {
                this.messageTask.cancel();
                this.messageTask = null;
            }
        }
    }

    public void delete(){
        PluginAuth.getPlugin().getUserData().deleteUser(this);
    }
}
