package pl.himc.core.base.drop.stone;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.utils.RandomUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.drop.cobblex.CobblexData;
import pl.himc.core.base.drop.luckybox.LuckyBoxData;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginConfig;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class DropManager {

    private static final List<Drop> drops = new ArrayList<>();
    private static final PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();

    private static File file;
    private static YamlConfiguration yaml;

    public static File getFile(){
        if(file == null){
            file = new File(PluginCore.getCore().getDataFolder() + "/drops.yml");
        }
        if(!file.exists()){
            PluginCore.getCore().saveResource("drops.yml", false);
        }
        return file;
    }

    public static YamlConfiguration getYaml(){
        if(yaml == null){
            yaml = YamlConfiguration.loadConfiguration(getFile());
        }
        return yaml;
    }

    public static void reloadFile(){
        file = null;
        yaml = null;
        getFile();
        getYaml();
    }

    public static List<Drop> getDrops(){
        return drops;
    }

    public static void addDrop(Drop drop){
        if(drops.contains(drop)) return;
        drops.add(drop);
    }

    public static void getDrop(Player player, Block block){
        User user = PluginCore.getCore().getUserManager().getUser(player);

        if(!user.isDropCobble()){
            block.setType(Material.AIR);
            recalculateDurability(player, player.getItemInHand());
        }
        user.addStone(1);

        drops.stream().filter(drop -> RandomUtil.getChance(drop.getChance() + user.getChance(drop.getChance())) && !drop.isDisableDrop(player) && block.getLocation().getBlockY() < drop.getMaxY() && block.getLocation().getBlockY() > drop.getMinY()).forEach(drop -> {

            ItemStack toDrop = drop.getItem();
            int exp = drop.getExp();
            int amount = RandomUtil.getRandInt(drop.getMinAmount(), drop.getMaxAmount());
            if(drop.isFortune()){
                amount = RandomUtil.getRandInt(drop.getMinAmount() + player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS), drop.getMaxAmount() +  player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS));
                exp = exp + player.getItemInHand().getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            }
            if(user.isTurboExp()){
                exp = exp + pluginConfiguration.turboDropExp;
            }
            toDrop.setAmount(amount);
            user.addPointsMiner(drop.getPoints());
            player.giveExp(exp);
            ItemsUtil.giveItem(player, toDrop);
            if(drop.getMessage() !=null && user.isDropMsg()){
                ChatUtil.sendMessage(player, drop.getMessage().replace("{AMOUNT}", Integer.toString(amount)).replace("{POINTS}", Integer.toString(drop.getPoints())));
            }
        });
    }

    public static void recalculateDurability(Player player, ItemStack item) {
        if (item.getType().getMaxDurability() == 0) {
            return;
        }
        int enchantLevel = item.getEnchantmentLevel(Enchantment.DURABILITY);
        short d = item.getDurability();
        if (enchantLevel > 0) {
            if (100 / (enchantLevel + 1) > RandomUtil.getRandInt(0, 100)) {
                if (d == item.getType().getMaxDurability()) {
                    player.getInventory().clear(player.getInventory().getHeldItemSlot());
                    player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                }
                else {
                    item.setDurability((short)(d + 1));
                }
            }
        }
        else if (d == item.getType().getMaxDurability()) {
            player.getInventory().clear(player.getInventory().getHeldItemSlot());
        }
        else {
            item.setDurability((short)(d + 1));
        }
    }

    public static void loadDrops(){
        ConfigurationSection cs1 = DropManager.getYaml().getConfigurationSection("drops");
        for(String s : cs1.getKeys(false)){
            ConfigurationSection cs = cs1.getConfigurationSection(s);
            Material material = Material.matchMaterial(cs.getString("drop.what"));
            if(material == null){
                PluginCore.getCore().getConsoleSender().error("Blad - nieznany material -> " + s + " " + material.toString());
                continue;
            }
            Drop drop = new Drop(s);

            drop.setChance(cs.getDouble("chance"));
            drop.setExp(cs.getInt("exp"));
            drop.setPoints(cs.getInt("points"));
            drop.setFortune(cs.getBoolean("fortune"));
            if(cs.getString("message") != null){
                drop.setMessage(cs.getString("message"));
            }
            drop.setMinY(cs.getInt("height.min"));
            drop.setMaxY(cs.getInt("height.max"));
            drop.setMinAmount(cs.getInt("amount.min"));
            drop.setMaxAmount(cs.getInt("amount.max"));

            ItemStack item = new ItemStack(material, (short)cs.getInt("drop.id"));
            drop.setItem(item);
        }

        List<ItemStack> items = (List<ItemStack>) DropManager.getYaml().getList("cobblex-drops");
        if(items.isEmpty()) return;
        for(ItemStack is : items) {
            CobblexData.getDrops().add(is);
        }

        List<ItemStack> itemsLuckyBlock = (List<ItemStack>) DropManager.getYaml().getList("luckybox-drops");
        if(itemsLuckyBlock.isEmpty()) return;
        for(ItemStack is : itemsLuckyBlock) {
            LuckyBoxData.getDrops().add(is);
        }
    }

    public static void disableDrops(Player player){
        getDrops().forEach(drop -> drop.setStatusDrop(player, false));
    }

    public static void enableDrops(Player player){
        getDrops().forEach(drop -> drop.setStatusDrop(player, true));
    }

    public static void reloadDrops(){
        DropManager.reloadFile();
        DropManager.getDrops().clear();
        CobblexData.getDrops().clear();
        LuckyBoxData.getDrops().clear();
        loadDrops();
        PluginCore.getCore().getConsoleSender().info("Drop zostal przeladowany!");
    }
}
