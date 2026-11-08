package com.taobao.accs.net;

import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import com.taobao.accs.internal.AccsJobService;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class f {
    protected static volatile f b;
    private static final int[] c = {270, 360, 480};
    protected Context a;
    private int d;
    private long e;
    private boolean f = false;
    private int[] g = {0, 0, 0};
    private boolean h;

    protected abstract void a(int i);

    protected f(Context context) {
        this.h = true;
        try {
            this.a = context;
            this.d = 0;
            this.e = System.currentTimeMillis();
            this.h = com.taobao.accs.utl.t.b();
        } catch (Throwable th) {
            ALog.e("HeartbeatManager", "HeartbeatManager", th, new Object[0]);
        }
    }

    public static f a(Context context) {
        if (b == null) {
            synchronized (f.class) {
                if (b == null) {
                    if (Build.VERSION.SDK_INT >= 21 && b(context)) {
                        ALog.i("HeartbeatManager", "hb use job", new Object[0]);
                        b = new r(context);
                    } else {
                        ALog.i("HeartbeatManager", "hb use alarm", new Object[0]);
                        b = new a(context);
                    }
                }
            }
        }
        return b;
    }

    private static boolean b(Context context) {
        try {
            return context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), AccsJobService.class.getName()), 0).isEnabled();
        } catch (Throwable th) {
            ALog.e("HeartbeatManager", "isJobServiceExist", th, new Object[0]);
            return false;
        }
    }

    public synchronized void a() {
        try {
            if (this.e < 0) {
                this.e = System.currentTimeMillis();
            }
            int iB = b();
            if (ALog.isPrintLog(ALog.Level.D)) {
                ALog.d("HeartbeatManager", "set " + iB, new Object[0]);
            }
            a(iB);
        } catch (Throwable th) {
            ALog.e("HeartbeatManager", "set", th, new Object[0]);
        }
    }

    public int b() {
        int i = this.h ? c[this.d] : 270;
        this.h = com.taobao.accs.utl.t.b();
        return i;
    }

    public void c() {
        this.e = -1L;
        if (this.f) {
            int[] iArr = this.g;
            int i = this.d;
            iArr[i] = iArr[i] + 1;
        }
        this.d = this.d > 0 ? this.d - 1 : 0;
        ALog.d("HeartbeatManager", "onNetworkTimeout", new Object[0]);
    }

    public void d() {
        this.e = -1L;
        ALog.d("HeartbeatManager", "onNetworkFail", new Object[0]);
    }

    public void e() {
        ALog.d("HeartbeatManager", "onHeartbeatSucc", new Object[0]);
        if (System.currentTimeMillis() - this.e > 7199000) {
            if (this.d >= c.length - 1 || this.g[this.d] > 2) {
                return;
            }
            ALog.d("HeartbeatManager", "upgrade", new Object[0]);
            this.d++;
            this.f = true;
            this.e = System.currentTimeMillis();
            return;
        }
        this.f = false;
        this.g[this.d] = 0;
    }

    public void f() {
        this.d = 0;
        this.e = System.currentTimeMillis();
        ALog.d("HeartbeatManager", "resetLevel", new Object[0]);
    }
}
