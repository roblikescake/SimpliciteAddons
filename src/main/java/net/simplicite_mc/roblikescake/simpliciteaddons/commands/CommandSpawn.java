package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(command.getName().equalsIgnoreCase("spawn"))) {
            return false;
        }

        Player player = (Player) sender;
        Location locationSpawn = Bukkit.getWorlds().get(0).getSpawnLocation();

        player.teleport(locationSpawn);
        return true;
    }
}
