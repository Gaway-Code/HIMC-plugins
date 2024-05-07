package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.EnchantUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;
import pl.himc.api.api.command.PlayerCommand;


public final class EnchantCommand extends PlayerCommand {

    public EnchantCommand(String permission) {
        super("enchant", permission, "/enchant <Zaklecie> <Level>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 2){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        if(!IntegerUtil.isInteger(args[1])){
            return ChatUtil.sendMessage(p, messageConfiguration.invalidInteger);
        }
        if(EnchantUtil.getName(args[0]) == null){
           return ChatUtil.sendMessage(p, messageConfiguration.enchantNotFound);
        }
        if(p.getItemInHand() == null || p.getItemInHand().getType() == Material.AIR || p.getItemInHand().getType().isBlock()){
            return ChatUtil.sendMessage(p, messageConfiguration.enchantNotNullItem);
        }
        Enchantment enchant = EnchantUtil.getName(args[0]);
        int level = Integer.parseInt(args[1]);
        p.getItemInHand().addUnsafeEnchantment(enchant, level);
        return ChatUtil.sendMessage(p, messageConfiguration.enchantSuccess.replace("{ENCHANT}", args[0]));
    }
}
