package pl.himc.core.command.player;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.core.configuration.PluginConfig;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.api.utils.StringUtil;
import pl.himc.api.utils.TimeUtil;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class HelpopCommand extends PlayerCommand {

    public HelpopCommand(String permission) {
        super("helpop", permission, "/helpop <Wiadomosc>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginConfig pluginConfiguration = PluginCore.getCore().getPluginConfig();
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length < 1){
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        User user = PluginCore.getCore().getUserManager().getUser(p.getName());

        if(!user.isHelpop()){
            return ChatUtil.sendMessage(p, messageConfiguration.helpopCooldown.replace("{TIME}", TimeUtil.getDuration(user.getWriteHelpop() - System.currentTimeMillis())));
        }
        String wiadomosc = StringUtil.newStringBuilder(args, 0);
        if(!p.hasPermission(this.getPermission() + ".time.bypass")){
            user.setWriteHelpop(System.currentTimeMillis() + pluginConfiguration.cfgHelpopCooldown);
        }
        ChatUtil.sendMessage(p, messageConfiguration.helpopSendSuccess);

        return ChatUtil.sendBroadcast(messageConfiguration.helpopFormat
                .replace("{PLAYER}", p.getName())
                .replace("{MESSAGE}", wiadomosc), this.getPermission() + ".see");
    }
}
