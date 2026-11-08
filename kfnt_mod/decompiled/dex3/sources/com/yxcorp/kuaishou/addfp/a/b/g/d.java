package com.yxcorp.kuaishou.addfp.a.b.g;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.RemoteException;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    private com.yxcorp.kuaishou.addfp.a.b.b c;
    private Context d;
    private a a = null;
    private String b = null;
    private CountDownLatch e = new CountDownLatch(1);
    private ServiceConnection f = new e(this);

    private void a(boolean z) {
        if (z) {
            try {
                String strB = b();
                if (!TextUtils.isEmpty(strB)) {
                    this.c.a(strB);
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.c.e();
    }

    private String b() {
        try {
            if (this.a != null) {
                return this.a.a();
            }
            return null;
        } catch (RemoteException unused) {
            return null;
        }
    }

    public final void a(Context context) {
        if (this.f == null || context == null) {
            return;
        }
        context.unbindService(this.f);
    }

    public final void a(Context context, com.yxcorp.kuaishou.addfp.a.b.b bVar) {
        try {
            this.c = bVar;
            this.d = context;
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
            if (!context.bindService(intent, this.f, 1)) {
                a(false);
                return;
            }
            this.e.await(10L, TimeUnit.SECONDS);
            if (this.a != null) {
                a(true);
            } else {
                a(false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            a(false);
        }
    }

    public final boolean a() {
        return this.a != null;
    }
}
