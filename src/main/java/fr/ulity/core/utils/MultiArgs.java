package fr.ulity.core.utils;

public class MultiArgs {
    public static String run(String[] args, Integer count) {
        String allArgs = "";

        for (String arg : args) {
            if (count != 0)
                count--;
            else
                allArgs = allArgs.concat(arg + " ");
        }

        return allArgs.trim();
    }

    public static String run(String[] args) {
        return run(args, 0);
    }
}
