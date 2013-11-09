package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSetSpawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (!(command.getName().equalsIgnoreCase("setspawn"))) {
            return false;
        }

        Player player = (Player) sender;

        if (!(player.isOp())) {
            return false;
        }

        int locationPlayerX = (int) player.getLocation().getX();
        int locationPlayerY = (int) player.getLocation().getY();
        int locationPlayerZ = (int) player.getLocation().getZ();

        Bukkit.getWorlds().get(0).setSpawnLocation(locationPlayerX, locationPlayerY, locationPlayerZ);
        return true;
    }
}
