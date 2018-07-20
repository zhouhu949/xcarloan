package com.fintecher.util;

import org.apache.commons.lang3.StringUtils;
import sun.util.calendar.LocalGregorianCalendar;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: PeiShouWen
 * @Description: 日期工具类
 * @Date 10:28 2017/3/13
 */
public class ZWDateUtil {

    /**
     * 获取当前日期
     *
     * @return
     */
    public static LocalDate getNowLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalDateTime getNowLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 获取指定的日期
     *
     * @param year
     * @param month
     * @param dayOfMonth
     * @return
     */
    public static LocalDate getDefinedLocalDate(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth);
    }

    /**
     * 获取当前日期 Date 类型
     *
     * @return
     */
    public static Date getNowDate() {
        LocalDate nowLocalDate = getNowLocalDate();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = nowLocalDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取当前时间 Date类型
     *
     * @return
     */
    public static Date getNowDateTime() {
        LocalDateTime nowLocalDateTime = getNowLocalDateTime();
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = nowLocalDateTime.atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String fomratterDate(Date date, String format) {
        if (date == null)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format == null ? "yyyy-MM-dd HH:mm:ss" : format);
        return sdf.format(date);
    }

    /**
     * 获取当前日期和时间
     *
     * @return
     */
    public static String getDateTime() {
        return fomratterDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前日期
     *
     * @return
     */
    public static String getDate() {
        return fomratterDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取当前年月日
     *
     * @return
     */
    public static String getYMDDate() {
        return fomratterDate(new Date(), "yyyyMMdd");
    }

    /**
     * 转换日期 20080101 -> Date
     *
     * @param dateStr 日期字符串
     * @param format  日期的字符串格式如yyyyMMdd
     * @return Date 日期
     * @throws ParseException 日期解析异常
     */
    public static Date getUtilDate(String dateStr, String format) throws ParseException {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat(format == null ? "yyyy-MM-dd HH:mm:ss" : format);
        return sdf.parse(dateStr);
    }

    /**
     * 转换日期 20080101 -> Date
     *
     * @param dateStr 日期字符串
     * @return Date 日期
     * @throws ParseException 日期解析异常
     */
    public static Date getFormatDate(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.parse(dateStr);
    }

    /**
     * 转换日期 20080101111111 -> DateTime
     *
     * @param dateStr 日期字符串
     * @return Date 日期
     * @throws ParseException 日期解析异常
     */
    public static Date getFormatDateTime(String dateStr) throws ParseException {
        if (dateStr == null || dateStr.length() == 0)
            return null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.parse(dateStr);
    }

    /**
     * 获取想要的当天晚上00:00:00的时间
     *
     * @param adday 参数是距离当天的天数，正数为当天之后，负数为当天之前
     * @return
     */
    public static Date getNightTime(Integer adday) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, adday + 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取当前日期的指定格式
     *
     * @param format
     * @return
     * @throws Exception
     */
    public static String getFormatNowDate(String format) throws Exception {
        return fomratterDate(getNowDate(), format);
    }

    /**
     * 获取两个日期相差的天数、月数(可以是负数或正数)
     *
     * @param startDate
     * @param endDate
     * @param type      :ChronoUnit.DAYS ChronoUnit.MONTHS
     * @return
     */
    public static Integer getBetween(Date startDate, Date endDate, ChronoUnit type) {
        LocalDateTime localDateTime1 = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime localDateTime2 = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        String difValue = String.valueOf(localDateTime1.until(localDateTime2, type));
        return Integer.parseInt(difValue);
    }

    /**
     * 获取传入时间下个月的某一天时间
     *
     * @param startDate
     * @param day
     * @return
     */
    public static Date getNextMonth(Date startDate, Integer day) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            int month = calendar.get(Calendar.MONTH);
            if (month+1>= 13) {
                calendar.add(Calendar.YEAR,+1);
                calendar.set(Calendar.MONTH,0);
            }else {
                calendar.add(Calendar.MONTH, +1);
            }
            int day2 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int day1 = day >= day2 ? day2 : day;
            calendar.set(Calendar.DAY_OF_MONTH ,day1);
            return  calendar.getTime();

        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }

    /**
     * 获取传入时间N个月的某一天时间
     *
     * @param startDate
     * @param day
     * @return
     */
    public static Date getNextMonthDay(Date startDate, Integer day,Integer periods) {
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            calendar.add(Calendar.MONTH,+periods);
            int day2 = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            int day1 = day >= day2 ? day2 : day;
            calendar.set(Calendar.DAY_OF_MONTH ,day1);
            return  calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
