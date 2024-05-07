package pl.himc.guilds.hook;

import pl.himc.guilds.hook.bungeetablistplus.BungeeTabListPlusHook;
import org.bukkit.Bukkit;
import pl.himc.guilds.api.PluginGuild;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public final class PluginHook {

    public static final String PLUGIN_BUNGEETABLISTPLUS = "BungeeTabListPlus";
    public static final String PLUGIN_HOLOGRAPHICDISPLAYS = "HolographicDisplays";

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
        tryInit(PLUGIN_HOLOGRAPHICDISPLAYS, () -> {
            try {
                Class.forName("com.gmail.filoghost.holographicdisplays.api.Hologram");
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
            PluginGuild.getPlugin().getLog().warning("Brak pluginu " + plugin + " niektore funkcje pluginu moga nie dzialac!");
        }
    }

    public static void tryInit(final String plugin, final Supplier<Boolean> init) {
        tryInit(plugin, init, true);
    }

    public static boolean hookHolographic() {
        return HOOK_LIST.contains(PLUGIN_HOLOGRAPHICDISPLAYS);
    }
}
