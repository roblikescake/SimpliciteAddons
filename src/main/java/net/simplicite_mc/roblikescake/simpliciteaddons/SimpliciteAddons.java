package net.simplicite_mc.roblikescake.simpliciteaddons;

// Import SimpliciteAddons Classes

import net.simplicite_mc.roblikescake.simpliciteaddons.api.PlayerUUID.IdentifierAPI;
import net.simplicite_mc.roblikescake.simpliciteaddons.commands.*;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.BlockListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.EntityListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.PlayerListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.ServerListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;

// Import Bukkit Classes
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpliciteAddons extends JavaPlugin {
	public static SimpliciteAddons p;
	public static IdentifierAPI identifier = new IdentifierAPI();

	// Apply methods when plugin is enabled.
	@Override
	public void onEnable() {
		p = this;

		registerListeners();
		registerManagers();
		registerCommands();
	}

	// Apply methods when plugin is disabled.
	@Override
	public void onDisable() {
		clearCustomRecipes();
	}

	// Register listeners used in the plugin.
	public void registerListeners() {
		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayerListener(), this);
		pm.registerEvents(new EntityListener(), this);
		pm.registerEvents(new ServerListener(), this);
		pm.registerEvents(new BlockListener(), this);
	}

	// Register managers used in the plugin.
	public void registerManagers() {
		new ItemManager();
		new HeadManager();
	}

	// Clear custom recipes made in the plugin.
	public void clearCustomRecipes() {
		getServer().clearRecipes();
	}

	// Register commands used in the plugin, and set their executor classes.
	public void registerCommands() {
		getCommand("bed").setExecutor(new CommandBed());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getCommand("setspawn").setExecutor(new CommandSetSpawn());
		getCommand("motd").setExecutor(new CommandPlayerMOTD());
		getCommand("getsponge").setExecutor(new CommandGetSponge());
	}
}
