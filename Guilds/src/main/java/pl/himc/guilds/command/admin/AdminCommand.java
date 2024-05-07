package pl.himc.guilds.command.admin;

import pl.himc.api.api.PluginApi;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.command.admin.guild.*;
import pl.himc.guilds.data.PluginMessages;

import java.util.HashSet;
import java.util.Set;

public class AdminCommand extends PlayerCommand {

    private static Set<CommandAPI> cmds = new HashSet<>();

    public AdminCommand() {
        super("gildiaadmin", PluginApi.getApi().getPluginConfig().prefixPermission + ".guild.cmd.admin", "Glowna komenda administratora", "ga", "guildadmin", "gildieadmin", "guildsadmin");
        final String prefix = PluginApi.getApi().getPluginConfig().prefixPermission;

        cmds.add(new AddCommand(prefix + ".guild.cmd.admin.add"));
        cmds.add(new DeathsCommand(prefix + ".guild.cmd.admin.deaths"));
        cmds.add(new DeleteCommand(prefix + ".guild.cmd.admin.delete"));
        cmds.add(new ItemsCommand(prefix + ".guild.cmd.admin.items"));
        cmds.add(new KickCommand(prefix + ".guild.cmd.admin.kick"));
        cmds.add(new KillsCommand(prefix + ".guild.cmd.admin.kills"));
        cmds.add(new LivesCommand(prefix + ".guild.cmd.admin.lives"));
        cmds.add(new OwnerCommand(prefix + ".guild.cmd.admin.owner"));
        cmds.add(new PointsCommand(prefix + ".guild.cmd.admin.points"));
        cmds.add(new TpCommand(prefix + ".guild.cmd.admin.tp"));
        cmds.add(new ValidityCommand(prefix + ".guild.cmd.admin.validity"));
        cmds.add(new TurboDropCommand(prefix + ".guild.cmd.admin.turbodrop"));
        cmds.add(new TurboExpCommand(prefix + ".guild.cmd.admin.turboexp"));
        cmds.add(new WarTimeCommand(prefix + ".guild.cmd.admin.war"));
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (args.length == 0) {
            messageConfiguration.guildAdminHelp.forEach(s -> ChatUtil.sendMessage(p, s));
            return false;
        }
        String name = args[0];
        CommandAPI sc = this.get(name);
        if (sc == null) {
            return ChatUtil.sendMessage(p, messageConfiguration.gaBadArgs);
        }
        if (!p.hasPermission(sc.getPermission())) {
            return ChatUtil.sendMessage(p, messageConfiguration.playerNotHasPermission.replace("{PERM}", sc.getPermission()));
        }
        return sc.onCommand(p, args);
    }

    public CommandAPI get(String message) {
        for (CommandAPI sc : cmds) {
            if (sc.getName().equalsIgnoreCase(message) || sc.getAliases().contains(message.toLowerCase())) {
                return sc;
            }
        }
        return null;
    }
}
