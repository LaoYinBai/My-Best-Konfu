package com.apm.insight.nativecrash;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.apm.insight.b.f;
import com.apm.insight.b.g;
import com.apm.insight.h;
import com.apm.insight.l.o;
import com.apm.insight.l.u;
import com.apm.insight.l.x;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class NativeImpl {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f2976a = false;

    /* renamed from: b, reason: collision with root package name */
    private static volatile boolean f2977b = false;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f2978c = true;

    public static int a(int i) {
        if (f2976a && i >= 0) {
            try {
                return doLock("", i);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    public static String a(String str) {
        if (f2976a) {
            return doGetCrashHeader(str);
        }
        return null;
    }

    public static void a(int i, String str) {
        if (!f2976a || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            doWriteFile(i, str, str.length());
        } catch (Throwable unused) {
        }
    }

    public static void a(long j) {
    }

    public static void a(File file) {
        if (f2976a) {
            doRebuildTombstone(o.c(file).getAbsolutePath(), o.b(file).getAbsolutePath(), o.d(file).getAbsolutePath());
        }
    }

    public static void a(String str, String str2, String str3) {
        if (f2976a) {
            try {
                doDumpLogcat(str, str2, str3);
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(boolean z) {
        f2978c = z;
        if (f2976a) {
            doSetResendSigQuit(z ? 1 : 0);
        }
    }

    public static boolean a() {
        if (f2977b) {
            return f2976a;
        }
        f2977b = true;
        if (!f2976a) {
            f2976a = u.a("apminsighta");
        }
        return f2976a;
    }

    public static boolean a(@NonNull Context context) {
        String strA;
        boolean zA = a();
        if (zA) {
            String str = o.j(context) + "/apminsight";
            if (new File(context.getApplicationInfo().nativeLibraryDir, "libapminsightb.so").exists()) {
                strA = context.getApplicationInfo().nativeLibraryDir;
            } else {
                strA = com.apm.insight.h.b.a();
                com.apm.insight.h.b.b("apminsightb");
            }
            doStart(Build.VERSION.SDK_INT, strA, str, h.f(), h.l());
        }
        return zA;
    }

    public static int b() {
        if (f2976a) {
            return doCreateCallbackThread();
        }
        return -1;
    }

    public static void b(int i) {
        if (f2976a) {
            try {
                doCloseFile(i);
            } catch (Throwable unused) {
            }
        }
    }

    public static void b(long j) {
        if (f2976a) {
            try {
                doSetAlogFlushAddr(j);
            } catch (Throwable unused) {
            }
        }
    }

    public static void b(String str) {
        if (f2976a) {
            doDumpHprof(str);
        }
    }

    public static int c(String str) {
        if (f2976a && !TextUtils.isEmpty(str)) {
            try {
                return doLock(str, -1);
            } catch (Throwable unused) {
            }
        }
        return -1;
    }

    public static long c(int i) {
        if (f2976a) {
            return doGetThreadCpuTime(i);
        }
        return 0L;
    }

    public static void c() {
    }

    public static void c(long j) {
        if (f2976a) {
            try {
                doSetAlogLogDirAddr(j);
            } catch (Throwable unused) {
            }
        }
    }

    public static void d(String str) {
        if (f2976a) {
            try {
                doDumpMemInfo(str);
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean d() {
        if (!f2976a) {
            return false;
        }
        try {
            return doCheckNativeCrash();
        } catch (Throwable unused) {
            return false;
        }
    }

    @Keep
    private static native boolean doCheckNativeCrash();

    @Keep
    private static native void doCloseFile(int i);

    @Keep
    private static native int doCreateCallbackThread();

    @Keep
    private static native void doDump(String str);

    @Keep
    private static native void doDumpFds(String str);

    @Keep
    private static native void doDumpHprof(String str);

    @Keep
    private static native void doDumpLogcat(String str, String str2, String str3);

    @Keep
    private static native void doDumpMaps(String str);

    @Keep
    private static native void doDumpMemInfo(String str);

    @Keep
    private static native void doDumpThreads(String str);

    @Keep
    private static native long doGetAppCpuTime();

    @Keep
    private static native long doGetChildCpuTime();

    @Keep
    private static native String doGetCrashHeader(String str);

    @Keep
    private static native long doGetDeviceCpuTime();

    @Keep
    private static native int doGetFDCount();

    @Keep
    private static native String[] doGetFdDump(int i, int i2, int[] iArr, String[] strArr);

    @Keep
    private static native long doGetFreeMemory();

    @Keep
    private static native long doGetThreadCpuTime(int i);

    @Keep
    private static native int doGetThreadsCount();

    @Keep
    private static native long doGetTotalMemory();

    @Keep
    private static native long doGetVMSize();

    @Keep
    private static native void doInitThreadDump();

    @Keep
    private static native int doLock(String str, int i);

    @Keep
    private static native int doOpenFile(String str);

    @Keep
    private static native void doRebuildTombstone(String str, String str2, String str3);

    @Keep
    private static native void doSetAlogConfigPath(String str);

    @Keep
    private static native void doSetAlogFlushAddr(long j);

    @Keep
    private static native void doSetAlogLogDirAddr(long j);

    @Keep
    private static native void doSetResendSigQuit(int i);

    @Keep
    private static native void doSetUploadEnd();

    @Keep
    private static native void doSignalMainThread();

    @Keep
    private static native int doStart(int i, String str, String str2, String str3, int i2);

    @Keep
    private static native void doStartAnrMonitor(int i);

    @Keep
    private static native void doWriteFile(int i, String str, int i2);

    public static void e(String str) {
        if (f2976a) {
            try {
                doDumpFds(str);
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean e() {
        if (!f2976a) {
            return false;
        }
        try {
            return is64Bit();
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void f() {
        x.a(new Runnable() { // from class: com.apm.insight.nativecrash.NativeImpl.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    NativeImpl.l();
                } catch (Throwable th) {
                    try {
                        com.apm.insight.b.a(th, "NPTH_ANR_MONITOR_ERROR");
                    } catch (Throwable unused) {
                    }
                }
            }
        }, "NPTH-AnrMonitor");
    }

    public static void f(String str) {
        if (f2976a) {
            try {
                doDumpMaps(str);
            } catch (Throwable unused) {
            }
        }
    }

    public static void g(String str) {
        if (f2976a) {
            try {
                doDumpThreads(str);
            } catch (Throwable unused) {
            }
        }
    }

    public static boolean g() {
        return f2978c;
    }

    public static int h(String str) {
        if (!f2976a) {
            return -1;
        }
        try {
            return doOpenFile(str);
        } catch (Throwable unused) {
            return -1;
        }
    }

    public static void h() {
        if (f2976a) {
            doSignalMainThread();
        }
    }

    @Keep
    private static void handleNativeCrash(String str) {
        NativeCrashCollector.onNativeCrash(str);
    }

    public static void i() {
        if (f2976a) {
            doSetUploadEnd();
        }
    }

    public static void i(String str) {
        if (f2976a) {
            doDump(str);
        }
    }

    @Keep
    private static native boolean is64Bit();

    public static void j() {
        if (f2976a) {
            doInitThreadDump();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void l() {
        if (f2976a) {
            try {
                doStartAnrMonitor(Build.VERSION.SDK_INT);
            } catch (Throwable unused) {
            }
        }
    }

    @Keep
    private static void reportEventForAnrMonitor() {
        try {
            System.currentTimeMillis();
            h.j();
            f.b(true);
            com.apm.insight.b.d.b();
            g.a(h.g()).a().e();
        } catch (Throwable unused) {
        }
    }
}
