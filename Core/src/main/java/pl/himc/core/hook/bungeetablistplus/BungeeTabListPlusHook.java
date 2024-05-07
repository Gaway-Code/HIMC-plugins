package pl.himc.core.hook.bungeetablistplus;

import codecrafter47.bungeetablistplus.api.bukkit.BungeeTabListPlusBukkitAPI;
import pl.himc.core.CorePlugin;
import pl.himc.core.api.PluginCore;
import pl.himc.core.hook.bungeetablistplus.variables.MineLevelVariable;
import pl.himc.core.hook.bungeetablistplus.variables.MinePointsVariable;
import pl.himc.core.hook.bungeetablistplus.variables.TurboDropVariable;
import pl.himc.core.hook.bungeetablistplus.variables.TurboExpVariable;

public final class BungeeTabListPlusHook {

    public static void initVariableHook() {
        final CorePlugin plugin = PluginCore.getCore();
        plugin.getConsoleSender().info("Wykryto plugin BungeeTabListPlus! Funkcje tego pluginu zostana wlaczone.");
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new MineLevelVariable("minelevel"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new MinePointsVariable("minepoints"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new TurboDropVariable("turbodrop"));
        BungeeTabListPlusBukkitAPI.registerVariable(plugin, new TurboExpVariable("turboexp"));

    }
}
