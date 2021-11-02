package org.xm06.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static String format(Date date) {
        return new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(date);
    }

    public static Date parse(String s) throws ParseException {
        return new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").parse(s);
    }
}
