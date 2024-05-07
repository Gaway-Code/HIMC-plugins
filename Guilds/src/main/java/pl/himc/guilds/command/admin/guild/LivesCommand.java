package pl.himc.guilds.command.admin.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.IntegerUtil;

public class LivesCommand extends PlayerCommand {
    public LivesCommand(String permission) {
        super("lives", permission, "/ga zycia <Tag> <Ilosc>", "zycia");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        if (args.length < 3) {
            ChatUtil.sendUsage(player, this.getUsage());
            return false;
        }
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!IntegerUtil.isInteger(args[2])) {
            return ChatUtil.sendMessage(player, messageConfiguration.invalidInteger);
        }
        int lives = Integer.parseInt(args[2]);
        if (lives < 1) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaNumberGreaterThanZero);
        }
        Guild guild = PluginGuild.getPlugin().getGuildManager().getGuildTag(args[1]);
        if (guild == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.gaGuildNotExists);
        }
        guild.setLives(lives);
        return ChatUtil.sendMessage(player, messageConfiguration.gaGuildLivesSet
                .replace("{GUILD}", guild.getName())
                .replace("{TAG}", guild.getTag())
                .replace("{LIVES}", Integer.toString(lives)));
    }
}
