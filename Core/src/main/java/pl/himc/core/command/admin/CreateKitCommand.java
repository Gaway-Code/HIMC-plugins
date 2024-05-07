package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.base.kit.Kit;
import pl.himc.core.base.kit.KitData;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class CreateKitCommand extends PlayerCommand {

    public CreateKitCommand(String permission) {
        super("createkit", permission, "/createkit <Nazwa> <Czas w sekundach>","addkit", "dodajkit");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        Kit kit = KitData.getInstance().get(args[0]);
        if(kit !=null) {
            return ChatUtil.sendMessage(player, messageConfiguration.kitIsAlreadyExists);
        }
        if(!StringUtil.isLettersAndNumbers(args[0])){
            return ChatUtil.sendMessage(player, messageConfiguration.kitNameOnlyLettersAndNumers);
        }
        ItemStack item = player.getItemInHand();
        if(item == null || item.getType() == Material.AIR){
            return ChatUtil.sendMessage(player, messageConfiguration.kitIconNull);
        }
        KitData.getInstance().addNewKit(player, item, args[0], Integer.parseInt(args[1]));
        return false;
    }
}
