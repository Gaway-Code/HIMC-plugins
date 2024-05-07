package pl.himc.core.listener.block;

import pl.himc.core.base.craft.stoniarka.Stoniarka;
import pl.himc.core.base.craft.stoniarka.StoniarkaData;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import pl.himc.guilds.api.PluginGuild;
import pl.himc.guilds.base.guild.Guild;

import java.util.List;

public final class BlockPiston implements Listener {

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkPistonExtend(BlockPistonExtendEvent e){
        List<Block> blocks = e.getBlocks();
        if (!blocks.isEmpty()) {
            for (Block b : blocks) {
                Stoniarka stoniarka = StoniarkaData.getGenerator(b.getLocation());
                if(stoniarka != null){
                    e.setCancelled(true);
                }
                Guild guild = PluginGuild.getPlugin().getGuildManager().getLocation(b.getLocation());
                if(guild != null){
                    if(guild.getCuboid().isInCentrum(b.getLocation(), 6, 1, 3)){
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void checkPistonRetract(BlockPistonRetractEvent e){
        List<Block> blocks = e.getBlocks();
        if (!blocks.isEmpty()) {
            for (Block b : blocks) {
                Stoniarka stoniarka = StoniarkaData.getGenerator(b.getLocation());
                if(stoniarka != null){
                    e.setCancelled(true);
                }
                Guild guild = PluginGuild.getPlugin().getGuildManager().getLocation(b.getLocation());
                if(guild != null){
                    if(guild.getCuboid().isInCentrum(b.getLocation(), 6, 1, 3)){
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}
