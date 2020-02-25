package fr.ulity.core.bukkit.commands.teleportation;

import fr.ulity.core.api.Lang;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class TpYesCommand implements CommandExecutor {

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

        int delay = MainBukkit.config.getInt("teleport.delay");

        if (delay != 0)
            origin.sendMessage(Lang.get("commands.teleport.waiting_delay").replaceAll("%seconds%", String.valueOf(delay)));

        sender.sendMessage(Lang.get("commands.teleport.response_sent"));

        MainBukkit.server.getScheduler().scheduleSyncDelayedTask(MainBukkit.plugin, new Runnable() {
            public void run() {
                if (!((Player) sender).isOnline()) {
                    sender.sendMessage(Lang.get("error.player_disconnected").replaceAll("%name%", origin.getName()));
                    return;
                }
                if (!((Player) sender).isOnline()){
                    origin.sendMessage(Lang.get("error.player_disconnected").replaceAll("%name%", ((Player) sender).getName()));
                    return;
                }

                origin.teleport((Player) sender);

                origin.sendMessage(Lang.get("commands.teleport.origin_notification")
                        .replaceAll("%name%", sender.getName()));

            }
        }, delay*20);


        return true;
    }

}
