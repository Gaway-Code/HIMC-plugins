package pl.himc.core.task;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.CorePlugin;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.core.api.PluginCore;

public final class LimitTask implements Runnable {

    private PluginConfig pluginConfiguration;
    private PluginMessages messageConfiguration;

    public LimitTask(CorePlugin plugin){
        this.pluginConfiguration = plugin.getPluginConfig();
        this.messageConfiguration = plugin.getPluginMessages();

        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, 20L, 20L);
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if(!player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".limits.bypass")){
                int kox = ItemsUtil.getAmountOfItem(Material.GOLDEN_APPLE, player, (short)1);
                int refil = ItemsUtil.getAmountOfItem(Material.GOLDEN_APPLE, player, (short)0);
                int perla = ItemsUtil.getAmountOfItem(Material.ENDER_PEARL, player, (short)0);

                boolean evip = player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".limits.evip");

                int maxKoxy = evip ? pluginConfiguration.limitKoxEVip : pluginConfiguration.limitKoxPlayer;
                int maxRefile = evip ? pluginConfiguration.limitRefilEVip : pluginConfiguration.limitRefilPlayer;
                int maxPerly = evip ? pluginConfiguration.limitPerlaEVip : pluginConfiguration.limitPerlaPlayer;

                if(kox > maxKoxy){
                    User user = PluginCore.getCore().getUserManager().getUser(player);
                    int toRemove = kox - maxKoxy;
                    user.addKoxy(toRemove);
                    ItemsUtil.remove(new ItemStack(Material.GOLDEN_APPLE, toRemove, (short)1), player, toRemove);
                    ChatUtil.sendMessage(player, messageConfiguration.limitMaxKoxy.replace("{LIMIT}", Integer.toString(maxKoxy)));
                }

                if(refil > maxRefile){
                    User user = PluginCore.getCore().getUserManager().getUser(player);
                    int toRemove = refil - maxRefile;
                    user.addRefile(toRemove);
                    ItemsUtil.remove(new ItemStack(Material.GOLDEN_APPLE, toRemove, (short)0), player, toRemove);
                    ChatUtil.sendMessage(player, messageConfiguration.limitMaxRefile.replace("{LIMIT}", Integer.toString(maxRefile)));
                }

                if(perla > maxPerly){
                    User user = PluginCore.getCore().getUserManager().getUser(player);
                    int toRemove = perla - maxPerly;
                    user.addPerly(toRemove);
                    ItemsUtil.remove(new ItemStack(Material.ENDER_PEARL, toRemove), player, toRemove);
                    ChatUtil.sendMessage(player, messageConfiguration.limitMaxPerly.replace("{LIMIT}", Integer.toString(maxPerly)));
                }
            }
        });
    }
}
