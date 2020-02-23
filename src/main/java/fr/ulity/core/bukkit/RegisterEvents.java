package fr.ulity.core.bukkit;

import fr.ulity.core.bukkit.events.LastPosition;

public class RegisterEvents {
    public static void exec(){
        MainBukkit.server.getPluginManager().registerEvents(new LastPosition(), MainBukkit.plugin);
    }

}
