package com.keyuan.schoolmap.common;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtils {

    /**
     * 获取当前时间,返回date类型
     */

    @SuppressLint("SimpleDateFormat")
    public static Date getCurrentSystemTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String steDate = format.format(date);
        Date currentTime = null;
        try {
            currentTime = format.parse(steDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return currentTime;
    }

    /**
     * 获取当前时间，返回String类型
     */

    @SuppressLint("SimpleDateFormat")
    public static String getStrCurrentSystemTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String steDate = format.format(date);
        return steDate;
    }

    /**
     * 获取当前时间，返回String    yyy类型
     */

    @SuppressLint("SimpleDateFormat")
    public static String getStrCurrentSystemTimeYear() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String steDate = format.format(date);
        return steDate;
    }

    /**
     * 获取当月天数
     */
    public static int getMonthDay(String time) throws ParseException {
        String source = time;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = format.parse(source);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 格式化时间，返回String类型 2017-05-26 11:36
     */

    @SuppressLint("SimpleDateFormat")
    public static String dataFormatString(String strDate) {
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date = null;
        try {
            date = format.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return format.format(date);
    }

    /**
     * 格式化时间，传入2017-05-26 11:36:00类型 返回String类型 2017-05-26
     */

    @SuppressLint("SimpleDateFormat")
    public static String getHHMMByDate(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
        return format2.format(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static String getYYMMDDHHMMSSByDate(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format2.format(date);
    }

    /**
     * 格式化时间，传入2017-05-26 11:36:00类型 ，返回String类型  05-26 11:30
     */
    @SuppressLint("SimpleDateFormat")
    public static String getMDHMByDate(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("MM-dd HH:mm");
        return format2.format(date);
    }

    /**
     * 格式化时间，传入2017-05-26 11:36:00类型 ，返回String类型  05.26 11:30
     */
    @SuppressLint("SimpleDateFormat")
    public static String getMDHMByDateTwo(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("MM-dd HH:mm");
        return format2.format(date);
    }

    /**
     * 传入2017-05-26 11:36:00类型 返回String类型 05-26-11
     */
    @SuppressLint("SimpleDateFormat")
    public static String getMMddByDate(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        return format2.format(date);
    }

    /**
     * 传入2017-05-26 11:36:00类型 返回String类型 05-26
     */
    @SuppressLint("SimpleDateFormat")
    public static String getMMByDate(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM");
        return format2.format(date);
    }
    /*
     * 传入2017-05-26 11:36:00类型 返回String类型 05-26
     */

    @SuppressLint("SimpleDateFormat")
    public static String getYYMMDate(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy年MM月");
        return format2.format(date);
    }

    /**
     * 获取日期的毫秒数，传入2017-05-26 11:36:00类型（字符串日期型），返货毫秒数getTime;
     */

    @SuppressLint("SimpleDateFormat")
    public static long getTimeByDate(String strDate) {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 根据毫秒返回时分秒 ，传入Long类型毫秒，返回String类型时分秒
     */

    public static String getHHMMSSByMS(Long ms) {
        int ss = 1000;
        int mi = ss * 60;
        int hh = mi * 60;
        int dd = hh * 24;

        long day = ms / dd;
        long hour = (ms - day * dd) / hh;
        long minute = (ms - day * dd - hour * hh) / mi;
        long second = (ms - day * dd - hour * hh - minute * mi) / ss;
        long milliSecond = ms - day * dd - hour * hh - minute * mi - second * ss;

        String strDay = day < 10 ? "0" + day : "" + day; // 天
        String strHour = hour < 10 ? "0" + hour : "" + hour;// 小时
        String strMinute = minute < 10 ? "0" + minute : "" + minute;// 分钟
        String strSecond = second < 10 ? "0" + second : "" + second;// 秒
        String strMilliSecond = milliSecond < 10 ? "0" + milliSecond : "" + milliSecond;// 毫秒
        strMilliSecond = milliSecond < 100 ? "0" + strMilliSecond : "" + strMilliSecond;

        return strDay + ":" + strHour + ":" + strMinute + ":" + strSecond;
    }

    /**
     * 根据毫秒，返回 分和秒 48:56
     */
    public static String getMMSSByMS(Long ms) {
        long mmm = ms / 1000; // 总秒数
        long mm = mmm / 60; // 分钟
        long ss = mmm % 60; // 秒

        String strMM = mm < 10 ? "0" + mm : mm + "";
        String strSS = ss < 10 ? "0" + ss : ss + "";

        return strMM + ":" + strSS;
    }

    /**
     * 计算两个日期之间差值，传入传入2017-05-26 11:36:00类型（字符串日期型），返回差值 分秒
     */

    public static String getMMSSByDateDifferenceValues(String startDate, String endDate) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return "";
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate);
        endDateMS = getTimeByDate(endDate);
        differenceValues = Math.abs(endDateMS - startDateMS);
        return getMMSSByMS(differenceValues);
    }

    /**
     * 计算两个日期之间的差值，传入传入2017-05-26 11:36:00类型（字符串日期型），返回（long）毫秒值
     */

    public static long getTimeByDateDifferenceValues(String startDate, String endDate) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return 0;
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate);
        endDateMS = getTimeByDate(endDate);
        differenceValues = Math.abs(endDateMS - startDateMS);
        return differenceValues;
    }

    /**
     * 计算两个日期之间的差值，传入传入2017-05-26 11:36:00类型（字符串日期型），返回（long）毫秒值
     */

    public static long getTimeByDateDifferenceValues(String startDate, String endDate, String dataType) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return 0;
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate, dataType);
        endDateMS = getTimeByDate(endDate, dataType);
        differenceValues = Math.abs(endDateMS - startDateMS);
        return differenceValues;
    }

    /**
     * 计算两个日期之间的差值，传入传入2017-05-26 11:36:00类型（字符串日期型），时间差。
     */

    public static String getDistanceTimeByDateDifferenceValues(String startDate, String endDate) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return "";
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate);
        endDateMS = getTimeByDate(endDate);
        differenceValues = Math.abs(endDateMS - startDateMS);
        return getDistanceTime(differenceValues);
    }

    /**
     * 计算两个日期之间的差值，传入传入2017-05-26 11:36:00类型（字符串日期型），时间差。
     *
     * @param isDay 如果传入的是true返回天数，如果传入false返回状态日期
     */

    public static String getDistanceTimeByDateDifferenceValues(String startDate, String endDate, String dataType, boolean isDay) {
        if (startDate == null || startDate.equals("") || endDate == null || endDate.equals("")) {
            return "";
        }
        long startDateMS = 0;
        long endDateMS = 0;
        long differenceValues = 0;

        startDateMS = getTimeByDate(startDate, dataType);
        endDateMS = getTimeByDate(endDate, dataType);
        if (startDateMS - endDateMS < 0) {
            differenceValues = -Math.abs(endDateMS - startDateMS);
        } else {
            differenceValues = Math.abs(endDateMS - startDateMS);
        }
        if (isDay) {
            return getDistanceDay(differenceValues);
        } else {
            return differenceValues + "";
        }
    }

    /**
     * 获取日期的毫秒数，传入2017-05-26 11:36:00类型（字符串日期型），返货毫秒数getTime;
     */

    @SuppressLint("SimpleDateFormat")
    public static long getTimeByDate(String strDate, String dataType) {
        SimpleDateFormat format1 = new SimpleDateFormat(dataType);
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return date.getTime();
    }

    /**
     * 根据差值返回天数
     */
    public static String getDistanceDay(long diff) {
        long day = 0;
        day = diff / (24 * 60 * 60 * 1000);
        return day + "";
    }

    /**
     * 时间格式转换 Data转换成string
     */
    public static String getTime(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * 时间格式转换 Data转换成string
     */
    public static String getTimeYYMM(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(date);
    }

    /**
     * 时间格式转换 Data转换成string
     */
    public static String getTimeYYMM1(Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
        return format.format(date);
    }

    /**
     * 根据差值返回多长之间前或多长时间后
     */
    public static String getDistanceTime(long diff) {
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        day = diff / (24 * 60 * 60 * 1000);
        hour = (diff / (60 * 60 * 1000) - day * 24);
        min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
        sec = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day != 0) {
            if (day > 7) {
                transferLongToDate("MM-dd", diff);
            } else {
                return day + "天前";
            }
        }
        if (hour != 0)
            return hour + "小时前";
        if (min != 0)
            return min + "分钟前";
        return "刚刚";
    }

    @SuppressLint("SimpleDateFormat")
    public static String forenoticeLiveDateFormat(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("MM月dd日   HH:mm");
        return format2.format(date);
    }

    // 输入年月日+时间，输出时间+年月日
    @SuppressLint("SimpleDateFormat")
    public static String getTimeDataFormat(String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat("HH:mm yyyy/MM/dd");
        return format2.format(date);
    }

    /**
     * 传入string转换成data
     */
    public static Date getStringToData(String string) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = sdf.parse(string);
        return date;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////yyyy-MM-dd HH:mm:ss//////////////////////////////////////////////////////////////////////////

    /**
     * long转data
     */
    private static String transferLongToDate(String dateFormat, Long millSec) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        Date date = new Date(millSec);
        return sdf.format(date);
    }

    /**
     * 时间格式转换 Data转换成string
     */
    public static String getTimeString(String dateFormat, Date date) {//可根据需要自行截取数据显示
        SimpleDateFormat format = new SimpleDateFormat(dateFormat);
        return format.format(date);
    }

    /**
     * 传入String格式化
     */
    @SuppressLint("SimpleDateFormat")
    public static String getStringToFormatDate(String dateFormat, String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
        return format2.format(date);
    }

    /**
     * 传入String格式化
     */
    @SuppressLint("SimpleDateFormat")
    public static String getStringToFormatDate2(String dateFormat, String strDate) {
        // 先转换成Date类型
        if (strDate == null || strDate.equals("")) {
            return "";
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = null;
        try {
            date = format1.parse(strDate);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        SimpleDateFormat format2 = new SimpleDateFormat(dateFormat);
        return format2.format(date);
    }
}
