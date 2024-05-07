package pl.himc.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class TimeUtil {

    public static long getTimeWithString(String s) {
        Pattern pattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[, \\s]*)?(?:([0-9]+)\\s*mo[a-z]*[, \\s]*)?(?:([0-9]+)\\s*d[a-z]*[, \\s]*)?(?:([0-9]+)\\s*h[a-z]*[, \\s]*)?(?:([0-9]+)\\s*m[a-z]*[, \\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", 2);
        Matcher matcher = pattern.matcher(s);
        long czas = 0L;
        boolean found = false;
        while (matcher.find()) {
            if ((matcher.group() != null) && (!matcher.group().isEmpty())) {
                for (int i = 0; i < matcher.groupCount(); i++) {
                    if ((matcher.group(i) != null) && (!matcher.group(i).isEmpty())) {
                        found = true;
                        break;
                    }
                }
                if ((matcher.group(1) != null) && (!matcher.group(1).isEmpty())) {
                    czas += 31536000 * Integer.valueOf(matcher.group(1)).intValue();
                }
                if ((matcher.group(2) != null) && (!matcher.group(2).isEmpty())) {
                    czas += 2592000 * Integer.valueOf(matcher.group(2)).intValue();
                }
                if ((matcher.group(3) != null) && (!matcher.group(3).isEmpty())) {
                    czas += 86400 * Integer.valueOf(matcher.group(3)).intValue();
                }
                if ((matcher.group(4) != null) && (!matcher.group(4).isEmpty())) {
                    czas += 3600 * Integer.valueOf(matcher.group(4)).intValue();
                }
                if ((matcher.group(5) != null) && (!matcher.group(5).isEmpty())) {
                    czas += 60 * Integer.valueOf(matcher.group(5)).intValue();
                }
                if ((matcher.group(6) != null) && (!matcher.group(6).isEmpty())) {
                    czas += Integer.valueOf(matcher.group(6)).intValue();
                }
            }
        }
        if (!found) {
            return -1L;
        }
        return czas * 1000L;
    }

    public static String getDurationBreakdownShort(long millis) {
        if (millis == 0L) {
            return "0";
        }
        long days = TimeUnit.MILLISECONDS.toDays(millis);
        if (days > 0L) {
            millis -= TimeUnit.DAYS.toMillis(days);
        }
        long hours = TimeUnit.MILLISECONDS.toHours(millis);
        if (hours > 0L) {
            millis -= TimeUnit.HOURS.toMillis(hours);
        }
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        if (minutes > 0L) {
            millis -= TimeUnit.MINUTES.toMillis(minutes);
        }
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        if (seconds > 0L) {
            millis -= TimeUnit.SECONDS.toMillis(seconds);
        }
        final StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days);
            sb.append("d ");
        }
        if (hours > 0L) {
            sb.append(hours);
            sb.append("h ");
        }
        if (minutes > 0L) {
            sb.append(minutes);
            sb.append("m ");
        }
        if (seconds > 0L) {
            sb.append(seconds);
            sb.append("s ");
        }
        return sb.toString();
    }

    public static String getDuration(long millis) {
        if (millis == 0L) {
            return "0";
        }
        final long days = TimeUnit.MILLISECONDS.toDays(millis);
        if (days > 0L) {
            millis -= TimeUnit.DAYS.toMillis(days);
        }
        final long hours = TimeUnit.MILLISECONDS.toHours(millis);
        if (hours > 0L) {
            millis -= TimeUnit.HOURS.toMillis(hours);
        }
        final long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        if (minutes > 0L) {
            millis -= TimeUnit.MINUTES.toMillis(minutes);
        }
        final long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        if (seconds > 0L) {
            millis -= TimeUnit.SECONDS.toMillis(seconds);
        }
        final StringBuilder sb = new StringBuilder();
        if (days > 0L) {
            sb.append(days);
            long i = days % 10L;
            if (i == 1L) {
                sb.append(" dzien ");
            } else {
                sb.append(" dni ");
            }
        }
        if (hours > 0L) {
            sb.append(hours);
            sb.append(" godz. ");
        }
        if (minutes > 0L) {
            sb.append(minutes);
            sb.append(" min. ");
        }
        if (seconds > 0L) {
            sb.append(seconds);
            sb.append(" sek. ");
        }
        return sb.toString();
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - HH:mm:ss");

    public static String getDate(long czas) {
        return sdf.format(new Date(czas));
    }

    public static long addTime(int seconds){
        return System.currentTimeMillis()+seconds*1000L;
    }
}
