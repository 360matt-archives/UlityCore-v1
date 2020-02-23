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


public class GamemodeCommand implements CommandExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        
        Player target;
        String gamemode;

        if (sender instanceof Player){
            if (args.length == 1){
                target = (Player) sender;
                gamemode = args[0];
            }
            else if (args.length == 2){
                target = MainBukkit.server.getPlayer(args[1]);
                gamemode = args[0];
            }
            else {
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[] {"gamemode", "[player]"}));
                return true;
            }
        }
        else{
            if (args.length == 2){
                target = MainBukkit.server.getPlayer(args[1]);
                gamemode = args[0];
            }
            else {
                sender.sendMessage(Syntax.notice(cmd.getName(), new String[] {"gamemode", "player"}));
                return true;
            }
        }

        if (target == null){
            sender.sendMessage(Lang.get("error.invalid_player")
                    .replaceAll("%name%", args[1]));
            return true;
        }

        GameMode final_gm = Normalize.getGamemode(gamemode);

        if (final_gm != null){
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
        else
            sender.sendMessage(Lang.get("commands.gamemode.invalid_gamemode")
                    .replaceAll("%gamemode%", gamemode));

        return true;
    }

}