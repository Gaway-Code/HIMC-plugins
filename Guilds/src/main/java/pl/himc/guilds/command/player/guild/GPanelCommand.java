package pl.himc.guilds.command.player.guild;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;

import java.util.Arrays;

public class GPanelCommand extends PlayerCommand {

    public GPanelCommand() {
        super("panel", null, "/g panel");
        this.guiItemDarkGlass = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 15).setName("&8*").getItem());
        this.guiItemGreenGlass = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short) 5).setName("&8*").getItem());
    }

    private GuiItem guiItemDarkGlass;
    private GuiItem guiItemGreenGlass;

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.isOwner(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }
        this.openMain(player, guild);
        return true;
    }

    public void openMain(Player player, Guild guild) {
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&dPanel gildii"), 5);

        gui.setItem(0, this.guiItemGreenGlass);
        gui.setItem(1, this.guiItemGreenGlass);
        gui.setItem(2, this.guiItemDarkGlass);
        gui.setItem(3, this.guiItemDarkGlass);
        gui.setItem(4, this.guiItemDarkGlass);
        gui.setItem(5, this.guiItemDarkGlass);
        gui.setItem(6, this.guiItemDarkGlass);
        gui.setItem(7, this.guiItemGreenGlass);
        gui.setItem(8, this.guiItemGreenGlass);
        gui.setItem(9, this.guiItemGreenGlass);
        gui.setItem(10, this.guiItemDarkGlass);
        gui.setItem(11, new ItemBuilder(Material.ENDER_CHEST).setName("&7Otworz &6skarbiec gildii").setClickExecutor(inventoryClickEvent -> guild.openTreasure(player)).getGuiItem());
        gui.setItem(15, new ItemBuilder(Material.SKULL_ITEM).setName("&7Uprawnienia &6czlonkow gildii").setSkullOwner(player.getName()).setClickExecutor(inventoryClickEvent -> openMembersList(player, guild)).getGuiItem());

        gui.setItem(16, this.guiItemDarkGlass);
        gui.setItem(17, this.guiItemGreenGlass);
        gui.setItem(18, this.guiItemDarkGlass);
        gui.setItem(19, this.guiItemDarkGlass);
        gui.setItem(25, this.guiItemDarkGlass);
        gui.setItem(26, this.guiItemDarkGlass);
        gui.setItem(27, this.guiItemGreenGlass);
        gui.setItem(28, this.guiItemDarkGlass);
        gui.setItem(30, new ItemBuilder(Material.GRASS).setName("&8").setLore(Arrays.asList("&7",
                "    &7&lPowiekszenie terenu gildii",
                "&7",
                " &8%> &7Aktualny teren: &a" + guild.getCuboid().getSize() + "&7x&a" + guild.getCuboid().getSize(),
                " &8%> &7Kliknij aby &2powiekszyc teren gildii&7!"
        )).setClickExecutor(inventoryClickEvent -> {
            guild.getCuboid().enlargeCuboid(player);
            openMain(player, guild);
        }).getGuiItem());

        gui.setItem(13, new ItemBuilder(Material.BOOK).setName("&8").setLore(Arrays.asList("&7",
                "         &7&lPrzedluzenie waznosci gildii",
                "&7",
                " &8%> &7Gildia jest wazna do: &6" + TimeUtil.getDate(guild.getValidity()),
                " &8%> &7Kliknij aby &6przedluzyc waznosc gildii&7!"
        )).setClickExecutor(inventoryClickEvent -> {
            PluginGuild.getPlugin().getGuildManager().validityGuild(player, guild);
            openMain(player, guild);
        }).getGuiItem());

        gui.setItem(32, new ItemBuilder(Material.DIAMOND_SWORD).setName("&8").setLore(Arrays.asList("&7",
                "      &7&lZarzadzanie PVP w gildii",
                "&7",
                " &8%> &7PVP w gildii jest aktualnie " + (guild.isPvp() ? "&awlaczone" : "&cwylaczone"),
                " &8%> &7Kliknij aby " + (guild.isPvp() ? "&cwylaczyc" : "&awlaczyc") + " &7PVP w gildii"
        )).setClickExecutor(inventoryClickEvent -> {
            guild.setPvp(!guild.isPvp());
            guild.sendMembers(guild.isPvp() ? messageConfiguration.pvpOnToMembers : messageConfiguration.pvpOffToMembers);
            openMain(player, guild);
        }).getGuiItem());
        gui.setItem(34, this.guiItemDarkGlass);
        gui.setItem(35, this.guiItemGreenGlass);
        gui.setItem(36, this.guiItemGreenGlass);
        gui.setItem(37, this.guiItemGreenGlass);
        gui.setItem(38, this.guiItemDarkGlass);
        gui.setItem(39, this.guiItemDarkGlass);
        gui.setItem(40, this.guiItemDarkGlass);
        gui.setItem(41, this.guiItemDarkGlass);
        gui.setItem(42, this.guiItemDarkGlass);
        gui.setItem(43, this.guiItemGreenGlass);
        gui.setItem(44, this.guiItemGreenGlass);
        gui.open(player);
    }

    public void openMembersList(Player player, Guild guild) {
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&7Zarzadzaj &duprawnieniami"), 3);

        for (GuildUser member : guild.getMembers()) {
            if (guild.isOwner(member)) continue;
            gui.setToNextFree(new ItemBuilder(Material.SKULL_ITEM).setName("&7Gracz: &6" + member.getName()).setLore(Arrays.asList(
                    " &8&l%> &7Online: " + (member.isOnline() ? "&a&l✔" : "&c&l✘"),
                    "&7",
                    " &8&l%> &7Kliknij aby &czmienic uprawnienia &7tego gracza.")).setSkullOwner(member.getName()).setClickExecutor(inventoryClickEvent -> openUserPerms(player, guild, member)).getGuiItem());
        }
        gui.setItem(26, new GuiItem(gui.undoPageItem(), inventoryClickEvent -> openMain(player, guild)));
        gui.open(player);
    }

    public void openUserPerms(Player owner, Guild guild, GuildUser member) {
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&7Zarzadzanie graczem &d" + member.getName()), 4);
        gui.setCloseEvent(inventoryCloseEvent -> gui.unregister());

        gui.setItem(0, this.guiItemDarkGlass);
        gui.setItem(1, this.guiItemDarkGlass);
        gui.setItem(2, this.guiItemDarkGlass);
        gui.setItem(3, this.guiItemGreenGlass);
        gui.setItem(4, new GuiItem(new ItemBuilder(Material.SKULL_ITEM).setName("&a" + member.getName()).setSkullOwner(member.getName()).getItem()));
        gui.setItem(5, this.guiItemGreenGlass);
        gui.setItem(6, this.guiItemDarkGlass);
        gui.setItem(7, this.guiItemDarkGlass);
        gui.setItem(8, this.guiItemDarkGlass);

        for (GuildPermission permission : GuildPermission.values()) {
            gui.setToNextFree(new GuiItem(new ItemBuilder(permission.getPermissionIcon()).setName("&7Uprawnienie do: &6" + permission.getPermissionName()).setLore(Arrays.asList("&7",
                    " &8* &7Status: " + (member.hasGuildPermission(permission) ? "&a&l✔" : "&c&l✘"),
                    " &8* &7Kliknij aby " + (member.hasGuildPermission(permission) ? "&cusunac" : "&anadac") + " &7uprawnienie graczowi",
                    "&7"
            )).getItem(), inventoryClickEvent -> {
                member.toggleGuildPermission(permission);
                openUserPerms(owner, guild, member);
            }));
        }

        gui.setItem(35, new GuiItem(gui.undoPageItem(), inventoryClickEvent -> openMembersList(owner, guild)));
        gui.open(owner);
    }
}
