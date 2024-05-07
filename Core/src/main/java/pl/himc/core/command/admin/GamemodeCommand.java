package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.CommandAPI;

public final class GamemodeCommand extends CommandAPI {

    public GamemodeCommand(String permission) {
        super("gamemode", permission, "/gamemode <Tryb> <Gracz>","gm", "creative", "survival", "spec");
    }

    public void setGamemode(Player p, GameMode mode, CommandSender changer) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        p.setGameMode(mode);
        p.setAllowFlight(mode == GameMode.CREATIVE || mode == GameMode.SPECTATOR);
        if (changer == null) {
            ChatUtil.sendMessage(p, messageConfiguration.gamemodeSet.replace("{GAMEMODE}", mode.toString()));
        }
        else {
            String c = changer.getName().equalsIgnoreCase("CONSOLE") ? "konsole" : changer.getName();
            ChatUtil.sendMessage(p, messageConfiguration.gamemodeSetOtherToPlayer.replace("{GAMEMODE}", mode.toString()).replace("{ADMIN}", c));
            ChatUtil.sendMessage(changer, messageConfiguration.gamemodeSetOtherToAdmin.replace("{GAMEMODE}", mode.toString()).replace("{PLAYER}", p.getName()));
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if (sender instanceof Player) {
            Player p = (Player)sender;
            if (args.length == 1) {
                String mode = args[0];
                if (mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("s") || mode.equalsIgnoreCase("survival")) {
                    setGamemode(p, GameMode.SURVIVAL, null);
                }
                else if (mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("creative")) {
                    setGamemode(p, GameMode.CREATIVE, null);
                }
                else if (mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("a") || mode.equalsIgnoreCase("adventure")) {
                    setGamemode(p, GameMode.ADVENTURE, null);
                }
                else if (mode.equalsIgnoreCase("3") || mode.equalsIgnoreCase("spec")) {
                    setGamemode(p, GameMode.SPECTATOR, null);
                }
                else {
                    ChatUtil.sendMessage(p, messageConfiguration.gamemodeInvalid);
                }
            }
            else {
                if (args.length != 2) {
                    return ChatUtil.sendUsage(sender, this.getUsage());
                }
                if (!sender.hasPermission(this.getPermission() + ".others")) {
                    return ChatUtil.sendMessage(sender, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
                }
                Player op = Bukkit.getPlayer(args[1]);
                if (op == null) {
                    return ChatUtil.sendMessage(sender, messageConfiguration.playerIsOffline);
                }
                String mode = args[0];
                if (mode.equalsIgnoreCase("0") || mode.equalsIgnoreCase("s") || mode.equalsIgnoreCase("survival")) {
                    setGamemode(op, GameMode.SURVIVAL, p);
                }
                else if (mode.equalsIgnoreCase("1") || mode.equalsIgnoreCase("c") || mode.equalsIgnoreCase("creative")) {
                    setGamemode(op, GameMode.CREATIVE, p);
                }
                else if (mode.equalsIgnoreCase("2") || mode.equalsIgnoreCase("a") || mode.equalsIgnoreCase("adventure")) {
                    setGamemode(op, GameMode.ADVENTURE, p);
                }
                else {
                    ChatUtil.sendMessage(p, messageConfiguration.gamemodeInvalid);
                }
            }
        }
        return false;
    }
}
