package fr.ulity.core.bukkit.commands.teleportation;

import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Syntax;
import fr.ulity.core.bukkit.MainBukkit;
import fr.ulity.core.utils.Time;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Date;

public class TpXCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player origin = null;
        Player target = null;

        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"player", "player"}));
                return true;
            }

            origin = (Player) sender;
            target = MainBukkit.server.getPlayer(args[0]);

            if (target == null){
                sender.sendMessage(Lang.get("error.invalid_player")
                        .replaceAll("%name%", args[0]));
                return true;
            }
        }
        else if (args.length == 2){
            if (!sender.hasPermission("ulity.teleport.others")){
                sender.sendMessage(Lang.get("commands.teleport.no_perm_other"));
                return true;
            }

            origin = MainBukkit.server.getPlayer(args[0]);
            target = MainBukkit.server.getPlayer(args[1]);

            if (origin == null){
                sender.sendMessage(Lang.get("error.invalid_player")
                        .replaceAll("%name%", args[0]));
                return true;
            }
            if (target == null){
                sender.sendMessage(Lang.get("error.invalid_player")
                        .replaceAll("%name%", args[1]));
                return true;
            }
        }
        else{
            if (sender instanceof Player)
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"player", "[player]"}));
            else
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"player", "player"}));
            return true;
        }

        int cooldown = MainBukkit.config.getInt("teleport.cooldown");
        long now = System.currentTimeMillis() / 1000;

        if (sender.getName().equals(target.getName())){
            int last = MainBukkit.temp.getInt("player." + sender.getName() + ".lastTeleport");

            System.out.println("cooldown: " + cooldown);
            System.out.println("last: " + last);
            System.out.println("now: " + now);

            if (last != 0 && last > now){
                long left = last - now;

                sender.sendMessage(Lang.get("error.cooldown")
                        .replaceAll("%time%", Time.text((int) left)));

                return true;
            }
        }


        if (cmd.getName().equals("teleport")){
            origin.teleport(target);
            // direct tp

            origin.sendMessage(Lang.get("commands.teleport.origin_notification")
                    .replaceAll("%name%", target.getName()));

            if (!origin.getName().equals(sender.getName()))
                sender.sendMessage(Lang.get("commands.teleport.sender_notification")
                        .replaceAll("%origin%", origin.getName())
                        .replaceAll("%target%", target.getName()));
        }
        else if (cmd.getName().equals("tpa")){
            int delay = MainBukkit.config.getInt("delays.teleport");

            if (MainBukkit.data.getList("player." + target.getName() + ".blocked") != null) {
                if (MainBukkit.data.getList("player." + target.getName() + ".blocked").contains(origin.getName())) {
                    if (args.length == 2)
                        sender.sendMessage(Lang.get("error.x_bloqued_by_x")
                                .replaceAll("%name1%", origin.getName())
                                .replaceAll("%name2%", target.getName()));
                    else
                        sender.sendMessage(Lang.get("error.your_bloqued_by_x")
                                .replaceAll("%name%", target.getName()));

                    return true;
                }
            }

            if (MainBukkit.temp.getString("player." + target.getName() + ".lastTpRequest.name").equals(sender.getName())){
                sender.sendMessage(Lang.get("commands.teleport.already_sent"));
                return true;
            }

            MainBukkit.temp.set("player." + target.getName() + ".lastTpRequest.name", origin.getName());
            MainBukkit.temp.set("player." + target.getName() + ".lastTpRequest.timestamp", new Date().getTime());

            origin.sendMessage(Lang.get("commands.teleport.request_sent")
                    .replaceAll("%name%", target.getName()));

            target.sendMessage(Lang.get("commands.teleport.request_tpa")
                    .replaceAll("%name%", origin.getName())
                    .replaceAll("%seconds%", String.valueOf(MainBukkit.config.getInt("teleport.timeout"))));

        }

        MainBukkit.temp.set("player." + sender.getName() + ".lastTeleport", now + cooldown);
        return true;
    }

}
