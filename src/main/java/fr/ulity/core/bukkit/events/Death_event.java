package fr.ulity.core.bukkit.events;

import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Death_event implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        MainBukkit.temp.set("player." + e.getEntity().getName() + ".lastPosition", e.getEntity().getLocation());
    }


}
