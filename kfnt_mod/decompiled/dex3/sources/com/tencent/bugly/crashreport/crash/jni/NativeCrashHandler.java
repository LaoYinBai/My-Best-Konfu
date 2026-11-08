package com.tencent.bugly.crashreport.crash.jni;

import android.annotation.SuppressLint;
import android.content.Context;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.crashreport.crash.c;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import com.umeng.message.common.inter.ITagManager;
import java.io.File;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class NativeCrashHandler implements com.tencent.bugly.crashreport.a {
    private static NativeCrashHandler a;
    private static boolean l;
    private static boolean m;
    private final Context b;
    private final com.tencent.bugly.crashreport.common.info.a c;
    private final w d;
    private NativeExceptionHandler e;
    private String f;
    private final boolean g;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;
    private com.tencent.bugly.crashreport.crash.b n;

    protected native boolean appendNativeLog(String str, String str2, String str3);

    protected native boolean appendWholeNativeLog(String str);

    protected native String getNativeKeyValueList();

    protected native String getNativeLog();

    protected native boolean putNativeKeyValue(String str, String str2);

    protected native String regist(String str, boolean z, int i);

    protected native String removeNativeKeyValue(String str);

    protected native void setNativeInfo(int i, String str);

    protected native void testCrash();

    protected native String unregist();

    @SuppressLint({"SdCardPath"})
    private NativeCrashHandler(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, w wVar, boolean z, String str) {
        this.b = z.a(context);
        try {
            if (z.a(str)) {
                str = context.getDir("bugly", 0).getAbsolutePath();
            }
        } catch (Throwable unused) {
            str = "/data/data/" + com.tencent.bugly.crashreport.common.info.a.a(context).c + "/app_bugly";
        }
        this.n = bVar;
        this.f = str;
        this.c = aVar;
        this.d = wVar;
        this.g = z;
        this.e = new a(context, aVar, bVar, com.tencent.bugly.crashreport.common.strategy.a.a());
    }

    public static synchronized NativeCrashHandler getInstance(Context context, com.tencent.bugly.crashreport.common.info.a aVar, com.tencent.bugly.crashreport.crash.b bVar, com.tencent.bugly.crashreport.common.strategy.a aVar2, w wVar, boolean z, String str) {
        if (a == null) {
            a = new NativeCrashHandler(context, aVar, bVar, wVar, z, str);
        }
        return a;
    }

    public static synchronized NativeCrashHandler getInstance() {
        return a;
    }

    public synchronized String getDumpFilePath() {
        return this.f;
    }

    public synchronized void setDumpFilePath(String str) {
        this.f = str;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(15:13|(1:15)(14:17|(1:19)|70|21|(1:23)|24|(1:26)|27|(1:29)(1:30)|31|(1:33)(1:34)|35|36|37)|16|70|21|(0)|24|(0)|27|(0)(0)|31|(0)(0)|35|36|37) */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007f A[Catch: Throwable -> 0x008d, all -> 0x0193, TryCatch #2 {Throwable -> 0x008d, blocks: (B:21:0x0075, B:23:0x007f, B:24:0x0081, B:26:0x008b), top: B:70:0x0075 }] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x008b A[Catch: Throwable -> 0x008d, all -> 0x0193, TRY_LEAVE, TryCatch #2 {Throwable -> 0x008d, blocks: (B:21:0x0075, B:23:0x007f, B:24:0x0081, B:26:0x008b), top: B:70:0x0075 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0091 A[Catch: Throwable -> 0x00bb, all -> 0x0193, TryCatch #0 {Throwable -> 0x00bb, blocks: (B:11:0x0015, B:13:0x001d, B:15:0x004f, B:16:0x005c, B:27:0x008d, B:29:0x0091, B:31:0x00a0, B:33:0x00a4, B:35:0x00b3, B:34:0x00ac, B:30:0x0099, B:17:0x0061, B:19:0x0067), top: B:66:0x0015, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0099 A[Catch: Throwable -> 0x00bb, all -> 0x0193, TryCatch #0 {Throwable -> 0x00bb, blocks: (B:11:0x0015, B:13:0x001d, B:15:0x004f, B:16:0x005c, B:27:0x008d, B:29:0x0091, B:31:0x00a0, B:33:0x00a4, B:35:0x00b3, B:34:0x00ac, B:30:0x0099, B:17:0x0061, B:19:0x0067), top: B:66:0x0015, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00a4 A[Catch: Throwable -> 0x00bb, all -> 0x0193, TryCatch #0 {Throwable -> 0x00bb, blocks: (B:11:0x0015, B:13:0x001d, B:15:0x004f, B:16:0x005c, B:27:0x008d, B:29:0x0091, B:31:0x00a0, B:33:0x00a4, B:35:0x00b3, B:34:0x00ac, B:30:0x0099, B:17:0x0061, B:19:0x0067), top: B:66:0x0015, outer: #3 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00ac A[Catch: Throwable -> 0x00bb, all -> 0x0193, TryCatch #0 {Throwable -> 0x00bb, blocks: (B:11:0x0015, B:13:0x001d, B:15:0x004f, B:16:0x005c, B:27:0x008d, B:29:0x0091, B:31:0x00a0, B:33:0x00a4, B:35:0x00b3, B:34:0x00ac, B:30:0x0099, B:17:0x0061, B:19:0x0067), top: B:66:0x0015, outer: #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private synchronized void a(boolean r11) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.a(boolean):void");
    }

    public synchronized void startNativeMonitor() {
        if (!this.i && !this.h) {
            String str = "Bugly";
            boolean z = !z.a(this.c.m);
            String str2 = this.c.m;
            if (z) {
                str = str2;
            } else {
                this.c.getClass();
            }
            this.i = a(str, z);
            if (this.i || this.h) {
                a(this.g);
                this.d.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler.1
                    @Override // java.lang.Runnable
                    public final void run() throws Throwable {
                        if (z.a(NativeCrashHandler.this.b, "native_record_lock", DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL)) {
                            try {
                                NativeCrashHandler.this.setNativeAppVersion(NativeCrashHandler.this.c.j);
                                NativeCrashHandler.this.setNativeAppChannel(NativeCrashHandler.this.c.l);
                                NativeCrashHandler.this.setNativeAppPackage(NativeCrashHandler.this.c.c);
                                NativeCrashHandler.this.setNativeUserId(NativeCrashHandler.this.c.g());
                                NativeCrashHandler.this.setNativeIsAppForeground(NativeCrashHandler.this.c.a());
                                NativeCrashHandler.this.setNativeLaunchTime(NativeCrashHandler.this.c.a);
                            } catch (Throwable th) {
                                if (!x.a(th)) {
                                    th.printStackTrace();
                                }
                            }
                            CrashDetailBean crashDetailBeanA = b.a(NativeCrashHandler.this.b, NativeCrashHandler.this.f, NativeCrashHandler.this.e);
                            if (crashDetailBeanA != null) {
                                x.a("[Native] Get crash from native record.", new Object[0]);
                                if (!NativeCrashHandler.this.n.a(crashDetailBeanA)) {
                                    NativeCrashHandler.this.n.a(crashDetailBeanA, 3000L, false);
                                }
                                b.a(false, NativeCrashHandler.this.f);
                            }
                            NativeCrashHandler.this.a();
                            z.b(NativeCrashHandler.this.b, "native_record_lock");
                            return;
                        }
                        x.a("[Native] Failed to lock file for handling native crash record.", new Object[0]);
                    }
                });
                return;
            }
            return;
        }
        a(this.g);
    }

    private static boolean a(String str, boolean z) {
        boolean z2;
        try {
            x.a("[Native] Trying to load so: %s", str);
            if (z) {
                System.load(str);
            } else {
                System.loadLibrary(str);
            }
            try {
                x.a("[Native] Successfully loaded SO: %s", str);
                return true;
            } catch (Throwable th) {
                th = th;
                z2 = true;
                x.d(th.getMessage(), new Object[0]);
                x.d("[Native] Failed to load so: %s", str);
                return z2;
            }
        } catch (Throwable th2) {
            th = th2;
            z2 = false;
        }
    }

    private synchronized void b() {
        if (!this.j) {
            x.d("[Native] Native crash report has already unregistered.", new Object[0]);
            return;
        }
        try {
        } catch (Throwable unused) {
            x.c("[Native] Failed to close native crash report.", new Object[0]);
        }
        if (unregist() != null) {
            x.a("[Native] Successfully closed native crash report.", new Object[0]);
            this.j = false;
            return;
        }
        try {
            z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "enableHandler", null, new Class[]{Boolean.TYPE}, new Object[]{false});
            this.j = false;
            x.a("[Native] Successfully closed native crash report.", new Object[0]);
            return;
        } catch (Throwable unused2) {
            x.c("[Native] Failed to close native crash report.", new Object[0]);
            this.i = false;
            this.h = false;
            return;
        }
    }

    public void testNativeCrash() {
        if (!this.i) {
            x.d("[Native] Bugly SO file has not been load.", new Object[0]);
        } else {
            testCrash();
        }
    }

    public NativeExceptionHandler getNativeExceptionHandler() {
        return this.e;
    }

    protected final void a() {
        File[] fileArrListFiles;
        int iIndexOf;
        long jB = z.b() - c.g;
        File file = new File(this.f);
        if (!file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length == 0) {
            return;
        }
        int length = "tomb_".length();
        int i = 0;
        for (File file2 : fileArrListFiles) {
            String name = file2.getName();
            if (name.startsWith("tomb_")) {
                try {
                    iIndexOf = name.indexOf(".txt");
                } catch (Throwable unused) {
                    x.e("[Native] Tomb file format error, delete %s", name);
                }
                if (iIndexOf <= 0 || Long.parseLong(name.substring(length, iIndexOf)) < jB) {
                    if (file2.delete()) {
                        i++;
                    }
                }
            }
        }
        x.c("[Native] Clean tombs %d", Integer.valueOf(i));
    }

    private synchronized void b(boolean z) {
        if (z) {
            startNativeMonitor();
        } else {
            b();
        }
    }

    public synchronized boolean isUserOpened() {
        return this.k;
    }

    private synchronized void c(boolean z) {
        if (this.k != z) {
            x.a("user change native %b", Boolean.valueOf(z));
            this.k = z;
        }
    }

    public synchronized void setUserOpened(boolean z) {
        c(z);
        boolean zIsUserOpened = isUserOpened();
        com.tencent.bugly.crashreport.common.strategy.a aVarA = com.tencent.bugly.crashreport.common.strategy.a.a();
        if (aVarA != null) {
            zIsUserOpened = zIsUserOpened && aVarA.c().g;
        }
        if (zIsUserOpened != this.j) {
            x.a("native changed to %b", Boolean.valueOf(zIsUserOpened));
            b(zIsUserOpened);
        }
    }

    public synchronized void onStrategyChanged(StrategyBean strategyBean) {
        if (strategyBean != null) {
            try {
                if (strategyBean.g != this.j) {
                    x.d("server native changed to %b", Boolean.valueOf(strategyBean.g));
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        boolean z = com.tencent.bugly.crashreport.common.strategy.a.a().c().g && this.k;
        if (z != this.j) {
            x.a("native changed to %b", Boolean.valueOf(z));
            b(z);
        }
    }

    @Override // com.tencent.bugly.crashreport.a
    public boolean appendLogToNative(String str, String str2, String str3) {
        if ((!this.h && !this.i) || !l || str == null || str2 == null || str3 == null) {
            return false;
        }
        try {
            if (this.i) {
                return appendNativeLog(str, str2, str3);
            }
            Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "appendNativeLog", null, new Class[]{String.class, String.class, String.class}, new Object[]{str, str2, str3});
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (UnsatisfiedLinkError unused) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean putKeyValueToNative(String str, String str2) {
        if ((!this.h && !this.i) || !l || str == null || str2 == null) {
            return false;
        }
        try {
            if (this.i) {
                return putNativeKeyValue(str, str2);
            }
            Boolean bool = (Boolean) z.a("com.tencent.feedback.eup.jni.NativeExceptionUpload", "putNativeKeyValue", null, new Class[]{String.class, String.class}, new Object[]{str, str2});
            if (bool != null) {
                return bool.booleanValue();
            }
            return false;
        } catch (UnsatisfiedLinkError unused) {
            l = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    private boolean a(int i, String str) {
        if (!this.i || !m) {
            return false;
        }
        try {
            setNativeInfo(i, str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            m = false;
            return false;
        } catch (Throwable th) {
            if (!x.a(th)) {
                th.printStackTrace();
            }
            return false;
        }
    }

    public boolean filterSigabrtSysLog() {
        return a(998, ITagManager.STATUS_TRUE);
    }

    public boolean setNativeAppVersion(String str) {
        return a(10, str);
    }

    public boolean setNativeAppChannel(String str) {
        return a(12, str);
    }

    public boolean setNativeAppPackage(String str) {
        return a(13, str);
    }

    public boolean setNativeUserId(String str) {
        return a(11, str);
    }

    @Override // com.tencent.bugly.crashreport.a
    public boolean setNativeIsAppForeground(boolean z) {
        return a(14, z ? ITagManager.STATUS_TRUE : "false");
    }

    public boolean setNativeLaunchTime(long j) {
        try {
            return a(15, String.valueOf(j));
        } catch (NumberFormatException e) {
            if (x.a(e)) {
                return false;
            }
            e.printStackTrace();
            return false;
        }
    }
}
