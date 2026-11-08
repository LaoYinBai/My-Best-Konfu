package com.umeng.commonsdk.proguard;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class m {
    private k b;
    private final LinkedBlockingQueue<IBinder> a = new LinkedBlockingQueue<>(1);
    private String c = null;
    private String d = null;
    private ServiceConnection e = new a(this);

    m() {
    }

    String a(Context context, String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot run on MainThread");
        }
        if (this.b == null) {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("android.intent.action.OPEN_ID");
            if (!context.bindService(intent, this.e, 1)) {
                return "";
            }
            try {
                this.b = j.a(this.a.take());
                return b(context, str);
            } catch (Exception e) {
                e.printStackTrace();
                return "";
            }
        }
        try {
            return b(context, str);
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private String b(Context context, String str) {
        this.c = context.getPackageName();
        this.d = n.a(context, this.c, "SHA1");
        return this.b.a(this.c, this.d, str);
    }

    boolean a(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 0);
            return Build.VERSION.SDK_INT >= 28 ? packageInfo != null && packageInfo.getLongVersionCode() >= 1 : packageInfo != null && packageInfo.versionCode >= 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    class a implements ServiceConnection {
        m a;

        a(m mVar) {
            this.a = null;
            this.a = mVar;
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                this.a.a.put(iBinder);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            this.a.b = null;
            this.a.a.clear();
        }
    }
}
