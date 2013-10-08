package net.simplicite_mc.roblikescake.simpliciteaddons;

import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpliciteAddons extends JavaPlugin{

    @Override
    public void onEnable() {
        PlayerListener playerListener = new PlayerListener(this);
        getServer().getPluginManager().registerEvents(playerListener, this);
        this.getServer().addRecipe(playerListener.getAnimalCatcherRecipe());
    }

    @Override
    public void onDisable() {
        getServer().clearRecipes();
    }
}