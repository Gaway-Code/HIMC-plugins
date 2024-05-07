package pl.himc.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.bungee.command.permission.*;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.data.PluginConfiguration;
import pl.himc.bungee.util.ChatUtil;
import pl.himc.bungee.BungeePlugin;

import java.util.HashSet;
import java.util.Set;

public class BungeeCommands extends Command {

    private static final Set<Command> cmds = new HashSet<>();

    public BungeeCommands() {
        super("bungee", BungeePlugin.getPlugin().getPluginConfiguration().prefixPermission + ".bungee");
        final PluginConfiguration pluginConfiguration = BungeePlugin.getPlugin().getPluginConfiguration();

        cmds.add(new AdminChatCommand(pluginConfiguration.prefixPermission + ".adminchat"));
        cmds.add(new CreateGroupCommand(pluginConfiguration.prefixPermission + ".creategroup"));
        cmds.add(new DeleteGroupCommand(pluginConfiguration.prefixPermission + ".deletegroup"));
        cmds.add(new RenameGroupCommand(pluginConfiguration.prefixPermission + ".renamegroup"));
        cmds.add(new AddPermGroupCommand(pluginConfiguration.prefixPermission + ".addpermgroup"));
        cmds.add(new RemovePermGroupCommand(pluginConfiguration.prefixPermission + ".removepermgroup"));
        cmds.add(new SetUserGroupCommand(pluginConfiguration.prefixPermission + ".setusergroup"));
        cmds.add(new BroadcastCommand(pluginConfiguration.prefixPermission + ".broadcast"));
        cmds.add(new BlacklistCommand(pluginConfiguration.prefixPermission + ".blacklist"));

    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();

        if(args.length == 0){
            ChatUtil.sendMessage(sender, messageConfiguration.mainHelpBungee);
            return;
        }
        String command = args[0];
        if(command.equalsIgnoreCase("reload")){
            BungeePlugin.getPlugin().reloadConfiguration();
            BungeePlugin.getPlugin().getWhitelistData().loadWhitelist();
            ChatUtil.sendMessage(sender, messageConfiguration.reloadConfiguration);
            return;
        }
        if(command.equalsIgnoreCase("perms")){
            ChatUtil.sendMessage(sender, messageConfiguration.permsHelp);
            return;
        }
        if(command.equalsIgnoreCase("whitelisthelp")){
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistHelp);
            return;
        }
        if(command.equalsIgnoreCase("blacklisthelp")){
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistHelp);
            return;
        }
        Command cmd = this.get(args[0]);
        if(cmd == null){
            ChatUtil.sendMessage(sender, messageConfiguration.commandNotFound);
            return;
        }
        if (!sender.hasPermission(cmd.getPermission())) {
            ChatUtil.sendMessage(sender, messageConfiguration.noHasPermission.replace("{PERMISSION}", cmd.getPermission()));
            return;
        }

        cmd.execute(sender, args);
    }

    private Command get(String message) {
        for (Command sc : cmds) {
            if (sc.getName().equalsIgnoreCase(message)) {
                return sc;
            }
        }
        return null;
    }
}
