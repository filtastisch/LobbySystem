package de.filtastisch.lobbysystem.essentialmodule.cmd;

import de.filtastisch.lobbysystem.utils.BuildManager;
import org.bukkit.command.*;
import org.bukkit.entity.*;

public class BuildCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (args.length == 0){
                BuildManager.toggleBuildMode(p);
                String buildMSG = BuildManager.canPlayerBuild(p) ? "Build an" : "Build aus"; //TODO - config
                p.sendMessage(buildMSG);
            }
        }
        return false;
    }
}