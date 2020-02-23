package fr.ulity.core.bukkit.commands.gamemode;

import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Normalize;
import fr.ulity.core.api.Syntax;
import fr.ulity.core.bukkit.MainBukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GmXCommand implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player target = null;

        if (sender instanceof Player) {
            if (args.length == 0)
                target = (Player) sender;
            else if (args.length == 1)
                target = MainBukkit.server.getPlayer(args[0]);
            else
                sender.sendMessage(Syntax.notice(command.getName(), new String[]{"[player]"}));
        }
        else{
            if (args.length == 1)
                target = MainBukkit.server.getPlayer(args[0]);
            else
                sender.sendMessage(Syntax.notice(command.getName(), new String[]{"player"}));
        }

        if (target == null)
            sender.sendMessage(Lang.get("error.invalid_player")
                .replaceAll("%name%", args[0]));
        else{
            GameMode final_gm = null;

            if (command.getName().equals("gmc"))
                final_gm = GameMode.CREATIVE;
            else if (command.getName().equals("gms"))
                final_gm = GameMode.SURVIVAL;
            else if (command.getName().equals("gma"))
                final_gm = GameMode.ADVENTURE;
            else
                final_gm = GameMode.SPECTATOR;

            target.setGameMode(final_gm);

            target.sendMessage(Lang.get("commands.gamemode.notification")
                    .replaceAll("%gamemode%", Normalize.getGamemode(final_gm)));

            if (!target.getName().equals(sender.getName())) {
                if (!sender.hasPermission("ulity.gamemode.others")) {
                    sender.sendMessage(Lang.get("commands.gamemode.no_perm_other"));
                    return true;
                }

                sender.sendMessage(Lang.get("commands.gamemode.changed_player")
                        .replaceAll("%player%", target.getName())
                        .replaceAll("%gamemode%", Normalize.getGamemode(final_gm)));
            }
        }

        return true;
    }
}
