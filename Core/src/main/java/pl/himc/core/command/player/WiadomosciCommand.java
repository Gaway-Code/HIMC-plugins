package pl.himc.core.command.player;

import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemBuilder;
import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;

public final class WiadomosciCommand extends PlayerCommand {

    public WiadomosciCommand(String permission) {
        super("wiadomosci", permission, "");
        this.plugin = PluginCore.getCore();
    }

    private CorePlugin plugin;
    @Override
    public boolean onCommand(Player player, String[] args) {
        this.openGui(player);
        return false;
    }

    private void openGui(Player player){
        GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&bZarzadzanie wiadomosciami"), 1);
        GuiItem yellow = new GuiItem(new ItemBuilder(Material.STAINED_GLASS_PANE, 1, (short)4).setName("&8*").getItem());

        User user = this.plugin.getUserManager().getUser(player);

        gui.setItem(0, yellow);
        gui.setItem(1, new ItemBuilder(Material.DIAMOND_PICKAXE).setName("&8* &7Informacja o &6dropie &8*").setLore(" &8&l%> &7Status: " + (user.isDropMsg() ? "&2&l✔" : "&4&l✖")).setClickExecutor(event -> {
            user.toggleDropMsg();
            this.openGui(player);
        }).getGuiItem());
        gui.setItem(2, yellow);
        gui.setItem(3, new ItemBuilder(Material.BEACON).setName("&8* &7Informacja o &6zakupionych efektach &8*").setLore(" &8&l%> &7Status: " + (user.isEffectMsg() ? "&2&l✔" : "&4&l✖")).setClickExecutor(event -> {
            user.toggleEffectMsg();
            this.openGui(player);
        }).getGuiItem());
        gui.setItem(4, yellow);
        gui.setItem(5, new ItemBuilder(Material.PAPER).setName("&8* &7Prywatne wiadomosci &6/msg &8*").setLore(" &8&l%> &7Status: " + (user.isEnableMsg() ? "&2&l✔" : "&4&l✖")).setClickExecutor(event -> {
            user.toggleEnableMsg();
            this.openGui(player);
        }).getGuiItem());
        gui.setItem(6, yellow);
        GuildUser guildUser = PluginGuild.getPlugin().getUserManager().getUser(player);
        gui.setItem(7, new ItemBuilder(Material.SKULL_ITEM, 1, (short)3).setName("&8* &7Informacja o &cwkroczeniu gracza na teren Twojej gildii &8*").setLore(" &8&l%> &7Status: " + (guildUser.isEnemyAlert() ? "&2&l✔" : "&4&l✖")).setSkullOwner(player.getName()).setClickExecutor(event -> {
            guildUser.toggleEnemyAlert();
            this.openGui(player);
        }).getGuiItem());
        gui.setItem(8, yellow);

        gui.open(player);
    }
}
