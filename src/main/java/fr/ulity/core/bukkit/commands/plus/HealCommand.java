package fr.ulity.core.bukkit.commands.plus;

import fr.ulity.core.api.Lang;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            if (sender instanceof Player) {
                ((Player) sender).setHealth(20);
                sender.sendMessage(Lang.get("commands.heal.healed"));
            } else
                sender.sendMessage(Lang.get("error.player_only"));
        } else if (args.length == 1) {
            Player target = MainBukkit.server.getPlayer(args[0]);

            if (target == null) {
                sender.sendMessage(Lang.get("invalid_player")
                        .replaceAll("%name%", target.getName()));
            } else {
                target.setHealth(20);

                if (target.getName() == sender.getName())
                    target.sendMessage(Lang.get("commands.heal.healed"));
                else {
                    target.sendMessage(Lang.get("commands.heal.healed"));
                    sender.sendMessage(Lang.get("commands.heal.healed_other")
                            .replaceAll("%name%", target.getName()));
                }
            }
        }

        return true;
    }

}