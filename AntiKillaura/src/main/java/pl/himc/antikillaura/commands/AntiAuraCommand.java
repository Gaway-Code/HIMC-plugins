package pl.himc.antikillaura.commands;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.antikillaura.AntiKillauraPlugin;
import pl.himc.antikillaura.object.NpcBot;
import pl.himc.antikillaura.object.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public final class AntiAuraCommand extends PlayerCommand {

    public AntiAuraCommand(String permission) {
        super("antikillaura", permission, "/antikillaura check [Gracz] \n/antikillaura notify");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if(args.length != 2){
            if(args.length == 1){
                if(args[0].equalsIgnoreCase("notify")){
                    User user = AntiKillauraPlugin.getPlugin().getUserManager().get(player);
                    user.toggleNotify();
                    return ChatUtil.sendMessage(player, "&7Powiadomienia o &4killaurze &7zostaly " + (user.isNotify() ? "&2WLACZONE" : "&4WYLACZONE"));
                }
            }
            return ChatUtil.sendUsage(player, this.usageMessage);
        }
        Player suspect = Bukkit.getPlayer(args[1]);
        if(suspect == null){
            return ChatUtil.sendMessage(player, "&cTego gracza nie ma na serwerze!");
        }
        ChatUtil.sendMessage(player, "&7Gracz &a&nzostanie sprawdzony&7!");
        new NpcBot(suspect);

        return false;
    }
}
