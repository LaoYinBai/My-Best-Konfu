package com.cgs.out;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CGManager implements CGISDK {
    public static volatile CGManager d;

    /* renamed from: a, reason: collision with root package name */
    public Object f5334a;

    /* renamed from: b, reason: collision with root package name */
    public Context f5335b;

    /* renamed from: c, reason: collision with root package name */
    public CGConfig f5336c;

    public class a extends Thread {
        public a(CGManager cGManager, String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            long j = 0;
            while (true) {
                j++;
                Context context = null;
                try {
                    context = CGManager.getInstance().getContext();
                } catch (Throwable unused) {
                }
                NativeLib.native_bridge(16777230, context, "" + j);
                try {
                    Thread.currentThread();
                    Thread.sleep(60000L);
                } catch (Throwable unused2) {
                }
            }
        }
    }

    public class b extends Thread {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ String f5337a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public b(String str, String str2) {
            super(str);
            this.f5337a = str2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            NativeLib.native_bridge(16777225, CGManager.this.f5335b, this.f5337a);
        }
    }

    public class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Activity f5339a;

        public c(Activity activity) {
            this.f5339a = activity;
        }

        @Override // java.lang.Runnable
        public void run() {
            CGManager.this.a(this.f5339a);
        }
    }

    public CGManager(Context context, CGConfig cGConfig) {
        this.f5334a = null;
        this.f5335b = null;
        this.f5336c = null;
        if (context == null) {
            throw new NullPointerException("context could not be null");
        }
        if (cGConfig == null) {
            throw new NullPointerException("config could not be null");
        }
        this.f5336c = cGConfig;
        this.f5335b = context.getApplicationContext();
        this.f5334a = NativeLib.native_bridge(16777217, this.f5335b, this.f5336c.a());
    }

    public static CGManager getInstance() {
        return d;
    }

    public static void init(Context context, CGConfig cGConfig) {
        Context applicationContext = null;
        if (context == null) {
            try {
                applicationContext = (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication", new Class[0]).invoke(null, null);
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                } catch (Throwable unused) {
                }
            }
        } else {
            applicationContext = context.getApplicationContext();
        }
        if (d == null) {
            synchronized (CGManager.class) {
                try {
                } catch (Throwable unused2) {
                    System.loadLibrary("labocgs");
                }
                if (Build.VERSION.SDK_INT < 21) {
                    throw new UnsatisfiedLinkError("android version not match");
                }
                if (a.a.c.a.a.f337a == null) {
                    a.a.c.a.a.f337a = applicationContext;
                }
                a.a.c.a.b bVar = a.a.c.a.b.f350a;
                if (bVar != null) {
                    bVar.a("labocgs", false);
                } else {
                    a.a.c.a.c cVar = a.a.c.a.a.f339c;
                    if (cVar != null) {
                        cVar.c("labocgs");
                    } else {
                        System.loadLibrary("labocgs");
                    }
                }
                d = new CGManager(applicationContext, cGConfig);
                d.a();
            }
        }
    }

    public final void a() throws Throwable {
        if (this.f5334a == null) {
            throw new Throwable("cg sdk init failed !?");
        }
        byte[] byteArray = null;
        try {
            byteArray = this.f5335b.getPackageManager().getPackageInfo(this.f5335b.getPackageName(), 64).signatures[0].toByteArray();
        } catch (Throwable unused) {
        }
        NativeLib.native_bridge(16777221, this.f5335b, byteArray);
        new a(this, "548c68d9").start();
    }

    public final void a(CGConfig cGConfig) {
        if (this.f5334a != null) {
            NativeLib.native_bridge(16777220, this.f5335b, cGConfig.a());
        }
    }

    public void a(byte[] bArr) {
        if (this.f5334a == null) {
            return;
        }
        new a.a.a.b(this, "cff8db96", bArr).start();
    }

    public void b(byte[] bArr) {
        if (this.f5334a == null) {
            return;
        }
        new a.a.a.a(this, "9a90504f", bArr).start();
    }

    @Override // com.cgs.out.CGISDK
    public void callbacksLogger(Activity activity) {
        if (!NativeLib.canALC() || NativeLib.getALC()) {
            return;
        }
        if (Looper.getMainLooper() == Looper.myLooper()) {
            a(activity);
        } else {
            new Handler(Looper.getMainLooper()).post(new c(activity));
        }
    }

    public void finalize() throws Throwable {
        if (this.f5334a != null) {
            NativeLib.native_bridge(16777218, this.f5335b, null);
            this.f5334a = null;
        }
        super.finalize();
    }

    public CGConfig getAPConfig() {
        return this.f5336c;
    }

    public Context getContext() {
        return this.f5335b;
    }

    @Override // com.cgs.out.CGISDK
    public void loggerAdClose(long j) {
        try {
            NativeLib.native_bridge(16777227, this.f5335b, String.format("%d", Long.valueOf(j)));
        } catch (Throwable unused) {
        }
    }

    @Override // com.cgs.out.CGISDK
    public void loggerAdStart(long j, long j2) {
        try {
            NativeLib.native_bridge(16777226, this.f5335b, String.format("%d %d", Long.valueOf(j), Long.valueOf(j2)));
        } catch (Throwable unused) {
        }
    }

    @Override // com.cgs.out.CGISDK
    public void reportNow(String str) {
        try {
            new b("08f93ac2", str).start();
        } catch (Throwable unused) {
        }
    }

    @Override // com.cgs.out.CGISDK
    public void reportNow(String str, boolean z) {
        if (z) {
            NativeLib.native_bridge(16777225, this.f5335b, str);
        } else {
            reportNow(str);
        }
    }

    public void setConfigStr(String str) {
        this.f5336c.mConfigStr = CGConfig.strFmt(str);
        a(this.f5336c);
    }

    public void setDeviceId(String str) {
        this.f5336c.mDeviceID = CGConfig.strFmt(str);
        a(this.f5336c);
    }

    public void setExtendInterface(CGExtend cGExtend) {
        this.f5336c.mExtendInterface = cGExtend;
    }

    public void setInstallId(String str) {
        this.f5336c.mInstallID = CGConfig.strFmt(str);
        a(this.f5336c);
    }

    public final void a(Activity activity) {
        if (activity == null) {
            try {
                activity = a.a.c.a.a();
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        activity.getApplication().registerActivityLifecycleCallbacks(a.a.b.a.a.a());
        if (a.a.b.a.a.a().f328b.contains(activity)) {
            return;
        }
        Window window = activity.getWindow();
        if (NativeLib.canWCB() && !NativeLib.getWCB() && window != null) {
            window.setCallback(new a.a.b.a.b(window.getCallback()));
        }
        a.a.b.a.a.a().f328b.f330a.add(new WeakReference<>(activity));
    }
}
