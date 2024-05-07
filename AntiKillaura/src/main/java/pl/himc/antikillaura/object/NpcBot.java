package pl.himc.antikillaura.object;

import com.mojang.authlib.GameProfile;
import pl.himc.antikillaura.AntiKillauraPlugin;
import pl.himc.antikillaura.utils.RandomBotName;
import pl.himc.antikillaura.utils.Reflections;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.UUID;

public final class NpcBot extends Reflections {

    private int entityID;
    private GameProfile gameprofile;
    private Player player;
    
    public NpcBot(final Player player) {
        this.player = player;
        this.gameprofile = new GameProfile(UUID.randomUUID(), RandomBotName.getBotName());
        this.spawn();
        AntiKillauraPlugin.getPlugin().addBot(this);
    }

    private void spawn() {
        MinecraftServer nmsServer = ((CraftServer)Bukkit.getServer()).getServer();
        WorldServer nmsWorld = ((CraftWorld)player.getWorld()).getHandle();
        EntityPlayer entityPlayer = new EntityPlayer(nmsServer, nmsWorld, this.gameprofile, new PlayerInteractManager(nmsWorld));
        entityPlayer.setLocation(player.getLocation().getX(),
                player.getLocation().getY() + 2,
                player.getLocation().getZ(),
                player.getLocation().getYaw(),
                player.getLocation().getPitch());
        this.entityID = entityPlayer.getId();

        this.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, entityPlayer), this.player);
        this.sendPacket(new PacketPlayOutNamedEntitySpawn(entityPlayer), this.player);
        this.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, entityPlayer), this.player);

        BukkitTask task = Bukkit.getScheduler().runTaskTimerAsynchronously(AntiKillauraPlugin.getPlugin(), () -> {
            entityPlayer.setLocation(player.getLocation().getX(),
                    player.getLocation().getY() + 2,
                    player.getLocation().getZ(),
                    player.getLocation().getYaw(),
                    player.getLocation().getPitch());

            PacketPlayOutEntityTeleport packet = new PacketPlayOutEntityTeleport(entityPlayer);
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
        }, 0, 5);

        Bukkit.getScheduler().runTaskLater(AntiKillauraPlugin.getPlugin(), () -> {
            this.destroy();
            task.cancel();
            AntiKillauraPlugin.getPlugin().removeBot(this);
        }, 3 * 20);
    }

    public void destroy() {
        final PacketPlayOutEntityDestroy packet = new PacketPlayOutEntityDestroy(this.entityID);
        this.sendPacket(packet, this.player);
    }
    public Integer getEntityID(){
        return this.entityID;
    }
}
