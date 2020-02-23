package fr.ulity.core.api;

import org.bukkit.command.CommandSender;

public class Permission {
    public static boolean isAdmin (CommandSender sender){
        if (sender.isOp() || sender.hasPermission("ulity.admin"))
            return true;
        else
            return false;
    }

    public static boolean isAdmin_error (CommandSender sender){
        if (isAdmin(sender))
            return true;

        sender.sendMessage("plugin.no_perm");
        return false;
    }

}
