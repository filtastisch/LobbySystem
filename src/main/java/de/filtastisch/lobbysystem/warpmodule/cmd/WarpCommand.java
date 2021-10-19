package de.filtastisch.lobbysystem.warpmodule.cmd;

import de.filtastisch.lobbysystem.warpmodule.utils.ShortPosition;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class WarpCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!(args.length == 0)){
                if (args.length == 1){
                    if (args[0].equalsIgnoreCase("list")){
                        p.sendMessage("Gesetzte Warps:"); //TODO - config
                        p.sendMessage(ShortPosition.getFancyPositionList("prefix")); //TODO - config
                    }
                }else if (args.length == 2){
                    if (args[0].equalsIgnoreCase("set")){
                        if (!args[1].equalsIgnoreCase("spawn")) {
                            if (!ShortPosition.isPosition(args[1])) {
                                ShortPosition.add(args[1], p.getLocation());
                                p.sendMessage("Position gesetzt!"); //TODO - config
                            } else {
                                p.sendMessage("Position bereits gesetzt"); //TODO - config
                            }
                        } else {
                            p.sendMessage("Spawn nicht nutzbar"); //TODO - config
                        }
                    } else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete")){
                        if (ShortPosition.isPosition(args[1])) {
                            ShortPosition pos = ShortPosition.getFromName(args[1]);
                            assert pos != null;
                            pos.remove();
                            p.sendMessage("Position gelöscht!"); //TODO - config
                        } else {
                            p.sendMessage("Position existiert nicht"); //TODO - config
                        }
                    }else {
                        p.sendMessage("usage"); //TODO - config
                    }
                } else {
                    p.sendMessage("usage"); //TODO - config
                }
            }
        }
        return false;
    }
}