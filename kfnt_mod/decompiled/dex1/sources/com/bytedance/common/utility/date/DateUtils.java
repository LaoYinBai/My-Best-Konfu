package com.bytedance.common.utility.date;

import android.content.Context;
import com.ss.union.game.sdk.core.base.R;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class DateUtils implements DateDef {
    private static final SimpleDateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("MM-dd HH:mm");

    private DateUtils() {
    }

    public static String getDateString(Context context, long j) {
        if (!isCurrentYear(j)) {
            return YEAR_FORMAT.format(Long.valueOf(j));
        }
        if (!android.text.format.DateUtils.isToday(j)) {
            return DAY_FORMAT.format(Long.valueOf(j));
        }
        long jCurrentTimeMillis = System.currentTimeMillis() - j;
        return jCurrentTimeMillis >= DateDef.HOUR ? context.getString(R.string.hours_ago, Long.valueOf(jCurrentTimeMillis / DateDef.HOUR)) : jCurrentTimeMillis >= 60000 ? context.getString(R.string.minutes_ago, Long.valueOf(jCurrentTimeMillis / 60000)) : context.getString(R.string.just_now);
    }

    public static boolean isCurrentYear(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(1);
        calendar.setTimeInMillis(System.currentTimeMillis());
        return i == calendar.get(1);
    }
}
