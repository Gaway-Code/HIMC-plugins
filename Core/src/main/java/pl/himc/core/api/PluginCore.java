package pl.himc.core.api;

import pl.himc.core.CorePlugin;

public final class PluginCore {

    private static CorePlugin instance;

    public PluginCore(CorePlugin plugin){
        PluginCore.instance = plugin;
    }

    public static CorePlugin getCore(){
        return instance;
    }
}
