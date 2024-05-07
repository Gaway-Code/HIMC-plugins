package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginMessages;

public class WarTimeCommand extends PlayerCommand {

    public WarTimeCommand(String permission) {
        super("war", permission, "/ga ochrona <Tag> <Ochrona np. 5d>", "ochrona");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 3) {
            return ChatUtil.sendUsage(player, this.getUsage());
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaGuildNotExists);
        }
        long war = TimeUtil.getTimeWithString(args[2]);
        if (war == 0L) {
            return ChatUtil.sendMessage(player, messageConfiguration.invalidTime);
        }
        war += System.currentTimeMillis();
        guild.setWar(war);

        return ChatUtil.sendMessage(player, messageConfiguration.gaGuildSetWar
                .replace("{GUILD}", guild.getName())
                .replace("{TAG}", guild.getTag())
                .replace("{DATA}", TimeUtil.getDate(war)));
    }
}
