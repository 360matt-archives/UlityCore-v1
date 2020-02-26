package fr.ulity.core.bukkit.events;

import fr.ulity.core.bukkit.MainBukkit;
import fr.ulity.core.bukkit.particles.FrostFlame;
import fr.ulity.core.bukkit.particles.Wave;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TP_event implements Listener {

    @EventHandler
    public void onTeleport(PlayerTeleportEvent e){
        MainBukkit.temp.set("player." + e.getPlayer().getName() + ".lastPosition", e.getPlayer().getLocation());

        if (MainBukkit.config.getBoolean("teleport.animation")){
            if (!MainBukkit.temp.getBoolean("player." + e.getPlayer().getName() + ".vanish")){
                FrostFlame.run(e.getTo());
            }
        }


    }
}
