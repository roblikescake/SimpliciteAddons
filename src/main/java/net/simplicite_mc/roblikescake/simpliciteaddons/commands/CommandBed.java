package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBed implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(MessageManager.getConsoleOnlyMessage());
            return false;
        }

        Player player = (Player) sender;

        if (!(args.length == 0)) {
            player.sendMessage(MessageManager.getCommandBedUsageMessage());
            return true;
        }

        Location locationBed = player.getBedSpawnLocation();

        if (locationBed == null) {
            player.sendMessage(MessageManager.getBedLocationNullMessage());
            return true;
        }

        player.teleport(locationBed);
        return true;
    }
}
