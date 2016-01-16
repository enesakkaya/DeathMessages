package com.leetzilantonis.deathmessage.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.leetzilantonis.deathmessage.Main;

public class DMReload implements CommandExecutor {

	Main plugin;

	public DMReload(Main instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (sender.hasPermission("deathmessages.reload")) {

			plugin.reloadConfig();
			plugin.reloadLangConfig();

			plugin.hearDistance = plugin.getConfig().getDouble("hearDistance");
			return true;

		} else {

			sender.sendMessage(ChatColor.RED + "You do not have permission to do that!");
			return true;

		}
	}
}
