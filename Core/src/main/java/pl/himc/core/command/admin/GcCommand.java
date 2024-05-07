package pl.himc.core.command.admin;

import pl.himc.api.utils.bukkit.ChatUtil;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import pl.himc.api.api.command.PlayerCommand;

import javax.management.MBeanServerConnection;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public final class GcCommand extends PlayerCommand {

    public GcCommand(String permission) {
        super("gc", permission,"/gc ");
    }
    private MBeanServerConnection mbsc;
    private OperatingSystemMXBean osMBean;

    @Override
    public boolean onCommand(Player player, String[] args) {
        ChatUtil.sendMessage(player, "&8&m---------------------------------");
        ChatUtil.sendMessage(player,"&3Procesor: &6 " + this.getPercentageUsage() + "%");
        ChatUtil.sendMessage(player,"&3Dostepne Rdzenie: &6 " + Runtime.getRuntime().availableProcessors());
        ChatUtil.sendMessage(player,"&3Pamiec:");
        ChatUtil.sendMessage(player,"  &7- Calkowita: &3" + this.getFullMemory());
        ChatUtil.sendMessage(player,"  &7- Zarezerwowana: &3" + this.getReservedMemory());
        ChatUtil.sendMessage(player,"  &7- Wolna: &3" + this.getFreeMemory());
        final World world = Bukkit.getWorld("world");
        ChatUtil.sendMessage(player,"&3Mapa:");
        ChatUtil.sendMessage(player,"  &7- Chunki: &3" + world.getLoadedChunks().length);
        ChatUtil.sendMessage(player,"  &7- Wszystkie Entites: &3" + world.getEntities().size());
        ChatUtil.sendMessage(player,"  &7- Zywe Entites: &3" + world.getLivingEntities().size());
        ChatUtil.sendMessage(player, "&8&m---------------------------------");

        return false;
    }

    public long getFreeMemory() {
        return Runtime.getRuntime().freeMemory() / 1024L / 1024L;
    }

    public long getReservedMemory() {
        return Runtime.getRuntime().totalMemory() / 1024L / 1024L;
    }

    public long getFullMemory() {
        return Runtime.getRuntime().maxMemory() / 1024L / 1024L;
    }
    public double getPercentageUsage() {
        try {
            if (this.mbsc == null) {
                this.mbsc = ManagementFactory.getPlatformMBeanServer();
                this.osMBean = ManagementFactory.newPlatformMXBeanProxy(this.mbsc, "java.lang:type=OperatingSystem", OperatingSystemMXBean.class);
            }
            return this.osMBean.getSystemLoadAverage();
        }
        catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
