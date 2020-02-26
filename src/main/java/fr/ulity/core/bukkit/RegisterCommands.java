package fr.ulity.core.bukkit;

import fr.ulity.core.bukkit.commands.gamemode.GamemodeCommand;
import fr.ulity.core.bukkit.commands.gamemode.GmXCommand;
import fr.ulity.core.bukkit.commands.general.BackCommand;
import fr.ulity.core.bukkit.commands.general.FlyCommand;
import fr.ulity.core.bukkit.commands.moderation.BroadcastCommand;
import fr.ulity.core.bukkit.commands.moderation.ClcCommand;
import fr.ulity.core.bukkit.commands.principal.UlityCoreCommand;
import fr.ulity.core.bukkit.commands.teleportation.TpCommand;
import fr.ulity.core.bukkit.commands.teleportation.TpNoCommand;
import fr.ulity.core.bukkit.commands.teleportation.TpYesCommand;
import fr.ulity.core.bukkit.commands.teleportation.TpaCommand;
import fr.ulity.core.bukkit.commands.world.TimeCommand;
import fr.ulity.core.bukkit.commands.world.WeatherCommand;

public class RegisterCommands {

    public static void exec (){
        MainBukkit.plugin.getCommand("ulitycore").setExecutor(new UlityCoreCommand());
        MainBukkit.plugin.getCommand("gamemode").setExecutor(new GamemodeCommand());

        MainBukkit.plugin.getCommand("gmc").setExecutor(new GmXCommand());
        MainBukkit.plugin.getCommand("gms").setExecutor(new GmXCommand());
        MainBukkit.plugin.getCommand("gma").setExecutor(new GmXCommand());
        MainBukkit.plugin.getCommand("gmp").setExecutor(new GmXCommand());

        MainBukkit.plugin.getCommand("fly").setExecutor(new FlyCommand());
        MainBukkit.plugin.getCommand("clc").setExecutor(new ClcCommand());
        MainBukkit.plugin.getCommand("broadcast").setExecutor(new BroadcastCommand());

        MainBukkit.plugin.getCommand("day").setExecutor(new TimeCommand());
        MainBukkit.plugin.getCommand("night").setExecutor(new TimeCommand());

        MainBukkit.plugin.getCommand("sun").setExecutor(new WeatherCommand());
        MainBukkit.plugin.getCommand("rain").setExecutor(new WeatherCommand());
        MainBukkit.plugin.getCommand("storm").setExecutor(new WeatherCommand());

        MainBukkit.plugin.getCommand("back").setExecutor(new BackCommand());

        MainBukkit.plugin.getCommand("teleport").setExecutor(new TpCommand());
        MainBukkit.plugin.getCommand("tpa").setExecutor(new TpaCommand());
        MainBukkit.plugin.getCommand("tpyes").setExecutor(new TpYesCommand());
        MainBukkit.plugin.getCommand("tpno").setExecutor(new TpNoCommand());

    }

}
