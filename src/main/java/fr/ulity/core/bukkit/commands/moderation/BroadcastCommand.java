package fr.ulity.core.bukkit.commands.moderation;

import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Syntax;
import fr.ulity.core.bukkit.MainBukkit;
import fr.ulity.core.utils.MultiArgs;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class BroadcastCommand  implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (args.length != 0)
            MainBukkit.server.broadcastMessage(MainBukkit.config.get("global.server-name") + "§7" + MultiArgs.run(args)
                    .replaceAll("&", "§"));
        else
            sender.sendMessage(Syntax.notice(cmd.getName(), new String[]{"text"}));


        return true;
    }

}
