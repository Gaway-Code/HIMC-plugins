package pl.himc.api.api.inventory;

import java.util.HashMap;
import java.util.Map;

public final class GuiManager {

    private static GuiManager inst;
    private Map<String, GuiWindow> windows;

    public GuiManager(){
        inst = this;
        this.windows = new HashMap<>();
    }

    public Map<String, GuiWindow> getAll(){
        return this.windows;
    }
    public GuiWindow get(String inv) {
        return this.windows.get(inv);
    }

    public void add(final String name, final GuiWindow window){
        this.windows.put(name, window);
    }
    public void remove(final String name){
        this.windows.remove(name);
    }

    public static GuiManager getGui() {
        if(inst == null){
            return new GuiManager();
        }
        return inst;
    }
}
