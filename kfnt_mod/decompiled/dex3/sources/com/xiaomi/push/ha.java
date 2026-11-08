package com.xiaomi.push;

import android.content.Context;
import android.net.TrafficStats;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ha implements fs {
    private int a;

    /* renamed from: a, reason: collision with other field name */
    fp f457a;

    /* renamed from: a, reason: collision with other field name */
    XMPushService f458a;

    /* renamed from: a, reason: collision with other field name */
    private Exception f459a;
    private long e;
    private long f;

    /* renamed from: a, reason: collision with other field name */
    private long f456a = 0;
    private long b = 0;
    private long c = 0;
    private long d = 0;

    /* renamed from: a, reason: collision with other field name */
    private String f460a = "";

    ha(XMPushService xMPushService) {
        this.e = 0L;
        this.f = 0L;
        this.f458a = xMPushService;
        b();
        int iMyUid = Process.myUid();
        try {
            this.f = TrafficStats.getUidRxBytes(iMyUid);
            this.e = TrafficStats.getUidTxBytes(iMyUid);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Failed to obtain traffic data during initialization: " + e);
            this.f = -1L;
            this.e = -1L;
        }
    }

    private void b() {
        this.b = 0L;
        this.d = 0L;
        this.f456a = 0L;
        this.c = 0L;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (bg.b(this.f458a)) {
            this.f456a = jElapsedRealtime;
        }
        if (this.f458a.m574c()) {
            this.c = jElapsedRealtime;
        }
    }

    private synchronized void c() {
        com.xiaomi.channel.commonutils.logger.b.c("stat connpt = " + this.f460a + " netDuration = " + this.b + " ChannelDuration = " + this.d + " channelConnectedTime = " + this.c);
        fd fdVar = new fd();
        fdVar.f351a = (byte) 0;
        fdVar.a(fc.CHANNEL_ONLINE_RATE.a());
        fdVar.a(this.f460a);
        fdVar.d((int) (System.currentTimeMillis() / 1000));
        fdVar.b((int) (this.b / 1000));
        fdVar.c((int) (this.d / 1000));
        hb.m374a().a(fdVar);
        b();
    }

    Exception a() {
        return this.f459a;
    }

    /* renamed from: a, reason: collision with other method in class */
    public synchronized void m373a() {
        if (this.f458a == null) {
            return;
        }
        String strM124a = bg.m124a((Context) this.f458a);
        boolean zB = bg.b(this.f458a);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (this.f456a > 0) {
            this.b += jElapsedRealtime - this.f456a;
            this.f456a = 0L;
        }
        if (this.c != 0) {
            this.d += jElapsedRealtime - this.c;
            this.c = 0L;
        }
        if (zB) {
            if ((!TextUtils.equals(this.f460a, strM124a) && this.b > com.umeng.commonsdk.proguard.b.d) || this.b > 5400000) {
                c();
            }
            this.f460a = strM124a;
            if (this.f456a == 0) {
                this.f456a = jElapsedRealtime;
            }
            if (this.f458a.m574c()) {
                this.c = jElapsedRealtime;
            }
        }
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar) {
        m373a();
        this.c = SystemClock.elapsedRealtime();
        hd.a(0, fc.CONN_SUCCESS.a(), fpVar.mo331a(), fpVar.a());
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar, int i, Exception exc) {
        long uidTxBytes;
        if (this.a == 0 && this.f459a == null) {
            this.a = i;
            this.f459a = exc;
            hd.b(fpVar.mo331a(), exc);
        }
        if (i == 22 && this.c != 0) {
            long jM329a = fpVar.m329a() - this.c;
            if (jM329a < 0) {
                jM329a = 0;
            }
            this.d += jM329a + (fv.b() / 2);
            this.c = 0L;
        }
        m373a();
        int iMyUid = Process.myUid();
        long j = -1;
        try {
            long uidRxBytes = TrafficStats.getUidRxBytes(iMyUid);
            uidTxBytes = TrafficStats.getUidTxBytes(iMyUid);
            j = uidRxBytes;
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Failed to obtain traffic data: " + e);
            uidTxBytes = -1L;
        }
        com.xiaomi.channel.commonutils.logger.b.c("Stats rx=" + (j - this.f) + ", tx=" + (uidTxBytes - this.e));
        this.f = j;
        this.e = uidTxBytes;
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar, Exception exc) {
        hd.a(0, fc.CHANNEL_CON_FAIL.a(), 1, fpVar.mo331a(), bg.c(this.f458a) ? 1 : 0);
        m373a();
    }

    @Override // com.xiaomi.push.fs
    public void b(fp fpVar) {
        this.a = 0;
        this.f459a = null;
        this.f457a = fpVar;
        this.f460a = bg.m124a((Context) this.f458a);
        hd.a(0, fc.CONN_SUCCESS.a());
    }
}
