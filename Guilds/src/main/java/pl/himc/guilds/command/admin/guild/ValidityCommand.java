package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public class ValidityCommand extends PlayerCommand {

    public ValidityCommand(String permission) {
        super("validity", permission, "/ga waznosc <Tag> <Waznosc np. 5d>", "waznosc");
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
        long valid = TimeUtil.getTimeWithString(args[2]);
        if (valid == 0L) {
            return ChatUtil.sendMessage(player, messageConfiguration.invalidTime);
        }
        valid += System.currentTimeMillis();
        guild.setValidity(valid);

        return ChatUtil.sendMessage(player, messageConfiguration.gaGuildValiditySet
                .replace("{GUILD}", guild.getName())
                .replace("{TAG}", guild.getTag())
                .replace("{DATA}", TimeUtil.getDate(valid)));
    }
}
