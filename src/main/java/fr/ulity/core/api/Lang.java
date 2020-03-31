package fr.ulity.core.api;

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
        lang = Api.config.getString("global.lang");

        langC = new Config(lang, "languages");

        try {
            InputStream reference = Lang.class.getResourceAsStream("/languages/" + Api.type + "/" + lang + ".yml");

            if (reference != null) {
                File tempo = new File(Api.prefix + "/temps/" + lang + "_tmp.yml");

                if (tempo.exists())
                    tempo.delete();
                Files.copy(reference, tempo.toPath());

                Config tempoC = new Config(lang + "_tmp.yml", "temps");

                for (String i : tempoC.keySet())
                    langC.setDefault(i, tempoC.getString(new String(i.getBytes(), System.getProperty("file.encoding", "ISO-8859-1"))));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
