package fr.ulity.core.utils;

import fr.ulity.core.api.Lang;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {
    public static int toSeconds (String period){
        final String regex = "([0-9]+)([A-z])";
        int value = 0;

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(period);

        while (matcher.find()) {
            int number = Integer.parseInt(matcher.group(1));
            String multiplier = matcher.group(2);

            value += (multiplier.equals("s")) ? number : 0;
            value += (multiplier.equals("m")) ? number*60 : 0;
            value += (multiplier.equals("h")) ? number*60*60 : 0;
            value += (multiplier.equals("d") || multiplier.equals("j")) ? number*60*60*24 : 0;
            value += (multiplier.equals("w") ) ? number*60*60*24*7 : 0;
            value += (multiplier.equals("o")) ? number*60*60*24*31 : 0;
            value += (multiplier.equals("y")) ? number*60*60*24*365 : 0;

        }

        return value;
    }

    private static String exp (String exp){
        return Lang.get("expressions.period." + exp);
    }

    public static String text (int seconds){
        Seconds sec = Seconds.seconds(seconds);
        Period period = new Period(sec);

        PeriodFormatter literal = new PeriodFormatterBuilder()
                .appendYears()
                .appendSuffix(" " + exp(("year")), " " + exp(("years")))
                .appendSeparator(", ")
                .appendMonths()
                .appendSuffix(" " + exp(("month")), " " + exp(("months")))
                .appendSeparator(", ")
                .appendDays()
                .appendSuffix(" " + exp(("day")), " " + exp(("days")))
                .appendSeparator(", ")
                .appendHours()
                .appendSuffix(" " + exp(("hour")), " " + exp(("hours")))
                .appendSeparator(", ")
                .appendMinutes()
                .appendSuffix(" " + exp(("minute")), " " + exp(("minutes")))
                .appendSeparator(", ")
                .appendSeconds()
                .appendSuffix(" " + exp(("second")), " " + exp(("seconds")))
                .toFormatter();

        return literal.print(period.normalizedStandard());
    }

    public static long timestamp () {
        Date date = new Date();
        return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
    }
}
