package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import pl.himc.core.manager.EnderchestManager;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import pl.himc.core.base.adminenderchest.AdminEnderchestData;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

public final class EnderchestCommand extends PlayerCommand {

    public EnderchestCommand(String permission) {
        super("enderchest", permission,"/enderchest <Gracz> <enderchest 1,2,3>","ec");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 0){
            open(p);
            return false;
        }
        if(args.length >= 2){
            if(!p.hasPermission(this.getPermission() + ".others")){
                return ChatUtil.sendMessage(p, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
            }
            User user = PluginCore.getCore().getUserManager().getUser(args[0]);
            if(user == null){
                return ChatUtil.sendMessage(p, messageConfiguration.playerIsNotExists);
            }
            if(!IntegerUtil.isInteger(args[1])){
                return ChatUtil.sendMessage(p, messageConfiguration.invalidInteger);
            }
            int i = Integer.parseInt(args[1]);
            AdminEnderchestData.open(p, i, user);
            return true;
        }else if(args.length >= 3 || args.length < 2 && p.hasPermission(this.getPermission() + ".others")){
            ChatUtil.sendUsage(p, this.getUsage());
        }else {
            open(p);
        }
        return false;
    }

    public static void open(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&6Enderchest"), 1);
        gui.setCloseEvent(inventoryCloseEvent -> gui.unregister());

        GuiItem dark = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)7).setName("&8*").getItem());
        GuiItem blue = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)11).setName("&8*").getItem());

        gui.setItem(0, blue);
        gui.setItem(1, new GuiItem(new ItemBuilder(Material.ENDER_CHEST).setName("&8* &7Enderchest &a1 &8*").getItem(), inventoryClickEvent -> EnderchestManager.openEnderchest(player, 1)));
        gui.setItem(2, dark);
        gui.setItem(3, dark);
        gui.setItem(4, new GuiItem(new ItemBuilder(Material.ENDER_CHEST).setName("&8* &7Enderchest &a2 &8*").getItem(), inventoryClickEvent -> EnderchestManager.openEnderchest(player, 2)));
        gui.setItem(5, dark);
        gui.setItem(6, dark);
        gui.setItem(7, new GuiItem(new ItemBuilder(Material.ENDER_CHEST).setName("&8* &7Enderchest &a3 &8*").getItem(), inventoryClickEvent -> EnderchestManager.openEnderchest(player, 3)));
        gui.setItem(8, blue);

        gui.open(player);
        player.playSound(player.getLocation(), Sound.CHEST_OPEN, 1.0F, 1.0F);
    }
}
