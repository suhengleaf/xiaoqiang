package com.suheng.test1.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Convert {
    static public String Calendar2String(Calendar value, String def) {
        return value == null ? def : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).format(value.getTime());
    }
}
