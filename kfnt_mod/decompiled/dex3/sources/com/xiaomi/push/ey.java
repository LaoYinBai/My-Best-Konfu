package com.xiaomi.push;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import com.xiaomi.push.ex;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ey implements ex.a {

    /* renamed from: a, reason: collision with other field name */
    protected Context f343a;

    /* renamed from: a, reason: collision with other field name */
    private PendingIntent f342a = null;
    private volatile long a = 0;

    public ey(Context context) {
        this.f343a = null;
        this.f343a = context;
    }

    private void a(AlarmManager alarmManager, long j, PendingIntent pendingIntent) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        try {
            AlarmManager.class.getMethod("setExact", Integer.TYPE, Long.TYPE, PendingIntent.class).invoke(alarmManager, 0, Long.valueOf(j), pendingIntent);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
        }
    }

    long a() {
        return fv.b();
    }

    @Override // com.xiaomi.push.ex.a
    /* renamed from: a, reason: collision with other method in class */
    public void mo307a() {
        if (this.f342a != null) {
            try {
                ((AlarmManager) this.f343a.getSystemService("alarm")).cancel(this.f342a);
            } catch (Exception unused) {
            } catch (Throwable th) {
                this.f342a = null;
                com.xiaomi.channel.commonutils.logger.b.c("unregister timer");
                this.a = 0L;
                throw th;
            }
            this.f342a = null;
            com.xiaomi.channel.commonutils.logger.b.c("unregister timer");
            this.a = 0L;
        }
        this.a = 0L;
    }

    public void a(Intent intent, long j) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        AlarmManager alarmManager = (AlarmManager) this.f343a.getSystemService("alarm");
        this.f342a = PendingIntent.getBroadcast(this.f343a, 0, intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            bh.a((Object) alarmManager, "setExactAndAllowWhileIdle", 0, Long.valueOf(j), this.f342a);
        } else if (Build.VERSION.SDK_INT >= 19) {
            a(alarmManager, j, this.f342a);
        } else {
            alarmManager.set(0, j, this.f342a);
        }
        com.xiaomi.channel.commonutils.logger.b.c("register timer " + j);
    }

    @Override // com.xiaomi.push.ex.a
    public void a(boolean z) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        long jA = a();
        if (z || this.a != 0) {
            if (z) {
                mo307a();
            }
            if (!z && this.a != 0) {
                this.a += jA;
                if (this.a < System.currentTimeMillis()) {
                }
                Intent intent = new Intent(com.xiaomi.push.service.az.o);
                intent.setPackage(this.f343a.getPackageName());
                a(intent, this.a);
            }
            jA -= SystemClock.elapsedRealtime() % jA;
            this.a = System.currentTimeMillis() + jA;
            Intent intent2 = new Intent(com.xiaomi.push.service.az.o);
            intent2.setPackage(this.f343a.getPackageName());
            a(intent2, this.a);
        }
    }

    @Override // com.xiaomi.push.ex.a
    /* renamed from: a */
    public boolean mo306a() {
        return this.a != 0;
    }
}
