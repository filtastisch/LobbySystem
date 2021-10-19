package de.filtastisch.lobbysystem.essentialmodule.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherChangeListener implements Listener {

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.getWorld().setWeatherDuration(0);
    }

}
