package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ai {
    private static volatile ai a;

    /* renamed from: a, reason: collision with other field name */
    private SharedPreferences f106a;

    /* renamed from: a, reason: collision with other field name */
    private ScheduledThreadPoolExecutor f109a = new ScheduledThreadPoolExecutor(1);

    /* renamed from: a, reason: collision with other field name */
    private Map<String, ScheduledFuture> f108a = new HashMap();

    /* renamed from: a, reason: collision with other field name */
    private Object f107a = new Object();

    public static abstract class a implements Runnable {
        /* renamed from: a */
        public abstract String mo136a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class b implements Runnable {
        a a;

        public b(a aVar) {
            this.a = aVar;
        }

        void a() {
        }

        void b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
            this.a.run();
            b();
        }
    }

    private ai(Context context) {
        this.f106a = context.getSharedPreferences("mipush_extra", 0);
    }

    public static ai a(Context context) {
        if (a == null) {
            synchronized (ai.class) {
                if (a == null) {
                    a = new ai(context);
                }
            }
        }
        return a;
    }

    private static String a(String str) {
        return "last_job_time" + str;
    }

    private ScheduledFuture a(a aVar) {
        ScheduledFuture scheduledFuture;
        synchronized (this.f107a) {
            scheduledFuture = this.f108a.get(aVar.mo136a());
        }
        return scheduledFuture;
    }

    public void a(Runnable runnable) {
        a(runnable, 0);
    }

    public void a(Runnable runnable, int i) {
        this.f109a.schedule(runnable, i, TimeUnit.SECONDS);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m93a(a aVar) {
        return b(aVar, 0);
    }

    public boolean a(a aVar, int i) {
        return a(aVar, i, 0);
    }

    public boolean a(a aVar, int i, int i2) {
        return a(aVar, i, i2, false);
    }

    public boolean a(a aVar, int i, int i2, boolean z) {
        if (aVar == null || a(aVar) != null) {
            return false;
        }
        String strA = a(aVar.mo136a());
        aj ajVar = new aj(this, aVar, z, strA);
        if (!z) {
            long jAbs = Math.abs(System.currentTimeMillis() - this.f106a.getLong(strA, 0L)) / 1000;
            if (jAbs < i - i2) {
                i2 = (int) (i - jAbs);
            }
        }
        try {
            ScheduledFuture<?> scheduledFutureScheduleAtFixedRate = this.f109a.scheduleAtFixedRate(ajVar, i2, i, TimeUnit.SECONDS);
            synchronized (this.f107a) {
                this.f108a.put(aVar.mo136a(), scheduledFutureScheduleAtFixedRate);
            }
            return true;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            return true;
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m94a(String str) {
        synchronized (this.f107a) {
            ScheduledFuture scheduledFuture = this.f108a.get(str);
            if (scheduledFuture == null) {
                return false;
            }
            this.f108a.remove(str);
            return scheduledFuture.cancel(false);
        }
    }

    public boolean b(a aVar, int i) {
        if (aVar == null || a(aVar) != null) {
            return false;
        }
        ScheduledFuture<?> scheduledFutureSchedule = this.f109a.schedule(new ak(this, aVar), i, TimeUnit.SECONDS);
        synchronized (this.f107a) {
            this.f108a.put(aVar.mo136a(), scheduledFutureSchedule);
        }
        return true;
    }
}
