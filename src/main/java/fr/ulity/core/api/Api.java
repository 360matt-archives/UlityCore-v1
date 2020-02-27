package fr.ulity.core.api;

import java.io.File;

public class Api {

    public static String prefix;
    public static String type;
    public static String name;

    public static Config config;
    public static Lang lang;
    public static Temp temp;
    public static Data data;

    private static Object pluginObj;

    public static void initialize(net.md_5.bungee.api.plugin.Plugin plugin) {
        pluginObj = plugin;
        runny();
    }

    public static void initialize(org.bukkit.plugin.Plugin plugin) {
        pluginObj = plugin;
        runny();
    }

    private static void runny() {
        File folder;

        try {
            Class.forName("net.md_5.bungee.api.plugin.Plugin");
            folder = ((net.md_5.bungee.api.plugin.Plugin) pluginObj).getDataFolder();
            type = "bungeecord";
            name = ((net.md_5.bungee.api.plugin.Plugin) pluginObj).getDescription().getName();
        } catch (ClassNotFoundException e) {
            folder = ((org.bukkit.plugin.Plugin) pluginObj).getDataFolder();
            type = "bukkit";
            name = ((org.bukkit.plugin.Plugin) pluginObj).getName();
        }

        if (!folder.exists()) {
            folder.mkdir();
        }

        prefix = folder.toString().replaceAll("\\\\", "/");

        File file = new File(prefix + "/temps/", "data.json");

        if (file.exists())
            file.delete();

        enddy();
    }

    private static void enddy() {
        config = new Config(); // load firstly Config
        lang = new Lang(); // then, Lang
        temp = new Temp(); // and Temp
        data = new Data();
        /*
         order of Config and Lang is very important
        */
    }


}
