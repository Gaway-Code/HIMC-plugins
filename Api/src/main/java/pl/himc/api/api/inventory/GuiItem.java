package pl.himc.api.api.inventory;

import pl.himc.api.utils.bukkit.item.ItemBuilder;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

public class GuiItem {

    private final ItemStack item;
    private final Consumer<InventoryClickEvent> consumer;

    public GuiItem(final ItemStack item) {
        this.item = item;
        this.consumer = ignored -> {};
    }

    public GuiItem(final ItemStack item, final Consumer<InventoryClickEvent> consumer) {
        this.item = item;
        this.consumer = consumer != null ? consumer : a -> {};
    }

    public GuiItem(final ItemBuilder itemBuilder) {
        this.item = itemBuilder.getItem();
        this.consumer = itemBuilder.getConsumer() != null ? itemBuilder.getConsumer() : a -> {};
    }

    public ItemStack wrap() {
        return this.item;
    }

    public void handleClick(final InventoryClickEvent e) {
        this.consumer.accept(e);
    }
}