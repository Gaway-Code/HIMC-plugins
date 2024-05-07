package pl.himc.guilds.command.admin.guild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

public class ItemsCommand extends PlayerCommand {

    public ItemsCommand(String permission) {
        super("items", permission, "/ga items <Gracz>", "itemy");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            ChatUtil.sendUsage(player, this.getUsage());
            return false;
        }
        PluginConfig pluginConfiguration = PluginGuild.getPlugin().getPluginConfiguration();
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        Player other = Bukkit.getPlayer(args[1]);
        if (other == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.playerIsOffline);
        }
        ItemsUtil.giveItems(other, pluginConfiguration.createGuildItemsPlayer);
        return ChatUtil.sendMessage(player, messageConfiguration.gaGiveItems.replace("{PLAYER}", other.getName()));
    }
}
