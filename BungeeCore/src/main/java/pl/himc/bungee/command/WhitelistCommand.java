package pl.himc.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.object.Whitelist;
import pl.himc.bungee.util.ChatUtil;
import pl.himc.bungee.BungeePlugin;

public final class WhitelistCommand extends Command {

    public WhitelistCommand(final BungeePlugin plugin) {
        super("whitelist", plugin.getPluginConfiguration().prefixPermission + ".whitelist");
        this.plugin = plugin;
    }
    private final BungeePlugin plugin;

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = this.plugin.getMessageConfiguration();
        if(args.length <= 1){
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistHelp);
            return;
        }
        final String serverName = args[0].toLowerCase();
        if(ProxyServer.getInstance().getServerInfo(serverName) == null){
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistServerNotFound);
            return;
        }
        Whitelist whitelist = this.plugin.getWhitelistData().getWhitelist(serverName);
        if(whitelist == null){
            whitelist = this.plugin.getWhitelistData().createWhitelist(serverName);
        }

        if (args[1].equalsIgnoreCase("on")) {
            if(whitelist.isEnable()){
                ChatUtil.sendMessage(sender, messageConfiguration.whitelistIsEnable);
                return;
            }
            whitelist.setEnable(true);
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistEnableSuccess);
        }else if (args[1].equalsIgnoreCase("off")) {
            if(!whitelist.isEnable()){
                ChatUtil.sendMessage(sender, messageConfiguration.whitelistIsDisable);
                return;
            }
            whitelist.setEnable(false);
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistDisableSuccess);
        }else if (args[1].equalsIgnoreCase("message") && args.length >= 4 && args[2].equalsIgnoreCase("set")) {
            final String message = ChatUtil.stringBuilder(args, 3);
            whitelist.setKickMessage(message);
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistReasonSet.replace("{REASON}", message));
        }else if (args[1].equalsIgnoreCase("message") && args.length == 3 && args[2].equalsIgnoreCase("get")) {
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistGet.replace("{REASON}", whitelist.getKickMessage()));
        }else if (args[1].equalsIgnoreCase("add") && args.length == 3) {
            if(whitelist.getPlayers().contains(args[2].toLowerCase())){
                ChatUtil.sendMessage(sender, messageConfiguration.whitelistAlreadyAdd);
                return;
            }
            whitelist.addPlayer(args[2]);
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistAddSuccess.replace("{PLAYER}", args[2]));
        }else if (args[1].equalsIgnoreCase("remove") && args.length == 3) {
            if(!whitelist.getPlayers().contains(args[2].toLowerCase())){
                ChatUtil.sendMessage(sender, messageConfiguration.whitelistAlreadyRemove);
                return;
            }
            whitelist.removePlayer(args[2]);
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistRemoveSuccess.replace("{PLAYER}", args[2]));
        }else if (args[1].equalsIgnoreCase("list")) {
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistList.replace("{LIST}", whitelist.getPlayers().toString()));
        }else {
            ChatUtil.sendMessage(sender, messageConfiguration.whitelistHelp);
        }
    }
}
