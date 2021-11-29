package org.xm06.pms.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

    public static String format(Date date) {
        return new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(date);
    }

    public static Date parse(String s) throws ParseException {
        return new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").parse(s);
    }

    public static Date getToday(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static Date getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        return calendar.getTime();
    }

    /**
     * 获取未来 第 past 天的日期
     * @param past
     * @return
     */
    public static Date getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        return calendar.getTime();
    }

    public static String formatSystemRecordDate(Date date) {
        return new SimpleDateFormat("MM/dd").format(date);
    }

    /**
     * 获取未来 num 天的日期
     * @param num
     * @return
     */
    public static List<Date> getPastDates(int num){
        List<Date> res = new ArrayList<>();
        if(num <= 0) return res;

        for (int i = num - 1; i >= 0; i--) {
            res.add(getPastDate(i));
        }
        return res;
    }

    public static boolean isSameDay(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }
}
