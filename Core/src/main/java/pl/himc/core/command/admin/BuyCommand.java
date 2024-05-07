package pl.himc.core.command.admin;

import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.core.CorePlugin;
import pl.himc.core.base.itemshop.ItemShop;
import pl.himc.core.configuration.PluginMessages;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public final class BuyCommand extends CommandAPI {

    public BuyCommand(final CorePlugin plugin, String permission) {
        super("buy", permission,"/itemshop <Gracz> <Usluga>","is", "itemshop");
        this.plugin = plugin;
        this.register();

    }
    private CorePlugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginMessages messageConfiguration = this.plugin.getPluginMessages();
        if(args.length != 2){
            ChatUtil.sendUsage(sender, this.getUsage());
            return ChatUtil.sendMessage(sender, this.plugin.getItemshopData().getProducts());
        }
        if(this.plugin.getItemshopData().get(args[1]) == null){
            return ChatUtil.sendMessage(sender, messageConfiguration.itemShopNotFound);
        }

        ItemShop shop = this.plugin.getItemshopData().get(args[1]);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), shop.getCommand().replace("{PLAYER}", args[0]));
        Bukkit.getOnlinePlayers().forEach(online -> shop.getMessage().forEach(msg -> ChatUtil.sendMessage(online, msg.replace("{PLAYER}", args[0]))));
        return false;
    }
}
