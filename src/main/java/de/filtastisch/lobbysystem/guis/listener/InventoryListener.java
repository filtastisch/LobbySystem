package de.filtastisch.lobbysystem.guis.listener;

import de.filtastisch.lobbysystem.utils.BuildManager;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInvClose(InventoryCloseEvent e) {
        Player p = (Player) e.getView().getPlayer();
        if (e.getView().getTitle().equals("§e§lNavigator")) {
            p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_CLOSE, (float) 0.35, 1);
        }
    }

    @EventHandler
    public void onInvClick(InventoryClickEvent e) {
        if (!BuildManager.canPlayerBuild((Player) e.getWhoClicked()))
            if (!e.getView().getTitle().equals("§e§lNavigator")) {
                e.setCancelled(true);
            }else if (!(e.getSlot() == 11 || e.getSlot() == 13 || e.getSlot() == 15))
                e.setCancelled(true);
    }

}
