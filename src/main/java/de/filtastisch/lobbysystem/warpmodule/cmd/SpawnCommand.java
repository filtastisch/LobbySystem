package de.filtastisch.lobbysystem.warpmodule.cmd;

import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class SpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0){
                if (ShortPosition.isPosition("spawn")){
                    ShortPosition pos = ShortPosition.getFromName("spawn");
                    assert pos != null;
                    pos.teleport(p);
                    p.sendMessage("Zum Spawn teleportiert!"); //TODO - config
                } else {
                    p.sendMessage("spawn not set"); //TODO - config
                }
            }
        }
        return false;
    }
}