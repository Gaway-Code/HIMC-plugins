package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.drop.luckybox.LuckyBoxData;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import pl.himc.api.api.command.CommandAPI;

public final class LuckyBoxCommand extends CommandAPI {

    public LuckyBoxCommand(String permission) {
        super("luckybox", permission, "/luckybox [Gracz/all] [ilosc]");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(args.length < 2){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        if(!IntegerUtil.isInteger(args[1])) return false;
        int ilosc = Integer.parseInt(args[1]);

        if(args[0].equalsIgnoreCase("all")){
            for(Player players : Bukkit.getOnlinePlayers()){
                TitleAPI.sendTitle(players, PluginCore.getCore().getPluginMessages().titleLuckyBoxGive, PluginCore.getCore().getPluginMessages().subTitleLuckyBoxGive.replace("{AMOUNT}", Integer.toString(ilosc)), 10, 60);
                ItemsUtil.giveItem(players, LuckyBoxData.getItem(ilosc));
            }
            return false;
        }
        Player player = Bukkit.getPlayer(args[0]);
        if(player == null){
            return ChatUtil.sendMessage(s, PluginCore.getCore().getPluginMessages().playerIsOffline);
        }
        ItemsUtil.giveItem(player, LuckyBoxData.getItem(ilosc));
        return ChatUtil.sendMessage(s, PluginCore.getCore().getPluginMessages().luckyBoxGive.replace("{AMOUNT}", args[1]).replace("{PLAYER}", player.getName()));
    }
}
