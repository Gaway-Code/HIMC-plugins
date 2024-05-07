package pl.himc.guilds.command.player.guild;

import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;
import pl.himc.guilds.base.guild.GuildPermission;
import pl.himc.guilds.base.user.GuildUser;
import pl.himc.guilds.data.PluginMessages;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public class HomeCommand extends PlayerCommand {

    public HomeCommand() {
        super("home", null, "/g dom", "dom", "baza");
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        GuildUser user = PluginGuild.getPlugin().getUserManager().getUser(player);
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (!user.hasGuild()) {
            return ChatUtil.sendMessage(player, messageConfiguration.dontHaveGuild);
        }
        if (!user.hasGuildPermission(GuildPermission.TELEPORT_HOME)) {
            return ChatUtil.sendMessage(player, messageConfiguration.noHasPermTpHome);
        }
        Guild guild = user.getGuild();

        PluginApi.getApi().getTeleport().sendTeleport(player, guild.getHome(), 5);
        return true;
    }
}
