package fr.ulity.core.bukkit.commands.general;

import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Syntax;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player target = null;

        if (sender instanceof Player) {
            if (args.length == 0)
                target = (Player) sender;
            else if (args.length == 1)
                target = MainBukkit.server.getPlayer(args[0]);
            else {
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"[player]"}));
                return true;
            }
        } else {
            if (args.length == 1)
                target = MainBukkit.server.getPlayer(args[0]);
            else {
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"player"}));
                return true;
            }
        }

        if (target == null) {
            sender.sendMessage(Lang.get("error.invalid_player")
                    .replaceAll("%name%", args[1]));
            return true;
        }

        String mod;

        if (target.getAllowFlight()) {
            mod = Lang.get("stats.disabled_colored");
            target.setAllowFlight(false);
        } else {
            mod = Lang.get("stats.enabled_colored");
            target.setAllowFlight(true);
        }

        target.sendMessage(Lang.get("commands.fly.notification")
                .replaceAll("%stat%", mod));

        if (!target.getName().equals(sender.getName())) {
            if (!sender.hasPermission("ulity.fly.others")) {
                sender.sendMessage(Lang.get("commands.fly.no_perm_other"));
                return true;
            }

            sender.sendMessage(Lang.get("commands.fly.changed_player")
                    .replaceAll("%player%", target.getName())
                    .replaceAll("%stat%", mod));
        }


        return true;
    }


}

