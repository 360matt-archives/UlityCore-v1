package fr.ulity.core.api;

import java.util.Date;
import java.util.HashMap;

public class Ban {
    private static MySQL banDB = new MySQL("ban");

    public static void ban(String username) {
        banDB.set(username, new HashMap<>());
    }

    public static void ban(String username, String reason) {
        HashMap<String, Object> truc = new HashMap<>();
        truc.put("reason", reason);

        banDB.set(username, truc);
    }

    public static void tempban(String username, int seconds) {
        HashMap<String, Object> truc = new HashMap<>();
        truc.put("expire", (Math.round(new Date().getTime() / 1000)) + seconds);

        banDB.set(username, truc);
    }

    public static void tempban(String username, int seconds, String reason) {
        HashMap<String, Object> truc = new HashMap<>();
        truc.put("expire", (Math.round(new Date().getTime() / 1000)) + seconds);
        truc.put("reason", reason);

        banDB.set(username, truc);
    }

    public static void unban(String username) {
        banDB.delete(username);
    }

    public static boolean isBanned(String username) {
        return banDB.isSet(username);
    }

}
