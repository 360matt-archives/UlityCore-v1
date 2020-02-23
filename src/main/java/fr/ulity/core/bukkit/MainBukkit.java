package fr.ulity.core.bukkit;

import fr.ulity.core.api.Api;
import fr.ulity.core.api.Config;
import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Temp;
import fr.ulity.core.utils.Time;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Date;


public class MainBukkit extends JavaPlugin {
	public static MainBukkit plugin;
	public static Server server;
	public static Config config;
	public static PluginManager pMan;
	public static Lang lang;
	public static Temp temp;


    @Override
    public void onEnable(){
		System.setProperty("file.encoding", "UTF-8");

    	plugin = this;
    	server = this.getServer();
    	pMan = server.getPluginManager();

		Api.initialize(plugin);

		config = Api.config;
		temp = Api.temp;


		RegisterCommands.exec();
		RegisterEvents.exec();





    	

    	



    }

    @Override
    public void onDisable(){




    }

}