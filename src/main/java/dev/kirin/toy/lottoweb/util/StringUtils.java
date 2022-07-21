package dev.kirin.toy.lottoweb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
    private static final String FORMAT_MARKUP = "{}";
    private static final String FORMAT_MARKUP_REGEX = "\\{\\}";

    public static final String BLANK = "";
    public static final String EQUALS = "=";
    public static final String COMMA = ",";
    public static final String DOT = ".";

    public static final String NUMBER_ONLY_REGEX = "[^0-9]";

    public static boolean isFormatString(String format) {
        return format.contains(FORMAT_MARKUP);
    }

    public static String format(final String str, Object... args) {
        Matcher matcher = Pattern.compile(FORMAT_MARKUP_REGEX).matcher(str);
        StringBuffer buffer = new StringBuffer();
        int index = 0;
        while(matcher.find()) {
            String value = BLANK;
            if(args.length >= index) {
                value = String.valueOf(args[index]);
            }
            matcher.appendReplacement(buffer, value);
            index++;
        }
        matcher.appendTail(buffer);
        return buffer.toString();
    }

    public static String numberOnly(String str) {
        return str.replaceAll(NUMBER_ONLY_REGEX, BLANK);
    }
}
