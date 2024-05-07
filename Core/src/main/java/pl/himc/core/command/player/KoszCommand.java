package pl.himc.core.command.player;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class KoszCommand extends PlayerCommand {

    public KoszCommand() {
        super("kosz",null,"");
        this.trash = Bukkit.createInventory(null, 54, ChatUtil.fixColor("&6&lHI&f&lMC&7&l.pl"));
    }
    private final Inventory trash;

    @Override
    public boolean onCommand(Player player, String[] args) {
        player.openInventory(this.trash);
        return false;
    }
}
