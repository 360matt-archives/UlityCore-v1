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

public class TpaCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (!(sender instanceof Player)){
            sender.sendMessage(Lang.get("error.player_only"));
            return true;
        }
        else{
            if (args.length != 1){
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"player"}));
                return true;
            }
            else{
                Player target = MainBukkit.server.getPlayer(args[0]);

                if (target == null){
                    sender.sendMessage(Lang.get("error.invalid_player").replaceAll("%name%", args[0]));
                    return true;
                }
                else{
                    if (MainBukkit.data.getList("player." + target.getName() + ".blocked") != null) {
                        if (MainBukkit.data.getList("player." + target.getName() + ".blocked").contains(sender.getName())) {
                                sender.sendMessage(Lang.get("error.your_bloqued_by_x")
                                        .replaceAll("%name%", target.getName()));

                            return true;
                        }
                    }

                    if (MainBukkit.temp.getString("player." + target.getName() + ".lastTpRequest.name").equals(sender.getName())){
                        sender.sendMessage(Lang.get("commands.tpa.already_sent"));
                        return true;
                    }

                    int cooldown = MainBukkit.config.getInt("teleport.cooldown");
                    long now = System.currentTimeMillis() / 1000;

                    if (sender.getName().equals(target.getName())){
                        if (!sender.hasPermission("ulity.teleport.bypass-cooldown")) {
                            int last = MainBukkit.temp.getInt("player." + sender.getName() + ".lastTeleport");

                            if (last != 0 && last > now) {
                                long left = last - now;

                                sender.sendMessage(Lang.get("error.cooldown")
                                        .replaceAll("%time%", Time.text((int) left)));

                                return true;
                            }
                        }
                    }

                    MainBukkit.temp.set("player." + target.getName() + ".lastTpRequest.name", sender.getName());
                    MainBukkit.temp.set("player." + target.getName() + ".lastTpRequest.timestamp", new Date().getTime());

                    sender.sendMessage(Lang.get("commands.tpa.request_sent")
                            .replaceAll("%name%", target.getName()));

                    target.sendMessage(Lang.get("commands.tpa.request")
                            .replaceAll("%name%", sender.getName())
                            .replaceAll("%seconds%", String.valueOf(MainBukkit.config.getInt("teleport.timeout"))));

                    MainBukkit.temp.set("player." + sender.getName() + ".lastTeleport", now + cooldown);

                }

            }

        }

        return true;
    }
}
