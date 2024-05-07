package pl.himc.core.base.effect;

import pl.himc.core.api.PluginCore;
import org.apache.commons.lang3.Validate;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EffectManager {

    private static EffectManager inst;
    private PluginConfig pluginConfiguration;
    private PluginMessages messageConfiguration;

    private File file;
    private YamlConfiguration yaml;

    private Map<String, EffectPlayer> effectPlayer = new HashMap<>();
    private Map<String, EffectVip> effectVip = new HashMap<>();

    private GuiWindow effectPlayerGui;
    private GuiWindow effectVipGui;

    public EffectManager(){
        inst = this;
        this.pluginConfiguration = PluginCore.getCore().getPluginConfig();
        this.messageConfiguration = PluginCore.getCore().getPluginMessages();

    }

    public void loadEffectPlayer(){
        this.getYaml().getConfigurationSection("gui-player").getKeys(false).forEach(name -> {
                final String path = "gui-player." + name + ".";
                //System.out.println("Path -> " + path);
                final Material material = Material.matchMaterial(this.getYaml().getString(path + "id"));
                final int data = this.getYaml().getInt(path + "data");
                final String nameItem = this.getYaml().getString(path + "name");
                final List<String> loreItem = this.getYaml().getStringList(path + "lore");
                final int invX = this.getYaml().getInt(path + "x");
                final int invY = this.getYaml().getInt(path + "y");

                final String pathEffect = path + "effect.";
                final Material payItem = Material.matchMaterial(this.getYaml().getString(pathEffect + "item"));
                final int payItemData = this.getYaml().getInt(pathEffect + "data");
                final int payAmount = this.getYaml().getInt(pathEffect + "amount");
                final PotionEffectType effectType = PotionEffectType.getByName(this.getYaml().getString(pathEffect + "effect"));
                final int duration = this.getYaml().getInt(pathEffect + "duration");
                final int amplifier = this.getYaml().getInt(pathEffect + "amplifier");

                Validate.notNull(material, path + "id - Jest nullem!");
                Validate.notNull(invX, path + "x - Jest nullem!");
                Validate.notNull(invY, path + "y - Jest nullem!");
                Validate.notNull(payItem, pathEffect + "item - Jest nullem!");
                Validate.notNull(effectType, pathEffect + "effect - Jest nullem!");

                ItemStack itemInGui = new ItemBuilder(material, 1, (short)data).setName(nameItem).setLore(loreItem).getItem();
                ItemStack payItemStack = new ItemBuilder(payItem, payAmount, (short)payItemData).getItem();

                this.addEffectPlayer(name, new EffectPlayer(name)
                        .setItemInGui(itemInGui)
                        .setInvX(invX)
                        .setInvY(invY)
                        .setUseEffect(true)
                        .setItemCost(payItemStack)
                        .setEffectType(effectType)
                        .setDuration(duration)
                        .setAmplifier(amplifier));
        });
        PluginCore.getCore().getConsoleSender().info("Zaladowano " + effectPlayer.size() + " efektow gracza!");

        this.effectPlayerGui = new GuiWindow(ChatUtil.fixColor("&eZwykle efekty"), 3);
        this.effectPlayer.values().forEach(effect -> this.effectPlayerGui.setItem(effect.getInvX(), effect.getInvY(), new GuiItem(effect.getItemInGui(), click -> this.eventEffectPlayer(click, effect))));
    }

    public void loadEffectVip(){
        this.getYaml().getConfigurationSection("gui-vip").getKeys(false).forEach(name -> {
            final String path = "gui-vip." + name + ".";
            //System.out.println("Path -> " + path);
            final Material material = Material.matchMaterial(this.getYaml().getString(path + "id"));
            final int data = this.getYaml().getInt(path + "data");
            final String nameItem = this.getYaml().getString(path + "name");
            final List<String> loreItem = this.getYaml().getStringList(path + "lore");
            final int invX = this.getYaml().getInt(path + "x");
            final int invY = this.getYaml().getInt(path + "y");

            final String pathEffect = path + "effect.";
            final Material payItem = Material.matchMaterial(this.getYaml().getString(pathEffect + "item"));
            final int payItemData = this.getYaml().getInt(pathEffect + "data");
            final int payAmount = this.getYaml().getInt(pathEffect + "amount");
            final PotionEffectType effectType = PotionEffectType.getByName(this.getYaml().getString(pathEffect + "effect"));
            final int duration = this.getYaml().getInt(pathEffect + "duration");
            final int amplifier = this.getYaml().getInt(pathEffect + "amplifier");

            Validate.notNull(material, path + "id - Jest nullem!");
            Validate.notNull(invX, path + "x - Jest nullem!");
            Validate.notNull(invY, path + "y - Jest nullem!");
            Validate.notNull(payItem, pathEffect + "item - Jest nullem!");
            Validate.notNull(effectType, pathEffect + "effect - Jest nullem!");

            ItemStack itemInGui = new ItemBuilder(material, 1, (short)data).setName(nameItem).setLore(loreItem).getItem();
            ItemStack payItemStack = new ItemBuilder(payItem, payAmount, (short)payItemData).getItem();

            this.addEffectVip(name, new EffectVip(name)
                    .setItemInGui(itemInGui)
                    .setInvX(invX)
                    .setInvY(invY)
                    .setUseEffect(true)
                    .setItemCost(payItemStack)
                    .setEffectType(effectType)
                    .setDuration(duration)
                    .setAmplifier(amplifier));
        });
        PluginCore.getCore().getConsoleSender().info("Zaladowano " + effectVip.size() + " efektow vipa!");

        this.effectVipGui = new GuiWindow(ChatUtil.fixColor("&eEfekty rangi &9M&6VIP"), 3);
        this.effectVip.values().forEach(effect -> this.effectVipGui.setItem(effect.getInvX(), effect.getInvY(), new GuiItem(effect.getItemInGui(), click -> this.eventEffectVip(click, effect))));
    }

    public void reloadEffect(){
        this.effectPlayerGui.unregister();
        this.effectVipGui.unregister();
        this.effectVip.clear();
        this.effectPlayer.clear();
        this.file = null;
        this.yaml = null;
        this.loadEffectPlayer();
        this.loadEffectVip();
        PluginCore.getCore().getConsoleSender().info("Efekty zostaly przeladowane!");
    }

    public void addEffectVip(String name, EffectVip effect){
        this.effectVip.put(name, effect);
    }
    public void addEffectPlayer(String name, EffectPlayer effect){
        this.effectPlayer.put(name, effect);
    }

    public void openMain(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&2Efekty"), 1);

        //GuiItem dark = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)15).setName("&8*").getItem());
        GuiItem yellow = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)4).setName("&8*").getItem());

        gui.setItem(1, yellow);
        gui.setItem(2, new ItemBuilder(Material.DIAMOND).setName("&8* &eZWYKLE EFEKTY &8*").setClickExecutor(inventoryClickEvent -> this.effectPlayerGui.open(player, false)).getGuiItem());
        gui.setItem(3, yellow);

        gui.setItem(5, yellow);
        gui.setItem(6, new ItemBuilder(Material.EMERALD).addEnchant(Enchantment.DURABILITY, 10).addItemFlag(ItemFlag.HIDE_ENCHANTS).setName("&8* &eEFEKTY RANGI &9M&6VIP &8*").setLore(" &8&l%> &6Posiadasz dostep: " + (player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".efekty.mvip") ? "&2&l✔" : "&4&l✖")).setClickExecutor(inventoryClickEvent -> {
            if(!player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".efekty.mvip")){
                ChatUtil.sendMessage(player, messageConfiguration.noPermissionVipEffect);
                return;
            }
            this.effectVipGui.open(player, false);
        }).getGuiItem());
        gui.setItem(7, yellow);
        gui.open(player);
    }

    public void eventEffectPlayer(InventoryClickEvent e, EffectPlayer eff){
        Player p = (Player)e.getWhoClicked();

        if(eff.getEffectType().equals(PotionEffectType.INCREASE_DAMAGE)){
            if(eff.getAmplifier() > 0){
                if(!pluginConfiguration.strenght2InEffect){
                    ChatUtil.sendMessage(p, messageConfiguration.strenghtIsDisable);
                    return;
                }
            }
        }

        if(ItemsUtil.getAmountOfItem(eff.getCostItem().getType(), p, eff.getCostItem().getDurability()) < eff.getCostItem().getAmount()) {
            ChatUtil.sendMessage(p, StringUtil.replaceItems(Arrays.asList(eff.getCostItem())));
            return;
        }

        if(p.hasPotionEffect(eff.getEffectType())){
            ChatUtil.sendMessage(p, messageConfiguration.hasEffect);
            return;
        }
        ItemsUtil.remove(eff.getCostItem().clone(), p, eff.getCostItem().getAmount());
        p.addPotionEffect(new PotionEffect(eff.getEffectType(), eff.getDuration() * 60 * 20, eff.getAmplifier()));

        Bukkit.getOnlinePlayers().stream().filter(online -> PluginCore.getCore().getUserManager().getUser(online).isEffectMsg()).forEach(online ->
                ChatUtil.sendMessage(online, messageConfiguration.buyEffectBroadcast
                        .replace("{PLAYER}", p.getName())
                        .replace("{EFFECT}", eff.getEffectType().getName())
                        .replace("{TIME}", Integer.toString(eff.getDuration()))));
        ChatUtil.sendMessage(p, messageConfiguration.buyEffect.replace("{EFFECT}", eff.getEffectType().getName()).replace("{TIME}", Integer.toString(eff.getDuration())));
    }

    public void eventEffectVip(InventoryClickEvent e, EffectVip eff){
        Player p = (Player)e.getWhoClicked();

        if(ItemsUtil.getAmountOfItem(eff.getCostItem().getType(), p, eff.getCostItem().getDurability()) < eff.getCostItem().getAmount()) {
            ChatUtil.sendMessage(p, StringUtil.replaceItems(Arrays.asList(eff.getCostItem())));
            return;
        }
        if(p.hasPotionEffect(eff.getEffectType())){
            ChatUtil.sendMessage(p, messageConfiguration.hasEffect);
            return;
        }
        ItemsUtil.remove(eff.getCostItem().clone(), p, eff.getCostItem().getAmount());
        p.addPotionEffect(new PotionEffect(eff.getEffectType(), eff.getDuration() * 60 * 20, eff.getAmplifier()));

        Bukkit.getOnlinePlayers().stream().filter(online -> PluginCore.getCore().getUserManager().getUser(online).isEffectMsg()).forEach(online ->
                ChatUtil.sendMessage(online, messageConfiguration.buyEffectBroadcast
                        .replace("{PLAYER}", p.getName())
                        .replace("{EFFECT}", eff.getEffectType().getName())
                        .replace("{TIME}", Integer.toString(eff.getDuration()))));
        ChatUtil.sendMessage(p, messageConfiguration.buyEffect.replace("{EFFECT}", eff.getEffectType().getName()).replace("{TIME}", Integer.toString(eff.getDuration())));
    }

    public File getFile(){
        if(file == null) file = new File(PluginCore.getCore().getDataFolder(), "efekty.yml");
        if (!file.exists()) PluginCore.getCore().saveResource("efekty.yml", false);
        return file;
    }

    public YamlConfiguration getYaml(){
        if(yaml == null) yaml = YamlConfiguration.loadConfiguration(getFile());
        return yaml;
    }

    public static EffectManager getInstance(){
        if(inst == null){
            return new EffectManager();
        }
        return inst;
    }
}
