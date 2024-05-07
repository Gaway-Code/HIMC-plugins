package pl.himc.auth.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;
import pl.himc.auth.util.ChatUtil;
import pl.himc.auth.util.PasswordHash;

public final class LoginCommand extends Command {

    public LoginCommand(AuthPlugin plugin) {
        super("login", null, "l");
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, this);

    }
    private AuthPlugin plugin;

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) return;
        ProxiedPlayer player = (ProxiedPlayer)sender;
        User user = this.plugin.getUserData().getOrCreate(player.getName());

        if (user.isPremium()) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cTa komenda jest wyłączona."));
            return;
        }
        if (user.isLoggedIn()) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cJesteś już zalogowany."));
            return;
        }
        if (user.getPassword().equals("brak")) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cMusisz się zarejestrować: &a/register <hasło> <powtórz hasło>"));
            return;
        }
        if (args.length == 0) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cPoprawne użycie: &a/login <hasło>"));
            return;
        }
        if (!(PasswordHash.cmpPassWithHash(args[0], user.getPassword()))) {
            player.disconnect(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cPodane hasło jest nieprawidłowe."));
        }
        player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aPomyślnie zalogowano!"));
        user.loggedPlayer(player);
    }
}
