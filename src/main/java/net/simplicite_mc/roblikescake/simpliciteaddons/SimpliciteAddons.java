package net.simplicite_mc.roblikescake.simpliciteaddons;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.EntityListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.PlayerListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.HeadManager;
import net.simplicite_mc.roblikescake.simpliciteaddons.utilities.ItemManager;

public class SimpliciteAddons extends JavaPlugin {
    public static SimpliciteAddons p;

    @Override
    public void onEnable() {
        p = this;

        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new PlayerListener(), this);
        pm.registerEvents(new EntityListener(), this);

        new ItemManager();
        new HeadManager();
    }

    @Override
    public void onDisable() {
        getServer().clearRecipes();
    }
}