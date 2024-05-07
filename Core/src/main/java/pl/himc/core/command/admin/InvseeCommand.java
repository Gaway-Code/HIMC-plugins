package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.api.inventory.GuiItem;
import pl.himc.api.api.inventory.GuiWindow;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class InvseeCommand extends PlayerCommand {

    public InvseeCommand(String permission) {
        super("invsee", permission, "/invsee <Gracz>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        Player other = Bukkit.getPlayer(args[0]);
        if(other == null){
            return ChatUtil.sendMessage(p, messageConfiguration.playerIsOffline);
        }
        if(p == other){
            return ChatUtil.sendMessage(p, messageConfiguration.invSeeSamePlayer);
        }
        if(!p.hasPermission(this.getPermission() + ".edit")){
            GuiWindow gui = new GuiWindow(ChatUtil.fixColor("&7Ekwipunek gracza &a" + other.getName()), 4);
            gui.setCloseEvent(close -> gui.unregister());
            for(ItemStack item : other.getInventory().getContents()){
                gui.setToNextFree(new GuiItem(item));
            }
            gui.open(p);
            return false;
        }
        p.openInventory(other.getInventory());
        return false;
    }
}
