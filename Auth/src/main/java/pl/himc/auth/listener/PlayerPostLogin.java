package pl.himc.auth.listener;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import net.md_5.bungee.event.EventHandler;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;
import pl.himc.auth.util.ChatUtil;

import java.util.concurrent.TimeUnit;

public final class PlayerPostLogin implements Listener {

    public PlayerPostLogin(final AuthPlugin plugin){
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerListener(plugin, this);
    }
    private final AuthPlugin plugin;

    @EventHandler
    public void onLogin(final PostLoginEvent event){
        final ProxiedPlayer player = event.getPlayer();

        User user = this.plugin.getUserData().getOrCreate(player.getName());
        if(user.isPremium()){
            user.loggedPlayer(player);
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &7Zostałeś zalogowany z konta &aPREMIUM&7!"));
            return;
        }
        if(user.isSession() && user.getIp().equals(player.getAddress().getAddress().getHostAddress())){
            user.loggedPlayer(player);
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &7Zostałeś zalogowany z powodu &asesji logowania&7!"));
            return;
        }
        user.setKickLoginTime(System.currentTimeMillis() + 30000);
        TextComponent msg = ChatUtil.fixColor(!user.getPassword().equals("brak") ? "&8&l[&9&l!&8&l] &7Zaloguj się komendą: &a/login <hasło>" : "&8&l[&9&l!&8&l] &cZarejestruj się komendą: &a/register <hasło> <powtórz hasło>");
        player.sendMessage(msg);

        final ScheduledTask task = ProxyServer.getInstance().getScheduler().schedule(this.plugin, () -> {
            if(user.isKickLoginTime()){
                player.disconnect(ChatUtil.fixColor("&cUpłynął czas na zalogowanie się!"));
                return;
            }
            player.sendMessage(msg);
        }, 3L, 3L, TimeUnit.SECONDS);
        user.setMessageTask(task);


    }
}
