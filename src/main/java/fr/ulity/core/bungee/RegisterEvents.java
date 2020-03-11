package fr.ulity.core.bungee;

import fr.ulity.core.bungee.events.OnJoin;

public class RegisterEvents {
    public static void run() {
        MainBungee.proxy.getPluginManager().registerListener(MainBungee.plugin, new OnJoin());
    }
}
