package com.ss.union.game.sdk.common.util;

import android.text.format.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DateUtils {
    public static final String[] WEEK_NAMES_CN = {"", "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
    public static final String[] WEEK_NAMES_EN = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    public static final String FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    private static final SimpleDateFormat a = new SimpleDateFormat(FORMAT_ONE);
    private static final SimpleDateFormat b = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat c = new SimpleDateFormat("yyyy年MM月dd日");
    private static final SimpleDateFormat d = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat e = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    public static int getYear(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(1);
    }

    public static int getMonth(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(2) + 1;
    }

    public static int getDay(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(5);
    }

    public static String getWeek(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (z) {
            return WEEK_NAMES_CN[calendar.get(7)];
        }
        return WEEK_NAMES_EN[7];
    }

    public static boolean isSameDay(long j, long j2) {
        Time time = new Time();
        time.set(j);
        int i = time.year;
        int i2 = time.month;
        int i3 = time.monthDay;
        time.set(j2);
        return i == time.year && i2 == time.month && i3 == time.monthDay;
    }

    public static long getDayDiff(long j, long j2) {
        return (j2 - j) / 86400000;
    }

    public static long getHourDiff(long j, long j2) {
        return (j2 - j) / 3600000;
    }

    public static long getDayStartTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return calendar.getTimeInMillis();
    }

    public static long getDayEndTime(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    public static String format1(long j) {
        try {
            return a.format(new Date(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String format2(long j) {
        try {
            return b.format(new Date(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String format3(long j) {
        try {
            return c.format(new Date(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String format4(long j) {
        try {
            return d.format(new Date(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String format5(long j) {
        try {
            return e.format(new Date(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static String format(long j, String str) {
        try {
            return new SimpleDateFormat(str).format(new Date(j));
        } catch (Throwable unused) {
            return "";
        }
    }

    public static long safeTimeStamp(long j) {
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("");
        return sb.toString().length() == 10 ? j * 1000 : j;
    }

    public static String dateToString(Date date, String str) {
        try {
            return new SimpleDateFormat(str).format(date);
        } catch (Exception unused) {
            return "";
        }
    }
}
