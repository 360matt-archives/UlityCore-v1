package fr.ulity.core.bukkit.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class Chat_event implements Listener {

    @EventHandler
    public void color(AsyncPlayerChatEvent e) {
        if (e.getPlayer().hasPermission("ulity.chat.color"))
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
    }

}
