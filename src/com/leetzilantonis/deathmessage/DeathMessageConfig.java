package com.leetzilantonis.deathmessage;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class DeathMessageConfig {

	public static void initiateDeathMessageDefaults(Main plugin) {

		FileConfiguration config = plugin.getConfig();
		FileConfiguration lang = plugin.getLangConfig();

		// Base config
		config.addDefault("allowCrossWorld", false);
		config.addDefault("hearDistance", 50);

		// Set initial methods as enabled
		config.addDefault("cactusEnabled", true);
		config.addDefault("drowningEnabled", true);
		config.addDefault("fallEnabled", true);
		config.addDefault("fireEnabled", true);
		config.addDefault("lavaEnabled", true);
		config.addDefault("lightningEnabled", true);
		config.addDefault("mobEnabled", true);
		config.addDefault("poisonEnabled", true);
		config.addDefault("pvpEnabled", true);
		config.addDefault("starvationEnabled", true);
		config.addDefault("suffocationEnabled", true);
		config.addDefault("suicideEnabled", true);
		config.addDefault("tntEnabled", true);
		config.addDefault("voidEnabled", true);
		config.addDefault("defaultEnabled", true);
		config.addDefault("stat", "deaths");

		List<String> cactus = new ArrayList<String>();
		cactus.add("&c{player} &7died from a cactus. We know, that's lame.");
		cactus.add("&c{player} &7poked a cactus, but the cactus poked back.");
		cactus.add("&c{player} &7had a serious FPS &ispike &r&7after touch that cactus.");
		lang.addDefault("cactusUseDefault", false);
		lang.addDefault("cactus", cactus);

		List<String> drowning = new ArrayList<String>();
		drowning.add("&c{player} &7drowned.");
		drowning.add("&c{player} &7is swimming with the fishes.");
		drowning.add("&c{player} &7took a long walk off a short pier.");
		lang.addDefault("drowningUseDefault", false);
		lang.addDefault("drowning", drowning);

		List<String> fall = new ArrayList<String>();
		fall.add("&c{player} &7fell to his ultimate demise.");
		fall.add("&c{player} &7hit the ground too hard.");
		fall.add("&c{player} &7perished from a brutal fall.");
		fall.add("&c{player} &7succumbed to gravity.");
		fall.add("&c{player} &7finally experienced terminal velocity.");
		fall.add("&c{player} &7went skydiving, forgot the parachute.");
		fall.add("&7We'll hold a moment of silence while we laugh at your falling death, &c{player}.");
		fall.add("&7Attempting a high-wire stunt yet again, &c{player} &7slipped, and died.");
		fall.add("&7Somehow tree tops are immune to gravity. &c{player} &7is not.");
		fall.add("&7Nice going &c{player}, &7you've fallen. You're in a group that includes sand, and gravel - the losers of three.");
		fall.add("&7We're here today to mourn the loss of &c{player}&7. He is survived by his Nyan Cat and Creeper statues.");
		fall.add("&7Like everything in life, &c{player} &7chose the most average, unexciting, unadventerous death - falling. Whoopie.");
		fall.add("&7Oh man that was hard fall &c{player}&7! You ok? &c{player}&7? How many fingers dude? Um, dude? Oh sh...");
		fall.add("&c{player} &7had a whoopsie-daisy!");
		fall.add("&c{player} &7was testing gravity. Yup, still works.");
		fall.add("&7Although &c{player}'s &7body lies on the ground somewhere, the question stands. Will it blend?");
		lang.addDefault("fallUseDefault", false);
		lang.addDefault("fall", fall);

		List<String> fire = new ArrayList<String>();
		fire.add("&c{player} &7burned to death.");
		fire.add("&c{player} &7forgot how to stop, drop and roll.");
		fire.add("&c{player} &7spontaneiously combusted, or possibly walked into fire.");
		fire.add("&c{player} &7became a human torch. Not a very long-lasting one either.");
		fire.add("&7Not only did you burn up &c{player}&7, but you may have started a forest fire. Nice going.");
		fire.add("&7You are not a replacement for coal, &c{player}&7. I'm not sure that even death can teach you that lesson.");
		fire.add("&7Taking himself out of the gene pool for us, &c{player} &7burned to death. Good job!");
		lang.addDefault("fireUseDefault", false);
		lang.addDefault("fire", fire);

		List<String> lava = new ArrayList<String>();
		lava.add("&c{player} &7was killed by lava.");
		lava.add("&c{player} &7became obsidian.");
		lava.add("&c{player} &7took a bath in a lake of fire.");
		lava.add("&c{player} &7lost an entire inventory to lava. He died too, but man, loosing your stuff's a bummer!");
		lava.add("&7I told you not to dig straight down &c{player}&7. Now look what happened.");
		lava.add("&7Look &c{player}&7, I'm sorry I boiled you to death. I just wanted a friend. No one likes me. - Your Best Buddy, Lava.");
		lava.add("&7Then &c{player} &7said \"Take my picture in front of this pit of boiling, killer lava.\"");
		lang.addDefault("lavaUseDefault", false);
		lang.addDefault("lava", lava);

		List<String> lightning = new ArrayList<String>();
		lightning.add("&c{player} &7was struck with a bolt of inspiration. Wait, nevermind. Lightning.");
		lang.addDefault("lightningUseDefault", false);
		lang.addDefault("lightning", lightning);

		List<String> mob = new ArrayList<String>();
		mob.add("&c{player} &7was ravaged by &c{mob}&7.");
		mob.add("&c{player} &7died after encountering the fierce &c{mob}&7.");
		mob.add("&c{player} &7was killed by an angry &c{mob}&7.");
		mob.add("&7It was a horrible death for &c{player} &7- ravaged by a &c{mob}&7.");
		mob.add("&7Dinner time for &c{mob}&7. Cooked pork for the main course, &c{player} &7for dessert.");
		mob.add("&c{player} &7went off into the woods alone and shall never return. Until respawn.");
		mob.add("&7While hunting, &c{player} &7was unaware that a &c{mob} &7was hunting him. Rest in pieces.");
		mob.add("&7We have unconfirmed reports that &c{player} &7was attacked by an &c{mob}.");
		mob.add("&7Look &c{player}&7, I'm sorry I killed you. I just wanted a friend. No one likes me. - Your Best Buddy, &c{mob}&7.");
		mob.add("&7Something killed &c{player}&7!");
		mob.add("&7Dear &c{player}&7, good luck finding your stuff. - &c{mob}&7.");
		mob.add("&c{player} &7was ravaged by &c{mob}&7.");
		lang.addDefault("mobUseDefault", false);
		lang.addDefault("mob", mob);

		List<String> zombie = new ArrayList<String>();
		zombie.add("&7Having not seen the plethora of zombie movies, &c{player} &7was amazingly unaware of how to escape.");
		zombie.add("&7Poor &c{player} &7- that zombie only wanted a hug! That's why his arms were stretched out.");
		lang.addDefault("mob.zombieUseDefault", false);
		lang.addDefault("mob.zombie", zombie);

		List<String> creeper = new ArrayList<String>();
		creeper.add("&c{player} &7was creeper bombed.");
		creeper.add("&c{player} &7hugged a creeper.");
		creeper.add("&7Sorry you died &c{player}&7, a creeper's gonna creep!");
		creeper.add("&c{player} &7was testing a new creeper-proof suit. It didn't work.");
		creeper.add("&c{player} &7was not involved in any explosion, nor are we able to confirm the existence of the \"creeper\". Move along.");
		creeper.add("&7Due to the laws of physics, the sound of a creeper explosion only reached &c{player} &7after he died from it.");
		creeper.add("&7Hell hath no fury like a creeper scorned. We drink to thy untimely end, lord &c{player}&7.");
		creeper.add("&7I'm sorry &c{player}&7, that's the only birthday gift Creepers know how to give.");
		lang.addDefault("mob.creeperUseDefault", false);
		lang.addDefault("mob.creeper", creeper);

		// posion
		List<String> potion = new ArrayList<String>();
		potion.add("&c{player} &7just learned the true power of magic.");
		potion.add("&c{player} &7just took a potion to the face *clap* *clap*.");
		lang.addDefault("potionUseDefault", false);
		lang.addDefault("potion", potion);

		List<String> pvp = new ArrayList<String>();
		pvp.add("&c{player} &7was just murdered by &c{killer}&7, using &a{item}&7.");
		pvp.add("&c{player} &7died, by &c{killer}'s &a{item}.");
		pvp.add("&c{killer} &7killed &c{player} &7wielding &a{item}");
		pvp.add("&7You think it was &c{killer} &7who killed &c{player}&7? Nope, Chuck Testa.");
		pvp.add("&7It was a bitter end for &c{player}&7, but &c{killer} &7won victoriously.");
		pvp.add("&7Embarrassingly, &c{player} &7died of fright before &c{killer} &7could even raise his weapon.");
		pvp.add("&c{killer} &7struck the mighty blow and ended &c{player}&7.");
		pvp.add("&c{player} &7never saw &c{killer} &7coming.");
		pvp.add("&c{killer} &7delivered the fatal blow on &c{player}&7.");
		pvp.add("&c{player}'s &7inventory now belongs to &c{killer}&7.");
		pvp.add("&c{killer} &7taught &c{player} &7the true meaning of PVP.");
		pvp.add("&7In the case of &c{player} &7v. &c{killer}&7, &c{player} &7is suing on charges of voluntary manslaughter. This judge finds &c{killer} &7guilty of BEING AWESOME!");
		pvp.add("&7What is this, like death number ten for &c{player}&7? Ask &c{killer}&7.");
		lang.addDefault("pvpUseDefault", false);
		lang.addDefault("pvp", pvp);

		List<String> starvation = new ArrayList<String>();
		starvation.add("&c{player} &7starved to death.");
		starvation.add("&c{player} &7starved to death. Because food is *so* hard to find.");
		lang.addDefault("starvationUseDefault", false);
		lang.addDefault("starvation", starvation);

		List<String> suffocation = new ArrayList<String>();
		suffocation.add("&c{player} &7suffocated.");
		lang.addDefault("suffocationUseDefault", false);
		lang.addDefault("suffocation",suffocation);

		List<String> suicide = new ArrayList<String>();
		suicide.add("&c{player} &7killed himself.");
		suicide.add("&c{player} &7ended it all. Goodbye cruel world!");
		lang.addDefault("suicideUseDefault", false);
		lang.addDefault("suicide", suicide);

		List<String> tnt = new ArrayList<String>();
		tnt.add("&c{player} &7blew up.");
		tnt.add("&c{player} &7was blown to tiny bits.");
		lang.addDefault("tntUseDefault", false);
		lang.addDefault("tnt", tnt);

		List<String> thevoid = new ArrayList<String>();
		thevoid.add("&c{player} &7ceased to exist. Thanks void!");
		thevoid.add("&c{player} &7passed the event horizon.");
		lang.addDefault("voidUseDefault", false);
		lang.addDefault("void", thevoid);

		List<String> defaultmsg = new ArrayList<String>();
		defaultmsg.add("&c{player} &7possibly died - we're looking into it.");
		defaultmsg.add("&7Nothing happened. &c{player} &7is totally ok. Why are you asking?");
		lang.addDefault("default",defaultmsg);

		lang.addDefault("noItem", "their bare hands");

		// Copy defaults
		lang.options().copyDefaults(true);
		config.options().copyDefaults(true);

		// save the defaults/config
		plugin.saveConfig();
		plugin.saveLangConfig();

	}
}
