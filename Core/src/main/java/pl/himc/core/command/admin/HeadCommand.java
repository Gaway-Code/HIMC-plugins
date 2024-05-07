package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class HeadCommand extends PlayerCommand {

    public HeadCommand(String permission) {
        super("head", permission, "/head <Gracz>","skull");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        ItemsUtil.giveItem(player, ItemsUtil.getPlayerHead(args[0]));
        return ChatUtil.sendMessage(player, messageConfiguration.giveSkullItem.replace("{PLAYER}", args[0]));
    }
}
