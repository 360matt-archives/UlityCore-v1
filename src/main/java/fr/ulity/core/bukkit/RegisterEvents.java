package fr.ulity.core.bukkit;

import fr.ulity.core.bukkit.events.Chat_event;
import fr.ulity.core.bukkit.events.Death_event;
import fr.ulity.core.bukkit.events.TP_event;

public class RegisterEvents {
    public static void exec(){
        MainBukkit.server.getPluginManager().registerEvents(new Death_event(), MainBukkit.plugin);
        MainBukkit.server.getPluginManager().registerEvents(new TP_event(), MainBukkit.plugin);
        MainBukkit.server.getPluginManager().registerEvents(new Chat_event(), MainBukkit.plugin);
    }

}
