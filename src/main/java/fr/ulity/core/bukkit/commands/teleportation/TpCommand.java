package fr.ulity.core.bukkit.commands.teleportation;

import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Syntax;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpCommand implements CommandExecutor {

    private void single_teleport (CommandSender sender, String[] args){
        Player origin = MainBukkit.server.getPlayer(args[0]);
        Player target = MainBukkit.server.getPlayer(args[1]);

        if (origin == null){
            sender.sendMessage(Lang.get("error.invalid_player").replaceAll("%name%", args[0]));
            return;
        }
        else if (target == null){
            sender.sendMessage(Lang.get("error.invalid_player").replaceAll("%name%", args[1]));
            return;
        }

        origin.teleport(target);

        origin.sendMessage(Lang.get("commands.teleport.notification.to_player")
                .replaceAll("%name%", target.getName()));

        if (!origin.getName().equals(sender.getName()))
            sender.sendMessage(Lang.get("commands.teleport.sender.to_player")
                    .replaceAll("%name%", origin.getName())
                    .replaceAll("%target%", target.getName()));

    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player origin;
        Player target;

        if (!(sender instanceof Player)){
            if (args.length != 2){
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"player", "player"}));
                return true;
            }
            else
                single_teleport(sender, args);
        }
        else{
            if (args.length == 3 || args.length == 4){
                int x;
                int y;
                int z;

                try {
                    x = Integer.parseInt(args[0]);
                    y = Integer.parseInt(args[1]);
                    z = Integer.parseInt(args[2]);
                }
                catch (NumberFormatException e){
                    sender.sendMessage(Lang.get("commands.teleport.syntax_player"));
                    return true;
                }

                Location location = new Location(((Player) sender).getWorld(), x, y, z);

                if (args.length == 4){
                    origin = MainBukkit.server.getPlayer(args[3]);

                    if (origin == null)
                        sender.sendMessage(Lang.get("error.invalid_player").replaceAll("%name%", args[3]));
                    else{
                        origin.teleport(location);
                        origin.sendMessage(Lang.get("commands.teleport.notification.to_coords"));

                        if (!origin.getName().equals(sender.getName()))
                            sender.sendMessage(Lang.get("commands.teleport.sender.to_coords")
                                    .replaceAll("%name%", origin.getName()));
                    }
                }
                else{
                    ((Player) sender).teleport(location);
                    sender.sendMessage(Lang.get("commands.teleport.notification.to_coords"));
                }
            }
            else if (args.length == 2)
                single_teleport(sender, args);
            else if (args.length == 1){
                target = MainBukkit.server.getPlayer(args[0]);

                if (target == null){
                    sender.sendMessage(Lang.get("error.invalid_player").replaceAll("%name%", args[0]));
                    return true;
                }
                else{
                    ((Player) sender).teleport(target);

                    sender.sendMessage(Lang.get("commands.teleport.notification.to_player")
                            .replaceAll("%name%", target.getName()));
                }
            }
            else
                sender.sendMessage(Lang.get("commands.teleport.syntax_player"));
        }


        return true;
    }
}
