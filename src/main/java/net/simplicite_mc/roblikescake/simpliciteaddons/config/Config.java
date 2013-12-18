package net.simplicite_mc.roblikescake.simpliciteaddons.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.InputStream;

public class Config extends YamlConfiguration {
	public static int getDBPort;
	public static String getDBUser, getDBPassword, getDBHost, getDBDatabase;
	public Plugin plugin;
	private File file;
	protected static YamlConfiguration configFile;

	public Config(Plugin plugin) {
		this.file = new File(plugin.getDataFolder(), "config.yml");
		this.plugin = plugin;
		if (exists()) {
			load();
		} else {
			loadDefaults(file.getName());
			load();
		}

		configFile = (YamlConfiguration) plugin.getConfig();
	}

	public void loadConfigOptions() {
		plugin.getLogger().info("Loading SimpliciteAddons Config file");

		getDBPort = configFile.getInt("database.port");
		getDBHost = configFile.getString("database.host");
		getDBDatabase = configFile.getString("database.database");
		getDBUser = configFile.getString("database.user");
		getDBPassword = configFile.getString("database.password");

		saveDefaults();
	}

	public static void reloadConfigOptions(YamlConfiguration newConfig) {
		configFile = newConfig;

		getDBPort = configFile.getInt("database.port");
		getDBHost = configFile.getString("database.host");
		getDBDatabase = configFile.getString("database.database");
		getDBUser = configFile.getString("database.user");
		getDBPassword = configFile.getString("database.password");
	}

	public final boolean load() {
		try {
			load(file);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public final void reload() {
		load();
		loadDefaults(file.getName());
	}

	public final boolean save() {
		try {
			save(file);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public final boolean exists() {
		return file.exists();
	}

	public final void loadDefaults(String fileName) {
		InputStream stream = plugin.getResource(fileName);

		if (stream == null) {
			return;
		}

		setDefaults(YamlConfiguration.loadConfiguration(stream));
	}

	public final boolean saveDefaults() {
		options().copyDefaults(true);
		boolean success = save();
		options().copyDefaults(false);
		return success;
	}


}
