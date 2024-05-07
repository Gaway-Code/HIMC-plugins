package pl.himc.core.hook;

import pl.himc.core.api.PluginCore;
import pl.himc.core.hook.bungeetablistplus.BungeeTabListPlusHook;
import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class PluginHook {

    public static final String PLUGIN_BUNGEETABLISTPLUS = "BungeeTabListPlus";

    private static final List<String> HOOK_LIST = new ArrayList<>();

    public static void init() {
        tryInit(PLUGIN_BUNGEETABLISTPLUS, () -> {
            try {
                Class.forName("codecrafter47.bungeetablistplus.api.bukkit.Variable");
                BungeeTabListPlusHook.initVariableHook();

                return true;
            } catch (final ClassNotFoundException exception) {
                return false;
            }
        });
    }

    public static void tryInit(final String plugin, final Supplier<Boolean> init, final boolean notifyIfMissing) {
        if (Bukkit.getPluginManager().getPlugin(plugin) != null) {
            if (!init.get()) {
                return;
            }
            HOOK_LIST.add(plugin);
        } else if (notifyIfMissing) {
            PluginCore.getCore().getConsoleSender().warning("Brak pluginu " + plugin + " niektore funkcje pluginu moga nie dzialac!");
        }
    }

    public static void tryInit(final String plugin, final Supplier<Boolean> init) {
        tryInit(plugin, init, true);
    }

}
