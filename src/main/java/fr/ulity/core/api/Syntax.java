package fr.ulity.core.api;

public class Syntax {
    public static String notice(String cmd, String[] args) {
        StringBuilder template = new StringBuilder("§e/" + cmd);

        for (String i : args) {
            if (Lang.isSet("format." + i))
                i = i.replaceAll(i, Lang.get("format." + i));
            if (i.startsWith("[") && i.endsWith("]"))
                template.append(" §7").append(i);
            else
                template.append(" §7<").append(i).append(">");
        }

        return Lang.get("plugin.syntax_notice")
                .replaceAll("%syntax%", template.toString());
    }

    public static String notice(String cmd) {
        return Lang.get("plugin.syntax_notice")
                .replaceAll("%syntax%", "§e/" + cmd);
    }
}
