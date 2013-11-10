package net.simplicite_mc.roblikescake.simpliciteaddons.commands;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.MessageManager;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpawn implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            System.out.println(MessageManager.getConsoleOnlyMessage());
            return false;
        }

        if (!(command.getName().equalsIgnoreCase("spawn"))) {
            return false;
        }

        Player player = (Player) sender;

        if (!(args.length == 0)) {
            player.sendMessage(MessageManager.getCommandSpawnUsageMessage());
            return true;
        }

        Location locationSpawn = SimpliciteAddons.p.getServer().getWorlds().get(0).getSpawnLocation();

        player.teleport(locationSpawn);
        return true;
    }
}
