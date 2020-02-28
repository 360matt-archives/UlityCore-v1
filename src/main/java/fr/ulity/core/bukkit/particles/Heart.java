package fr.ulity.core.bukkit.particles;

import fr.ulity.core.bukkit.particles.utils.MathUtils;
import fr.ulity.core.bukkit.particles.utils.UtilParticle;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Heart {
    // code by: https://www.spigotmc.org/members/julien0312.80619/
    // je suis pas un batard je fournie mes sources
    // toutes les particles viennent de cette personne

    public static void run(Player player) {
        Location loc = player.getLocation();
        for (double height = 0.0; height < 1.0; height += 0.2)
            UtilParticle.sendParticle(loc.clone().add((double) MathUtils.randomRange(-1.0f, 1.0f), height, (double) MathUtils.randomRange(-1.0f, 1.0f)), Particle.HEART, 1, new Vector(0, 0, 0), 0.0F);
    }
}
