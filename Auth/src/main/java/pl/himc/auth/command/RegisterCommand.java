package pl.himc.auth.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;
import pl.himc.auth.util.ChatUtil;
import pl.himc.auth.util.PasswordHash;

public final class RegisterCommand extends Command {

    public RegisterCommand(AuthPlugin plugin) {
        super("register", null, "reg");
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
        if (!user.getPassword().equals("brak")) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cJesteś już zarejestrowany."));
            return;
        }
        if (args.length < 2) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cPoprawne użycie: &a/register <hasło> <powtórz hasło>"));
            return;
        }
        final String ip = player.getAddress().getAddress().getHostAddress();
        if (this.plugin.getUserData().getUsersFromIP(ip) > 5) {
            player.sendMessage(ChatUtil.fixColor("&cPrzekroczyłeś limit zarejestrowanych kont na serwerze."));
            player.sendMessage(ChatUtil.fixColor("&7Jeśli chcesz założyć konto należy"));
            player.sendMessage(ChatUtil.fixColor("&7Wejść na discorda &6https://dc.himc.pl &7i zgłosić się do administratora."));
            return;
        }

        if(!(args[0].equals(args[1]))) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cPodane hasła różnią się."));
            return;
        }
        player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aPomyślnie zarejestrowano!"));
        user.setPassword(PasswordHash.shaSalted(args[1], PasswordHash.createSalt(16)));
        user.loggedPlayer(player);
    }
}
