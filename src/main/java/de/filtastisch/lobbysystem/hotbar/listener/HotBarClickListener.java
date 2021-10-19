package de.filtastisch.lobbysystem.hotbar.listener;

import de.filtastisch.lobbysystem.guis.types.NavigatorGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

public class HotBarClickListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onHotBarClick(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if (Objects.equals(e.getItem(), JoinListener.navItem)){
                new NavigatorGUI().openGUI(p);
                e.setCancelled(true);
            }
        }
    }

}
