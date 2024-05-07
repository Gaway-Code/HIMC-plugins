package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.kit.Kit;
import pl.himc.core.base.kit.KitData;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class DeleteKitCommand extends PlayerCommand {

    public DeleteKitCommand(String permission) {
        super("deletekit", permission, "/deletekit <Nazwa>","removekit", "delkit");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 0) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        Kit kit = KitData.getInstance().get(args[0]);
        if(kit == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.kitIsNotExists);
        }
        KitData.getInstance().delKit(kit);
        return ChatUtil.sendMessage(player, messageConfiguration.kitDeleteSuccess.replace("{KIT}", args[0]));
    }
}
