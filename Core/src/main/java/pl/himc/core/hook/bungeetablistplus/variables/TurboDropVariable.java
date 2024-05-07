package pl.himc.core.hook.bungeetablistplus.variables;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;
import org.bukkit.entity.Player;

public class TurboDropVariable extends Variable {

    public TurboDropVariable(String name) {
        super(name);
    }

    @Override
    public String getReplacement(Player player) {
        User user = PluginCore.getCore().getUserManager().getUser(player);
        return (user.isTurboDrop() ? user.getDurationTruboDrop() : "&cnieaktywny");
    }
}
