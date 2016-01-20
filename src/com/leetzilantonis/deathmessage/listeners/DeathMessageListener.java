package com.leetzilantonis.deathmessage.listeners;

import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import com.leetzilantonis.deathmessage.DeathUtils;
import com.leetzilantonis.deathmessage.Main;

import mkremins.fanciful.FancyMessage;

public class DeathMessageListener implements Listener{

	Main plugin;

	public DeathMessageListener(Main instance) {

		plugin = instance;

	}

	@EventHandler
	public void onPlayerKill(PlayerDeathEvent e) {

		Entity killer = e.getEntity().getKiller();

		if (e.getEntity() instanceof Player) {

			e.setDeathMessage(null);
			Player p = e.getEntity();
			Random rand = new Random();

			if (killer instanceof Player) {

				plugin.addDeath(e.getEntity());
				plugin.addKill((Player)killer);

				ItemStack it = ((Player) killer).getItemInHand();
				String itemName;

				if (it == null || it.getType() == Material.AIR) {
					itemName = plugin.getLangConfig().getString("noItem");
				} else {
					itemName = it.getItemMeta().getDisplayName();
					if (itemName == null) {
						itemName = it.getType().toString().replace("_", " ").toLowerCase();
					}
				}

				String cause = DeathUtils.getCauseNiceName(p);
				String attacker = DeathUtils.getAttackerName(p);

				if (plugin.getConfig().getBoolean(cause.toLowerCase() + "Enabled")) {

					List<String> messages = plugin.getLangConfig().getStringList(cause.toLowerCase());

					String finalMsg = "";
					if (messages != null && !messages.isEmpty()) {
						if (cause == "mob"){
							List<String> mob_msg = plugin.getLangConfig().getStringList("mob." + attacker.toLowerCase());
							if (mob_msg != null && !mob_msg.isEmpty()) {
								messages = mob_msg;
							}
						}
						finalMsg = messages.get(rand.nextInt(messages.size()));  
					} else {
						messages = plugin.getLangConfig().getStringList("default");
						finalMsg = messages.get(rand.nextInt(messages.size()));  
					}

					if(attacker == "pvpwolf"){
						attacker = DeathUtils.getTameWolfOwner(e) + "'s wolf";
					}

					finalMsg = finalMsg.replace("{player}", p.getName());
					finalMsg = finalMsg.replace("{killer}", attacker);
					finalMsg = finalMsg.replace("{itemName}", itemName);
					finalMsg = finalMsg.replace("{item}", "{item}" + ChatColor.RESET.toString());

					String s = plugin.getConfig().getString("stat");
					if (s.equalsIgnoreCase("deaths")) {
						finalMsg = finalMsg.replace("{stat}", plugin.getDeaths(p) + "");
						finalMsg = finalMsg.replace("{killerStat}", plugin.getDeaths((Player)killer) + "");
					} else if (s.equalsIgnoreCase("kills")) {
						finalMsg = finalMsg.replace("{stat}", plugin.getKills(p) + "");
						finalMsg = finalMsg.replace("{killerStat}", plugin.getKills((Player)killer) + "");
					} else {
						finalMsg = finalMsg.replace("{stat}", "");
						finalMsg = finalMsg.replace("{killerStat}", "");
					}

					finalMsg = ChatColor.translateAlternateColorCodes('&', finalMsg);
					System.out.println(finalMsg);

					FancyMessage fm = new FancyMessage();

					String[] split = finalMsg.split("\\{item\\}");
					for (int i = 0; i < split.length; i++) {
						if (i == split.length - 1) {
							fm.then(split[i]);
						} else {
							String substring = split[i].length() > 2 ? split[i].substring(split[i].length() - 2) : split[i];
							itemName = substring + itemName;
							fm.then(split[i])
							.then(itemName)
							.itemTooltip(it);
						}
					}

					if(plugin.getConfig().getBoolean("allowCrossWorld") ){
						for (Player player : plugin.getServer().getOnlinePlayers()) {
							if(player.hasPermission("deathmessages.hear")){
								fm.send(player);
							}
						}
					} else {
						for (Player player : p.getWorld().getPlayers()) {
							if (player.hasPermission("deathmessages.hear")) {
								double dist = player.getLocation().distance(p.getLocation());
								if (plugin.getConfig().getDouble("hearDistance") == 0 || dist <= plugin.getConfig().getDouble("hearDistance")) {
									fm.send(player);
								}
							}
						}
					}
				}
			} else {

				String cause = DeathUtils.getCauseNiceName(p);
				String attacker = DeathUtils.getAttackerName(p);

				if(plugin.getConfig().getBoolean(cause.toLowerCase() + "Enabled")){

					List<String> messages;
					if (plugin.getLangConfig().getBoolean(cause.toLowerCase() + "UseDefault")) {
						messages = plugin.getLangConfig().getStringList("default");
					} else {
						messages = plugin.getLangConfig().getStringList(cause.toLowerCase());
					}

					String finalMsg = "";
					if(messages != null && !messages.isEmpty()) {

						if(cause == "mob") {
							List<String> mob_msg = plugin.getLangConfig().getStringList("mob."+attacker.toLowerCase());
							if(mob_msg != null && !mob_msg.isEmpty()){
								messages = mob_msg;
							}
						}
						finalMsg = (String) messages.get(rand.nextInt(messages.size()));  
					} else {
						messages = plugin.getLangConfig().getStringList("default");
						finalMsg = (String) messages.get(rand.nextInt(messages.size()));  
					}

					if(attacker.equalsIgnoreCase("pvpwolf")){
						attacker = DeathUtils.getTameWolfOwner(e) + "'s wolf";
					}

					finalMsg = finalMsg.replace("{player}", p.getName());
					finalMsg = finalMsg.replace("{killer}", attacker);
					finalMsg = finalMsg.replace("{mob}", attacker);

					String s = plugin.getConfig().getString("stat");

					if (s.equalsIgnoreCase("deaths")) {
						finalMsg = finalMsg.replace("{stat}", plugin.getDeaths(p) + "");
					} else if (s.equalsIgnoreCase("kills")) {
						finalMsg = finalMsg.replace("{stat}", plugin.getKills(p) + "");
					} else {
						finalMsg = finalMsg.replace("{stat}", "");
					}

					finalMsg = ChatColor.translateAlternateColorCodes('&', finalMsg);

					if (plugin.getConfig().getBoolean("allowCrossWorld")) {
						for (Player player : plugin.getServer().getOnlinePlayers()) {
							if (player.hasPermission("deathmessages.hear")) {
								player.sendMessage(finalMsg);
							}
						}
					} else {
						for (Player player : p.getWorld().getPlayers()) {
							if (player.hasPermission("deathmessages.hear")){
								double dist = player.getLocation().distance(p.getLocation());
								if (plugin.getConfig().getDouble("hearDistance") == 0 || dist <= plugin.getConfig().getDouble("hearDistance")) {
									player.sendMessage(finalMsg);
								}
							}
						}
					}
				}
			}
		}
	}
}
