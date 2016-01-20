package com.leetzilantonis.deathmessage;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.leetzilantonis.deathmessage.commands.DMReload;
import com.leetzilantonis.deathmessage.listeners.DeathMessageListener;

public class Main extends JavaPlugin {

	private FileConfiguration langConfig = null;
	private File langConfigFile = null;

	public double hearDistance;

	public StorageManager sm;

	@Override
	public void onEnable() {

		sm = new StorageManager(this);

		DeathMessageConfig.initiateDeathMessageDefaults(this);

		hearDistance = this.getConfig().getDouble("hearDistance");

		this.getServer().getPluginManager().registerEvents(new DeathMessageListener(this), this);
		this.getCommand("dmreload").setExecutor(new DMReload(this));
		
	}

	@Override
	public void onDisable() {

		this.saveConfig();
		this.sm.saveStorageConfig();
		this.saveLangConfig();

	}

	public void reloadLangConfig() {
		if (langConfigFile == null) {
			langConfigFile = new File(getDataFolder(), "lang.yml");
		}
		langConfig = YamlConfiguration.loadConfiguration(langConfigFile);

		// Look for defaults in the jar
		Reader defConfigStream = null;
		try {
			defConfigStream = new InputStreamReader(this.getResource("lang.yml"), "UTF8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (defConfigStream != null) {
			YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			langConfig.setDefaults(defConfig);
		}
	}

	public FileConfiguration getLangConfig() {
		if (langConfig == null) {
			reloadLangConfig();
		}
		return langConfig;
	}

	public void saveLangConfig() {
		if (langConfig == null || langConfigFile == null) {
			return;
		}
		try {
			getLangConfig().save(langConfigFile);
		} catch (IOException ex) {
			getLogger().log(Level.SEVERE, "Could not save config to " + langConfigFile, ex);
		}
	}

	public void saveDefaultLangConfig() {
		if (langConfigFile == null) {
			langConfigFile = new File(getDataFolder(), "lang.yml");
		}
		if (!langConfigFile.exists()) {            
			this.saveResource("lang.yml", false);
		}
	}
	// End Custom Config

	public void addDeath(Player p) {

		int deaths = this.sm.getStorageConfig().getInt(p.getUniqueId().toString() + ".deaths", 0) + 1;
		this.sm.getStorageConfig().set(p.getUniqueId().toString() + ".deaths", deaths);

	}

	public void addKill(Player p) {

		int kills = this.sm.getStorageConfig().getInt(p.getUniqueId().toString() + ".kills", 0) + 1;
		this.sm.getStorageConfig().set(p.getUniqueId().toString() + ".kills", kills);

	}

	public void setDeaths(Player p, int deaths) {

		this.sm.getStorageConfig().set(p.getUniqueId().toString() + ".deaths", deaths);

	}

	public void setKills(Player p, int kills) {

		this.sm.getStorageConfig().set(p.getUniqueId().toString() + ".kills", kills);

	}

	public int getDeaths(Player p) {

		return this.sm.getStorageConfig().getInt(p.getUniqueId().toString() + ".deaths", 0);

	}

	public int getKills(Player p) {

		return this.sm.getStorageConfig().getInt(p.getUniqueId().toString() + ".kills", 0);

	}

}
