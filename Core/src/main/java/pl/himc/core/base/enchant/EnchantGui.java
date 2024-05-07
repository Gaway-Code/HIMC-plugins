package pl.himc.core.base.enchant;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

import java.util.Arrays;

public final class EnchantGui {

    private static ItemBuilder prot4 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ochrona &2IV &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &650 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &625")));

    private static ItemBuilder prot3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ochrona &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder prot2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ochrona &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder prot1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ochrona &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &615 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &67")));

    private static ItemBuilder fireprot4 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odpornosc na ogien &2IV &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder fireprot3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odpornosc na ogien &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &615 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &67")));

    private static ItemBuilder fireprot2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odpornosc na ogien &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &65")));

    private static ItemBuilder fireprot1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odpornosc na ogien &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &65 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &62")));

    private static ItemBuilder featherfall4 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Powolne opadanie &2IV &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &635 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &617")));

    private static ItemBuilder featherfall3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Powolne opadanie &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder featherfall2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Powolne opadanie &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder featherfall1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Powolne opadanie &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &615 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &67")));

    private static ItemBuilder unb3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Niezniszczalnosc &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &640 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder unb2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Niezniszczalnosc &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder unb1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Niezniszczalnosc &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder thorns3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ciernie &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &625 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &612")));

    private static ItemBuilder thorns2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ciernie &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &615 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &67")));

    private static ItemBuilder thorns1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ciernie &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &65")));

    private static ItemBuilder eff5 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Wydajnosc &2V &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &640 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder eff4 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Wydajnosc &2IV &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder eff3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Wydajnosc &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder eff2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Wydajnosc &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &65")));

    private static ItemBuilder eff1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Wydajnosc &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &65 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &62")));

    private static ItemBuilder fortune3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Szczescie &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &635 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &617")));

    private static ItemBuilder fortune2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Szczescie &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &625 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &612")));

    private static ItemBuilder fortune1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Szczescie &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &615 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &67")));

    private static ItemBuilder sharp5 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ostrosc &2V &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &650 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &625")));

    private static ItemBuilder sharp4 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ostrosc &2IV &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &640 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder sharp3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ostrosc &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder sharp2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ostrosc &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder sharp1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Ostrosc &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &65")));

    private static ItemBuilder fireasp2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Zaklety ogien &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &640 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder fireasp1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Zaklety ogien &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder knock3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odrzut &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &625")));

    private static ItemBuilder knock2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odrzut &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder knock1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odrzut &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &615 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &67")));

    private static ItemBuilder power5 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Moc &2V &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &650 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &625")));

    private static ItemBuilder power4 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Moc &2IV &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &640 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder power3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Moc &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder power2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Moc &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &610")));

    private static ItemBuilder power1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Moc &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &65")));

    private static ItemBuilder infinity1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Nieskonczonosc &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder plomien3 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Plomien &2III &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &630 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &625")));

    private static ItemBuilder plomien2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Plomien &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder plomien1 =  new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Plomien &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));

    private static ItemBuilder punch2 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odrzut &2II &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &620 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &620")));

    private static ItemBuilder punch1 = new ItemBuilder(Material.ENCHANTED_BOOK)
            .setName("&8%> &7Odrzut &2I &8<%")
            .setLore(Arrays.asList(ChatUtil.fixColor("&8%> &7Koszt: &610 LvL"), ChatUtil.fixColor("&8%> &7Wymagane biblioteczki: &615")));


    public static void open(Player p, ItemStack item, int books) {
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&b&oWybierany enchant"), 6);

        if (EnchantManager.getEnchantmentPartTypeForItemStack(item) == EnchantManager.EnchantType.ARMOR) {
            gui.setItem(19, new GuiItem(prot4.getItem(), event -> EnchantAction.onClick(event, "prot4", books)));
            gui.setItem(28, new GuiItem(prot3.getItem(), event -> EnchantAction.onClick(event, "prot3", books)));
            gui.setItem(37, new GuiItem(prot2.getItem(), event -> EnchantAction.onClick(event, "prot2", books)));
            gui.setItem(46, new GuiItem(prot1.getItem(), event -> EnchantAction.onClick(event, "prot1", books)));

            gui.setItem(20, new GuiItem(fireprot4.getItem(), event -> EnchantAction.onClick(event, "fireprot4", books)));
            gui.setItem(29, new GuiItem(fireprot3.getItem(), event -> EnchantAction.onClick(event, "fireprot3", books)));
            gui.setItem(38, new GuiItem(fireprot2.getItem(), event -> EnchantAction.onClick(event, "fireprot2", books)));
            gui.setItem(47, new GuiItem(fireprot1.getItem(), event -> EnchantAction.onClick(event, "fireprot1", books)));

            if (EnchantManager.getEnchantTypeForItemStack(item) == EnchantManager.EnchantType.BOOTS) {
                gui.setItem(21, new GuiItem(featherfall4.getItem(), event -> EnchantAction.onClick(event, "featherfall4", books)));
                gui.setItem(30, new GuiItem(featherfall3.getItem(), event -> EnchantAction.onClick(event, "featherfall3", books)));
                gui.setItem(39, new GuiItem(featherfall2.getItem(), event -> EnchantAction.onClick(event, "featherfall2", books)));
                gui.setItem(48, new GuiItem(featherfall1.getItem(), event -> EnchantAction.onClick(event, "featherfall1", books)));

                gui.setItem(31, new GuiItem(unb3.getItem(), event -> EnchantAction.onClick(event, "unb3", books)));
                gui.setItem(40, new GuiItem(unb2.getItem(), event -> EnchantAction.onClick(event, "unb2", books)));
                gui.setItem(49, new GuiItem(unb1.getItem(), event -> EnchantAction.onClick(event, "unb1", books)));

                gui.setItem(32, new GuiItem(thorns3.getItem(), event -> EnchantAction.onClick(event, "thorns3", books)));
                gui.setItem(41, new GuiItem(thorns2.getItem(), event -> EnchantAction.onClick(event, "thorns2", books)));
                gui.setItem(50, new GuiItem(thorns1.getItem(), event -> EnchantAction.onClick(event, "thorns1", books)));
            } else {
                gui.setItem(30, new GuiItem(unb3.getItem(), event -> EnchantAction.onClick(event, "unb3", books)));
                gui.setItem(39, new GuiItem(unb2.getItem(), event -> EnchantAction.onClick(event, "unb2", books)));
                gui.setItem(48, new GuiItem(unb1.getItem(), event -> EnchantAction.onClick(event, "unb1", books)));

                gui.setItem(31, new GuiItem(thorns3.getItem(), event -> EnchantAction.onClick(event, "thorns3", books)));
                gui.setItem(40, new GuiItem(thorns2.getItem(), event -> EnchantAction.onClick(event, "thorns2", books)));
                gui.setItem(49, new GuiItem(thorns1.getItem(), event -> EnchantAction.onClick(event, "thorns1", books)));
            }
        } else if (EnchantManager.getEnchantmentPartTypeForItemStack(item) == EnchantManager.EnchantType.TOOL) {
            gui.setItem(10, new GuiItem(eff5.getItem(), event -> EnchantAction.onClick(event, "eff5", books)));
            gui.setItem(19, new GuiItem(eff4.getItem(), event -> EnchantAction.onClick(event, "eff4", books)));
            gui.setItem(28, new GuiItem(eff3.getItem(), event -> EnchantAction.onClick(event, "eff3", books)));
            gui.setItem(37, new GuiItem(eff2.getItem(), event -> EnchantAction.onClick(event, "eff2", books)));
            gui.setItem(46, new GuiItem(eff1.getItem(), event -> EnchantAction.onClick(event, "eff1", books)));

            gui.setItem(29, new GuiItem(unb3.getItem(), event -> EnchantAction.onClick(event, "unb3", books)));
            gui.setItem(38, new GuiItem(unb2.getItem(), event -> EnchantAction.onClick(event, "unb2", books)));
            gui.setItem(47, new GuiItem(unb1.getItem(), event -> EnchantAction.onClick(event, "unb1", books)));

            gui.setItem(30, new GuiItem(fortune3.getItem(), event -> EnchantAction.onClick(event, "fortune3", books)));
            gui.setItem(39, new GuiItem(fortune2.getItem(), event -> EnchantAction.onClick(event, "fortune2", books)));
            gui.setItem(48, new GuiItem(fortune1.getItem(), event -> EnchantAction.onClick(event, "fortune1", books)));

        } else if (EnchantManager.getEnchantTypeForItemStack(item) == EnchantManager.EnchantType.SWORD) {
            gui.setItem(10, new GuiItem(sharp5.getItem(), event -> EnchantAction.onClick(event, "sharp5", books)));
            gui.setItem(19, new GuiItem(sharp4.getItem(), event -> EnchantAction.onClick(event, "sharp4", books)));
            gui.setItem(28, new GuiItem(sharp3.getItem(), event -> EnchantAction.onClick(event, "sharp3", books)));
            gui.setItem(37, new GuiItem(sharp2.getItem(), event -> EnchantAction.onClick(event, "sharp2", books)));
            gui.setItem(46, new GuiItem(sharp1.getItem(), event -> EnchantAction.onClick(event, "sharp1", books)));

            gui.setItem(29, new GuiItem(unb3.getItem(), event -> EnchantAction.onClick(event, "unb3", books)));
            gui.setItem(38, new GuiItem(unb2.getItem(), event -> EnchantAction.onClick(event, "unb2", books)));
            gui.setItem(47, new GuiItem(unb1.getItem(), event -> EnchantAction.onClick(event, "unb1", books)));

            gui.setItem(39, new GuiItem(fireasp2.getItem(), event -> EnchantAction.onClick(event, "fireasp2", books)));
            gui.setItem(48, new GuiItem(fireasp1.getItem(), event -> EnchantAction.onClick(event, "fireasp1", books)));

            gui.setItem(31, new GuiItem(knock3.getItem(), event -> EnchantAction.onClick(event, "knock3", books)));
            gui.setItem(40, new GuiItem(knock2.getItem(), event -> EnchantAction.onClick(event, "knock2", books)));
            gui.setItem(49, new GuiItem(knock1.getItem(), event -> EnchantAction.onClick(event, "knock1", books)));
        } else {
            if (EnchantManager.getEnchantTypeForItemStack(item) != EnchantManager.EnchantType.BOW) {
                p.sendMessage(ChatUtil.fixColor("&cNie mozna enchantowac tego przemiotu!"));
                return;
            }
            gui.setItem(10, new GuiItem(power5.getItem(), event -> EnchantAction.onClick(event, "power5", books)));
            gui.setItem(19, new GuiItem(power4.getItem(), event -> EnchantAction.onClick(event, "power4", books)));
            gui.setItem(28, new GuiItem(power3.getItem(), event -> EnchantAction.onClick(event, "power3", books)));
            gui.setItem(37, new GuiItem(power2.getItem(), event -> EnchantAction.onClick(event, "power2", books)));
            gui.setItem(46, new GuiItem(power1.getItem(), event -> EnchantAction.onClick(event, "power1", books)));

            gui.setItem(29, new GuiItem(plomien3.getItem(), event -> EnchantAction.onClick(event, "plomien3", books)));
            gui.setItem(38, new GuiItem(plomien2.getItem(), event -> EnchantAction.onClick(event, "plomien2", books)));
            gui.setItem(47, new GuiItem(plomien1.getItem(), event -> EnchantAction.onClick(event, "plomien1", books)));

            gui.setItem(39, new GuiItem(punch2.getItem(), event -> EnchantAction.onClick(event, "punch2", books)));
            gui.setItem(48, new GuiItem(punch1.getItem(), event -> EnchantAction.onClick(event, "punch1", books)));

            gui.setItem(31, new GuiItem(unb3.getItem(), event -> EnchantAction.onClick(event, "unb3", books)));
            gui.setItem(40, new GuiItem(unb2.getItem(), event -> EnchantAction.onClick(event, "unb2", books)));
            gui.setItem(49, new GuiItem(unb1.getItem(), event -> EnchantAction.onClick(event, "unb1", books)));

            gui.setItem(50, new GuiItem(infinity1.getItem(), event -> EnchantAction.onClick(event, "infinity1", books)));
        }
        gui.setItem(0, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(4, new GuiItem(new ItemBuilder(Material.ENCHANTMENT_TABLE).setName("&8* &7&oAktywne biblioteczki: &a&o" + books + " &8*").addEnchant(Enchantment.DURABILITY, 1337).addItemFlag(ItemFlag.HIDE_ENCHANTS).getItem()));
        gui.setItem(9, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(18, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(27, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(36, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(45, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(8, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(17, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(26, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(35, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(44, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.setItem(53, new GuiItem(new ItemBuilder(Material.BOOKSHELF).setName("&6").getItem()));
        gui.open(p);
    }
}
