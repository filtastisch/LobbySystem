package de.filtastisch.lobbysystem.utils;

import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class BuildManager {

    private static List<UUID> buildpls;

    public static Map<UUID, ItemStack[]> safeInventory = new HashMap<>();
    public static Map<UUID, Location> safeLocation = new HashMap<>();

    public static void setPlayerBuildMode(final Player p, final boolean mode) {
        if (mode) {
            BuildManager.buildpls.add(p.getUniqueId());
            p.setGameMode(GameMode.CREATIVE);
        } else {
            BuildManager.buildpls.remove(p.getUniqueId());
            p.setGameMode(GameMode.SURVIVAL);
        }
    }

    public static boolean canPlayerBuild(final Player p) {
        return BuildManager.buildpls.contains(p.getUniqueId());
    }

    public static void toggleBuildMode(final Player p) {
        final boolean mode = !canPlayerBuild(p);

        ItemStack[] content = safeInventory.getOrDefault(p.getUniqueId(), new ItemStack[9 * 4]);
        safeInventory.put(p.getUniqueId(), p.getInventory().getContents());
        p.getInventory().setContents(content);

        Location old = safeLocation.getOrDefault(p.getUniqueId(), Objects.requireNonNull(ShortPosition.getFromName("spawn")).getLocation());
        safeLocation.put(p.getUniqueId(), p.getLocation());
        p.setAllowFlight(true);
        p.setFlying(true);
        p.teleport(old);

        setPlayerBuildMode(p, mode);
    }

    public static void removeBuildPlayer(final Player p) {
        if (BuildManager.canPlayerBuild(p))
            BuildManager.buildpls.remove(p.getUniqueId());
    }

    static {
        buildpls = new ArrayList<UUID>();
    }
}

