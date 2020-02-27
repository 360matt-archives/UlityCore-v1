package fr.ulity.core.api;

import de.leonhard.storage.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class Lang {
    public static Config langC;
    public static String lang;

    public Lang() {
        reload();
    }

    public static void reload() {
        lang = new Config().getString("global.lang");

        InputStream reference = Lang.class.getResourceAsStream("/languages/" + Api.type + "/" + lang + ".yml");

        File file = new File(Api.prefix + "/languages/" + lang + ".yml");
        file.getParentFile().mkdirs();

        if (reference != null) {
            Yaml ref;

            if (file.exists()) {
                file = new File(Api.prefix + "/temps/" + lang + "_tmp.yml");
                file.getParentFile().mkdirs();

                if (file.exists())
                    file.delete();
                try {
                    Files.copy(reference, file.getAbsoluteFile().toPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                File temp = new File(Api.prefix + "/languages/" + lang + ".yml");
                temp.mkdirs();

                ref = new Config(lang + "_tmp", "/temps");

                langC = new Config(lang, "languages");

                for (String i : ref.keySet()) {
                    langC.setDefault(i, ref.getString(i));
                }
            } else {
                try {
                    Files.copy(reference, file.getAbsoluteFile().toPath());
                    langC = new Config(lang, "languages");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else
            langC = new Config(lang, "languages");
    }

    public static String get(String key) {
        String value = langC.getString(key);

        return value
                .replaceAll("&", "§")
                .replaceAll("§§", "&");
    }

    public static void set(String key, String value) {
        langC.set(key, value);
    }

    public static void setDefault(String key, String value) {
        langC.setDefault(key, value);
    }

    public static boolean isSet(String key) {
        return langC.getString(key) != null;
    }

}
