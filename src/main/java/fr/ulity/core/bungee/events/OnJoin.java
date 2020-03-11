package fr.ulity.core.bungee.events;

import fr.ulity.core.api.Ban;
import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class OnJoin implements Listener {

    @EventHandler
    public void onPostLogin(PostLoginEvent e) {
        if (Ban.isBanned(e.getPlayer().getName())) {
            e.getPlayer().disconnect("tu es banni");
        }
    }

}
