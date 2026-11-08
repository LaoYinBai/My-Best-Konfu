package anet.channel.strategy.utils;

import anet.channel.util.ALog;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static AtomicInteger f589a = new AtomicInteger(0);

    /* renamed from: b, reason: collision with root package name */
    private static ScheduledThreadPoolExecutor f590b = null;

    static ScheduledThreadPoolExecutor a() {
        if (f590b == null) {
            synchronized (a.class) {
                if (f590b == null) {
                    f590b = new ScheduledThreadPoolExecutor(2, new b());
                    f590b.setKeepAliveTime(60L, TimeUnit.SECONDS);
                    f590b.allowCoreThreadTimeOut(true);
                }
            }
        }
        return f590b;
    }

    public static void a(Runnable runnable) {
        try {
            a().submit(runnable);
        } catch (Exception e) {
            ALog.e(anet.channel.strategy.dispatch.b.TAG, "submit task failed", null, e, new Object[0]);
        }
    }

    public static void a(Runnable runnable, long j) {
        try {
            a().schedule(runnable, j, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            ALog.e(anet.channel.strategy.dispatch.b.TAG, "schedule task failed", null, e, new Object[0]);
        }
    }
}
