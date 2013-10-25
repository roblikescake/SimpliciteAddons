package net.simplicite_mc.roblikescake.simpliciteaddons;

import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.*;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpliciteAddons extends JavaPlugin{

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        pm.registerEvents(new BloodEffectListener(this), this);
        pm.registerEvents(new HeadsListener(this), this);
        pm.registerEvents(new MobCatcherListener(this), this);
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new XPBankListener(this), this);

        this.getServer().addRecipe(MobCatcherListener.getAnimalCatcherRecipe());
    }

    @Override
    public void onDisable() {
        getServer().clearRecipes();
    }
}