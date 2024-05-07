package pl.himc.core.listener;

import pl.himc.core.api.PluginCore;
import pl.himc.core.base.adminenderchest.AdminEnderchestData;
import pl.himc.core.base.kit.KitData;
import pl.himc.core.base.user.User;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.ArrayList;
import java.util.List;

public final class InventoryClose implements Listener {

    @EventHandler
    public void invsee(InventoryCloseEvent e){
        if(e.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&c&lEdytowanie zestawu"))){
            Player player = (Player)e.getPlayer();
            List<ItemStack> content = new ArrayList<>();
            for (ItemStack item : e.getInventory().getContents()) {
                if (item != null) {
                    if (item.getType() != Material.AIR) {
                        content.add(item);
                    }
                }
            }
            KitData.getInstance().endEditKit(player, content);
        }
        if(e.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&7Enderchest &a&l1")) ||
                e.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&7Enderchest &a&l2")) ||
                e.getInventory().getName().equalsIgnoreCase(ChatUtil.fixColor("&7Enderchest &a&l3"))){
            Player p = (Player)e.getPlayer();
            if(AdminEnderchestData.close(p)){
                return;
            }
            User user = PluginCore.getCore().getUserManager().getUser(p);
            user.setChanges(true);
            p.playSound(p.getLocation(), Sound.CHEST_CLOSE, 1.0F, 1.0F);
        }
    }
}
