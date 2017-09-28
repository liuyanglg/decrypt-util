package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatDate {
    private static String format = "yyyy-MM-dd HH:mm:ss";

    public static String getDateStr(String format) {
        SimpleDateFormat formatter = null;
        if (format != null && format.trim().length() > 0) {
            formatter = new SimpleDateFormat(format);
        }
        return formatter.format(new Date());
    }

    public static String getDateStr() {
        SimpleDateFormat formatter = null;
        formatter = new SimpleDateFormat(format);
        return formatter.format(new Date());
    }
}
