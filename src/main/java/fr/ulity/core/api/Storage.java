package fr.ulity.core.api;

import de.leonhard.storage.Json;

public class Storage extends Json {

    public Storage() {
        super("data", Api.prefix);
    }

    public Storage(String name) {
        super(name, Api.prefix);
    }

    public Storage(String name, String path) {
        super(name, Api.prefix + path);
    }
}