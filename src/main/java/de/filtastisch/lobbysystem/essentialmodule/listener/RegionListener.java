package de.filtastisch.lobbysystem.essentialmodule.listener;

import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.Objects;

public class RegionListener implements Listener {

    @EventHandler
    public void onFallUnderZero(PlayerMoveEvent e){
        Player p = e.getPlayer();
        if (p.getLocation().getY() <= 0){
            Objects.requireNonNull(ShortPosition.getFromName("spawn")).teleport(p);
            p.playSound(p.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, (float) 0.35, 1);
        }
    }
}
