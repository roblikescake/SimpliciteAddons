package net.simplicite_mc.roblikescake.simpliciteaddons;

import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.BloodEffectListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.HeadsListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.MobCatcherListener;
import net.simplicite_mc.roblikescake.simpliciteaddons.listeners.PlayerListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpliciteAddons extends JavaPlugin{

    @Override
    public void onEnable() {
        PluginManager pm = getServer().getPluginManager();

        final BloodEffectListener bloodEffectListener = new BloodEffectListener(this);
        pm.registerEvents(bloodEffectListener, this);

        final HeadsListener headsListener = new HeadsListener(this);
        pm.registerEvents(headsListener, this);

        final MobCatcherListener mobCatcherListener = new MobCatcherListener(this);
        pm.registerEvents(mobCatcherListener, this);

        final PlayerListener playerListener = new PlayerListener(this);
        pm.registerEvents(playerListener, this);

        this.getServer().addRecipe(mobCatcherListener.getAnimalCatcherRecipe());
    }

    @Override
    public void onDisable() {
        getServer().clearRecipes();
    }
}