package com.yxcorp.kuaishou.addfp.a.b.c;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e {
    private Context a;
    private a b;
    private com.yxcorp.kuaishou.addfp.a.b.b c;
    private CountDownLatch d = new CountDownLatch(1);
    private ServiceConnection e = new f(this);

    public e(Context context, com.yxcorp.kuaishou.addfp.a.b.b bVar) {
        this.a = null;
        try {
            this.a = context;
            this.c = bVar;
            Intent intent = new Intent();
            intent.setClassName("com.zui.deviceidservice", "com.zui.deviceidservice.DeviceidService");
            if (!this.a.bindService(intent, this.e, 1)) {
                a(false);
                return;
            }
            this.d.await(10L, TimeUnit.SECONDS);
            if (this.b != null) {
                a(true);
            } else {
                a(false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            a(false);
        }
    }

    private void a(boolean z) {
        if (z) {
            try {
                String strC = c();
                if (!TextUtils.isEmpty(strC)) {
                    this.c.a(strC);
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.c.e();
    }

    private String c() {
        try {
            if (this.b != null) {
                return this.b.a();
            }
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final boolean a() {
        try {
            return this.a.getPackageManager().getPackageInfo("com.zui.deviceidservice", 0) != null;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    public final void b() {
        try {
            this.a.unbindService(this.e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        this.b = null;
    }
}
