package fr.ulity.core.bukkit.commands.debug;

import fr.ulity.core.bukkit.particles.Mousse;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class testCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Mousse.run((Player) sender);
        return true;
    }
}
