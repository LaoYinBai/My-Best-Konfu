package com.mbridge.msdk.system;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.MBridgeSDK;
import com.mbridge.msdk.click.c;
import com.mbridge.msdk.foundation.controller.authoritycontroller.AuthorityInfoBean;
import com.mbridge.msdk.foundation.controller.authoritycontroller.CallBackForDeveloper;
import com.mbridge.msdk.foundation.controller.b;
import com.mbridge.msdk.foundation.db.i;
import com.mbridge.msdk.foundation.download.MBDownloadConfig;
import com.mbridge.msdk.foundation.download.MBDownloadManager;
import com.mbridge.msdk.foundation.download.database.IDatabaseOpenHelper;
import com.mbridge.msdk.foundation.download.resource.ResourceConfig;
import com.mbridge.msdk.foundation.download.utils.ILogger;
import com.mbridge.msdk.foundation.same.net.n;
import com.mbridge.msdk.foundation.same.report.d;
import com.mbridge.msdk.foundation.tools.j;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.s;
import com.mbridge.msdk.out.SDKInitStatusListener;
import com.mbridge.msdk.widget.custom.CustomViewMessageWrap;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* compiled from: MBridgeSDKImpl.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a implements MBridgeSDK {
    public static Map<String, String> a;
    private static final Lock c = new ReentrantReadWriteLock().writeLock();
    private volatile Context e;
    private SDKInitStatusListener j;
    private volatile MBridgeSDK.PLUGIN_LOAD_STATUS d = MBridgeSDK.PLUGIN_LOAD_STATUS.INITIAL;
    public boolean b = true;
    private boolean f = false;
    private BroadcastReceiver g = null;
    private boolean h = false;
    private BroadcastReceiver i = null;
    private boolean k = false;
    private final Application.ActivityLifecycleCallbacks l = new Application.ActivityLifecycleCallbacks() { // from class: com.mbridge.msdk.system.a.1
        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityCreated(Activity activity, Bundle bundle) {
            j.a().c();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStarted(Activity activity) {
            int iB = com.mbridge.msdk.foundation.controller.a.f().b();
            s.d("com.mbridge.msdk", "onActivityStarted currentActivityNum:" + iB);
            s.d("com.mbridge.msdk", "onActivityStarted isCoolStart:" + a.this.b);
            if (!a.this.b && iB == 0) {
                d.a("1");
            }
            c.a(activity, iB, a.this.b, a.this.e);
            a.this.b = false;
            com.mbridge.msdk.foundation.controller.a.f().a(iB + 1);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityPaused(Activity activity) {
            s.d("com.mbridge.msdk", "onActivityPaused currentActivityNum:" + com.mbridge.msdk.foundation.controller.a.f().b());
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            int iB = com.mbridge.msdk.foundation.controller.a.f().b();
            s.d("com.mbridge.msdk", "onActivityStopped currentActivityNum:" + iB);
            if (iB <= 1) {
                d.a("2");
            }
            com.mbridge.msdk.foundation.controller.a.f().a(iB - 1);
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            j.a().d();
        }
    };

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void setThirdPartyFeatures(Map<String, Object> map) {
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void showUserPrivateInfoTips(Context context, CallBackForDeveloper callBackForDeveloper) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context, String str) throws PackageManager.NameNotFoundException {
        if (context != null) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 2);
                if (packageInfo != null && packageInfo.receivers != null) {
                    for (ActivityInfo activityInfo : packageInfo.receivers) {
                        if (activityInfo != null && str.equals(activityInfo.name)) {
                            return true;
                        }
                    }
                }
            } catch (PackageManager.NameNotFoundException e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }
        return false;
    }

    private void a() {
        c.lock();
        this.k = false;
        try {
            if (this.e instanceof Application) {
                Application application = (Application) this.e;
                s.d("com.mbridge.msdk", "registerActivityLifecycleListener:" + application.getPackageName());
                application.registerActivityLifecycleCallbacks(this.l);
            }
            n.a(this.e);
            b.a().a(a, this.e);
            this.d = MBridgeSDK.PLUGIN_LOAD_STATUS.COMPLETED;
            new Thread(new Runnable() { // from class: com.mbridge.msdk.system.a.2
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    if (a.this.e != null) {
                        try {
                            CustomViewMessageWrap.copyViewFile(a.this.e, "mbridge_download_dialog_view.xml");
                        } catch (Exception unused) {
                        }
                        if (a.a(a.this, a.this.e)) {
                            if (a.this.a(a.this.e, "com.mbridge.msdk.click.AppReceiver")) {
                                a.b(a.this, a.this.e);
                            }
                            if (a.this.a(a.this.e, "com.alphab.receiver.AlphabReceiver")) {
                                a.c(a.this, a.this.e);
                            }
                        }
                    }
                }
            }).start();
            new Thread(new Runnable() { // from class: com.mbridge.msdk.system.a.3
                @Override // java.lang.Runnable
                public final void run() {
                    Looper.prepare();
                    com.mbridge.msdk.b.b.a.a().e();
                    Looper.loop();
                }
            }).start();
            com.mbridge.msdk.foundation.same.net.g.d.c().d();
            HandlerThread handlerThread = new HandlerThread("mb_db_thread");
            handlerThread.start();
            Handler handler = new Handler(handlerThread.getLooper());
            MBDownloadConfig.Builder builder = new MBDownloadConfig.Builder();
            builder.setDatabaseHandler(handler);
            builder.setDatabaseOpenHelper(new IDatabaseOpenHelper() { // from class: com.mbridge.msdk.system.a.4
                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseOpenHelper
                public final SQLiteDatabase getReadableDatabase() {
                    return i.a(com.mbridge.msdk.foundation.controller.a.f().j()).a();
                }

                @Override // com.mbridge.msdk.foundation.download.database.IDatabaseOpenHelper
                public final SQLiteDatabase getWritableDatabase() {
                    return i.a(com.mbridge.msdk.foundation.controller.a.f().j()).b();
                }
            });
            builder.setLogger(new ILogger() { // from class: com.mbridge.msdk.system.a.5
                @Override // com.mbridge.msdk.foundation.download.utils.ILogger
                public final void log(String str, String str2) {
                    s.a(str, str2);
                }

                @Override // com.mbridge.msdk.foundation.download.utils.ILogger
                public final void log(String str, Exception exc) {
                    s.a(str, exc.getMessage());
                }
            });
            MBDownloadManager.getInstance().initialize(com.mbridge.msdk.foundation.controller.a.f().j(), builder.build(), new ResourceConfig.Builder().setMaxStorageSpace(100L).setMaxStorageTime(259200000L).build());
            com.mbridge.msdk.foundation.same.report.a.a().b();
            if (this.j != null && !this.k) {
                this.k = true;
                this.j.onInitSuccess();
            }
            try {
                j.a().a(this.e.getApplicationContext());
            } catch (Exception e) {
                s.b("com.mbridge.msdk", "INIT", e);
            }
        } catch (Exception e2) {
            if (MBridgeConstans.DEBUG) {
                s.b("com.mbridge.msdk", "无法初始化MMSDK", e2);
                e2.printStackTrace();
            }
            if (this.j != null && !this.k) {
                this.k = true;
                this.j.onInitFail(e2.getMessage());
            }
        }
        c.unlock();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final MBridgeSDK.PLUGIN_LOAD_STATUS getStatus() {
        return this.d;
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void preload(Map<String, Object> map) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        if (this.d == MBridgeSDK.PLUGIN_LOAD_STATUS.COMPLETED) {
            b.a().a(map, 0);
        }
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void setUserPrivateInfoType(Context context, String str, int i) {
        a(context);
        com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(str, i);
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void setDoNotTrackStatus(boolean z) {
        com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().b(z ? 1 : 0);
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void setDoNotTrackStatus(Context context, boolean z) {
        a(context);
        com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().b(z ? 1 : 0);
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final AuthorityInfoBean userPrivateInfo(Context context) {
        a(context);
        return com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().b();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void setConsentStatus(Context context, int i) {
        a(context);
        com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(i);
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final boolean getConsentStatus(Context context) {
        a(context);
        return com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().e();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void preloadFrame(Map<String, Object> map) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException {
        b.a().a(map, 1);
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final Map<String, String> getMBConfigurationMap(String str, String str2) {
        HashMap map = new HashMap();
        map.put(MBridgeConstans.ID_MBRIDGE_APPID, str);
        map.put(MBridgeConstans.ID_MBRIDGE_APPKEY, str2);
        map.put(MBridgeConstans.ID_MBRIDGE_STARTUPCRASH, String.valueOf(1));
        return map;
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void release() {
        if (this.d == MBridgeSDK.PLUGIN_LOAD_STATUS.COMPLETED) {
            b.a().b();
        }
        Context context = this.e;
        if (context != null && this.g != null && this.f) {
            this.f = false;
            context.unregisterReceiver(this.g);
        }
        Context context2 = this.e;
        if (context2 != null && this.i != null && this.h) {
            this.h = false;
            context2.unregisterReceiver(this.i);
        }
        if (this.e instanceof Application) {
            Application application = (Application) this.e;
            s.d("com.mbridge.msdk", "unregisterActivityLifecycleListener:" + application.getPackageName());
            application.unregisterActivityLifecycleCallbacks(this.l);
        }
        com.mbridge.msdk.b.b.a.a().b();
    }

    public final void a(Application application) {
        this.e = application.getApplicationContext();
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void init(Map<String, String> map, Application application) {
        this.e = application.getApplicationContext();
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void initAsync(Map<String, String> map, Application application) {
        this.e = application.getApplicationContext();
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void init(Map<String, String> map, Context context) {
        this.e = context.getApplicationContext();
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void initAsync(Map<String, String> map, Context context) {
        this.e = context.getApplicationContext();
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void init(Map<String, String> map, Application application, SDKInitStatusListener sDKInitStatusListener) {
        this.e = application;
        this.j = sDKInitStatusListener;
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void initAsync(Map<String, String> map, Application application, SDKInitStatusListener sDKInitStatusListener) {
        this.e = application;
        this.j = sDKInitStatusListener;
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void init(Map<String, String> map, Context context, SDKInitStatusListener sDKInitStatusListener) {
        this.e = context.getApplicationContext();
        this.j = sDKInitStatusListener;
        a = map;
        a();
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void initAsync(Map<String, String> map, Context context, SDKInitStatusListener sDKInitStatusListener) {
        this.e = context.getApplicationContext();
        this.j = sDKInitStatusListener;
        a = map;
        a();
    }

    private void a(Context context) {
        if (com.mbridge.msdk.foundation.controller.a.f().j() != null || context == null) {
            return;
        }
        com.mbridge.msdk.foundation.controller.a.f().b(context);
    }

    @Override // com.mbridge.msdk.MBridgeSDK
    public final void updateDialogWeakActivity(WeakReference<Activity> weakReference) {
        com.mbridge.msdk.foundation.controller.a.f().a(weakReference);
    }

    static /* synthetic */ boolean a(a aVar, Context context) {
        return (context != null ? o.v(context) : 0) >= 26 && Build.VERSION.SDK_INT >= 26;
    }

    static /* synthetic */ void b(a aVar, Context context) {
        if (context == null || aVar.f) {
            return;
        }
        aVar.f = true;
        try {
            aVar.g = (BroadcastReceiver) Class.forName("com.mbridge.msdk.click.AppReceiver").newInstance();
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            context.registerReceiver(aVar.g, intentFilter);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (InstantiationException e3) {
            e3.printStackTrace();
        }
    }

    static /* synthetic */ void c(a aVar, Context context) {
        Class<?> cls;
        if (context != null) {
            try {
                if (aVar.h || (cls = Class.forName("com.alphab.receiver.AlphabReceiver")) == null || !(cls.newInstance() instanceof BroadcastReceiver)) {
                    return;
                }
                aVar.h = true;
                aVar.i = (BroadcastReceiver) cls.newInstance();
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
                intentFilter.addDataScheme("package");
                context.registerReceiver(aVar.i, intentFilter);
            } catch (ClassNotFoundException e) {
                if (MBridgeConstans.DEBUG) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                if (MBridgeConstans.DEBUG) {
                    e2.printStackTrace();
                }
            }
        }
    }
}
