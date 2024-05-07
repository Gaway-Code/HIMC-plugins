package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.EnchantUtil;
import pl.himc.api.utils.bukkit.item.ItemNameUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;
import pl.himc.api.api.command.CommandAPI;

import java.util.HashMap;

public final class GiveCommand extends CommandAPI {

    public GiveCommand(String permission) {
        super("give", permission, "/give <Gracz> <id[:base]> <Ilosc> <Zaklecia... (np. sharpness:2)>");
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if (args.length < 2) {
            return ChatUtil.sendUsage(sender, this.getUsage());
        }
        Player p = Bukkit.getPlayer(args[0]);
        String[] datas = args[1].split(":");
        Material m = Material.matchMaterial(datas[0]);
        short data = ((datas.length > 1) ? Short.parseShort(datas[1]) : 0);
        ItemStack item = null;
        if (p == null) {
            return ChatUtil.sendMessage(sender, messageConfiguration.playerIsOffline);
        }
        if (m == null) {
            return ChatUtil.sendMessage(sender, messageConfiguration.giveInvalidItem);
        }
        if (args.length == 2) {
            item = new ItemStack(m, 1, data);
        }
        else if (args.length == 3) {
            item = new ItemStack(m, IntegerUtil.isInteger(args[2]) ? Integer.parseInt(args[2]) : 1, data);
        }
        else if (args.length >= 4) {
            HashMap<Enchantment, Integer> enchants = new HashMap<>();
            for (int i = 3; i < args.length; ++i) {
                String[] nameAndLevel = args[i].split(":");
                Enchantment e = EnchantUtil.getName(nameAndLevel[0]);
                int level = IntegerUtil.isInteger(nameAndLevel[1]) ? Integer.parseInt(nameAndLevel[1]) : 1;
                enchants.put(e, level);
            }
            item = new ItemStack(m, IntegerUtil.isInteger(args[2]) ? Integer.parseInt(args[2]) : 1, data);
            item.addUnsafeEnchantments(enchants);
        }
        if (item == null) {
            return ChatUtil.sendMessage(sender, messageConfiguration.giveItemError);
        }
        ItemsUtil.giveItem(p, item);
        return ChatUtil.sendMessage(p, messageConfiguration.giveItemSuccess
                .replace("{ITEM}", ItemNameUtil.getName(item.getType()))
                .replace("{AMOUNT}", args[2])
                .replace("{ADMIN}", sender.getName()));
    }
}
