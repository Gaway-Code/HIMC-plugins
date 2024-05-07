package pl.himc.lobbycore.base.server;

import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;
import pl.himc.lobbycore.LobbyPlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Servers {

    private final String name;
    private ItemStack item;
    private String serverconnector;
    private boolean randomslot;
    private int slot;

    public Servers(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getServerconnector() {
        return serverconnector;
    }

    public boolean isRandomslot() {
        return randomslot;
    }

    public int getSlot() {
        return slot;
    }

    public void setItem(ItemStack item) {
        this.item = item;
    }

    public void setServerconnector(String serverconnector) {
        this.serverconnector = serverconnector;
    }

    public void setRandomslot(boolean randomslot) {
        this.randomslot = randomslot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void playerCount() {
        final ByteArrayOutputStream array = new ByteArrayOutputStream();
        final DataOutputStream output = new DataOutputStream(array);
        try {
            output.writeUTF("PlayerCount");
            output.writeUTF(this.name);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        Bukkit.getOnlinePlayers().iterator().next().sendPluginMessage(LobbyPlugin.getPlugin(), "BungeeCord", array.toByteArray());
    }
}
