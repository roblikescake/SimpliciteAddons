package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBed implements CommandExecutor{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(command.getName().equalsIgnoreCase("bed"))) {
            return false;
        }

        Player player = (Player) sender;
        Location locationBed = player.getBedSpawnLocation();

        if (locationBed == null) {
            return false;
        }

        player.teleport(locationBed);
        return true;
    }

}
