package de.filtastisch.lobbysystem.guis.types;

import de.carlos17kopra.capi.spigot.ItemBuilder;
import de.filtastisch.lobbysystem.LobbySystem;
import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import de.carlos17kopra.capi.spigot.GuiManager;
import org.bukkit.permissions.Permission;

public class NavigatorGUI {

    GuiManager inv;
    Player target;
    LobbySystem plugin = LobbySystem.getLobby();

    public NavigatorGUI() {
        this.inv = new GuiManager("§e§lNavigator", 9 * 3);
    }

    public void openGUI(Player p) {
        this.inv.open(p);
        this.fillInv(p);
        p.playSound(p.getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, (float) 0.35, 1);
    }

    public void fillInv(Player current) {
        target = current;
        GuiManager.InventoryItem spawnItem = new GuiManager.InventoryItem(new ItemBuilder(Material.NETHER_STAR).setName("§a§lSpawn").create(), 13, true, e -> {
            Player p = (Player) e.getWhoClicked();
            if (ShortPosition.isPosition("spawn")) {
                ShortPosition.getFromName("spawn").teleport(p);
                p.playSound(p.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, (float) 0.35, 1);
            }else
                p.sendMessage("Spawn not set"); //TODO - config
        });

        GuiManager.InventoryItem smpItem = new GuiManager.InventoryItem(new ItemBuilder(Material.GRASS_BLOCK).setName("§aSMP-Server").create(), 11, true, e -> {
            Player p = (Player) e.getWhoClicked();
            if (ShortPosition.isPosition("smp")) {
                ShortPosition.getFromName("smp").teleport(p);
                p.playSound(p.getLocation(), Sound.ITEM_CHORUS_FRUIT_TELEPORT, (float) 0.35, 1);
            }else
                p.sendMessage("warp not set"); //TODO - config
        });

        GuiManager.InventoryItem survivalItem = new GuiManager.InventoryItem(new ItemBuilder(Material.IRON_PICKAXE).setName("§c§lSurvival").create(), 15, true, e -> {
            Player p = (Player) e.getWhoClicked();
            plugin.sendPlayerToServer(p, "larissa_1.16");
        });

        this.inv.setItem(smpItem);
        this.inv.setItem(spawnItem);

        if (current.hasPermission(new Permission("lobby.survival"))){
            this.inv.setItem(survivalItem);
        }

        this.inv.fillBorders(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setName(" ").create());
    }
}
