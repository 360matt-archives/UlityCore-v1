package fr.ulity.core.bungee;


import fr.ulity.core.api.Api;
import fr.ulity.core.api.Config;
import fr.ulity.core.api.Lang;
import net.md_5.bungee.api.plugin.Plugin;

public class MainBungee extends Plugin {
	public static MainBungee plugin;
    public static Config config;
    public static Lang lang;
	
    @Override
    public void onEnable() {
        plugin = this;
        Api.initialize(plugin);

        config = new Config();


    	
        
    }
    
    @Override
    public void onDisable() {

    	
    }
}