package pl.himc.api.utils;

import org.bukkit.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemNameUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public final class StringUtil {

    public static String stringBuilder(String[] args, int liczOdArgumentu) {
        String msg = "";
        for (int i = liczOdArgumentu; i < args.length; i++) {
            msg = msg + args[i];
            if (i <= args.length - 2) {
                msg = msg + " ";
            }
        }
        return msg;
    }

    public static Location locationFromString(String msg) {
        if (msg == null || msg.equalsIgnoreCase("") || !msg.contains(",")) {
            return new Location(Bukkit.getWorld("world"), 0.0, 0.0, 0.0);
        }
        String[] m = msg.split(",");
        World world = Bukkit.getWorld(m[0]);
        double x = Double.parseDouble(m[1]);
        double y = Double.parseDouble(m[2]);
        double z = Double.parseDouble(m[3]);
        if (m.length <= 4) {
            return new Location(world, x, y, z);
        }
        float yaw = Float.parseFloat(m[4]);
        float pitch = Float.parseFloat(m[5]);
        return new Location(world, x, y, z, yaw, pitch);
    }

    public static String locationLongToString(Location loc) {
        if (loc == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(loc.getWorld().getName());
        sb.append("," + loc.getX());
        sb.append("," + loc.getY());
        sb.append("," + loc.getZ());
        sb.append("," + loc.getYaw());
        sb.append("," + loc.getPitch());
        return sb.toString();
    }

    public static String locationToString(Location loc) {
        if (loc == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(loc.getWorld().getName());
        sb.append("," + loc.getX());
        sb.append("," + loc.getY());
        sb.append("," + loc.getZ());
        return sb.toString();
    }

    public static String toString(Collection<String> list, boolean send) {
        StringBuilder builder = new StringBuilder();
        for (String s : list) {
            builder.append(s);
            builder.append(',');

            if (send) {
                builder.append(' ');
            }
        }

        String s = builder.toString();
        if (send) {
            if (s.length() > 2) {
                s = s.substring(0, s.length() - 2);
            } else if (s.length() > 1) {
                s = s.substring(0, s.length() - 1);
            }
        }

        return s;
    }

    public static List<String> fromString(String s) {
        List<String> list = new ArrayList<>();
        if (s == null || s.isEmpty()) {
            return list;
        }

        list = Arrays.asList(s.split(","));
        return list;
    }

    public static String replaceItems(List<ItemStack> items) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (ItemStack item : items) {
            if (i == 0) {
                sb.append(item.getAmount() + " " + ItemNameUtil.getName(item.getType()));
            }
            else {
                sb.append(", " + item.getAmount() + " " + ItemNameUtil.getName(item.getType()));
            }
            ++i;
        }
        String message = PluginApi.getApi().getPluginMessages().playerNoHasItems;
        message = message.replace("{ITEMS}", sb.toString());
        return message;
    }


    public static boolean isLettersAndNumbers(String string){
        return string.matches("[a-zA-Z0-9]*");
    }

    public static String newStringBuilder(String[] args, int liczOdArgs){
        return ChatUtil.removeColor(stringBuilder(args, liczOdArgs));
    }

    public static String inventoryToString(Inventory inventory) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(inventory.getSize());
            dataOutput.writeUTF(inventory.getName());
            for (int i = 0; i < inventory.getSize(); ++i) {
                dataOutput.writeObject(inventory.getItem(i));
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        }
        catch (Exception e) {
            throw new IllegalStateException("Wystapil blad podczas zapisania inventory.", e);
        }
    }

    public static Inventory inventoryFromString(String data) throws IOException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            Inventory inventory = Bukkit.getServer().createInventory(null, dataInput.readInt(), ChatUtil.fixColor(dataInput.readUTF()));
            for (int i = 0; i < inventory.getSize(); ++i) {
                inventory.setItem(i, (ItemStack)dataInput.readObject());
            }
            dataInput.close();
            return inventory;
        }
        catch (ClassNotFoundException e) {
            throw new IOException("Wystapil blad podczas wczytywania inventory.", e);
        }
    }
    public static String itemStackArrayToBase64(ItemStack[] items) {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BukkitObjectOutputStream dataOutput = new BukkitObjectOutputStream(outputStream);
            dataOutput.writeInt(items.length);
            for (int i = 0; i < items.length; i++) {
                dataOutput.writeObject(items[i]);
            }
            dataOutput.close();
            return Base64Coder.encodeLines(outputStream.toByteArray());
        } catch (Exception e) {
            throw new IllegalStateException("Unable to save item stacks.", e);
        }
    }
    public static ItemStack[] itemStackArrayFromBase64(String data) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(Base64Coder.decodeLines(data));
            BukkitObjectInputStream dataInput = new BukkitObjectInputStream(inputStream);
            ItemStack[] items = new ItemStack[dataInput.readInt()];
            for (int i = 0; i < items.length; i++) {
                items[i] = (ItemStack) dataInput.readObject();
            }
            dataInput.close();
            return items;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ItemStack[]{};
    }

    public static ItemStack[] cloneItemStacks(ItemStack[] contents) {
        ItemStack[] itemStacks = new ItemStack[contents.length];
        for(int i = 0; i < contents.length; i++) {
            if(contents[i] == null) continue;
            itemStacks[i] = new ItemStack(contents[i]);
        }
        return itemStacks;
    }

    public static String kitsToString(Map<String, Long> kits) {
        if (kits == null || kits.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Map.Entry<String, Long> e : kits.entrySet()) {
            if (i == 0) {
                sb.append(e.getKey() + ":" + e.getValue());
            }
            else {
                sb.append("," + e.getKey() + ":" + e.getValue());
            }
            ++i;
        }
        return sb.toString();
    }

    public static Map<String, Long> stringToKits(String string) {
        Map<String, Long> kits = new HashMap<>();
        if (string == null || string.isEmpty()) {
            return kits;
        }
        String[] ss = string.split(",");
        for (int i = 0; i < ss.length; ++i) {
            String s = ss[i];
            String[] sss = s.split(":");
            long delay = Long.parseLong(sss[1]);
            kits.put(sss[0], delay);
        }
        return kits;
    }

    public static String homesToString(Map<String, Location> homes) {
        if (homes == null || homes.isEmpty()) {
            return "";
        }
        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Location> e : homes.entrySet()) {
            if (i == 0) {
                sb.append(e.getKey() + ":" + locationLongToString(e.getValue()));
            }
            else {
                sb.append(";" + e.getKey() + ":" + locationLongToString(e.getValue()));
            }
            ++i;
        }
        return sb.toString();
    }

    public static Map<String, Location> stringToHomes(String string) {
        Map<String, Location> homes = new HashMap<>();
        if (string == null || string.isEmpty()) {
            return homes;
        }
        String[] sss = string.split(";");
        for (int i = 0; i < sss.length; ++i) {
            String ss = sss[i];
            String[] s = ss.split(":");
            homes.put(s[0], locationFromString(s[1]));
        }
        return homes;
    }

    public static String rainbow(String original){
        char[] chars = new char[]{'c', '6', 'e', 'a', 'b', '3', 'd'};
        int index = 0;
        String returnValue = "";
        for (char c : original.toCharArray()){
            returnValue += "&" + chars[index] + c;
            index++;
            if (index == chars.length){
                index = 0;
            }
        }
        return ChatColor.translateAlternateColorCodes('&', returnValue);
    }
}
