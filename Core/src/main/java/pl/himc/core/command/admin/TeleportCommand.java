package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class TeleportCommand extends PlayerCommand {

    public TeleportCommand(String permission) {
        super("teleport", permission, "/teleport <Kto> <Do kogo>  lub  <Kto> <X> <Y> <Z>","tp");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        switch (args.length) {
            case 1: {
                Player other = Bukkit.getPlayer(args[0]);
                if (other == null) {
                    return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
                }
                player.teleport(other);
                ChatUtil.sendMessage(player, messageConfiguration.teleportToPlayer.replace("{PLAYER}", other.getName()));
                break;
            }
            case 2: {
                if (!player.hasPermission(this.getPermission() + ".others")) {
                    return ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
                }
                Player kto = Bukkit.getPlayer(args[0]);
                Player dokogo = Bukkit.getPlayer(args[1]);
                if (kto == null || dokogo == null) {
                    return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
                }
                kto.teleport(dokogo);
                ChatUtil.sendMessage(kto, messageConfiguration.teleportPlayerToPlayer.replace("{DOKOGO}", dokogo.getName()).replace("{ADMIN}", player.getName()));
                ChatUtil.sendMessage(player, messageConfiguration.teleportPlayerToPlayerAdmin.replace("{KTO}", kto.getName()).replace("{DOKOGO}", dokogo.getName()));

                break;
            }
            case 3: {
                if (!IntegerUtil.isInteger(args[0]) || !IntegerUtil.isInteger(args[1]) || !IntegerUtil.isInteger(args[2])) {
                    return ChatUtil.sendMessage(player, messageConfiguration.invalidInteger);
                }
                int x = Integer.parseInt(args[0]);
                int y = Integer.parseInt(args[1]);
                int z = Integer.parseInt(args[2]);
                Location l = new Location(player.getWorld(), x, y, z).add(0.5, 0.5, 0.5);
                player.teleport(l);
                ChatUtil.sendMessage(player, messageConfiguration.teleportKoords
                        .replace("{X}", Integer.toString(x))
                        .replace("{Y}", Integer.toString(y))
                        .replace("{Z}", Integer.toString(z)));
                break;
            }
            case 4: {
                if (!player.hasPermission(this.getPermission() + ".others")) {
                    return ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
                }
                Player ot = Bukkit.getPlayer(args[0]);
                if (ot == null) {
                    return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
                }
                if (!IntegerUtil.isInteger(args[1]) || !IntegerUtil.isInteger(args[2]) || !IntegerUtil.isInteger(args[3])) {
                    return ChatUtil.sendMessage(player, messageConfiguration.invalidInteger);
                }
                int x2 = Integer.parseInt(args[1]);
                int y2 = Integer.parseInt(args[2]);
                int z2 = Integer.parseInt(args[3]);
                Location l2 = new Location(player.getWorld(), x2, y2, z2).add(0.5, 0.5, 0.5);
                ot.teleport(l2);

                ChatUtil.sendMessage(ot, messageConfiguration.teleportKoordsOtherToPlayer
                        .replace("{X}", Integer.toString(x2))
                        .replace("{Y}", Integer.toString(y2))
                        .replace("{Z}", Integer.toString(z2))
                        .replace("{ADMIN}", player.getName()));

                ChatUtil.sendMessage(player, messageConfiguration.teleportKoordsOtherToAdmin
                        .replace("{X}", Integer.toString(x2))
                        .replace("{Y}", Integer.toString(y2))
                        .replace("{Z}", Integer.toString(z2))
                        .replace("{PLAYER}", ot.getName()));
                break;
            }
            default: {
                ChatUtil.sendUsage(player, this.getUsage());
                break;
            }
        }
        return false;
    }
}