package com.leetzilantonis.deathmessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class StorageManager {

	Main plugin;
	
	public StorageManager(Main instance) {
		plugin = instance;
	}
	
	private FileConfiguration storageConfig = null;
	private File storageConfigFile = null;
	
	public void reloadStorageConfig() {
		if (storageConfigFile == null) {
			storageConfigFile = new File(plugin.getDataFolder(), "storage.yml");
		}
		storageConfig = YamlConfiguration.loadConfiguration(storageConfigFile);

		// Look for defaults in the jar
		Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(plugin.getResource("storage.yml"), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			storageConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getStorageConfig() {
		if (storageConfig == null) {
			reloadStorageConfig();
		}
		return storageConfig;
	}

	public void saveStorageConfig() {
		if (storageConfig == null || storageConfigFile == null) {
			return;
		}
		try {
			getStorageConfig().save(storageConfigFile);
		} catch (IOException ex) {
			plugin.getLogger().log(Level.SEVERE, "Could not save config to " + storageConfigFile, ex);
		}
	}

	public void saveDefaultStorageConfig() {
		if (storageConfigFile == null) {
			storageConfigFile = new File(plugin.getDataFolder(), "storage.yml");
		}
		if (!storageConfigFile.exists()) {            
			plugin.saveResource("storage.yml", false);
		}
	}
	// End Custom Config

	
}
