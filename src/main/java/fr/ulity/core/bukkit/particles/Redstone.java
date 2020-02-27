package fr.ulity.core.bukkit.particles;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import static fr.ulity.core.bukkit.particles.utils.MathUtils.randomRange;

public class Redstone {

    public static void run(Player player) {
        run(player.getLocation());
    }

    public static void run(Location loc) {
        for (double height = 0.0; height < 1.0; height += 0.8) {
            loc.getWorld().playEffect(loc.clone().add(randomRange(-1.0f, 1.0f), height, randomRange(-1.0f, 1.0f)), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
            loc.getWorld().playEffect(loc.clone().add(randomRange(1.0f, -1.0f), height, randomRange(-1.0f, 1.0f)), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
        }
    }
}
