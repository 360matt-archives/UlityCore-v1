package fr.ulity.core.api;

import de.leonhard.storage.Yaml;

public class DefaultConfig extends Yaml {
    DefaultConfig() {
        super("config", Api.prefix);

        if (Api.type.equals("bungeecord"))
            isBungee();
        else if (Api.type.equals("bukkit"))
            isBukkit();
    }

    public void isBukkit() {
        setDefault("global.lang", "fr");
        setDefault("global.server-name", "&e[Beautiful server] ");
        setDefault("teleport.delay", 5);
        setDefault("teleport.timeout", 60);
        setDefault("teleport.cooldown", 60);
        setDefault("teleport.animation", true);
        setDefault("animations.death.player", true);
        setDefault("animations.death.mobs", true);
    }

    public void isBungee() {
        setDefault("global.lang", "fr");
    }


}