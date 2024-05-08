package pl.himc.api.api.book;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

public class FancyBook {

    private final String title;
    private final String author;

    private final ArrayList<FancyBookPage> pages = new ArrayList<>();

    public FancyBook(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void addPage(FancyBookPage page) {
        pages.add(page);
    }

    public void addPage(FancyBookPage... pages) {
        this.pages.addAll(Arrays.asList(pages));
    }

    @SuppressWarnings("deprecation")
    public ItemStack getBook() {

        ItemStack stack = new ItemStack(Material.WRITTEN_BOOK);

        try {
            return Bukkit.getUnsafe().modifyItemStack(stack, translateBook());
        } catch (Throwable localThrowable) {
            return stack;
        }
    }

    @SuppressWarnings("All")
    public void openBook(ItemStack book, Player player) {

        int slot = player.getInventory().getHeldItemSlot();
        ItemStack old = player.getInventory().getItem(slot);
        player.getInventory().setItem(slot, book);

        ByteBuf buf = Unpooled.buffer(256);
        buf.setByte(0, (byte) 0);
        buf.writerIndex(1);

        try {
            //get minecraft server version
            String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
            //get player handle
            Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
            //get player connection
            Object connection = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
            Class<?> packetDataSerializer = Class.forName("net.minecraft.server." + version + ".PacketDataSerializer");
            Constructor<?> packetDataSerializerConstructor = packetDataSerializer.getConstructor(ByteBuf.class);

            Class<?> packetPlayOutCustomPayload = Class.forName("net.minecraft.server." + version + ".PacketPlayOutCustomPayload");
            Constructor packetPlayOutCustomPayloadConstructor = packetPlayOutCustomPayload.getConstructor(String.class,
                    Class.forName("net.minecraft.server." + version + ".PacketDataSerializer"));

            connection.getClass().getMethod("sendPacket", Class.forName("net.minecraft.server." + version + ".Packet"))
                    .invoke(connection, packetPlayOutCustomPayloadConstructor.newInstance("MC|BOpen", packetDataSerializerConstructor.newInstance(buf)));

        } catch (Exception ex) {
            player.getInventory().addItem(book);
        }
        player.getInventory().setItem(slot, old);
    }

    public void openBook(Player player) {
        openBook(getBook(), player);
    }

    public String translateBook() {
        return new Translate(title, author, pages).translate.toString();
    }

    private class Translate {

        private StringBuilder translate;

        private Translate(String title, String author, ArrayList<FancyBookPage> pages) {

            translate = new StringBuilder("{pages:[");
            
            if (pages != null && !pages.isEmpty()) {

                int pageNumber = 1;
                for (FancyBookPage page : pages) {
                    translate.append("\"[\\\"\\\",");
                    translate.append(translatePage(page));
                    translate.append("]\"").append(pageNumber < pages.size() ? "," : "");
                    pageNumber++;
                }
            }

            translate.append(String.format("],title:%s,author:%s}", title, author));
        }

        private String translatePage(FancyBookPage page) {

            StringBuilder str = new StringBuilder();
            int textNumber = 1;

            for (FancyTextComponent textComponent : page.getText()) {

                if (textNumber == 1) {
                    str.append("\\\"\\\",");
                }

                str.append(String.format("{\\\"text\\\":\\\"%s\\\",\\\"color\\\":\\\"%s\\\"%s%s%s}",
                        textComponent.getText(),
                        textComponent.getColor(),

                        translateAttributes(textComponent),

                        (textComponent.hasClickEvent() ? String.format(",\\\"clickEvent\\\":{\\\"action\\\":\\\"%s\\\",\\\"value\\\":\\\"%s\\\"}",
                                textComponent.getClickEvent().getClickEvent(), textComponent.getClickEvent().getValue()) : ""),

                        (textComponent.hasHoverEvent() ? String.format("%s", translateHoverEvent(textComponent.getHoverEvent())) : "")))


                        .append(textNumber < page.getText().size() ? "," : "");
                textNumber++;
            }
            return str.toString();
        }

        private String translateAttributes(FancyTextComponent textComponent) {
            StringBuilder str = new StringBuilder();
            for (String attribute : textComponent.getAttributes()) {
                str.append(String.format(",\\\"%s\\\":\\\"true\\\"", attribute));
            }
            return str.toString();
        }

        private String translateHoverEvent(FancyHoverEvent hoverEvent) {
            StringBuilder str = new StringBuilder(",\\\"hoverEvent\\\":{\\\"action\\\":\\\"show_text\\\",\\\"value\\\":{\\\"text\\\":\\\"\\\",\\\"extra\\\":[");
            int textNumber = 1;

            for (FancyTextComponent textComponent : hoverEvent.getText()) {
                str.append(String.format("{\\\"text\\\":\\\"%s\\\",\\\"color\\\":\\\"%s\\\"}", textComponent.getText(), textComponent.getColor()))

                        .append(textNumber < hoverEvent.getText().size() ? "," : "");
                textNumber++;
            }

            str.append("]}}");

            return str.toString();
        }
    }
}