package com.sigmob.sdk.base.services;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Build;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.common.t;
import com.sigmob.sdk.base.services.j;
import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.SDKConfig;
import com.sigmob.sdk.common.b.c;
import com.sigmob.sdk.common.utils.p;
import java.util.Date;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b implements j.a {
    a a;
    k b = k.STOP;

    public b() {
        if (this.a == null) {
            this.a = new a();
        }
    }

    public static void a() {
        try {
            f();
        } catch (Throwable unused) {
        }
    }

    @SuppressLint({"SimpleDateFormat"})
    private static boolean d() {
        if (com.sigmob.sdk.common.a.n() == null || com.sigmob.sdk.common.a.n().af() == null) {
            return true;
        }
        return p.a(com.sigmob.sdk.common.a.n().af(), "BuriedPointConfig").getString(Constants.LAST_DAY, "").equals(Constants.sdf.format(new Date()));
    }

    private static void e() {
        c.a.b().a(new Runnable() { // from class: com.sigmob.sdk.base.services.b.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    List<PackageInfo> listAl = com.sigmob.sdk.common.a.n().al();
                    if (listAl == null) {
                        return;
                    }
                    for (int i = 0; i < listAl.size(); i++) {
                        PackageInfo packageInfo = listAl.get(i);
                        if ((packageInfo.applicationInfo.flags & 1) == 0) {
                            t.a(packageInfo);
                        }
                    }
                    p.a(com.sigmob.sdk.common.a.n().af(), "BuriedPointConfig").edit().putString(Constants.LAST_DAY, Constants.sdf.format(new Date())).commit();
                } catch (Throwable th) {
                    SigmobLog.e("update app info", th);
                }
            }
        });
    }

    private static void f() {
        try {
            if (com.sigmob.sdk.common.a.b()) {
                boolean zD = d();
                if (SDKConfig.sharedInstance().isDisableUpAppInfo() || zD) {
                    return;
                }
                e();
            }
        } catch (Throwable th) {
            SigmobLog.e("BuriedPointManager getInstance", th);
        }
    }

    public void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.INSTALL_PACKAGE");
        if (Build.VERSION.SDK_INT >= 27) {
            intentFilter.addAction("android.intent.action.INSTALL_FAILURE");
        }
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addDataScheme(com.umeng.message.common.a.u);
        new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE");
        context.registerReceiver(this.a, intentFilter);
    }

    public void b(Context context) {
        context.unregisterReceiver(this.a);
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public boolean b() {
        if (this.b == k.RUNNING) {
            return false;
        }
        a(com.sigmob.sdk.common.a.n().af());
        this.b = k.RUNNING;
        return true;
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public void c() {
        if (this.b == k.RUNNING) {
            b(com.sigmob.sdk.common.a.n().af());
            this.b = k.STOP;
        }
    }
}
