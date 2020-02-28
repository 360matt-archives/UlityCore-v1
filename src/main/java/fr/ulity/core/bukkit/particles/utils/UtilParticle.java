package fr.ulity.core.bukkit.particles.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

public class UtilParticle {
    public static void sendParticle(Location location, Particle particle, int n, Vector vector, float n2) {
        location.getWorld().spawnParticle(particle, location, n, vector.getX(), vector.getY(), vector.getZ(), n2);
    }
}