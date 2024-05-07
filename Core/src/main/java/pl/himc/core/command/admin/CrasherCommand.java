package pl.himc.core.command.admin;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import pl.himc.api.api.PluginApi;
import pl.himc.api.api.command.CommandAPI;
import pl.himc.api.utils.bukkit.ChatUtil;

public class CrasherCommand extends CommandAPI {
    public CrasherCommand(String permission) {
        super("crasher", permission, "/crasher <Gracz>");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(args.length != 1){
            return ChatUtil.sendUsage(s, this.getUsage());
        }
        Bukkit.broadcast(ChatUtil.fixColor("&6HI&fCrash &8»&r &cGracz &6"+args[0]+" &cWyslal duzy pakiet"), PluginApi.getApi().getPluginConfig().prefixPermission + ".crasher.see");
        return false;
    }
}
