package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.kit.Kit;
import pl.himc.core.base.kit.KitData;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class EditKitCommand extends PlayerCommand {

    public EditKitCommand(String permission) {
        super("editkit", permission,"/editkit <Nazwa> <Nowy czas w sekundach>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 0) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        if(args.length == 1) {
            Kit kit = KitData.getInstance().get(args[0]);
            if(kit == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.kitIsNotExists);
            }

            KitData.getInstance().startEditKit(player, kit, 0);
        }else if (args.length == 2) {
            Kit kit = KitData.getInstance().get(args[0]);
            if(kit == null) {
                return ChatUtil.sendMessage(player, messageConfiguration.kitIsNotExists);
            }
            int delay = Integer.parseInt(args[1]);
            KitData.getInstance().startEditKit(player, kit, delay);
        }else {
            ChatUtil.sendUsage(player, this.getUsage());
        }
        return false;
    }
}
