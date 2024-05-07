package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.entity.Player;
import pl.himc.core.base.user.User;
import pl.himc.core.configuration.PluginMessages;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.api.command.PlayerCommand;

public final class GodCommand extends PlayerCommand {

    public GodCommand(String permission) {
        super("god", permission, "/god <Gracz>");
    }

    @Override
    public boolean onCommand(Player p, String[] args) {
        PluginMessages messageConfiguration = PluginCore.getCore().getPluginMessages();
        if(args.length == 0){
            User u = PluginCore.getCore().getUserManager().getUser(p);
            u.toggleGodMode();
            if(u.isGodMode()){
                ChatUtil.sendMessage(p, messageConfiguration.godModeEnable);
            }else{
                ChatUtil.sendMessage(p, messageConfiguration.godModeDisable);
            }
        }else if(args.length == 1){
            if(!p.hasPermission(this.getPermission() + ".others")){
                return ChatUtil.sendMessage(p, messageConfiguration.playerNotHasPermission.replace("{PERM}", this.getPermission() + ".others"));
            }
            User u2 = PluginCore.getCore().getUserManager().getUser(args[0]);
            if(u2 == null){
                return ChatUtil.sendMessage(p, messageConfiguration.playerIsNotExists);
            }
            u2.toggleGodMode();
            if(u2.isGodMode()){
                ChatUtil.sendMessage(p, messageConfiguration.godModeEnableOther.replace("{PLAYER}", u2.getName()));
            }else{
                ChatUtil.sendMessage(p, messageConfiguration.godModeDisableOther.replace("{PLAYER}", u2.getName()));
            }
        }else{
            return ChatUtil.sendUsage(p, this.getUsage());
        }
        return false;
    }
}
