package com.yxcorp.kuaishou.addfp.a.b.j;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    private com.yxcorp.kuaishou.addfp.a.b.b b;
    private Context c;
    public a a = null;
    private CountDownLatch d = new CountDownLatch(1);
    private ServiceConnection e = new e(this);

    private void a(boolean z) {
        if (z) {
            try {
                String strA = this.a.a();
                if (!TextUtils.isEmpty(strA)) {
                    this.b.a(strA);
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.b.e();
    }

    private static boolean b(Context context) throws PackageManager.NameNotFoundException {
        try {
            context.getPackageManager().getPackageInfo("com.mdid.msa", 0);
            return true;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    public final void a(Context context) {
        try {
            if (this.e == null || context == null) {
                return;
            }
            context.unbindService(this.e);
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public final void a(Context context, com.yxcorp.kuaishou.addfp.a.b.b bVar) {
        try {
            this.b = bVar;
            this.c = context;
            if (b(context)) {
                String packageName = context.getPackageName();
                try {
                    Intent intent = new Intent();
                    intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
                    intent.setAction("com.bun.msa.action.start.service");
                    intent.putExtra("com.bun.msa.param.pkgname", packageName);
                    intent.putExtra("com.bun.msa.param.runinset", true);
                    context.startService(intent);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
            Intent intent2 = new Intent();
            intent2.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
            intent2.setAction("com.bun.msa.action.bindto.service");
            intent2.putExtra("com.bun.msa.param.pkgname", context.getPackageName());
            if (!context.bindService(intent2, this.e, 1)) {
                a(false);
                return;
            }
            this.d.await(10L, TimeUnit.SECONDS);
            if (this.a != null) {
                a(true);
            } else {
                a(false);
            }
        } catch (Throwable th2) {
            th2.printStackTrace();
            a(false);
        }
    }

    public final boolean a() {
        try {
            return this.c.getPackageManager().getPackageInfo("com.mdid.msa", 0) != null;
        } catch (Throwable unused) {
            return false;
        }
    }
}
