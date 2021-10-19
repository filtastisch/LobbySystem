package de.filtastisch.lobbysystem.warpmodule.utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.*;

public class ShortPosition {

    private String posName;
    private Location location;

    public static List<ShortPosition> all = new ArrayList<>();

    public ShortPosition(String posName, Location location) {
        this.posName = posName;
        this.location = location;
        if (!isPosition(posName)) {
            all.add(this);
        }
    }

    /*Statics Start*/

    public static void add(String posName, Location loc) {
        new ShortPosition(posName, loc);
    }

    public static String noPosition(ShortPosition pos, String prefix) {
        return prefix + "§cDie Position §e" + pos.getPosName() + " §cexistiert nich!";
    }

    public static String getFancyPositionList(String prefix) {

        StringBuilder sb = new StringBuilder();

        if (!all.isEmpty()) {
            int count = 1;
            for (ShortPosition current : all) {
                if (count == all.size()) {
                    sb.append("§7└ ").append(ChatColor.GOLD).append(current.getPosName()).append("\n");
                } else {
                    sb.append("§7├ ").append(ChatColor.GOLD).append(current.getPosName()).append("\n");
                }
                count++;
            }
            return sb.toString();
        }
        return prefix + "§cGibt noch keine Positionen";
    }

    public static List<String> getPosList(){
        List<String> pos = new ArrayList<>();
        if (!all.isEmpty()){
            for(ShortPosition current : all){
                pos.add(current.getPosName());
            }
        }
        return pos;
    }

    public static boolean isPosition(String posName) {
        for (ShortPosition current : all) {
            if (current.getPosName().equals(posName)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPosition(ShortPosition pos) {
        for (ShortPosition current : all) {
            if (current.getPosName().equals(pos.getPosName())) {
                return true;
            }
        }
        return false;
    }

    public static ShortPosition getFromName(String name) {
        for (ShortPosition current : all) {
            if (current.getPosName().equals(name)) {
                return current;
            }
        }
        return null;
    }

    /*Statics End*/

    public String sendPosition() {
        StringBuilder sb = new StringBuilder();
        sb.append("§aPosition: \n §7─ §6").append(this.posName);
        sb.append("\n     §7├ §6X: §e").append(this.location.getBlockX());
        sb.append("\n     §7├ §6Y: §e").append(this.location.getBlockY());
        sb.append("\n     §7├ §6Z: §e").append(this.location.getBlockZ());

        if (Objects.requireNonNull(this.location.getWorld()).getName().equals("world")) {
            sb.append("\n     §7└ §6World: §aWorld");
        } else if (Objects.requireNonNull(this.location.getWorld()).getName().equals("world_nether")) {
            sb.append("\n     §7└ §6World: §cNether");
        } else if (Objects.requireNonNull(this.location.getWorld()).getName().equals("world_the_end")) {
            sb.append("\n     §7└ §6World: §dEnd");
        } else {
            sb.append("\n     §7└ §6World: §e").append(Objects.requireNonNull(Objects.requireNonNull(this.location).getWorld()).getName());
        }
        return sb.toString();
    }

    public void teleport(Player p) {
        p.teleport(this.location);
        p.setVelocity(new Vector(0, 0, 0));
    }

    public void remove() {
        all.remove(this);
    }

    /*Getter / Setter*/


    public String getPosName() {
        return posName;
    }

    public Location getLocation() {
        return location;
    }
}

