package pl.himc.api.api;

import pl.himc.api.ApiPlugin;

public final class PluginApi {

    private static ApiPlugin instance;

    public static void setInstance(final ApiPlugin api){
        PluginApi.instance = api;
    }

    public static ApiPlugin getApi(){
        return instance;
    }
}
