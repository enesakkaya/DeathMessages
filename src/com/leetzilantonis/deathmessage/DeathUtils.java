package com.leetzilantonis.deathmessage;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.entity.Wolf;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.projectiles.ProjectileSource;

public class DeathUtils {

	/**
	 * 
	 * This class was edited by me, not created
	 * by me, any code inside here belongs to an
	 * alternate API that had too many things to
	 * include which would have increased the file 
	 * size for no reason.
	 * 
	 */
	
	public static String getCauseNiceName(Entity entity) {

		EntityDamageEvent e = entity.getLastDamageCause();

		if (e == null)
			return "unknown";

		DamageCause damageCause = e.getCause();
		Entity killer = null;

		if (entity.getLastDamageCause() instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent entityDamageByEntityEvent = (EntityDamageByEntityEvent) entity.getLastDamageCause();
			if (entityDamageByEntityEvent.getDamager() instanceof Arrow) {
				Arrow arrow = (Arrow) entityDamageByEntityEvent.getDamager();
				ProjectileSource source = arrow.getShooter();
				if (source instanceof Player) {
					killer = (Player)source;
				}
			} else {
				killer = entityDamageByEntityEvent.getDamager();
			}
		}

		if (entity instanceof Player){
			Player player = (Player) entity;
			if (killer instanceof Player) {
				if (((Player)killer).getName().equals( player.getName())) {
					return "suicide";
				}
				if ((damageCause.equals(DamageCause.ENTITY_ATTACK) || damageCause.equals(DamageCause.PROJECTILE))) {
					return "pvp";
				}
			}
		}

		if (damageCause.equals(DamageCause.ENTITY_ATTACK)) {
			return "mob";
		} else if (damageCause.equals(DamageCause.PROJECTILE)) {
			return "skeleton";
		} else if (damageCause.equals(DamageCause.ENTITY_EXPLOSION)) {
			return "creeper";
		} else if (damageCause.equals(DamageCause.CONTACT)) {
			return "cactus";
		} else if (damageCause.equals(DamageCause.BLOCK_EXPLOSION)) {
			return "tnt";
		} else if (damageCause.equals(DamageCause.FIRE) || damageCause.equals(DamageCause.FIRE_TICK)) {
			return "fire";
		} else if (damageCause.equals(DamageCause.MAGIC)) {
			return "potion";
		}
		return damageCause.name().toLowerCase();
	}

	@SuppressWarnings("deprecation")
	public static String getAttackerName(Entity victim){

		if (victim instanceof Player) {
			Player killer = ((Player)victim).getKiller();
			if (killer != null) {
				return killer.getName();
			}
		}

		String cause = getCauseNiceName(victim);

		if (cause == "mob") {

			Entity killer = ((EntityDamageByEntityEvent)victim.getLastDamageCause()).getDamager();

			if (killer instanceof Player) {
				return ((Player)killer).getName(); 
			} else if (killer instanceof Skeleton) {
				Skeleton sk = (Skeleton) killer;
				if (sk.getSkeletonType() == SkeletonType.WITHER){
					return "sither_skeleton";
				} else {
					return "skeleton";
				}
			} else if (killer instanceof Arrow) {
				return "skeleton";
			} else if (killer instanceof Wolf) {
				Wolf w = (Wolf) killer;
				if (w.isTamed()) {
					if (w.getOwner() instanceof Player || w.getOwner() instanceof OfflinePlayer) {
						return "pvpwolf";
					} else {
						return "wolf";
					}
				} else {
					return "wolf";
				}
			} else if (killer instanceof Guardian) {
				Guardian g = (Guardian)killer;
				if (g.isElder()) {
					return "elder_guardian";
				} else {
					return "guardian";
				}
			} else {
				return killer.getType().getName().toLowerCase();
			}
		}
		return cause;
	}

	public static String getTameWolfOwner(EntityDeathEvent event){
		String owner = "";
		Entity killer = ((EntityDamageByEntityEvent)event.getEntity().getLastDamageCause()).getDamager();
		if (killer instanceof Wolf) {
			Wolf wolf = (Wolf)killer;
			if (wolf.isTamed()) {
				if (wolf.getOwner() instanceof Player) {
					owner = ((Player)wolf.getOwner()).getName();
				}
				if (wolf.getOwner() instanceof OfflinePlayer) {
					owner = ((OfflinePlayer)wolf.getOwner()).getName();
				}
			}
		}
		return owner;
	}

}
