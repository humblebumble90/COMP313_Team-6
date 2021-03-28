package com.example.fat2fit.helpers;

import java.util.regex.Pattern;

public class StringHelper {
    private static final Pattern RE_SPACES = Pattern.compile("^\\s+$");

    public static boolean isBlank(String str) {
        return isNullOrEmpty(str) || RE_SPACES.matcher(str).matches();
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
