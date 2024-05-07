package pl.himc.core.listener.player;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import pl.himc.core.base.user.User;

public final class PlayerEat implements Listener {

    @EventHandler
    public void onEat(PlayerItemConsumeEvent e){
        if(e.getItem().getType() == Material.GOLDEN_APPLE){
            if(e.getItem().getDurability() == 1) {
                User user = PluginCore.getCore().getUserManager().getUser(e.getPlayer());
                user.addEatKox(1);
                return;
            }
            User user = PluginCore.getCore().getUserManager().getUser(e.getPlayer());
            user.addEatRefil(1);
        }
        Player player = e.getPlayer();
        ItemStack ref = new ItemStack(Material.GOLDEN_APPLE,1);
        if(player.getItemInHand().isSimilar(ref)){
            if(player.hasPotionEffect(PotionEffectType.ABSORPTION)){
                player.removePotionEffect(PotionEffectType.ABSORPTION);
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 1));
            if(player.hasPotionEffect(PotionEffectType.FIRE_RESISTANCE)){
                player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
            }
            player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 200, 6));
        }
    }
}

