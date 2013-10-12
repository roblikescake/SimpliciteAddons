package net.simplicite_mc.roblikescake.simpliciteaddons.listeners;

import net.simplicite_mc.roblikescake.simpliciteaddons.SimpliciteAddons;
import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {
    public SimpliciteAddons pl;
    public PlayerListener(SimpliciteAddons i) {
        pl = i;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(ChatColor.BLACK + "[" + ChatColor.GREEN + "+" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.GREEN + " Online" + ChatColor.GRAY + "!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        e.setQuitMessage(ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerKick(PlayerKickEvent e) {
        Player p = e.getPlayer();
        e.setLeaveMessage(ChatColor.BLACK + "[" + ChatColor.RED + "-" + ChatColor.BLACK + "] " + ChatColor.AQUA + p.getName() + ChatColor.GRAY + " is now" + ChatColor.RED + " Offline" + ChatColor.GRAY + "!");
    }
}