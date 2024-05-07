package pl.himc.core.command.admin;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.backup.Backup;
import pl.himc.core.configuration.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public final class BackupCommand extends PlayerCommand {

    public BackupCommand(String permission) {
        super("backup", permission,"/backup <Gracz>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        Backup backup = PluginCore.getCore().backupManager().get(args[0]);
        if(backup == null){
            return ChatUtil.sendMessage(player, messageConfiguration.backupNull);
        }
        GuiWindow gui = new GuiWindow("&7Backup &2" + args[0], 6);
        for(ItemStack item : backup.getContents()){
            gui.setToNextFree(new GuiItem(item));
        }
        for(ItemStack item : backup.getArmorContents()){
            gui.setToNextFree(new GuiItem(item));
        }
        gui.setItem(52, new ItemBuilder(Material.HOPPER).setName("&aZwroc przedmioty graczowi").setClickExecutor(event -> backup.restoreBackup(player)).getGuiItem());
        gui.setItem(53, new ItemBuilder(Material.CHEST).setName("&aOtworz gui z przedmiotami").setClickExecutor(event -> player.openInventory(this.inventoryItems(backup.getContents(), backup.getArmorContents()))).getGuiItem());
        gui.open(player);
        return false;
    }

    private Inventory inventoryItems(final ItemStack[] contents, final ItemStack[] armorContents){
        Inventory inv = Bukkit.createInventory(null, 54, "");
        for (ItemStack content : contents) {
            if(content == null || content.getType() == Material.AIR) continue;
            inv.addItem(content);
        }
        for (ItemStack armorContent : armorContents) {
            if(armorContent == null || armorContent.getType() == Material.AIR) continue;
            inv.addItem(armorContent);
        }
        return inv;
    }
}
