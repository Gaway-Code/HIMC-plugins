package pl.himc.antikillaura.utils;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Reflections {
    
    public void sendPacket(final Packet packet, final Player player) {
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket(packet);
    }

}
