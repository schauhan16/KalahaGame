package com.shailendra.util;

public class CommonUtil {

    public static String leftpad(String text, int length) {
        return String.format("%" + length + "." + length + "s", text);
    }

}
