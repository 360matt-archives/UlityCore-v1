package fr.ulity.core.bungee;


import fr.ulity.core.api.Api;
import fr.ulity.core.api.Config;
import fr.ulity.core.api.Lang;
import fr.ulity.core.api.adapter.Exec_MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class MainBungee extends Plugin {
    public static MainBungee plugin;
    public static Config config;
    public static Lang lang;

    public static ProxyServer proxy;

    @Override
    public void onEnable() {
        plugin = this;
        proxy = getProxy();

        Api.initialize(plugin);

        config = new Config();

        Exec_MySQL db = new Exec_MySQL("network");

        RegisterEvents.run();


    }

    @Override
    public void onDisable() {


    }
}