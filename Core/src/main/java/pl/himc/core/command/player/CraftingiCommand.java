package pl.himc.core.command.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.core.base.craft.CraftManager;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;

public final class CraftingiCommand extends PlayerCommand {

    public CraftingiCommand() {
        super("craft", null, "/craft","craftingi");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.openNewGui(player, "stoniarka");
        return false;
    }

    public void openNewGui(final Player player, String type){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&eCraftingi"),6);
        GuiItem yellow = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)4).setName("&8*").getItem());
        GuiItem green = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)5).setName("&a&oRECEPTURA TEGO PRZEDMIOTU").getItem());
        gui.setItem(10, yellow);
        gui.setItem(14, yellow);
        gui.setItem(30, yellow);
        gui.setItem(45, new GuiItem(StoniarkaData.getStoniarka(), click -> this.openNewGui(player, "stoniarka")));
        gui.setItem(46, new GuiItem(new ItemBuilder(Material.ENDER_CHEST).setClickExecutor(click -> this.openNewGui(player, "enderchest"))));
        gui.setItem(47, new GuiItem(CraftManager.getBoyfarmer(), click -> this.openNewGui(player, "boyfarmer")));
        gui.setItem(48, new GuiItem(CraftManager.getSandfarmer(), click -> this.openNewGui(player, "sandfarmer")));
        gui.setItem(49, new GuiItem(CraftManager.getKopaczfosy(), click -> this.openNewGui(player, "kopaczfosy")));
        gui.setItem(50, new GuiItem(CraftManager.getRzucaneTnt(), click -> this.openNewGui(player, "rzucanetnt")));
        gui.setItem(51, new GuiItem(CraftManager.getSwichBall(), click -> this.openNewGui(player, "switchball")));
        gui.setItem(52, new GuiItem(CraftManager.getRatunek(), click -> this.openNewGui(player, "ratunek")));
        switch (type) {
            case "stoniarka":
                gui.setItem(36, green);
                gui.setItem(2, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(3, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(4, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.REDSTONE_BLOCK).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(20, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(22, new ItemBuilder(Material.STONE).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(StoniarkaData.getStoniarka()));
                break;
            case "enderchest":
                gui.setItem(37, green);
                gui.setItem(2, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(3, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(4, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.ENDER_PEARL).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(20, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(22, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(16, new ItemBuilder(Material.ENDER_CHEST).getGuiItem());
                break;
            case "boyfarmer":
                gui.setItem(38, green);
                gui.setItem(2, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(3, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(4, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.GOLD_BLOCK).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(20, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(22, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(CraftManager.getBoyfarmer()));
                break;
            case "sandfarmer":
                gui.setItem(39, green);
                gui.setItem(2, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(3, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(4, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.GOLD_BLOCK).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(20, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(22, new ItemBuilder(Material.SAND).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(CraftManager.getSandfarmer()));
                break;
            case "kopaczfosy":
                gui.setItem(40, green);
                gui.setItem(2, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(3, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(4, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(20, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(22, new ItemBuilder(Material.OBSIDIAN).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(CraftManager.getKopaczfosy()));
                break;
            case "rzucanetnt":
                gui.setItem(41, green);
                gui.setItem(2, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(3, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(4, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(20, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(22, new ItemBuilder(Material.TNT, 64).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(CraftManager.getRzucaneTnt()));
                break;
            case "switchball":
                gui.setItem(42, green);
                gui.setItem(3, new ItemBuilder(Material.ARROW, 16).setName("&7").getGuiItem());
                gui.setItem(11, new ItemBuilder(Material.EMERALD_BLOCK, 32).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.ENDER_PEARL).setName("&7").getGuiItem());
                gui.setItem(13, new ItemBuilder(Material.EMERALD_BLOCK, 32).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.REDSTONE_TORCH_ON).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(CraftManager.getSwichBall()));
                break;
            case "ratunek":
                gui.setItem(43, green);
                gui.setItem(3, new ItemBuilder(Material.EMERALD_BLOCK,16).setName("&7").getGuiItem());
                gui.setItem(12, new ItemBuilder(Material.DIAMOND_BLOCK,16).setName("&7").getGuiItem());
                gui.setItem(21, new ItemBuilder(Material.FENCE,6).setName("&7").getGuiItem());
                gui.setItem(16, new GuiItem(CraftManager.getRatunek()));
                break;
        }
        gui.open(player);
    }
}
