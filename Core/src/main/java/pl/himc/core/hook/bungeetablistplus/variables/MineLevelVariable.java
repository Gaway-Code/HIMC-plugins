package pl.himc.core.hook.bungeetablistplus.variables;

import codecrafter47.bungeetablistplus.api.bukkit.Variable;
import pl.himc.core.api.PluginCore;
import pl.himc.core.base.user.User;
import org.bukkit.entity.Player;

public class MineLevelVariable extends Variable {

    public MineLevelVariable(String name) {
        super(name);
    }

    @Override
    public String getReplacement(Player player) {
        User user = PluginCore.getCore().getUserManager().getUser(player);
        return user.getLvl() + "";
    }
}
