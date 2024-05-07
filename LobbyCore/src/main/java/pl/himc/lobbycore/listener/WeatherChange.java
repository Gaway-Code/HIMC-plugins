package pl.himc.lobbycore.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import pl.himc.lobbycore.LobbyPlugin;

public class WeatherChange implements Listener {

	public WeatherChange(final LobbyPlugin plugin){
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void WeatherChangeEvent(WeatherChangeEvent e) {
		if (!e.toWeatherState()) {
			return;
		}
		e.setCancelled(true);
		e.getWorld().setWeatherDuration(0);
		e.getWorld().setThundering(false);
	}
}
