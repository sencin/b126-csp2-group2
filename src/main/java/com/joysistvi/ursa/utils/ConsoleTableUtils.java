package com.joysistvi.ursa.utils;

import java.util.Collections;

public class ConsoleTableUtils {
    public static String repeat(String str, int count) {
        if (count <= 0) return "";
        return String.join("", Collections.nCopies(count, str));
    }
}