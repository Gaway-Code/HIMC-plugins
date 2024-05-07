package pl.himc.core.command.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.configuration.PluginMessages;

import java.util.Arrays;
import java.util.List;

public final class NaprawItemCommand extends PlayerCommand {

    public NaprawItemCommand() {
        super("naprawitem", null, "");
    }

    List<Material> RepairMaterials = Arrays.asList(Material.WOOD_AXE, Material.WOOD_HOE, Material.WOOD_PICKAXE, Material.WOOD_SPADE, Material.WOOD_SWORD, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SPADE, Material.IRON_SWORD, Material.IRON_HELMET, Material.IRON_CHESTPLATE, Material.IRON_LEGGINGS, Material.IRON_BOOTS, Material.STONE_AXE, Material.STONE_SPADE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SWORD, Material.GOLD_HOE, Material.GOLD_SPADE, Material.GOLD_PICKAXE, Material.GOLD_AXE, Material.GOLD_SWORD, Material.GOLD_HELMET, Material.GOLD_CHESTPLATE, Material.GOLD_LEGGINGS, Material.GOLD_BOOTS, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SPADE, Material.DIAMOND_SWORD, Material.DIAMOND_HELMET, Material.DIAMOND_CHESTPLATE, Material.DIAMOND_LEGGINGS, Material.DIAMOND_BOOTS, Material.CHAINMAIL_BOOTS, Material.CHAINMAIL_CHESTPLATE, Material.CHAINMAIL_HELMET, Material.CHAINMAIL_LEGGINGS, Material.BOW, Material.FLINT, Material.FISHING_ROD, Material.SHEARS);
    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(player.getItemInHand() == null || player.getItemInHand().getType() == Material.AIR) {
            return ChatUtil.sendMessage(player, messageConfiguration.repairItemNull);
        }
        if(!canRepair(player.getItemInHand())) {
            return ChatUtil.sendMessage(player, messageConfiguration.repairItemNull);
        }
        if(player.getLevel()< 50 ) {
            return ChatUtil.sendMessage(player, messageConfiguration.repairItemXPError);
        }
        player.setLevel(player.getLevel() - 50);
        player.getItemInHand().setDurability((short)0);
        return ChatUtil.sendMessage(player, messageConfiguration.repairItemSuccess);
    }

    private boolean canRepair(ItemStack is) {
        return this.RepairMaterials.contains(is.getType()) && is.getDurability() > 0;
    }
}
