package fr.ulity.core.bukkit.commands.general;

import fr.ulity.core.api.Lang;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BackCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player))
            sender.sendMessage(Lang.get("error.player_online"));
        else{
            Location location = (Location) MainBukkit.temp.get("player." + sender.getName() + ".lastPosition");

            if (location == null)
                sender.sendMessage(Lang.get("commands.back.no_last_position"));
            else{
                ((Player) sender).teleport(location);
                sender.sendMessage(Lang.get("commands.back.teleported"));
            }
        }


        return true;
    }
}
