package pl.himc.guilds.command.admin.guild;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;

public class TurboDropCommand extends PlayerCommand {

    public TurboDropCommand(String permission) {
        super("turbodrop", permission, "/ga turbodrop <all/Tag> <Czas np. 5min>");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 3) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }

        if (args[1].equalsIgnoreCase("all")) {
            for (Guild guilds : PluginGuild.getPlugin().getGuildManager().getGuilds()) {
                guilds.setTurbodrop(TimeUtil.getTimeWithString(args[2]));
            }
            Bukkit.getOnlinePlayers().stream().forEach(players -> TitleAPI.sendTitle(players, "&8[ &bTURBO&dDROP &8]", "&7Aktywowano kazdej gildii na &6" + TimeUtil.getDuration(TimeUtil.getTimeWithString(args[2])), 10, 70));
            return false;
        }

        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaGuildNotExists);
        }
        guild.setTurbodrop(TimeUtil.getTimeWithString(args[2]));
        for (GuildUser members : guild.getOnlineMembers()) {
            TitleAPI.sendTitle(members.getPlayer(), "&8[ &bTURBO&dDROP &8]", "&7Aktywowano Twojej gildii na &6" + guild.getDurationTruboDrop() + "", 15, 70);
        }
        return true;
    }
}
