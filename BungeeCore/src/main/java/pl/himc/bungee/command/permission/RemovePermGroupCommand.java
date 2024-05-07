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

public final class RemovePermGroupCommand extends Command {

    public RemovePermGroupCommand(String permission) {
        super("removepermgroup", permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();
        if(args.length != 3){
            ChatUtil.sendMessage(sender, messageConfiguration.usageCommand.replace("{USAGE}", "/bungee removepermgroup <Uprawnienie> <Nazwa grupy>"));
            return;
        }
        try {
            File file = new File(BungeePlugin.getPlugin().getProxy().getPluginsFolder().getParentFile(), "config.yml");
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            if (cfg.get("permissions." + args[2]) != null) {
                List<String> list = cfg.getStringList("permissions." + args[2]);
                if (list.contains(args[1])) {
                    list.remove(args[1]);
                }
                cfg.set("permissions." + args[2], list);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
                ProxyServer.getInstance().getConfigurationAdapter().load();
                PermissionsUtil.reloadPermissions();
                ChatUtil.sendMessage(sender, messageConfiguration.removePermFromGroupSuccess.replace("{PERMISSION}", args[1]).replace("{GROUP}", args[2]));
            } else {
                ChatUtil.sendMessage(sender, messageConfiguration.groupIsNotExists.replace("{GROUP}", args[2]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
