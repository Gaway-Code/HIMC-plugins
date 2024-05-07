package pl.himc.api.api.inventory;

import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import org.apache.commons.lang.Validate;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class GuiWindow {

    private final Inventory inv;
    private final String name;
    private ItemStack rightArrowItem;
    private ItemStack leftArrowItem;
    private final Map<Integer, GuiItem> items;
    private Consumer<InventoryOpenEvent>  openHandler  = null;
    private Consumer<InventoryCloseEvent> closeHandler = null;

    public GuiWindow(String name, int rows) {
        this.name = getValidName(ChatUtil.fixColor(name));

        this.rightArrowItem = new ItemBuilder(Material.SKULL_ITEM).setSkullOwner("MHF_ArrowRight").getItem();
        this.leftArrowItem = new ItemBuilder(Material.SKULL_ITEM).setSkullOwner("MHF_ArrowLeft").getItem();

        this.inv = Bukkit.createInventory(null, rows > 6 ? 6 * 9 : rows * 9, this.name);
        this.items = new HashMap<>(rows > 6 ? 6 * 9 : rows * 9);

        GuiManager.getGui().add(this.name, this);
    }

    public GuiWindow(String name, List<ItemStack> items) {
        this.name = this.getValidName(name);

        this.inv = Bukkit.createInventory(null, this.roundUp(items.size()), this.name);
        this.items = new HashMap<>(this.roundUp(items.size()));

        GuiManager.getGui().add(name, this);
    }

    public void setItem(int slot, GuiItem item) {
        this.items.put(slot, item);
        this.inv.setItem(slot, item.wrap());
    }

    public void setItem(int x, int y, GuiItem item) {
        setItem(x + y * 9, item);
    }

    public void setToNextFree(GuiItem item) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                this.items.put(i, item);
                this.inv.setItem(i, item.wrap());
                break;
            }
        }
    }

    public void setToNextFree(final GuiItem item, int start) {
        for (int i = start; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                this.items.put(i, item);
                this.inv.setItem(i, item.wrap());
                break;
            }
        }
    }

    public GuiItem getItem(int slot) {
        return this.items.get(slot);
    }

    public GuiItem getItem(int x, int y) {
        return getItem(x * 9 + y);
    }

    public void setOpenEvent(final Consumer<InventoryOpenEvent> e) {
        this.openHandler = e;
    }

    public void setCloseEvent(final Consumer<InventoryCloseEvent> e) {
        this.closeHandler = e;
    }

    public void handleOpen(final InventoryOpenEvent e) {
        if (this.openHandler != null) {
            this.openHandler.accept(e);
        }
    }

    public void handleClose(final InventoryCloseEvent e) {
        if (this.closeHandler != null) {
            this.closeHandler.accept(e);
        }
    }

    public void open(HumanEntity entity, boolean unregister) {
        if(unregister) this.setCloseEvent(inventoryCloseEvent -> this.unregister());
        entity.openInventory(this.inv);
    }

    public void open(HumanEntity entity) {
        this.setCloseEvent(inventoryCloseEvent -> this.unregister());
        entity.openInventory(this.inv);
    }

    public void unregister() {
        GuiManager.getGui().remove(this.name);
        this.items.clear();
    }

    private String getValidName(String name) {
        if (GuiManager.getGui().getAll().containsKey(name)) {
            return getValidName(name + ChatColor.RESET);
        }
        else {
            return name;
        }
    }

    public void fillEmpty(final GuiItem guiItem) {
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                this.items.put(i, guiItem);
                this.inv.setItem(i, guiItem.wrap());
            }
        }
    }

    public void fillEmpty(final Material material) {
        Validate.notNull(material, "Material cannot be NULL!");
        for (int i = 0; i < inv.getSize(); i++) {
            if (inv.getItem(i) == null) {
                inv.setItem(i, new ItemStack(material, 1));
            }
        }
    }

    public ItemStack undoPageItem(){
        ItemStack is = this.leftArrowItem;
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatUtil.fixColor("&7&l<% &cCofnij"));
        is.setItemMeta(im);
        return is;
    }
    public ItemStack nextPageItem(){
        ItemStack is = this.rightArrowItem;
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(ChatUtil.fixColor("&aNastepna strona &7&l%>"));
        is.setItemMeta(im);
        return is;
    }
    private int roundUp(int size) {
        return (size + 8) / 9 * 9 > 54 ? 54 : (size + 8) / 9 * 9;
    }

}