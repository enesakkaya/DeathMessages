package com.leetzilantonis.deathmessage.listeners;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.leetzilantonis.deathmessage.Main;

public class PlayerJoinListener implements Listener {

	Main plugin;
	
	public PlayerJoinListener(Main instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		
		String uuid = e.getPlayer().getUniqueId().toString();
		FileConfiguration c = plugin.sm.getStorageConfig();
		
		if (!c.contains(uuid + ".deaths")) {
			
			plugin.setDeaths(e.getPlayer(), 0);
			
		}
		
		if (!c.contains(uuid + ".kills")) {
			
			plugin.setKills(e.getPlayer(), 0);
			
		}
		
	}
	
}
