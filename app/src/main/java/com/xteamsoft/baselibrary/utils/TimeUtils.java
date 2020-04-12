package com.xteamsoft.baselibrary.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    private static final String[] CHINESE_ZODIAC = new String[]{
            ", ", ", ", ", ", ", ", ", ",
            ", "};

    public static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String[] ZODIAC = new String[]{
            ", ", ", ", ", ", ", ", ", ",
            ", "};

    private static final int[] ZODIAC_FLAGS = new int[]{
            20, 19, 21, 21, 21, 22, 23, 23, 23, 24,
            23, 22};

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static long date2Millis(Date paramDate) {
        return paramDate.getTime();
    }

    public static String date2String(Date paramDate) {
        return date2String(paramDate, "yyyy-MM-dd HH:mm:ss");
    }

    public static String date2String(Date paramDate, String paramString) {
        return (new SimpleDateFormat(paramString, Locale.getDefault())).format(paramDate);
    }

    public static String getChineseZodiac(int paramInt) {
        return CHINESE_ZODIAC[paramInt % 12];
    }

    public static String getChineseZodiac(long paramLong) {
        return getChineseZodiac(millis2Date(paramLong));
    }

    public static String getChineseZodiac(String paramString) {
        return getChineseZodiac(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static String getChineseZodiac(String paramString1, String paramString2) {
        return getChineseZodiac(string2Date(paramString1, paramString2));
    }

    @SuppressLint("WrongConstant")
    public static String getChineseZodiac(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        return CHINESE_ZODIAC[calendar.get(1) % 12];
    }

    public static String getFitTimeSpan(long paramLong1, long paramLong2, int paramInt) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(paramLong1 - paramLong2), paramInt);
    }

    public static String getFitTimeSpan(String paramString1, String paramString2, int paramInt) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(string2Millis(paramString1, "yyyy-MM-dd HH:mm:ss") - string2Millis(paramString2, "yyyy-MM-dd HH:mm:ss")), paramInt);
    }

    public static String getFitTimeSpan(String paramString1, String paramString2, int paramInt, String paramString3) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(string2Millis(paramString1, paramString3) - string2Millis(paramString2, paramString3)), paramInt);
    }

    public static String getFitTimeSpan(Date paramDate1, Date paramDate2, int paramInt) {
        return ConvertUtils.millis2FitTimeSpan(Math.abs(date2Millis(paramDate1) - date2Millis(paramDate2)), paramInt);
    }

    public static String getFitTimeSpanByNow(long paramLong, int paramInt) {
        return getFitTimeSpan(System.currentTimeMillis(), paramLong, paramInt);
    }

    public static String getFitTimeSpanByNow(String paramString, int paramInt) {
        return getFitTimeSpan(getNowTimeString(), paramString, paramInt, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getFitTimeSpanByNow(String paramString1, int paramInt, String paramString2) {
        return getFitTimeSpan(getNowTimeString(), paramString1, paramInt, paramString2);
    }

    public static String getFitTimeSpanByNow(Date paramDate, int paramInt) {
        return getFitTimeSpan(getNowTimeDate(), paramDate, paramInt);
    }

    @SuppressLint({"DefaultLocale"})
    public static String getFriendlyTimeSpanByNow(long paramLong) {
        long l1 = System.currentTimeMillis();
        long l2 = l1 - paramLong;
        if (l2 < 0L)
            return String.format("%tc", new Object[]{Long.valueOf(paramLong)});
        if (l2 < 1000L)
            return "刚刚";
        if (l2 < 60000L)
            return String.format("%d秒前, new Object[] { Long.valueOf(l2 / 1000L) }");
        if (l2 < 3600000L)
            return String.format("%d分钟前, new Object[] { Long.valueOf(l2 / 60000L) }");
        l1 = l1 / 86400000L * 86400000L;
        return (paramLong >= l1) ? String.format("今天%tR, new Object[] { Long.valueOf(paramLong) }) : ((paramLong >= l1 - 86400000L) ? String.format(", new Object[]{Long.valueOf(paramLong)}) : String.format("%tF", new Object[]{Long.valueOf(paramLong)});
    }

    public static String getFriendlyTimeSpanByNow(String paramString) {
        return getFriendlyTimeSpanByNow(paramString, "yyyy-MM-dd HH:mm:ss");
    }

    public static String getFriendlyTimeSpanByNow(String paramString1, String paramString2) {
        return getFriendlyTimeSpanByNow(string2Millis(paramString1, paramString2));
    }

    public static String getFriendlyTimeSpanByNow(Date paramDate) {
        return getFriendlyTimeSpanByNow(paramDate.getTime());
    }

    public static Date getNowTimeDate() {
        return new Date();
    }

    public static long getNowTimeMills() {
        return System.currentTimeMillis();
    }

    public static String getNowTimeString() {
        return millis2String(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getNowTimeString(String paramString) {
        return millis2String(System.currentTimeMillis(), paramString);
    }

    public static long getTimeSpan(long paramLong1, long paramLong2, ConstUtils.TimeUnit paramTimeUnit) {
        return ConvertUtils.millis2TimeSpan(Math.abs(paramLong1 - paramLong2), paramTimeUnit);
    }

    public static long getTimeSpan(String paramString1, String paramString2, ConstUtils.TimeUnit paramTimeUnit) {
        return getTimeSpan(paramString1, paramString2, paramTimeUnit, "yyyy-MM-dd HH:mm:ss");
    }

    public static long getTimeSpan(String paramString1, String paramString2, ConstUtils.TimeUnit paramTimeUnit, String paramString3) {
        return ConvertUtils.millis2TimeSpan(Math.abs(string2Millis(paramString1, paramString3) - string2Millis(paramString2, paramString3)), paramTimeUnit);
    }

    public static long getTimeSpan(Date paramDate1, Date paramDate2, ConstUtils.TimeUnit paramTimeUnit) {
        return ConvertUtils.millis2TimeSpan(Math.abs(date2Millis(paramDate1) - date2Millis(paramDate2)), paramTimeUnit);
    }

    public static long getTimeSpanByNow(long paramLong, ConstUtils.TimeUnit paramTimeUnit) {
        return getTimeSpan(System.currentTimeMillis(), paramLong, paramTimeUnit);
    }

    public static long getTimeSpanByNow(String paramString, ConstUtils.TimeUnit paramTimeUnit) {
        return getTimeSpan(getNowTimeString(), paramString, paramTimeUnit, "yyyy-MM-dd HH:mm:ss");
    }

    public static long getTimeSpanByNow(String paramString1, ConstUtils.TimeUnit paramTimeUnit, String paramString2) {
        return getTimeSpan(getNowTimeString(), paramString1, paramTimeUnit, paramString2);
    }

    public static long getTimeSpanByNow(Date paramDate, ConstUtils.TimeUnit paramTimeUnit) {
        return getTimeSpan(new Date(), paramDate, paramTimeUnit);
    }

    public static String getWeek(long paramLong) {
        return getWeek(new Date(paramLong));
    }

    public static String getWeek(String paramString) {
        return getWeek(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static String getWeek(String paramString1, String paramString2) {
        return getWeek(string2Date(paramString1, paramString2));
    }

    public static String getWeek(Date paramDate) {
        return (new SimpleDateFormat("EEEE", Locale.getDefault())).format(paramDate);
    }

    public static int getWeekIndex(long paramLong) {
        return getWeekIndex(millis2Date(paramLong));
    }

    public static int getWeekIndex(String paramString) {
        return getWeekIndex(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static int getWeekIndex(String paramString1, String paramString2) {
        return getWeekIndex(string2Date(paramString1, paramString2));
    }

    @SuppressLint("WrongConstant")
    public static int getWeekIndex(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        //return calendar.get(7);
        return calendar.get(7);
    }

    public static int getWeekOfMonth(long paramLong) {
        return getWeekOfMonth(millis2Date(paramLong));
    }

    public static int getWeekOfMonth(String paramString) {
        return getWeekOfMonth(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static int getWeekOfMonth(String paramString1, String paramString2) {
        return getWeekOfMonth(string2Date(paramString1, paramString2));
    }

    @SuppressLint("WrongConstant")
    public static int getWeekOfMonth(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        return calendar.get(4);
    }

    public static int getWeekOfYear(long paramLong) {
        return getWeekOfYear(millis2Date(paramLong));
    }

    public static int getWeekOfYear(String paramString) {
        return getWeekOfYear(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static int getWeekOfYear(String paramString1, String paramString2) {
        return getWeekOfYear(string2Date(paramString1, paramString2));
    }

    @SuppressLint("WrongConstant")
    public static int getWeekOfYear(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        return calendar.get(3);
    }

    public static String getZodiac(int paramInt1, int paramInt2) {
        String[] arrayOfString = ZODIAC;
        if (paramInt2 >= ZODIAC_FLAGS[paramInt1 - 1])
            return arrayOfString[--paramInt1];
        paramInt1 = (paramInt1 + 10) % 12;
        return arrayOfString[paramInt1];
    }

    public static String getZodiac(long paramLong) {
        return getZodiac(millis2Date(paramLong));
    }

    public static String getZodiac(String paramString) {
        return getZodiac(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static String getZodiac(String paramString1, String paramString2) {
        return getZodiac(string2Date(paramString1, paramString2));
    }

    @SuppressLint("WrongConstant")
    public static String getZodiac(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        return getZodiac(calendar.get(2) + 1, calendar.get(5));
    }

    public static boolean isLeapYear(int paramInt) {
        return ((paramInt % 4 == 0 && paramInt % 100 != 0) || paramInt % 400 == 0);
    }

    public static boolean isLeapYear(long paramLong) {
        return isLeapYear(millis2Date(paramLong));
    }

    public static boolean isLeapYear(String paramString) {
        return isLeapYear(string2Date(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static boolean isLeapYear(String paramString1, String paramString2) {
        return isLeapYear(string2Date(paramString1, paramString2));
    }

    @SuppressLint("WrongConstant")
    public static boolean isLeapYear(Date paramDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(paramDate);
        return isLeapYear(calendar.get(1));
    }

    public static boolean isSameDay(long paramLong) {
        long l = System.currentTimeMillis() / 86400000L * 86400000L;
        return (paramLong >= l && paramLong < l + 86400000L);
    }

    public static boolean isSameDay(String paramString) {
        return isSameDay(string2Millis(paramString, "yyyy-MM-dd HH:mm:ss"));
    }

    public static boolean isSameDay(String paramString1, String paramString2) {
        return isSameDay(string2Millis(paramString1, paramString2));
    }

    public static boolean isSameDay(Date paramDate) {
        return isSameDay(paramDate.getTime());
    }

    public static Date millis2Date(long paramLong) {
        return new Date(paramLong);
    }

    public static String millis2String(long paramLong) {
        return (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())).format(new Date(paramLong));
    }

    public static String millis2String(long paramLong, String paramString) {
        return (new SimpleDateFormat(paramString, Locale.getDefault())).format(new Date(paramLong));
    }

    public static Date string2Date(String paramString) {
        return string2Date(paramString, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date string2Date(String paramString1, String paramString2) {
        return new Date(string2Millis(paramString1, paramString2));
    }

    public static long string2Millis(String paramString) {
        return string2Millis(paramString, "yyyy-MM-dd HH:mm:ss");
    }

    public static long string2Millis(String paramString1, String paramString2) {
        try {
            return (new SimpleDateFormat(paramString2, Locale.getDefault())).parse(paramString1).getTime();
        } catch (ParseException parseException) {
            parseException.printStackTrace();
            return -1L;
        }
    }
}
