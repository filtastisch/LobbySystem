package de.filtastisch.lobbysystem.hotbar.listener;

import de.carlos17kopra.capi.spigot.ItemBuilder;
import de.filtastisch.lobbysystem.utils.BuildManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {

    static ItemStack navItem = new ItemBuilder(Material.CLOCK).setName("§6§lNavigator").create();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (!BuildManager.canPlayerBuild(p)){
            p.getInventory().clear();
            p.getInventory().setItem(4, navItem);
        }
    }

}
