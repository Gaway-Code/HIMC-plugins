package pl.himc.core.listener.block;

import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.item.ItemNameUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.RandomUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.craft.CraftManager;
import pl.himc.core.base.craft.stoniarka.Stoniarka;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;
import pl.himc.core.base.drop.cobblex.CobblexData;
import pl.himc.core.base.drop.luckybox.LuckyBoxData;
import pl.himc.core.command.admin.SprawdzCommand;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;

public final class BlockPlace implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkTntPlace(BlockPlaceEvent e){
        if(e.isCancelled()) return;
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(pluginConfiguration.tntPlaceY){
            Block b = e.getBlock();
            if(b.getType() == Material.TNT) {
                if(b.getY() >= pluginConfiguration.tntPlaceKoordY) {
                    e.setCancelled(true);
                    ChatUtil.sendMessage(e.getPlayer(), messageConfiguration.placeTntKoordY.replace("{Y}", Integer.toString(pluginConfiguration.tntPlaceKoordY)));
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void antyBlockPlace(BlockPlaceEvent e) {
        if(e.isCancelled()) return;
        if(e.getPlayer().hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".antycrash.bypass")) return;
        if(e.getBlock().getType() == Material.DIODE_BLOCK_OFF ||
                e.getBlock().getType() == Material.REDSTONE_COMPARATOR_OFF ||
                e.getBlock().getType() == Material.BEDROCK) {
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            ChatUtil.sendMessage(e.getPlayer(), messageConfiguration.antycrashBlock);
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkBlockPlace(BlockPlaceEvent e){
        if(e.isCancelled()) return;
        if(SprawdzCommand.isSuspect(e.getPlayer())) {
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            e.setCancelled(true);
            ChatUtil.sendMessage(e.getPlayer(), messageConfiguration.checkSuspectNotBuild);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent event) {
        if (event.isCancelled()) return;
        Block b = event.getBlock();
        Player p = event.getPlayer();
        if (b.getType() == Material.MOSSY_COBBLESTONE && p.getItemInHand().isSimilar(CobblexData.getItem())) {
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            if (!CobblexData.isEnable()) {
                event.setCancelled(true);
                ChatUtil.sendMessage(p, messageConfiguration.cobblexIsDisable);
                return;
            }
            b.setType(Material.AIR);
            ItemStack is = CobblexData.getDrops().get(RandomUtil.nextInt(CobblexData.getDrops().size())).clone();

            b.getWorld().dropItemNaturally(b.getLocation(), is);
            ChatUtil.sendMessage(p, messageConfiguration.cobblexOpen.replace("{ITEM}", ItemNameUtil.getName(is.getType())).replace("{AMOUNT}", Integer.toString(is.getAmount())));
        }

        if(b.getType() == Material.SPONGE && p.getItemInHand().isSimilar(LuckyBoxData.getItem())){
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            if (!LuckyBoxData.isEnable()) {
                event.setCancelled(true);
                ChatUtil.sendMessage(p, messageConfiguration.luckyboxIsDisable);
                return;
            }
            b.setType(Material.AIR);
            LuckyBoxData.getDrop(p);
        }

        if(p.getItemInHand().isSimilar(StoniarkaData.getStoniarka())){
            new Stoniarka(p, b.getLocation());
        }
        if(p.getItemInHand().isSimilar(CraftManager.getRzucaneTnt())){
            b.setType(Material.AIR);
            b.getWorld().spawn(b.getLocation(), TNTPrimed.class);
        }
        if(p.getItemInHand().isSimilar(CraftManager.getBoyfarmer())){
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            ChatUtil.sendMessage(p, messageConfiguration.placeBoyfarmer);
            b.setType(Material.OBSIDIAN);
            Guild guild = PluginGuild.getPlugin().getGuildManager().getLocation(b.getLocation());
            for (int hight = b.getY() - 1; hight > 4; hight--) {
                Block block = b.getWorld().getBlockAt(b.getX(), hight, b.getZ());
                if (guild != null) {
                    if (guild.getCuboid().isInCentrum(block.getLocation(), 6, 1, 3)) {
                        continue;
                    }
                }
                if (block.getType() == Material.ENDER_CHEST || block.getType() == Material.FURNACE || block.getType() == Material.CHEST || block.getType() == Material.HOPPER || block.getType() == Material.TRAPPED_CHEST || block.getType() == Material.BEDROCK) {
                    continue;
                }
                block.setType(Material.OBSIDIAN);
            }
        }
        if(p.getItemInHand().isSimilar(CraftManager.getSandfarmer())){
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            ChatUtil.sendMessage(p, messageConfiguration.placeSandfarmer);
            b.setType(Material.SAND);
            Guild guild = PluginGuild.getPlugin().getGuildManager().getLocation(b.getLocation());
            for (int hight = b.getY() - 1; hight > 4; hight--) {
                Block block = b.getWorld().getBlockAt(b.getX(), hight, b.getZ());
                if (guild != null) {
                    if (guild.getCuboid().isInCentrum(block.getLocation(), 6, 1, 3)) {
                        continue;
                    }
                }
                if (block.getType() == Material.ENDER_CHEST || block.getType() == Material.FURNACE || block.getType() == Material.CHEST || block.getType() == Material.HOPPER || block.getType() == Material.TRAPPED_CHEST || block.getType() == Material.BEDROCK) {
                    continue;
                }
                block.setType(Material.SAND);
            }
        }
        if(p.getItemInHand().isSimilar(CraftManager.getKopaczfosy())){
            PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
            ChatUtil.sendMessage(p, messageConfiguration.placeKopaczFosy);
            b.setType(Material.AIR);
            Guild guild = PluginGuild.getPlugin().getGuildManager().getLocation(b.getLocation());
            for (int hight = b.getY() - 1; hight > 4; hight--) {
                Block block = b.getWorld().getBlockAt(b.getX(), hight, b.getZ());
                if (guild != null) {
                    if (guild.getCuboid().isInCentrum(block.getLocation(), 6, 1, 3)) {
                        continue;
                    }
                }
                if (block.getType() == Material.ENDER_CHEST || block.getType() == Material.FURNACE || block.getType() == Material.CHEST || block.getType() == Material.HOPPER || block.getType() == Material.TRAPPED_CHEST || block.getType() == Material.BEDROCK) {
                    continue;
                }
                block.setType(Material.AIR);
            }
        }
    }
}
