package com.yxcorp.kuaishou.addfp.a.b.b;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class a {
    private com.yxcorp.kuaishou.addfp.a.b.b a;
    private Context b;
    private d c = null;
    private CountDownLatch d = new CountDownLatch(1);
    private ServiceConnection e = new b(this);

    public a(Context context) {
        this.b = context;
    }

    private void a(boolean z) {
        try {
            if (!z) {
                this.a.e();
                return;
            }
            String strA = this.c.a();
            if (TextUtils.isEmpty(strA)) {
                this.a.a(strA);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a() {
        if (this.e == null || this.b == null) {
            return;
        }
        this.b.unbindService(this.e);
    }

    public final void a(com.yxcorp.kuaishou.addfp.a.b.b bVar) {
        try {
            this.a = bVar;
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            intent.setPackage("com.huawei.hwid");
            if (!this.b.bindService(intent, this.e, 1)) {
                a(false);
                return;
            }
            this.d.await(10L, TimeUnit.SECONDS);
            if (this.c != null) {
                a(true);
            } else {
                a(false);
            }
        } catch (Throwable th) {
            th.printStackTrace();
            a(false);
        }
    }
}
