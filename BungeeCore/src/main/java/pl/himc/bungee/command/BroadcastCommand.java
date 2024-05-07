package pl.himc.bungee.command;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.util.ChatUtil;
import pl.himc.bungee.BungeePlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class BroadcastCommand extends Command {

    public BroadcastCommand(String permission) {
        super("broadcast", permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();
        if(args.length < 2){
            ChatUtil.sendMessage(sender, messageConfiguration.usageCommand.replace("{USAGE}", "/broadcast <wiadomosc>"));
            return;
        }
        String bc = Arrays.stream(args, 1, args.length).collect(Collectors.joining(" "));
        ProxyServer.getInstance().getPlayers().forEach(proxiedPlayer -> ChatUtil.sendMessage(proxiedPlayer, messageConfiguration.broadcastFormat.replace("{MESSAGE}", bc)));
    }
}
