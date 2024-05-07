package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.api.api.command.CommandAPI;

public final class UnMuteAllCommand extends CommandAPI {

    public UnMuteAllCommand(String permission) {
        super("unmuteall", permission, "/unbanall");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        PluginCore.getCore().getMuteManager().unMuteAll(s);
        return false;
    }
}
