package net.simplicite_mc.roblikescake.simpliciteaddons;

import net.simplicite_mc.roblikescake.simpliciteaddons.commands.CommandBed;
import net.simplicite_mc.roblikescake.simpliciteaddons.commands.CommandSetSpawn;
import net.simplicite_mc.roblikescake.simpliciteaddons.commands.CommandSpawn;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.EntityListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.PlayerListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpliciteAddons extends JavaPlugin {
    public static SimpliciteAddons p;

    @Override
    public void onEnable() {
        p = this;

        registerListeners();
        registerManagers();
        registerCommands();
    }

    @Override
    public void onDisable() {
        clearCustomRecipes();
    }

    public void registerListeners() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new EntityListener(), this);
    }

    public void registerManagers() {
        new ItemManager();
        new HeadManager();
    }

    public void clearCustomRecipes() {
        getServer().clearRecipes();
    }

    public void registerCommands() {
        getCommand("bed").setExecutor(new CommandBed());
        getCommand("spawn").setExecutor(new CommandSpawn());
        getCommand("setspawn").setExecutor(new CommandSetSpawn());
    }
}
