package fr.ulity.core.bukkit.particles.utils;

import java.util.Random;

public class MathUtils {

    public static float randomRange(float min, float max) {
        return min + (float) Math.random() * (max - min);
    }

    public static int randomRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public static double randomRange(double min, double max) {
        return (Math.random() < 0.5) ? ((1.0 - Math.random()) * (max - min) + min) : (Math.random() * (max - min) + min);
    }


}