package de.filtastisch.lobbysystem.essentialmodule.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodChangeListener implements Listener {

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
        e.setFoodLevel(20);
    }

}
