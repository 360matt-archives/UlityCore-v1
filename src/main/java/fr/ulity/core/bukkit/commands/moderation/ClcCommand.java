package fr.ulity.core.bukkit.commands.moderation;

import fr.ulity.core.api.Lang;
import fr.ulity.core.bukkit.MainBukkit;
import fr.ulity.core.utils.MultiArgs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClcCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        for(int i = 0; i < 100; i++)
            MainBukkit.plugin.getServer().broadcastMessage("");

        String msg = Lang.get("commands.clc.cleared");

        if (sender instanceof Player)
            msg = msg.replaceAll("%staff%", sender.getName());
        else
            msg = msg.replaceAll("%staff%", Lang.get("expressions.console"));

        if (args.length == 0)
            msg = msg.replaceAll("%reason%", Lang.get("expressions.unknown_reason"));
        else
            msg = msg.replaceAll("%reason%", MultiArgs.run(args));

        MainBukkit.server.broadcastMessage(msg);

        return true;
    }
}
