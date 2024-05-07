package pl.himc.core.base.itemshop;

import pl.himc.core.api.PluginCore;

import java.util.List;

public final class ItemShop {

    private final String name;
    private final String command;
    private final List<String> message;

    public ItemShop(String name, String command, List<String> message){
        this.name = name;
        this.command = command;
        this.message = message;
        PluginCore.getCore().getItemshopData().addShop(this);
    }

    public String getName() {
        return this.name;
    }

    public String getCommand() {
        return this.command;
    }

    public List<String> getMessage() {
        return this.message;
    }
}
