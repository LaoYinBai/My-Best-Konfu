package com.yxcorp.kuaishou.addfp.a.b.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class e {
    public b a;
    private Context b;
    private com.yxcorp.kuaishou.addfp.a.b.b c;
    private CountDownLatch d = new CountDownLatch(1);
    private ServiceConnection e = new f(this);

    public e(Context context) {
        this.b = context;
    }

    private void a(boolean z) {
        if (z) {
            try {
                String strA = this.a.a();
                if (!TextUtils.isEmpty(strA)) {
                    this.c.a(strA);
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.c.e();
    }

    public final void a() {
        if (this.e == null || this.b == null) {
            return;
        }
        this.b.unbindService(this.e);
    }

    public final void a(com.yxcorp.kuaishou.addfp.a.b.b bVar) {
        try {
            this.c = bVar;
            Intent intent = new Intent("com.asus.msa.action.ACCESS_DID");
            ComponentName componentName = new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService");
            Intent intent2 = new Intent(intent);
            intent2.setComponent(componentName);
            if (!this.b.bindService(intent2, this.e, 1)) {
                a(false);
                return;
            }
            this.d.await(10L, TimeUnit.SECONDS);
            if (this.a != null) {
                a(true);
            } else {
                a(false);
            }
        } catch (Throwable unused) {
            a(false);
        }
    }
}
