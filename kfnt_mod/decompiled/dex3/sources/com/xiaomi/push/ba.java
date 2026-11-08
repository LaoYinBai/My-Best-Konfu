package com.xiaomi.push;

import android.os.IBinder;
import com.xiaomi.push.ay;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ba implements Runnable {
    final /* synthetic */ IBinder a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ ay.b f147a;

    ba(ay.b bVar, IBinder iBinder) {
        this.f147a = bVar;
        this.a = iBinder;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            String packageName = ay.this.f140a.getPackageName();
            String strE = ay.this.e();
            ay.a aVar = new ay.a();
            aVar.f144a = ay.c.a(this.a, packageName, strE, "GUID");
            aVar.b = ay.c.a(this.a, packageName, strE, "OUID");
            aVar.c = ay.c.a(this.a, packageName, strE, "DUID");
            aVar.d = ay.c.a(this.a, packageName, strE, "AUID");
            ay.this.f142a = aVar;
            ay.this.b();
            ay.this.f139a = 2;
            synchronized (ay.this.f143a) {
                try {
                    ay.this.f143a.notifyAll();
                } catch (Exception unused) {
                }
            }
        } catch (Exception unused2) {
            ay.this.b();
            ay.this.f139a = 2;
            synchronized (ay.this.f143a) {
                try {
                    ay.this.f143a.notifyAll();
                } catch (Exception unused3) {
                }
            }
        } catch (Throwable th) {
            ay.this.b();
            ay.this.f139a = 2;
            synchronized (ay.this.f143a) {
                try {
                    ay.this.f143a.notifyAll();
                } catch (Exception unused4) {
                }
                throw th;
            }
        }
    }
}
