package com.bytedance.gameprotect;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import c.a.a.a.a.an;
import c.a.a.a.a.aq;
import com.appsflyer.share.Constants;
import com.ss.cgpa.NativeDataHelp;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class k {

    /* renamed from: a, reason: collision with root package name */
    private static Context f3880a;

    /* renamed from: b, reason: collision with root package name */
    private static Handler f3881b;

    static class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            com.ss.cgpa.c.h().b(k.f3880a.getPackageName());
        }
    }

    private static void a(int i, String str) {
        IlIlllIiI.a(i, str);
    }

    public static void a(Context context) throws PackageManager.NameNotFoundException {
        f3880a = context.getApplicationContext();
        f3881b = new Handler(Looper.getMainLooper());
        d();
        e();
    }

    @Deprecated
    public static void a(Runnable runnable) {
        if (f()) {
            runnable.run();
        } else {
            f3881b.post(runnable);
        }
    }

    @Deprecated
    public static void a(Runnable runnable, long j) {
        f3881b.postDelayed(runnable, j);
    }

    public static Context b() {
        return f3880a;
    }

    public static String c() {
        if (f3880a.getFilesDir() != null) {
            return f3880a.getFilesDir().getAbsolutePath();
        }
        File dir = f3880a.getDir("gpfiles", 0);
        if (dir != null) {
            return dir.getAbsolutePath();
        }
        try {
            String str = "/data/data/" + f3880a.getApplicationInfo().packageName + Constants.URL_PATH_DELIMITER + "gpfiles";
            new File(str).mkdirs();
            return str;
        } catch (Exception e) {
            throw new RuntimeException("getFilesDir create gpfiles failed", e);
        }
    }

    public static void d() {
        File externalFilesDir;
        ApplicationInfo applicationInfo = f3880a.getApplicationInfo();
        a(2, c());
        a(3, applicationInfo.nativeLibraryDir);
        a(4, applicationInfo.sourceDir);
        a(5, applicationInfo.packageName);
        a(9, applicationInfo.dataDir);
        a(10, String.valueOf(Build.VERSION.SDK_INT));
        a(11, aq.a());
        a(6, an.c(f3880a));
        a(7, an.a(f3880a));
        a(8, String.valueOf(an.b(f3880a)));
        if ((Build.VERSION.SDK_INT >= 19 || s.a()) && (externalFilesDir = f3880a.getExternalFilesDir(null)) != null) {
            a(12, externalFilesDir.getPath());
        }
    }

    private static void e() throws PackageManager.NameNotFoundException {
        try {
            f3880a.getPackageManager().getPackageInfo("com.bytedance.gameprotect.profiler", 0);
            new Thread(new a()).start();
        } catch (Exception unused) {
            new NativeDataHelp().stopReport();
        }
    }

    private static boolean f() {
        return Thread.currentThread() == Looper.getMainLooper().getThread();
    }
}
