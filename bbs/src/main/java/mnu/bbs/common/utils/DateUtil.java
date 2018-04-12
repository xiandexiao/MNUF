package mnu.bbs.common.utils;

import java.util.Calendar;

/**
* Author: xian
* Date 2018/4/11 16:34
* Description 获取日期时间工具类
*/
public class DateUtil {

    /**
     * 获取年份
     * @return
     */
    public static int getYear() {

        Calendar cal = Calendar.getInstance();

        return  cal.get(Calendar.YEAR);
    }

    /**
     * 获取日期
     * @return
     */
    public static int getMonth() {
        Calendar cal = Calendar.getInstance();

        return  cal.get(Calendar.MONTH)+1;
    }

}
