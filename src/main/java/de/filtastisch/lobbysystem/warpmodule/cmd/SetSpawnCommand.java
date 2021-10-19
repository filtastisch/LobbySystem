package de.filtastisch.lobbysystem.warpmodule.cmd;

import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class SetSpawnCommand implements CommandExecutor {

    ShortPosition shortPosition;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0){
                if (!ShortPosition.isPosition("spawn")){
                    ShortPosition.add("spawn", p.getLocation());
                    p.sendMessage("Spawn gesetzt!"); //TODO - config
                } else {
                    p.sendMessage("spawn already set"); //TODO - config
                }
            }
        }
        return false;
    }
}