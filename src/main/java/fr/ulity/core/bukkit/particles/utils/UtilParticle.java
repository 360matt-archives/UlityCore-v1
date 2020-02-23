package fr.ulity.core.bukkit.particles.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

// import org.bukkit.material.MaterialData;
import org.bukkit.util.Vector;


@SuppressWarnings("deprecation")
public class UtilParticle {
  public static void sendParticle(Location location, Particle particle, int n, Vector vector, float n2) { 
	  location.getWorld().spawnParticle(particle, location, n, vector.getX(), vector.getY(), vector.getZ(), n2); 
  }
  
  public static void sendParticle(Location location, Particle particle, int n, Vector vector, float n2, Player player) { 
	  player.spawnParticle(particle, location, n, vector.getX(), vector.getY(), vector.getZ(), n2); 
  }

  /*
  public static void sendParticle(Location location, Particle particle, int n, Vector vector, MaterialData materialData) {
	  location.getWorld().spawnParticle(particle, location, n, vector.getX(), vector.getY(), vector.getZ(), 1.0D, materialData);
  }
  */
  
  public static void sendParticle(Location location, Particle particle, Vector vector, ItemStack itemStack) { 
	  location.getWorld().spawnParticle(particle, location, 0, vector.getX(), vector.getY(), vector.getZ(), 0.0D, itemStack); 
  }
}