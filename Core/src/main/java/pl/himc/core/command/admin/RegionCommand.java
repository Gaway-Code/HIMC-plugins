package pl.himc.core.command.admin;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.regions.Region;
import pl.himc.core.base.regions.RegionFlags;
import pl.himc.core.base.regions.RegionManager;
import pl.himc.core.configuration.PluginMessages;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

public final class RegionCommand extends PlayerCommand {

    public RegionCommand(String permission) {
        super("region", permission,"/region <get, create, delete, flag> <nazwa>", "rg");

        stick = new ItemBuilder(Material.STICK).addEnchant(Enchantment.DURABILITY, 1337).setFlag(ItemFlag.HIDE_ENCHANTS).setName("&cREGION").getItem();
    }

    private static ItemStack stick;

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 0){
            return ChatUtil.sendUsage(player, this.getUsage());
        }else if(args.length >= 2){
            if(args[0].equalsIgnoreCase("create")){
                RegionManager.createRegion(player, args[1]);
                return false;
            }else if(args[0].equalsIgnoreCase("delete")){
                Region region = PluginCore.getCore().getRegionData().getRegion(args[1]);
                if(region == null){
                    return ChatUtil.sendMessage(player, messageConfiguration.regionNotExists);
                }
                PluginCore.getCore().getRegionData().deleteRegion(region, true);
                return ChatUtil.sendMessage(player, messageConfiguration.regionDeleteSuccess);
            }else if(args[0].equalsIgnoreCase("flag")){
                Region region = PluginCore.getCore().getRegionData().getRegion(args[1]);
                if(region == null){
                    return ChatUtil.sendMessage(player, messageConfiguration.regionNotExists);
                }
                this.openFlagGui(player, region);
                return false;
            }
        } else if(args[0].equalsIgnoreCase("get")){
            ItemsUtil.giveItem(player, stick);
            return ChatUtil.sendMessage(player, messageConfiguration.regionGetStick);
        }
        return ChatUtil.sendUsage(player, this.getUsage());
    }

    private void openFlagGui(final Player player, Region region){
        GuiWindow gui = new GuiWindow("&7Zarzadzanie regionem &b&l" + region.getName(), 3);
        for(RegionFlags.Flag flag : RegionFlags.Flag.values()){
            gui.setToNextFree(new ItemBuilder(Material.PAPER).setName("&7Flaga: &b" + flag.name()).setLore(" &7Ta czynnosc jest " + (region.isFlagEnable(flag) ? "&aODBLOKOWANA" : "&cZABLOKOWANA")).setClickExecutor( inventoryClickEvent -> {
                region.toggleFlag(flag);
                this.openFlagGui(player, region);
            }).getGuiItem());
        }
        gui.open(player);
    }

    public static ItemStack getStick() {
        return stick;
    }
}
