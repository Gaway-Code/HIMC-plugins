package pl.himc.core.command.player;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.kit.Kit;
import pl.himc.core.base.kit.KitData;
import pl.himc.core.base.user.User;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public final class KitCommand extends PlayerCommand {

    public KitCommand(String permission) {
        super("kit", permission, "/kit", "zestawy" );
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.openKitGui(player);
        return false;
    }

    public void openKitGui(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&7Dostepne &2zestawy"), 4);
        gui.setCloseEvent(close -> gui.unregister());

        int k = KitData.getInstance().getKits().size();
        int[] r = new int[36];
        if (k == 1) {
            r = new int[] { 13 };
        }
        else if (k == 2) {
            r = new int[] { 12, 14 };
        }
        else if (k == 3) {
            r = new int[] { 12, 13, 14 };
        }
        else if (k == 4) {
            r = new int[] { 11, 12, 14, 15 };
        }
        else if (k == 5) {
            r = new int[] { 11, 12, 13, 14, 15 };
        }
        else if (k == 6) {
            r = new int[] { 10, 11, 12, 14, 15, 16 };
        }
        else if (k == 7) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16 };
        }
        else if (k == 8) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16, 22 };
        }
        else if (k == 9) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16, 21, 23 };
        }
        else if (k == 10) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16, 21, 22, 23 };
        }
        else if (k == 11) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16, 20, 21, 23, 24 };
        }
        else if (k == 12) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24 };
        }
        else if (k == 13) {
            r = new int[] { 10, 11, 12, 13, 14, 15, 16, 20, 21, 22, 23, 24 };
        }
        else {
            for (int j = 0; j < 36; ++j) {
                r[j] = j;
            }
        }
        int i = 0;
        User user = PluginCore.getCore().getUserManager().getUser(player);
        for(Kit kit : KitData.getInstance().getKits()){
            ItemStack icon = kit.getIcon().clone();
            ItemMeta im = icon.getItemMeta();
            im.setDisplayName(kit.getItemInInventoryName());
            if(!kit.isEnable()){
                im.setLore(ChatUtil.fixColor(Arrays.asList(
                        "&7",
                        " &8&l%> &4Ten zestaw jest wylaczony!",
                        " &8&l%> &7Kliknij &6PPM &7aby sprawdzic co zawiera ten zestaw.",
                        "&7")));
            }else if(player.hasPermission(kit.getPermission())){
                im.setLore(ChatUtil.fixColor(Arrays.asList(
                        "&7",
                        (user.isKit(kit.getName()) ? " &8&l%> &7Ten zestaw mozesz wziasc za &c" + TimeUtil.getDuration(user.getKit(kit.getName()) - System.currentTimeMillis()) : " &8%> &7Ten zestaw jest &2&lDOSTEPNY&7!"),
                        (user.isKit(kit.getName()) ? " &8&l%> &4Nie mozesz odebrac tego zestawu!" : " &8%> &7Kliknij &6LPM &7aby &2odebrac zestaw"),
                        " &8&l%> &7Kliknij &6PPM &7aby sprawdzic &aco zawiera ten zestaw.",
                        "&7")));
            } else {
                im.setLore(ChatUtil.fixColor(Arrays.asList(
                        "&7",
                        " &8&l%> &4Ten zestaw jest niedostepny dla Ciebie!",
                        " &8&l%> &7Kliknij &6PPM &7aby sprawdzic co zawiera ten zestaw.",
                        "&7")));
            }
            icon.setItemMeta(im);
            gui.setItem(r[i++], new GuiItem(icon, click -> {
                if(click.getClick().isLeftClick()){
                    KitData.getInstance().getKit(kit, player);
                }else {
                    this.viewKit(player, kit);
                }
            }));
        }
        gui.open(player);
    }
    public void viewKit(Player player, Kit kit){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&7Podglad zestawu &8- &6" + kit.getName()), 3);

        for(ItemStack items : kit.getItems()){
            gui.setToNextFree(new GuiItem(items));
        }
        gui.setItem(26, new GuiItem(gui.undoPageItem(), click -> this.openKitGui(player)));
        gui.open(player);
    }
}
