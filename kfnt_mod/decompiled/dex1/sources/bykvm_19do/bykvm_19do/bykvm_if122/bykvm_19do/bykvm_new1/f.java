package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.bykvm_new1;

import com.bytedance.common.utility.date.DateDef;
import com.bytedance.msdk.adapter.util.Logger;
import java.text.SimpleDateFormat;
import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {
    public static long a(long j) {
        Date date = new Date();
        Date date2 = (j >= DateDef.HOUR || date.getMinutes() <= 30) ? new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), 0, 0) : new Date(date.getYear(), date.getMonth(), date.getDate(), date.getHours(), 30, 0);
        return a(date2.getTime(), j);
    }

    public static String b(long j) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j));
    }

    private static long a(long j, long j2) {
        Date date = new Date(j2 + j);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long time = date.getTime();
        Logger.d("TTMediationSDK_IntervalEffectiveTime", "old time " + j + " new time = " + simpleDateFormat.format(date) + " 毫秒 = " + time);
        return time;
    }
}
