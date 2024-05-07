package pl.himc.core.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public final class WeatherChange implements Listener {

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
