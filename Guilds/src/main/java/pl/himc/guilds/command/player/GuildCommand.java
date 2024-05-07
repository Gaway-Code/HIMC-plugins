package pl.himc.guilds.command.player;

import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.bukkit.ChatUtil;
import org.bukkit.entity.Player;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.command.player.guild.*;
import pl.himc.guilds.data.PluginMessages;

import java.util.HashSet;
import java.util.Set;

public class GuildCommand extends PlayerCommand {
    private static Set<CommandAPI> cmds = new HashSet<>();

    public GuildCommand() {
        super("gildia", null, "/gildia", "g", "guild", "gildie", "guilds");

        cmds.add(new AllyCommand());
        cmds.add(new BreakCommand());
        cmds.add(new CreateCommand());
        cmds.add(new DeleteCommand());
        cmds.add(new EnlargeCommand());
        cmds.add(new HomeCommand());
        cmds.add(new InfoCommand());
        cmds.add(new InviteCommand());
        cmds.add(new JoinCommand());
        cmds.add(new KickCommand());
        cmds.add(new LeaveCommand());
        cmds.add(new ModCommand());
        cmds.add(new OwnerCommand());
        cmds.add(new PvPCommand());
        new ResetRankCommand().register();
        cmds.add(new SetHomeCommand());
        cmds.add(new ValidityCommand());
        cmds.add(new TreasureCommand());
        cmds.add(new GPanelCommand());
        cmds.add(new ItemsCommand());
    }

    @Override
    public boolean onCommand(Player player, String[] args) {
        PluginMessages messageConfiguration = PluginGuild.getPlugin().getMessageConfiguration();
        if (args.length == 0) {
            for (String s : messageConfiguration.mainHelpGuild) {
                ChatUtil.sendMessage(player, s);
            }
            return false;
        }
        String name = args[0];
        CommandAPI sc = this.get(name);
        if (sc == null) {
            return ChatUtil.sendMessage(player, messageConfiguration.badArgs);
        }
        return sc.onCommand(player, args);
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
