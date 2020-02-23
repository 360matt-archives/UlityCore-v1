package fr.ulity.core.api;

import org.bukkit.GameMode;

public class Normalize {

    public static GameMode getGamemode (String gm){
        if (gm.equals(Lang.get("gamemode.creative")) || gm.equals("creative") || gm.equals("1"))
            return GameMode.CREATIVE;
        if (gm.equals(Lang.get("gamemode.survival")) || gm.equals("survival") || gm.equals("0"))
            return GameMode.SURVIVAL;
        if (gm.equals(Lang.get("gamemode.adventure")) || gm.equals("adventure") || gm.equals("2"))
            return GameMode.ADVENTURE;
        if (gm.equals(Lang.get("gamemode.spectator")) || gm.equals("spectator") || gm.equals("3"))
            return GameMode.SPECTATOR;
        return null;
    }

    public static String getGamemode (GameMode gm){
        if (gm.equals(GameMode.CREATIVE))
            return Lang.get("gamemode.creative");
        if (gm.equals(GameMode.SURVIVAL))
            return Lang.get("gamemode.survival");
        if (gm.equals(GameMode.ADVENTURE))
            return Lang.get("gamemode.adventure");
        if (gm.equals(GameMode.SPECTATOR))
            return Lang.get("gamemode.spectator");
        return null;
    }

}