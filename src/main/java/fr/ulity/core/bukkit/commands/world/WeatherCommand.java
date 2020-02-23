package fr.ulity.core.bukkit.commands.world;

import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Syntax;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

public class WeatherCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        World world = null;

        if (!(sender instanceof Player))
            if (args.length == 1)
                world = MainBukkit.server.getWorld(args[0]);
            else {
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"world"}));
                return true;
            }
        else
        if (args.length == 1)
            world = MainBukkit.server.getWorld(args[0]);
        else if (args.length == 0)
            world = ((Player) sender).getWorld();
        else {
            sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"[world]"}));
            return true;
        }

        if (world == null){
            sender.sendMessage(Lang.get("error.invalid_world").replaceAll("%world%", args[0]));
            return true;
        }

        int duration = (300 + new Random().nextInt(600)) * 20;

        world.setWeatherDuration(duration);
        world.setThunderDuration(duration);



        if (cmd.getName().equals("sun")) {
            world.setStorm(false);
            world.setThundering(false);
        }
        else if (cmd.getName().equals("rain")) {
            world.setStorm(true);
            world.setThundering(false);
        }
        else {
            world.setStorm(true);
            world.setThundering(true);
        }

        sender.sendMessage(Lang.get("commands.weather.changed")
                .replaceAll("%weather%", Lang.get("commands.weather." + cmd.getName()))
                .replaceAll("%world%", world.getName()));

        return true;
    }
}
