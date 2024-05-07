package pl.himc.auth.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.auth.AuthPlugin;
import pl.himc.auth.object.User;
import pl.himc.auth.util.ChatUtil;
import pl.himc.auth.util.PasswordHash;

public final class AuthCommand extends Command {

    public AuthCommand(AuthPlugin plugin) {
        super("auth", "memek.cmd.auth");
        this.plugin = plugin;
        ProxyServer.getInstance().getPluginManager().registerCommand(plugin, this);

    }
    private AuthPlugin plugin;

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length == 0) {
            this.helpCommand(sender);
            return;
        }
        if (args[0].equalsIgnoreCase("register") && (args.length == 2 || args.length == 3)) {
            final String nick = args[1];
            User user = this.plugin.getUserData().get(nick);
            if (user != null) {
                sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cTen gracz jest już zarejestrowany!"));
                return;
            }
            user = this.plugin.getUserData().getOrCreate(nick);
            user.setPassword(PasswordHash.shaSalted(args[2], PasswordHash.createSalt(16)));
            sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aPomyślnie zarejestrowano gracza!"));
            ProxiedPlayer regPlayer = ProxyServer.getInstance().getPlayer(nick);
            if(regPlayer != null){
                regPlayer.disconnect(ChatUtil.fixColor("&c&lAdministrator Cię zarejestrował. Wejdź ponownie na serwer"));
            }
        }else if (args[0].equalsIgnoreCase("unregister") && args.length == 2) {
            final String nick = args[1];
            User user = this.plugin.getUserData().get(nick);
            if (user == null) {
                sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cTen gracz nie jest zarejestrowany."));
                return;
            }
            user.delete();
            sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aKonto gracza zostało usunięte!"));
        }else if (args[0].equalsIgnoreCase("changepass") && args.length == 3) {
            final String nick = args[1];
            User user = this.plugin.getUserData().get(nick);
            if (user == null) {
                sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cTen gracz nie jest zarejestrowany."));
                return;
            }
            user.setPassword(PasswordHash.shaSalted(args[2], PasswordHash.createSalt(16)));
            sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aHasło gracza zostało zmienione."));
            ProxiedPlayer regPlayer = ProxyServer.getInstance().getPlayer(nick);
            if(regPlayer != null){
                regPlayer.disconnect(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cAdministrator zmienił Ci hasło do konta. Wejdź ponownie na serwer."));
            }
        }else if (args[0].equalsIgnoreCase("premium") && args.length == 2) {
            final String nick = args[1];
            User user = this.plugin.getUserData().get(nick);
            if (user == null) {
                sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &cTen gracz nie jest zarejestrowany!"));
                return;
            }
            user.setPremium(!user.isPremium());
            sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aZmieniono typ konta gracza na &6" + user.isPremium() + "&a!"));
            ProxiedPlayer regPlayer = ProxyServer.getInstance().getPlayer(nick);
            if(regPlayer != null){
                regPlayer.disconnect(ChatUtil.fixColor("&cAdministrator zmienił Ci typ konta. Wejdź ponownie na serwer."));
            }
        }else if (args[0].equalsIgnoreCase("multiip") && args.length == 2) {
            final String ip = args[1];
            sender.sendMessage(ChatUtil.fixColor("&aGracze ktorzy maja ip &6" + ip + "&a:"));
            this.plugin.getUserData().getUsersByIP(ip).forEach(user -> sender.sendMessage(ChatUtil.fixColor("&7" + user.getRealPlayerName())));

        }else if (args[0].equalsIgnoreCase("multinick") && args.length == 2) {
            final String nick = args[1];
            User user = this.plugin.getUserData().get(nick);
            if (user == null) {
                sender.sendMessage(ChatUtil.fixColor("&cTen gracz nie jest zarejestrowany!"));
                return;
            }
            sender.sendMessage(ChatUtil.fixColor("&aMultikonta gracza &6" + nick + "&a:"));
            this.plugin.getUserData().getUsersByIP(user.getIp()).forEach(users -> sender.sendMessage(ChatUtil.fixColor("&7" + users.getRealPlayerName())));
        }else if (args[0].equalsIgnoreCase("reload")) {
            this.plugin.loadConfiguration();
            sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aNowa konfiguracja pluginu została wczytana!"));
        }else if (args[0].equalsIgnoreCase("save")) {
            long start = System.currentTimeMillis();
            this.plugin.getUserData().saveUsers();
            sender.sendMessage(ChatUtil.fixColor("&8&l[&9&l!&8&l] &aZapisano wszystkie konta!"));
            sender.sendMessage(ChatUtil.fixColor("&aZapis trwal &6" + (System.currentTimeMillis() - start) + " ms."));
        }else {
            this.helpCommand(sender);
        }
    }

    private void helpCommand(final CommandSender sender){
        sender.sendMessage(ChatUtil.fixColor("&6/auth register <nick> [haslo] &7- Rejestruje nowego gracza"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth unregister <nick> &7- Usuwa konto gracza"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth changepass <nick> <haslo> &7- Zmienia hasło gracza"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth premium <nick> &7- Zmienia typ logowania dla danego gracza (premium/non-premium)"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth multiip <ip> &7- Sprawdzanie zarejestrowanych kont na IP"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth multinick <nick> &7- Sprawdzanie multikont gracza"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth reload &7- Przeładowanie konfiguracji pluginu"));
        sender.sendMessage(ChatUtil.fixColor("&6/auth save &7- Zapisuje wszystkie konta do bazy danych"));
    }
}
