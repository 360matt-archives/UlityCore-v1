package fr.ulity.core.bukkit.commands.principal;

import fr.ulity.core.api.Config;
import fr.ulity.core.api.Lang;
import fr.ulity.core.api.Permission;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.ulity.core.bukkit.MainBukkit;


public class UlityCoreCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            switch(Lang.lang) {
                case "fr":
                    sender.sendMessage("§bUlityCore §7est créé par §c360matt");
                    sender.sendMessage("§7Version: §av" + MainBukkit.plugin.getDescription().getVersion());
                    sender.sendMessage("§7Ce plugin est une librairie de fonctionnalités.");
                    break;
                default:
                    sender.sendMessage("§bUlityCore §7is created by §c360matt");
                    sender.sendMessage("§7Version: §av" + MainBukkit.plugin.getDescription().getVersion());
                    sender.sendMessage("§7This plugin is a fonctionnality's library.");

            }
        }
        else
            switch(args[0]) {
                case "reload":
                case "rl":
                    if (Permission.isAdmin_error(sender)) {
                        MainBukkit.pMan.disablePlugin(MainBukkit.plugin);
                        MainBukkit.pMan.enablePlugin(MainBukkit.plugin);

                        sender.sendMessage(Lang.get("plugin.reloaded"));
                    }
                    break;
                case "lang":
                case "langue":
                    if (args.length == 1)
                        sender.sendMessage(Lang.get("plugin.actual_lang"));
                    else {
                        if (Permission.isAdmin_error(sender)) {
                            MainBukkit.config.set("global.lang", args[1]);
                            Lang.reload();
                            sender.sendMessage(Lang.get("plugin.lang_reloaded"));
                        }
                    }
                    break;
                default:
                    sender.sendMessage(Lang.get("msg.invalid_arg").replaceAll("%arg%", args[0]));
            }


        return true;
    }
}