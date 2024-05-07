package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.drop.cobblex.CobblexData;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

public final class CobblexCommand extends PlayerCommand {

    public CobblexCommand() {
        super("cobblex",null,"");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();

        if(ItemsUtil.getAmountOfItem(Material.COBBLESTONE, player,(short)0) < 576){
            return ChatUtil.sendMessage(player, messageConfiguration.cobblexErrorCobblestone);
        }
        ItemsUtil.remove(new ItemStack(Material.COBBLESTONE), player, 576);
        ItemsUtil.giveItem(player, CobblexData.getItem(1));
        return ChatUtil.sendMessage(player, messageConfiguration.cobblexGive);
    }
}
