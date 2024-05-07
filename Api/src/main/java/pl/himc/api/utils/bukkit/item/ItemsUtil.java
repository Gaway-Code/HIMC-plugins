package pl.himc.api.utils.bukkit.item;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.*;
import java.util.stream.Stream;

public final class ItemsUtil {

    public static void giveItem(Player player, ItemStack item) {
        HashMap<Integer, ItemStack> notStored = player.getInventory().addItem(item);
        for (Map.Entry<Integer, ItemStack> en : notStored.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), en.getValue());
        }
    }

    public static List<ItemStack> getItems(String string, int modifier) {
        List<ItemStack> items = new ArrayList<>();
        for (String s : string.split(";")) {
            String[] split = s.split("-");
            String id = split[0].split(":")[0];
            int data = Integer.parseInt(split[0].split(":")[1]);
            int amount = Integer.parseInt(split[1]) * modifier;
            items.add(new ItemStack(Material.matchMaterial(id), amount, (short)data));
        }
        return items;
    }

    public static ItemStack getPlayerHead(String name) {
        ItemStack itemStack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta)itemStack.getItemMeta();
        meta.setOwner(name);
        meta.setDisplayName(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static void remove(ItemStack is, Player player, int amount) {
        int removed = 0;
        boolean all = false;
        List<ItemStack> toRemove = new ArrayList<>();
        for(ItemStack item : player.getInventory().getContents()) {
            if (item != null) {
                if (!item.getType().equals(Material.AIR)) {
                    if (item.getType().equals(is.getType())) {
                        if (item.getDurability() == is.getDurability()) {
                            if (!all) {
                                if (removed != amount) {
                                    if (item.getAmount() == amount) {
                                        if (removed == 0) {
                                            toRemove.add(item.clone());
                                            all = true;
                                            removed = item.getAmount();
                                        }
                                        else {
                                            int a = amount - removed;
                                            ItemStack s = item.clone();
                                            s.setAmount(a);
                                            toRemove.add(s);
                                            removed += a;
                                            all = true;
                                        }
                                    }
                                    else if (item.getAmount() > amount) {
                                        if (removed == 0) {
                                            ItemStack s2 = item.clone();
                                            s2.setAmount(amount);
                                            toRemove.add(s2);
                                            all = true;
                                            removed = amount;
                                        }
                                        else {
                                            int a = amount - removed;
                                            ItemStack s = item.clone();
                                            s.setAmount(a);
                                            toRemove.add(s);
                                            removed += a;
                                            all = true;
                                        }
                                    }
                                    else if (item.getAmount() < amount) {
                                        if (removed == 0) {
                                            toRemove.add(item.clone());
                                            removed = item.getAmount();
                                        }
                                        else {
                                            int a = amount - removed;
                                            if (a == item.getAmount()) {
                                                toRemove.add(item.clone());
                                                removed += item.getAmount();
                                                all = true;
                                            }
                                            else if (item.getAmount() > a) {
                                                ItemStack s = item.clone();
                                                s.setAmount(a);
                                                toRemove.add(s);
                                                removed += a;
                                                all = true;
                                            }
                                            else if (item.getAmount() < a) {
                                                toRemove.add(item.clone());
                                                removed += item.getAmount();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        removeItems(player, toRemove);
    }

    public static void removeItems(Player player, List<ItemStack> items) {
        if (player == null || items == null || items.isEmpty()) {
            return;
        }
        for (ItemStack is : items) {
            player.getInventory().removeItem(is);
        }
    }

    public static void removeItem(Player player, String displayName){
        Stream.of(player.getInventory().getContents())
                .filter(Objects::nonNull)
                .filter(itemStack -> itemStack.getItemMeta() != null)
                .filter(itemStack -> itemStack.getItemMeta().getDisplayName() != null)
                .filter(itemStack -> itemStack.getItemMeta().getDisplayName().equalsIgnoreCase(ChatUtil.fixColor(displayName)))
                .forEach(itemStack -> player.getInventory().remove(itemStack));
    }

    public static int getAmountOfItem(Material material, Player player, short durability) {
        return Stream.of(player.getInventory().getContents())
                .filter(Objects::nonNull)
                .filter(itemStack -> itemStack.getType().equals(material))
                .filter(itemStack -> itemStack.getDurability() == durability).mapToInt(ItemStack::getAmount).sum();
    }

    public static void giveItems(Player player, List<ItemStack> items){
        items.forEach(item -> giveItem(player, item));
    }

    public static boolean hasItems(Player player, List<ItemStack> items) {
        if (player == null || items == null) {
            return false;
        }
        if (items.isEmpty()) {
            return true;
        }
        for (ItemStack is : items) {
            if (!player.getInventory().containsAtLeast(is, is.getAmount())) {
                return false;
            }
        }
        return true;
    }

    public static void removeItemInHand(Player p){
        if(p.getItemInHand().getAmount() > 1){
            p.getItemInHand().setAmount(p.getItemInHand().getAmount() - 1);
        }else{
            p.setItemInHand(null);
        }
    }
}
