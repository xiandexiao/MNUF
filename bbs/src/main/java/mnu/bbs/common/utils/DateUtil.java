package mnu.bbs.common.utils;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
* Author: xian
* Date 2018/4/11 16:34
* Description 获取日期时间工具类
*/
public class DateUtil {
    private static LocalDateTime date = LocalDateTime.now();
    /**
     * 获取年份
     */
    public static int getYear() {

        return  date.getYear();
    }

    /**
     * 获取日期
     */
    public static int getMonth() {
        return  date.getMonth().getValue();
    }
    /**
     * 获取星期
     */
    public static String getWeek() {
        int weekValue = date.getDayOfWeek().getValue();
        switch (weekValue) {
            case 1: return "一";
            case 2: return "二";
            case 3: return "三";
            case 4: return "四";
            case 5: return "五";
            case 6: return "六";
            case 7: return "天";
            default: return "?";
        }
    }
}
