package pl.himc.api.api.command;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public abstract class PlayerCommand extends CommandAPI {

    public PlayerCommand(String name, String permission, String usage, String... aliases) {
        super(name, permission, usage, aliases);
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        if(!(s instanceof Player)){
            return ChatUtil.sendMessage(s, PluginApi.getApi().getPluginMessages().commandOnlyPlayerExecute);
        }
        return this.onCommand((Player)s, args);
    }

    public abstract boolean onCommand(Player player, String[] args);
}
