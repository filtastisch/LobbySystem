package de.filtastisch.lobbysystem.guis.cmd;

import de.filtastisch.lobbysystem.guis.types.NavigatorGUI;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class NavigatorCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0){
                new NavigatorGUI().openGUI(p);
            }
        }
        return false;
    }
}