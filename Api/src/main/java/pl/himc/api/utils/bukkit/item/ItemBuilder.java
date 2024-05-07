package pl.himc.api.utils.bukkit.item;

import org.apache.commons.lang.Validate;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public final class ItemBuilder {

    private final ItemStack itemStack;
    private final ItemMeta itemMeta;
    private Consumer<InventoryClickEvent> consumer;

    public ItemBuilder(final Material material) {
        this.itemStack = new ItemStack(material);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, int stack) {
        this.itemStack = new ItemStack(material, stack);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final Material material, int stack, int data) {
        this.itemStack = new ItemStack(material, stack, (short) data);
        this.itemMeta = itemStack.getItemMeta();
    }

    public ItemBuilder(final ItemStack itemStack) {
        this.itemStack = itemStack;
        this.itemMeta = itemStack.getItemMeta();
    }

    public void refreshMeta() {
        this.itemStack.setItemMeta(itemMeta);
    }

    public ItemBuilder setName(String name) {
        this.itemMeta.setDisplayName(ChatUtil.fixColor(name));
        this.refreshMeta();

        return this;
    }

    public ItemBuilder setClickExecutor(final Consumer<InventoryClickEvent> consumer){
        this.consumer = consumer;
        return this;
    }

    public ItemBuilder setSkullOwner(String owner) {
        Validate.isTrue(this.itemStack.getItemMeta() instanceof SkullMeta, "Not an skull.");
        if (this.itemStack.getDurability() != 3) this.itemStack.setDurability((short) 3);
        SkullMeta meta = (SkullMeta) this.itemStack.getItemMeta();
        meta.setOwner(owner);
        this.itemStack.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setLore(List<String> lore) {
        final List<String> formatted = new ArrayList<>();
        for (String str : lore) {
            formatted.add(ChatUtil.fixColor(str));
        }

        this.itemMeta.setLore(formatted);
        this.refreshMeta();

        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag flag){
        this.itemMeta.addItemFlags(flag);
        this.refreshMeta();
        return this;
    }

    public ItemBuilder setLore(String... lore) {
        return setLore(Arrays.asList(lore));
    }

    public ItemBuilder addEnchant(Enchantment enchant, int level) {
        this.itemMeta.addEnchant(enchant, level, true);
        this.refreshMeta();

        return this;
    }

    public ItemBuilder setFlag(ItemFlag flag) {
        this.itemMeta.addItemFlags(flag);
        this.refreshMeta();

        return this;
    }

    public ItemBuilder setAmount(int amount){
        this.itemStack.setAmount(amount);
        return this;
    }

    public ItemStack getItem() {
        return this.itemStack;
    }

    public ItemMeta getMeta() {
        return this.itemMeta;
    }
    public GuiItem getGuiItem(){
        return new GuiItem(this);
    }
    public Consumer<InventoryClickEvent> getConsumer() {
        return consumer;
    }
}