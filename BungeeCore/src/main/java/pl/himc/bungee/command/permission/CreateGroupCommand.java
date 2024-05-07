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
import java.util.ArrayList;
import java.util.List;

public final class CreateGroupCommand extends Command {

    public CreateGroupCommand(String permission) {
        super("creategroup", permission);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        final MessageConfiguration messageConfiguration = BungeePlugin.getPlugin().getMessageConfiguration();
        if(args.length != 2){
            ChatUtil.sendMessage(sender, messageConfiguration.usageCommand.replace("{USAGE}", "/bungee creategroup <Nazwa grupy>"));
            return;
        }

        try {
            File file = new File(BungeePlugin.getPlugin().getProxy().getPluginsFolder().getParentFile(), "config.yml");
            Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            if (cfg.get("permissions." + args[1]) == null) {
                final List<String> list = new ArrayList<>();
                list.add("bungeecord.command.server");
                cfg.set("permissions." + args[1], list);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, file);
                ProxyServer.getInstance().getConfigurationAdapter().load();
                PermissionsUtil.reloadPermissions();
                ChatUtil.sendMessage(sender, messageConfiguration.createGroupSuccess.replace("{GROUP}", args[1]));
            } else {
                ChatUtil.sendMessage(sender, messageConfiguration.groupAlreadyExists.replace("{GROUP}", args[1]));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}