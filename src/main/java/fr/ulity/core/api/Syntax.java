package fr.ulity.core.api;

public class Syntax {
    public static String notice (String cmd, String[] args){
        String template = "§e/" + cmd;

        for (String i : args){
            if (Lang.isSet("format." + i))
                i.replaceAll(i, Lang.get("format." + i));
            if (i.startsWith("[") && i.endsWith("]"))
                template += " §7" + i;
            else
            template += " §7<" + i + ">";
        }

        return Lang.get("plugin.syntax_notice")
                .replaceAll("%syntax%", template);
    }

    public static String notice (String cmd){
        return Lang.get("plugin.syntax_notice")
                .replaceAll("%syntax%", "§e/" + cmd);
    }
}
