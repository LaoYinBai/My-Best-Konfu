package anet.channel.strategy;

import com.bytedance.common.utility.date.DateDef;
import java.io.Serializable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class ConnHistoryItem implements Serializable {

    /* renamed from: a, reason: collision with root package name */
    byte f512a = 0;

    /* renamed from: b, reason: collision with root package name */
    long f513b = 0;

    /* renamed from: c, reason: collision with root package name */
    long f514c = 0;

    ConnHistoryItem() {
    }

    void a(boolean z) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - (z ? this.f513b : this.f514c) > 10000) {
            this.f512a = (byte) ((this.f512a << 1) | (!z ? 1 : 0));
            if (z) {
                this.f513b = jCurrentTimeMillis;
            } else {
                this.f514c = jCurrentTimeMillis;
            }
        }
    }

    int a() {
        int i = 0;
        for (int i2 = this.f512a & 255; i2 > 0; i2 >>= 1) {
            i += i2 & 1;
        }
        return i;
    }

    boolean b() {
        return (this.f512a & 1) == 1;
    }

    boolean c() {
        return a() >= 3 && System.currentTimeMillis() - this.f514c <= 300000;
    }

    boolean d() {
        long j = this.f513b > this.f514c ? this.f513b : this.f514c;
        return j != 0 && System.currentTimeMillis() - j > DateDef.DAY;
    }
}
