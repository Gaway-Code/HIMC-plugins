package pl.himc.antikillaura.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.EnumWrappers;
import pl.himc.antikillaura.AntiKillauraPlugin;
import pl.himc.antikillaura.object.User;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;
import pl.himc.api.utils.bukkit.TitleAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public final class PlayerDamagePacket extends PacketAdapter {

    public PlayerDamagePacket(Plugin plugin) {
        super(plugin, PacketType.Play.Client.USE_ENTITY);
        ProtocolLibrary.getProtocolManager().addPacketListener(this);
    }

    @Override
    public void onPacketReceiving(PacketEvent event) {
        if (event.getPacket().getEntityUseActions().read(0) != EnumWrappers.EntityUseAction.ATTACK) return;
        final int a =  event.getPacket().getIntegers().read(0);
        if(AntiKillauraPlugin.getNpcList().get(a) != null){
            final User user = AntiKillauraPlugin.getPlugin().getUserManager().get(event.getPlayer());
            user.addDamageBot();
            TitleAPI.sendTitle(event.getPlayer(), "&4&lKILLAURA", "&c&lNie bij bota! " + user.getAttactBot() + "/" + AntiKillauraPlugin.getPlugin().getPluginConfig().damageBotAmount,5, 15, 5);
            for(Player admin : Bukkit.getOnlinePlayers()){
                if(admin.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".cmd.antikillaura")){
                    User userAdmin = AntiKillauraPlugin.getPlugin().getUserManager().get(admin);
                    if(userAdmin.isNotify()){
                        ChatUtil.sendMessage(admin, "&4AntiKillaura &8&l%> &4Gracz &6" + event.getPlayer().getName() + " &4bije boty! (Killaura)");
                    }
                }
            }
        }
    }
}
