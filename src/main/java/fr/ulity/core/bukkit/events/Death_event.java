package fr.ulity.core.bukkit.events;

import fr.ulity.core.bukkit.MainBukkit;
import fr.ulity.core.bukkit.particles.Mousse;
import fr.ulity.core.bukkit.particles.Satan;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death_event implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        MainBukkit.temp.set("player." + e.getEntity().getName() + ".lastPosition", e.getEntity().getLocation());
    }


    @EventHandler
    public void onEntityKilled(EntityDeathEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            if (MainBukkit.config.getBoolean("animations.death.player"))
                Satan.run((Player) e.getEntity());
        } else {
            if (MainBukkit.config.getBoolean("animations.death.mobs"))
                Mousse.run(e.getEntity().getLocation());
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent e) {
        if (e.getEntityType() == EntityType.PLAYER) {
            if (MainBukkit.config.getBoolean("animations.death.player"))
                Satan.run((Player) e.getEntity());
        } else {
            if (MainBukkit.config.getBoolean("animations.death.mobs"))
                Mousse.run(e.getEntity().getLocation());
        }
    }

}
