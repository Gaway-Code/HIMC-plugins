package pl.himc.core.command.player;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.core.ranking.*;

import java.util.Arrays;

public final class TopkiCommand extends PlayerCommand {

    public TopkiCommand() {
        super("topki", null, "/topki","topka");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        this.openMain(player);
        return false;
    }

    public void openMain(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&aTop 10 &e&lnajlepszych graczy"),5);
        GuiItem yellow = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)4).setName("&8*").getItem());

        ItemStack poziom = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("&7").setLore(Arrays.asList("&7",
                "     &8* &a&lPOZIOM KOPANIA &8*",
                "&7",
                "&81. &7" + (RankLevel.getUser(1) != null ? RankLevel.getUser(1).getName() + " &8- &a" + RankLevel.getUser(1).getLvl() + " poziom" : "&cBRAK"),
                "&82. &7" + (RankLevel.getUser(2) != null ? RankLevel.getUser(2).getName() + " &8- &a" + RankLevel.getUser(2).getLvl() + " poziom" : "&cBRAK"),
                "&83. &7" + (RankLevel.getUser(3) != null ? RankLevel.getUser(3).getName() + " &8- &a" + RankLevel.getUser(3).getLvl() + " poziom" : "&cBRAK"),
                "&84. &7" + (RankLevel.getUser(4) != null ? RankLevel.getUser(4).getName() + " &8- &a" + RankLevel.getUser(4).getLvl() + " poziom" : "&cBRAK"),
                "&85. &7" + (RankLevel.getUser(5) != null ? RankLevel.getUser(5).getName() + " &8- &a" + RankLevel.getUser(5).getLvl() + " poziom" : "&cBRAK"),
                "&86. &7" + (RankLevel.getUser(6) != null ? RankLevel.getUser(6).getName() + " &8- &a" + RankLevel.getUser(6).getLvl() + " poziom" : "&cBRAK"),
                "&87. &7" + (RankLevel.getUser(7) != null ? RankLevel.getUser(7).getName() + " &8- &a" + RankLevel.getUser(7).getLvl() + " poziom" : "&cBRAK"),
                "&88. &7" + (RankLevel.getUser(8) != null ? RankLevel.getUser(8).getName() + " &8- &a" + RankLevel.getUser(8).getLvl() + " poziom" : "&cBRAK"),
                "&89. &7" + (RankLevel.getUser(9) != null ? RankLevel.getUser(9).getName() + " &8- &a" + RankLevel.getUser(9).getLvl() + " poziom" : "&cBRAK"),
                "&810. &7" + (RankLevel.getUser(10) != null ? RankLevel.getUser(10).getName() + " &8- &a" + RankLevel.getUser(10).getLvl() + " poziom" : "&cBRAK")
        )).getItem();


        ItemStack kills = new ItemBuilder(Material.DIAMOND_SWORD).setName("&7").setLore(Arrays.asList("&7",
                "         &8* &a&lZABOJSTWA &8*",
                "&7",
                "&81. &7" + (RankKills.getUser(1) != null ? RankKills.getUser(1).getName() + " &8- &a" + RankKills.getUser(1).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&82. &7" + (RankKills.getUser(2) != null ? RankKills.getUser(2).getName() + " &8- &a" + RankKills.getUser(2).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&83. &7" + (RankKills.getUser(3) != null ? RankKills.getUser(3).getName() + " &8- &a" + RankKills.getUser(3).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&84. &7" + (RankKills.getUser(4) != null ? RankKills.getUser(4).getName() + " &8- &a" + RankKills.getUser(4).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&85. &7" + (RankKills.getUser(5) != null ? RankKills.getUser(5).getName() + " &8- &a" + RankKills.getUser(5).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&86. &7" + (RankKills.getUser(6) != null ? RankKills.getUser(6).getName() + " &8- &a" + RankKills.getUser(6).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&87. &7" + (RankKills.getUser(7) != null ? RankKills.getUser(7).getName() + " &8- &a" + RankKills.getUser(7).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&88. &7" + (RankKills.getUser(8) != null ? RankKills.getUser(8).getName() + " &8- &a" + RankKills.getUser(8).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&89. &7" + (RankKills.getUser(9) != null ? RankKills.getUser(9).getName() + " &8- &a" + RankKills.getUser(9).getRank().getKills() + " zabojstw" : "&cBRAK"),
                "&810. &7" + (RankKills.getUser(10) != null ? RankKills.getUser(10).getName() + " &8- &a" + RankKills.getUser(10).getRank().getKills() + " zabojstw" : "&cBRAK")
        )).getItem();

        ItemStack cobble = new ItemBuilder(Material.COBBLESTONE).setName("&7").setLore(Arrays.asList("&7",
                "    &8* &a&lWYKOPANY KAMIEN &8*",
                "&7",
                "&81. &7" + (RankStone.getUser(1) != null ? RankStone.getUser(1).getName() + " &8- &a" + RankStone.getUser(1).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&82. &7" + (RankStone.getUser(2) != null ? RankStone.getUser(2).getName() + " &8- &a" + RankStone.getUser(2).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&83. &7" + (RankStone.getUser(3) != null ? RankStone.getUser(3).getName() + " &8- &a" + RankStone.getUser(3).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&84. &7" + (RankStone.getUser(4) != null ? RankStone.getUser(4).getName() + " &8- &a" + RankStone.getUser(4).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&85. &7" + (RankStone.getUser(5) != null ? RankStone.getUser(5).getName() + " &8- &a" + RankStone.getUser(5).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&86. &7" + (RankStone.getUser(6) != null ? RankStone.getUser(6).getName() + " &8- &a" + RankStone.getUser(6).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&87. &7" + (RankStone.getUser(7) != null ? RankStone.getUser(7).getName() + " &8- &a" + RankStone.getUser(7).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&88. &7" + (RankStone.getUser(8) != null ? RankStone.getUser(8).getName() + " &8- &a" + RankStone.getUser(8).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&89. &7" + (RankStone.getUser(9) != null ? RankStone.getUser(9).getName() + " &8- &a" + RankStone.getUser(9).getStoneBreak() + " kamienia" : "&cBRAK"),
                "&810. &7" + (RankStone.getUser(10) != null ? RankStone.getUser(10).getName() + " &8- &a" + RankStone.getUser(10).getStoneBreak() + " kamienia" : "&cBRAK")
        )).getItem();

        ItemStack obsydian = new ItemBuilder(Material.OBSIDIAN).setName("&8").setLore(Arrays.asList("&7",
                "    &8* &a&lWYKOPANY OBSYDIAN &8*",
                "&7",
                "&81. &7" + (RankObsydian.getUser(1) != null ? RankObsydian.getUser(1).getName() + " &8- &a" + RankObsydian.getUser(1).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&82. &7" + (RankObsydian.getUser(2) != null ? RankObsydian.getUser(2).getName() + " &8- &a" + RankObsydian.getUser(2).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&83. &7" + (RankObsydian.getUser(3) != null ? RankObsydian.getUser(3).getName() + " &8- &a" + RankObsydian.getUser(3).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&84. &7" + (RankObsydian.getUser(4) != null ? RankObsydian.getUser(4).getName() + " &8- &a" + RankObsydian.getUser(4).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&85. &7" + (RankObsydian.getUser(5) != null ? RankObsydian.getUser(5).getName() + " &8- &a" + RankObsydian.getUser(5).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&86. &7" + (RankObsydian.getUser(6) != null ? RankObsydian.getUser(6).getName() + " &8- &a" + RankObsydian.getUser(6).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&87. &7" + (RankObsydian.getUser(7) != null ? RankObsydian.getUser(7).getName() + " &8- &a" + RankObsydian.getUser(7).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&88. &7" + (RankObsydian.getUser(8) != null ? RankObsydian.getUser(8).getName() + " &8- &a" + RankObsydian.getUser(8).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&89. &7" + (RankObsydian.getUser(9) != null ? RankObsydian.getUser(9).getName() + " &8- &a" + RankObsydian.getUser(9).getObsidianBreak() + " obsydianu" : "&cBRAK"),
                "&810. &7" + (RankObsydian.getUser(10) != null ? RankObsydian.getUser(10).getName() + " &8- &a" + RankObsydian.getUser(10).getObsidianBreak() + " obsydianu" : "&cBRAK")
        )).getItem();

        ItemStack kox = new ItemBuilder(Material.GOLDEN_APPLE, 1, (short)1).setName("&8").setLore(Arrays.asList("&7",
                "    &8* &a&lZJEDZONE KOXY &8*",
                "&7",
                "&81. &7" + (RankEatKox.getUser(1) != null ? RankEatKox.getUser(1).getName() + " &8- &a" + RankEatKox.getUser(1).getEatKox() + " koxy" : "&cBRAK"),
                "&82. &7" + (RankEatKox.getUser(2) != null ? RankEatKox.getUser(2).getName() + " &8- &a" + RankEatKox.getUser(2).getEatKox() + " koxy" : "&cBRAK"),
                "&83. &7" + (RankEatKox.getUser(3) != null ? RankEatKox.getUser(3).getName() + " &8- &a" + RankEatKox.getUser(3).getEatKox() + " koxy" : "&cBRAK"),
                "&84. &7" + (RankEatKox.getUser(4) != null ? RankEatKox.getUser(4).getName() + " &8- &a" + RankEatKox.getUser(4).getEatKox() + " koxy" : "&cBRAK"),
                "&85. &7" + (RankEatKox.getUser(5) != null ? RankEatKox.getUser(5).getName() + " &8- &a" + RankEatKox.getUser(5).getEatKox() + " koxy" : "&cBRAK"),
                "&86. &7" + (RankEatKox.getUser(6) != null ? RankEatKox.getUser(6).getName() + " &8- &a" + RankEatKox.getUser(6).getEatKox() + " koxy" : "&cBRAK"),
                "&87. &7" + (RankEatKox.getUser(7) != null ? RankEatKox.getUser(7).getName() + " &8- &a" + RankEatKox.getUser(7).getEatKox() + " koxy" : "&cBRAK"),
                "&88. &7" + (RankEatKox.getUser(8) != null ? RankEatKox.getUser(8).getName() + " &8- &a" + RankEatKox.getUser(8).getEatKox() + " koxy" : "&cBRAK"),
                "&89. &7" + (RankEatKox.getUser(9) != null ? RankEatKox.getUser(9).getName() + " &8- &a" + RankEatKox.getUser(9).getEatKox() + " koxy" : "&cBRAK"),
                "&810. &7" + (RankEatKox.getUser(10) != null ? RankEatKox.getUser(10).getName() + " &8- &a" + RankEatKox.getUser(10).getEatKox() + " koxy" : "&cBRAK")
        )).getItem();

        ItemStack refile = new ItemBuilder(Material.GOLDEN_APPLE).setName("&8").setLore(Arrays.asList("&7",
                "    &8* &a&lZJEDZONE REFILE &8*",
                "&7",
                "&81. &7" + (RankEatRefil.getUser(1) != null ? RankEatRefil.getUser(1).getName() + " &8- &a" + RankEatRefil.getUser(1).getEatRefil() + " refile" : "&cBRAK"),
                "&82. &7" + (RankEatRefil.getUser(2) != null ? RankEatRefil.getUser(2).getName() + " &8- &a" + RankEatRefil.getUser(2).getEatRefil() + " refile" : "&cBRAK"),
                "&83. &7" + (RankEatRefil.getUser(3) != null ? RankEatRefil.getUser(3).getName() + " &8- &a" + RankEatRefil.getUser(3).getEatRefil() + " refile" : "&cBRAK"),
                "&84. &7" + (RankEatRefil.getUser(4) != null ? RankEatRefil.getUser(4).getName() + " &8- &a" + RankEatRefil.getUser(4).getEatRefil() + " refile" : "&cBRAK"),
                "&85. &7" + (RankEatRefil.getUser(5) != null ? RankEatRefil.getUser(5).getName() + " &8- &a" + RankEatRefil.getUser(5).getEatRefil() + " refile" : "&cBRAK"),
                "&86. &7" + (RankEatRefil.getUser(6) != null ? RankEatRefil.getUser(6).getName() + " &8- &a" + RankEatRefil.getUser(6).getEatRefil() + " refile" : "&cBRAK"),
                "&87. &7" + (RankEatRefil.getUser(7) != null ? RankEatRefil.getUser(7).getName() + " &8- &a" + RankEatRefil.getUser(7).getEatRefil() + " refile" : "&cBRAK"),
                "&88. &7" + (RankEatRefil.getUser(8) != null ? RankEatRefil.getUser(8).getName() + " &8- &a" + RankEatRefil.getUser(8).getEatRefil() + " refile" : "&cBRAK"),
                "&89. &7" + (RankEatRefil.getUser(9) != null ? RankEatRefil.getUser(9).getName() + " &8- &a" + RankEatRefil.getUser(9).getEatRefil() + " refile" : "&cBRAK"),
                "&810. &7" + (RankEatRefil.getUser(10) != null ? RankEatRefil.getUser(10).getName() + " &8- &a" + RankEatRefil.getUser(10).getEatRefil() + " refile" : "&cBRAK")
        )).getItem();

        gui.setItem(9, yellow);
        gui.setItem(10, new GuiItem(kills));
        gui.setItem(11, yellow);
        gui.setItem(12, new GuiItem(kox));
        gui.setItem(13, yellow);
        gui.setItem(14, new GuiItem(refile));
        gui.setItem(15, yellow);
        gui.setItem(16, new GuiItem(poziom));
        gui.setItem(17, yellow);
        gui.setItem(28, yellow);
        gui.setItem(29, new GuiItem(cobble));
        gui.setItem(30, yellow);
        gui.setItem(32, yellow);
        gui.setItem(33, new GuiItem(obsydian));
        gui.setItem(34, yellow);

        gui.open(player);
    }
}
