package de.filtastisch.lobbysystem.essentialmodule.listener;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class CancelDamageListener implements Listener {

    @EventHandler
    public void onPlayerDamage(EntityDamageEvent e){
        e.setCancelled(true);
        if (e.getEntity() instanceof Player){
            Player p = (Player) e.getEntity();
            p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_HURT, (float) 0.35, 1);
        }
    }

}
