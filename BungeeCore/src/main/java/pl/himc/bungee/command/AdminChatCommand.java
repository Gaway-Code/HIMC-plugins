package pl.himc.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.util.ChatUtil;
import pl.himc.bungee.BungeePlugin;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class AdminChatCommand extends Command {

    public AdminChatCommand(String permission) {
        super("adminchat", permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();
        if(args.length < 2){
            ChatUtil.sendMessage(sender, messageConfiguration.usageCommand.replace("{USAGE}", "/adminchat <wiadomosc>"));
            return;
        }
        String bc = Arrays.stream(args, 1, args.length).collect(Collectors.joining(" "));
        ProxyServer.getInstance().getPlayers().stream().filter(player -> player.hasPermission(this.getPermission())).forEach(proxiedPlayer -> ChatUtil.sendMessage(proxiedPlayer, messageConfiguration.adminchatFormat.replace("{PLAYER}", sender.getName()).replace("{MESSAGE}", bc)));
    }
}
