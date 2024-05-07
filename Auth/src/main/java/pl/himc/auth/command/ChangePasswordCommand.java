package pl.himc.auth.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;
import pl.himc.auth.util.ChatUtil;
import pl.himc.auth.util.PasswordHash;

public final class ChangePasswordCommand extends Command {

    public ChangePasswordCommand(AuthPlugin plugin) {
        super("changepassword", null, "changepass");
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
        if (!user.isLoggedIn()) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cNie jesteś zalogowany."));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cPoprawne użycie: &a/changepassword <nowe hasło> <powtórz hasło>"));
            return;
        }
        if (!(args[0].equals(args[1]))) {
            player.disconnect(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cPodane hasła różnią się!"));
        }
        user.setPassword(PasswordHash.shaSalted(args[1], PasswordHash.createSalt(16)));
        player.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aTwoje hasło zostało zmienione!"));
    }
}
