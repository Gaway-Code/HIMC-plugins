package pl.himc.guilds.command.player.guild;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.guilds.data.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.item.ItemsUtil;

import java.util.List;

public class CreateCommand extends PlayerCommand {

    public CreateCommand() {
        super("create", null, "/g zaloz <Tag> <Nazwa>", "zaloz", "stworz");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginConfig pluginConfiguration = PluginGuild.getPlugin().getPluginConfiguration();
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!pluginConfiguration.enableCreateGuild) {
            return ChatUtil.sendMessage(player, messageConfiguration.disableCreateGuild);
        }
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        if (user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.youHaveGuild);
        }
        if (args.length < 3) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        String tag = args[1];
        if (tag.length() < pluginConfiguration.createMinGuildTag) {
            return ChatUtil.sendMessage(player, messageConfiguration.tagGuildMin.replace("{LENGHT}", Integer.toString(pluginConfiguration.createMinGuildTag)));
        }
        if (tag.length() > pluginConfiguration.createMaxGuildTag) {
            return ChatUtil.sendMessage(player, messageConfiguration.tagGuildMax.replace("{LENGHT}", Integer.toString(pluginConfiguration.createMaxGuildTag)));
        }
        String name = args[2];
        if (name.length() < pluginConfiguration.createMinGuildName) {
            return ChatUtil.sendMessage(player, messageConfiguration.nameGuildMin.replace("{LENGHT}", Integer.toString(pluginConfiguration.createMinGuildName)));
        }
        if (name.length() > pluginConfiguration.createMaxGuildName) {
            return ChatUtil.sendMessage(player, messageConfiguration.nameGuildMax.replace("{LENGHT}", Integer.toString(pluginConfiguration.createMaxGuildName)));
        }
        if (!tag.matches("[a-zA-Z]+")) {
            return ChatUtil.sendMessage(player, messageConfiguration.nameAndTagGuildOnlyLetters);
        }
        if (!name.matches("[a-zA-Z]+")) {
            return ChatUtil.sendMessage(player, messageConfiguration.nameAndTagGuildOnlyLetters);
        }
        if (PluginGuild.getPlugin().getGuildManager().getGuildTag(tag) != null) {
            return ChatUtil.sendMessage(player, messageConfiguration.guildTagAlreadyExists);
        }
        if (PluginGuild.getPlugin().getGuildManager().getGuildName(name) != null) {
            return ChatUtil.sendMessage(player, messageConfiguration.guildNameAlreadyExists);
        }

        Location guildLocation = player.getLocation().getBlock().getLocation();

        int d = pluginConfiguration.guildMaxRegionSize + pluginConfiguration.createGuildDistanceSpawn;
        if (d > player.getWorld().getSpawnLocation().distance(guildLocation)) {
            return ChatUtil.sendMessage(player, messageConfiguration.createGuildDistanceSpawn);
        }

        /*
        Location loc = player.getWorld().getSpawnLocation();
        loc.setY(player.getLocation().getY());
        if (loc.distance(player.getLocation()) < pluginConfiguration.createGuildDistanceSpawn) {
            Replacer.build(messageConfiguration.createGuildDistanceSpawn).send(player);
            return false;
        }
        */
        if (!PluginGuild.getPlugin().getGuildManager().distance(player.getLocation(), pluginConfiguration.createGuildDistanceOtherGuild)) {
            return ChatUtil.sendMessage(player, messageConfiguration.createGuildDistanceOtherGuild);
        }
        List<ItemStack> itemsCreate = player.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.vip") ? pluginConfiguration.createGuildItemsVip : pluginConfiguration.createGuildItemsPlayer;
        if (!ItemsUtil.hasItems(player, itemsCreate)) {
            player.chat("/g itemy");
            return false;
        }
        ItemsUtil.removeItems(player, itemsCreate);

        PluginGuild.getPlugin().getGuildManager().createGuild(name, tag, user);
        return true;
    }
}
