package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;

import java.util.HashMap;
import java.util.Map;

public final class SprawdzCommand extends PlayerCommand {

    public SprawdzCommand(String permission) {
        super("sprawdz", permission,"/sprawdz", "spr" );
    }

    private static final Map<Player, Location> spr$players = new HashMap<>();

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 1 && args[0].equalsIgnoreCase("ustaw")){
            if(!player.hasPermission(this.getPermission() + ".ustawspr")){
                return ChatUtil.sendMessage(player, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".ustawspr"));
            }
            pluginConfiguration.sprawdzLocation = StringUtil.locationLongToString(player.getLocation());
            pluginConfiguration.cfgSprawdzLocation = player.getLocation();
            PluginCore.getCore().savePluginConfiguration();
            ChatUtil.sendMessage(player, messageConfiguration.setSprawdzarka.replace("{X}", Integer.toString(player.getLocation().getBlockX()))
                    .replace("{Y}", Integer.toString(player.getLocation().getBlockY()))
                    .replace("{Z}", Integer.toString(player.getLocation().getBlockZ())));
        }else if(args.length != 2){
            ChatUtil.sendMessage(player, messageConfiguration.sprawdzHelp);

        }else if(args[0].equalsIgnoreCase("gracz")){
            if(args[1].equalsIgnoreCase(player.getName())){
                return ChatUtil.sendMessage(player, messageConfiguration.checkErrorYourSelf);
            }

            Player suspect = Bukkit.getPlayer(args[1]);
            if(suspect == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
            }

            if(spr$players.containsKey(suspect)){
                return ChatUtil.sendMessage(player, messageConfiguration.checkSuspectIsChecked);
            }

            spr$players.put(suspect, suspect.getLocation());

            suspect.teleport(pluginConfiguration.cfgSprawdzLocation);
            player.teleport(suspect);
            ChatUtil.sendBroadcast(messageConfiguration.checkSuspectBroadcast.replace("{PLAYER}", suspect.getName()).replace("{ADMIN}", player.getName()));

            TitleAPI.sendTitle(suspect, messageConfiguration.titleToSuspect, messageConfiguration.subtitleToSuspect, 5, 90);
            ChatUtil.sendMessage(suspect, messageConfiguration.checkSendToSuspect.replace("{ADMIN}", player.getName()));
        }else if(args[0].equalsIgnoreCase("cheaty")){
            Player suspect = Bukkit.getPlayer(args[1]);
            if(suspect == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
            }

            if(!spr$players.containsKey(suspect)){
                return ChatUtil.sendMessage(player, messageConfiguration.checkSuspectIsNotChecked);
            }

            suspect.teleport(spr$players.get(suspect));
            spr$players.remove(suspect);
            ChatUtil.sendBroadcast(messageConfiguration.checkSuspectBanBroadcast.replace("{PLAYER}", suspect.getName()).replace("{ADMIN}", player.getName()));
            PluginCore.getCore().getBanManager().addBan(suspect.getName(), player, 0L, "Cheaty", System.currentTimeMillis(), false);
        }else if(args[0].equalsIgnoreCase("przyznanie")){
            Player suspect = Bukkit.getPlayer(args[1]);
            if(suspect == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
            }

            if(!spr$players.containsKey(suspect)){
                return ChatUtil.sendMessage(player, messageConfiguration.checkSuspectIsNotChecked);
            }

            suspect.teleport(spr$players.get(suspect));
            spr$players.remove(suspect);
            ChatUtil.sendBroadcast(messageConfiguration.checkSuspectBanBroadcast.replace("{PLAYER}", suspect.getName()).replace("{ADMIN}", player.getName()));
            PluginCore.getCore().getBanManager().addBan(suspect.getName(), player, pluginConfiguration.cfgSprawdzBanPrzyznanie + System.currentTimeMillis(), "Przyznanie sie do cheatow", System.currentTimeMillis(), false);
        }else if(args[0].equalsIgnoreCase("czysty")){
            Player suspect = Bukkit.getPlayer(args[1]);
            if(suspect == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
            }

            if(!spr$players.containsKey(suspect)){
                return ChatUtil.sendMessage(player, messageConfiguration.checkSuspectIsNotChecked);
            }

            suspect.teleport(spr$players.get(suspect));
            spr$players.remove(suspect);
            ChatUtil.sendBroadcast(messageConfiguration.checkSuspectClearBroadcast.replace("{PLAYER}", suspect.getName()).replace("{ADMIN}", player.getName()));
        }else if(args[0].equalsIgnoreCase("brakwspolpracy")){
            Player suspect = Bukkit.getPlayer(args[1]);
            if(suspect == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
            }

            if(!spr$players.containsKey(suspect)){
                return ChatUtil.sendMessage(player, messageConfiguration.checkSuspectIsNotChecked);
            }

            suspect.teleport(spr$players.get(suspect));
            spr$players.remove(suspect);
            ChatUtil.sendBroadcast(messageConfiguration.checkSuspectBanBroadcast.replace("{PLAYER}", suspect.getName()).replace("{ADMIN}", player.getName()));
            PluginCore.getCore().getBanManager().addBan(suspect.getName(), player, 0L, "Brak wspolpracy", System.currentTimeMillis(), false);
        }else if(args[0].equalsIgnoreCase("wyloguj")){
            Player suspect = Bukkit.getPlayer(args[1]);
            if(suspect == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
            }

            if(!spr$players.containsKey(suspect)){
                return ChatUtil.sendMessage(player, messageConfiguration.checkSuspectIsNotChecked);
            }

            suspect.teleport(spr$players.get(suspect));
            spr$players.remove(suspect);
            suspect.kickPlayer(ChatUtil.fixColor(messageConfiguration.checkAllowLogoutSuspect));
        }
        return false;
    }
    public static void checkLogout(Player player){
        if(spr$players.containsKey(player)){
            ChatUtil.sendBroadcast(PluginCore.getCore().getPluginMessages().checkSuspectLogoutBroadcast.replace("{PLAYER}", player.getName()));
            spr$players.remove(player);
        }
    }
    public static boolean isSuspect(Player player){
        return spr$players.containsKey(player);
    }
}
