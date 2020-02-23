package fr.ulity.core.utils;

public class MultiArgs {
    public static String run (String[] args, Integer count) {
        String allArgs = "";

        for(int i = 0; i < args.length; i++){
            if (count != 0)
                count--;
            else
                allArgs = allArgs.concat(args[i] + " ");
        }

        return allArgs.trim();
    }

    public static String run (String[] args) {
        return run(args, 0);
    }
}
