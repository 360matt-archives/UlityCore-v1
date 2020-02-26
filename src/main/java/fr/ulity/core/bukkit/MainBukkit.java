package fr.ulity.core.bukkit;

import fr.ulity.core.api.*;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class MainBukkit extends JavaPlugin {
	public static MainBukkit plugin;
	public static Server server;
	public static Config config;
	public static PluginManager pMan;
	public static Lang lang;
	public static Temp temp;
	public static Data data;


    @Override
    public void onEnable(){
		System.setProperty("file.encoding", "UTF-8");

		Metrics metrics = new Metrics(this, 6520);


    	plugin = this;
    	server = this.getServer();
    	pMan = server.getPluginManager();

		Api.initialize(plugin);

		config = Api.config;
		temp = Api.temp;
		data = Api.data;


		RegisterCommands.exec();
		RegisterEvents.exec();





    	

    	



    }

    @Override
    public void onDisable(){




    }

}
