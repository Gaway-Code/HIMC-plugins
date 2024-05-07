package pl.himc.core.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import pl.himc.api.api.PluginApi;
import pl.himc.api.utils.bukkit.ChatUtil;

public final class SignChange implements Listener {

	@EventHandler
	public void onSign(SignChangeEvent e) {
		Player p = e.getPlayer();
		if(!(p.hasPermission(PluginApi.getApi().getPluginConfig().prefixPermission + ".color.sign"))) {
			return;
		}
		String[] line = e.getLines();
		for (int i = 0; i < line.length; ++i) {
            String l = line[i];
            String c = ChatUtil.fixColor(l);
            e.setLine(i, c);
        }
	}
}
