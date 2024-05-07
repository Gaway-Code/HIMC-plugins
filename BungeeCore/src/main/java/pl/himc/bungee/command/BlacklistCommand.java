package pl.himc.bungee.command;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.data.PluginConfiguration;
import pl.himc.bungee.BungeePlugin;

public final class BlacklistCommand extends Command {

    public BlacklistCommand(String permission) {
        super("blacklist", permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();
        final PluginConfiguration pluginConfiguration = BungeePlugin.getPlugin().getPluginConfiguration();

    }
}
