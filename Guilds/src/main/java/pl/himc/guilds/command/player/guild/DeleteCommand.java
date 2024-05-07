package pl.himc.guilds.command.player.guild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

import java.util.HashMap;
import java.util.Map;

public class DeleteCommand extends PlayerCommand {

    private static Map<Player, BukkitTask> confirmation = new HashMap<>();

    public DeleteCommand() {
        super("delete", null, "/g usun", "usun");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);

        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        Guild guild = user.getGuild();
        if (!guild.getOwner().equals(user)) {
            return ChatUtil.sendMessage(player, messageConfiguration.youDontOwnerGuild);
        }

        if (args.length <= 1 || !args[1].equalsIgnoreCase("potwierdz")) {
            if (confirmation.containsKey(player)) confirmation.remove(player);
            confirmation.put(player, Bukkit.getScheduler().runTaskLaterAsynchronously(PluginGuild.getPlugin(), () -> confirmation.remove(player), 600L));

            return ChatUtil.sendMessage(player, messageConfiguration.deleteConfirm);
        }

        if (!confirmation.containsKey(player)) {
            return ChatUtil.sendMessage(player, messageConfiguration.deleteGuildFirst);
        }
        if (confirmation.get(player) != null) confirmation.remove(player);
        PluginGuild.getPlugin().getGuildManager().deleteGuild(guild);

        ChatUtil.sendMessage(player, messageConfiguration.deleteGuildSuccess.replace("{GUILD}", guild.getName()).replace("{TAG}", guild.getTag()));
        return ChatUtil.sendBroadcast(messageConfiguration.guildDeleteBroadcast
                .replace("{TAG}", guild.getTag())
                .replace("{GUILD}", guild.getName())
                .replace("{PLAYER}", player.getName()));
    }
}
