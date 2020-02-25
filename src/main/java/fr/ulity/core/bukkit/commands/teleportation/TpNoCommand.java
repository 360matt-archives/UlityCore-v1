package fr.ulity.core.bukkit.commands.teleportation;

import fr.ulity.core.api.Lang;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class TpNoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(Lang.get("error.player_only"));
            return true;
        }

        if (MainBukkit.temp.get("player." + sender.getName() + ".lastTpRequest") == null){
            sender.sendMessage(Lang.get("commands.teleport.no_request"));
            return true;
        }

        if ((MainBukkit.temp.getInt("player." + sender.getName() + ".lastTpRequest.timestamp") + MainBukkit.config.getInt("teleportation.timeout")) > new Date().getTime()){
            sender.sendMessage(Lang.get("commands.teleport.timed_out"));
            return true;
        }

        MainBukkit.temp.set("player." + sender.getName() + ".lastTpRequest", null);

        Player origin = MainBukkit.server.getPlayer(MainBukkit.temp.getString("player." + sender.getName() + ".lastTpRequest.name"));
        origin.sendMessage(Lang.get("commands.teleport.request_refused"));

        sender.sendMessage(Lang.get("commands.teleport.response_sent"));

        return true;
    }
}
