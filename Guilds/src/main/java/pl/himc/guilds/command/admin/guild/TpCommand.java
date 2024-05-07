package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;

public class TpCommand extends PlayerCommand {

    public TpCommand(String permission) {
        super("tp", permission, "/ga tp <Tag>", "teleport");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 2) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaGuildNotExists);
        }
        player.teleport(guild.getCuboid().getCenter());

        return ChatUtil.sendMessage(player, messageConfiguration.gaGuildTeleportSuccess
                .replace("{GUILD}", guild.getName())
                .replace("{TAG}", guild.getTag()));
    }
}
