package pl.himc.bungee.command.permission;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;
import pl.himc.bungee.data.MessageConfiguration;
import pl.himc.bungee.util.ChatUtil;
import pl.himc.bungee.util.PermissionsUtil;
import pl.himc.bungee.BungeePlugin;

import java.io.File;
import java.util.List;

public final class RenameGroupCommand extends Command {

    public RenameGroupCommand(String permission) {
        super("renamegroup", permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();
        if(args.length != 3){
            ChatUtil.sendMessage(sender, messageConfiguration.usageCommand.replace("{USAGE}", "/bungee renamegroup <Stara nazwa grupy> <Nowa nazwa grupy>"));
            return;
        }
        try {
            final File file = new File(BungeePlugin.getPlugin().getProxy().getPluginsFolder().getParentFile(), "config.yml");
            final Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            if (cfg.get("permissions." + args[1]) != null) {
                final String path = "permissions." + args[1];
                final List<String> oldPerms = cfg.getStringList(path);
                cfg.set(path, null);
                cfg.set("permissions." + args[2], oldPerms);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
                ProxyServer.getInstance().getConfigurationAdapter().load();
                PermissionsUtil.reloadPermissions();
                ChatUtil.sendMessage(sender, messageConfiguration.groupRenameSuccess.replace("{OLDGROUP}", args[1]).replace("{NEWGROUP}", args[2]));
            } else {
                ChatUtil.sendMessage(sender, messageConfiguration.groupIsNotExists.replace("{GROUP}", args[1]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
