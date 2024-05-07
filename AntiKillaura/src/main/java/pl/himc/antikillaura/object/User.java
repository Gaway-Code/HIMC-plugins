package pl.himc.antikillaura.object;

import pl.himc.antikillaura.AntiKillauraPlugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class User {

    private final String name;
    private int attactBot;
    private long timedOut;
    private boolean notify;

    public User(final Player player){
        this.name = player.getName();
        this.attactBot = 1;
        this.timedOut = System.currentTimeMillis() + AntiKillauraPlugin.getPlugin().getPluginConfig().damageBotExpire;
        this.notify = false;
    }

    public String getName(){
        return this.name;
    }

    public void checkBan(){
        if(this.timedOut >= System.currentTimeMillis()){
            if(this.attactBot > AntiKillauraPlugin.getPlugin().getPluginConfig().damageBotAmount){
                Bukkit.getScheduler().runTask(AntiKillauraPlugin.getPlugin(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), AntiKillauraPlugin.getPlugin().getPluginConfig().banPlayerCommand.replace("{PLAYER}", this.name)));
                this.deleteUser();
            }
        }
    }

    public void addDamageBot(){
        this.resetDamageBots();
        this.attactBot += 1;
        this.timedOut = System.currentTimeMillis() + AntiKillauraPlugin.getPlugin().getPluginConfig().damageBotExpire;
        this.checkBan();
    }

    public void resetDamageBots(){
        if(System.currentTimeMillis() > this.timedOut){
            this.attactBot = 0;
        }
    }

    public int getAttactBot(){
        return this.attactBot;
    }
    public void toggleNotify(){
        this.notify = !this.notify;
    }
    public boolean isNotify(){
        return this.notify;
    }
    public void setNotify(boolean notify){
        this.notify = notify;
    }
    public void deleteUser(){
        AntiKillauraPlugin.getPlugin().getUserManager().removeUser(this);
    }
}
