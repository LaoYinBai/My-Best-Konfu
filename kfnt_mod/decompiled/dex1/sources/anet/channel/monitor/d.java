package anet.channel.monitor;

import anet.channel.util.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class d implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ long f466a;

    /* renamed from: b, reason: collision with root package name */
    final /* synthetic */ long f467b;

    /* renamed from: c, reason: collision with root package name */
    final /* synthetic */ long f468c;
    final /* synthetic */ b d;

    d(b bVar, long j, long j2, long j3) {
        this.d = bVar;
        this.f466a = j;
        this.f467b = j2;
        this.f468c = j3;
    }

    @Override // java.lang.Runnable
    public void run() {
        b.f461a++;
        b.e += this.f466a;
        if (b.f461a == 1) {
            b.d = this.f467b - this.f468c;
        }
        if (b.f461a >= 2 && b.f461a <= 3) {
            if (this.f468c >= b.f463c) {
                b.d += this.f467b - this.f468c;
            } else if (this.f468c < b.f463c && this.f467b >= b.f463c) {
                b.d += this.f467b - this.f468c;
                b.d -= b.f463c - this.f468c;
            }
        }
        b.f462b = this.f468c;
        b.f463c = this.f467b;
        if (b.f461a == 3) {
            b.i = (long) this.d.n.a(b.e, b.d);
            b.f++;
            b.b(this.d);
            if (b.f > 30) {
                this.d.n.a();
                b.f = 3L;
            }
            double d = (b.i * 0.68d) + (b.h * 0.27d) + (b.g * 0.05d);
            b.g = b.h;
            b.h = b.i;
            if (b.i < b.g * 0.65d || b.i > b.g * 2.0d) {
                b.i = d;
            }
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.BandWidthSampler", "NetworkSpeed", null, "mKalmanDataSize", Long.valueOf(b.e), "mKalmanTimeUsed", Long.valueOf(b.d), "speed", Double.valueOf(b.i), "mSpeedKalmanCount", Long.valueOf(b.f));
            }
            if (this.d.m > 5 || b.f == 2) {
                a.a().a(b.i);
                this.d.m = 0;
                this.d.l = b.i < b.j ? 1 : 5;
                ALog.i("awcn.BandWidthSampler", "NetworkSpeed notification!", null, "Send Network quality notification.");
            }
            b.d = 0L;
            b.e = 0L;
            b.f461a = 0;
        }
    }
}
