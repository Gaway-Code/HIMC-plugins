package pl.himc.core.command.player;

import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;
import pl.himc.core.CorePlugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public final class LobbyCommand extends PlayerCommand {

    public LobbyCommand(final CorePlugin plugin) {
        super("lobby", null, "");
        plugin.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        this.plugin = plugin;
        this.register();
    }
    private final CorePlugin plugin;

    @Override
    public boolean onCommand(Player player, String[] args) {
        ByteArrayOutputStream array = new ByteArrayOutputStream();
        DataOutputStream output = new DataOutputStream(array);
        try {
            output.writeUTF("Connect");
            output.writeUTF("lobby");
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(this.plugin, "BungeeCord", array.toByteArray());
        return false;
    }

}
