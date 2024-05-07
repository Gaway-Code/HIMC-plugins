package pl.himc.core.command.admin;

import pl.himc.core.api.PluginCore;
import org.bukkit.command.CommandSender;
import pl.himc.api.api.command.CommandAPI;

public final class UnBanAllCommand extends CommandAPI {

    public UnBanAllCommand(String permission) {
        super("unbanall", permission, "/unbanall");
    }

    @Override
    public boolean onCommand(CommandSender s, String[] args) {
        PluginCore.getCore().getBanManager().unBanAll(s);
        return false;
    }
}
