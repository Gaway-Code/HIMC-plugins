package pl.himc.core.manager;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;

import java.util.ArrayList;

public final class EnderchestManager {

    public static Integer getSize(Player p) {
        if (p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".enderchest.5")) {
            return 54;
        }
        if (p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".enderchest.4")) {
            return 45;
        }
        if (p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".enderchest.3")) {
            return 36;
        }
        if (p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".enderchest.2")) {
            return 18;
        }
        if (p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".enderchest.1")) {
            return 9;
        }
        return 27;
    }

    public static Inventory createEnderchest(Player player, int num){
        return Bukkit.createInventory(player, getSize(player), ChatUtil.fixColor("&7Enderchest &a&l" + num));
    }

    public static void openEnderchest(Player player, int ender) {
        User u = PluginCore.getCore().getUserManager().getUser(player);
        Inventory userender = u.getEnderchest(ender);
        //if(userender == null) createEnderchest(player, ender);

        if(getSize(player) < userender.getSize()) {
            ChatUtil.sendMessage(player, PluginCore.getCore().getPluginMessages().enderchestOpenError);
            ArrayList<ItemStack> newitems = new ArrayList<>();
            for (int i = 0; i < getSize(player); i++) {
                ItemStack item = userender.getContents()[i];
                newitems.add(item);
            }
            ItemStack[] nowe = newitems.toArray(new ItemStack[newitems.size()]);
            u.createEnderchest(ender, nowe);
            userender = u.getEnderchest(ender);
        }
        if(getSize(player) > userender.getSize()){
            ItemStack[] items = userender.getContents();
            u.createEnderchest(ender, items);
            userender = u.getEnderchest(ender);
        }

        player.openInventory(userender);
    }
}
