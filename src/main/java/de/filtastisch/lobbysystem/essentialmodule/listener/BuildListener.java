package de.filtastisch.lobbysystem.essentialmodule.listener;

import de.filtastisch.lobbysystem.utils.BuildManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class BuildListener implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        Player p = e.getPlayer();
        if (!BuildManager.canPlayerBuild(p)){
            e.setCancelled(true);
        } else
            e.setCancelled(false);
    }

    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e){
        Player p = e.getPlayer();
        e.setCancelled(!BuildManager.canPlayerBuild(p));
    }

}
