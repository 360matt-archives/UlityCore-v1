package fr.ulity.core.api;

import de.leonhard.storage.Yaml;

public class Config extends Yaml {
    private static Config config;

    public Config() {
        super("config", Api.prefix + "/");
        new DefaultConfig();
    }

    public Config(String name) {
        super(name, Api.prefix + "/");

        if (name.equals("config"))
            new DefaultConfig();

    }

    public Config(String name, String path) {
        super(name, Api.prefix + "/" + path);


        if (name.equals("config") && path.equals(""))
            new DefaultConfig();

    }


}