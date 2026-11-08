package com.apm.insight.l;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    private static String f2962a;

    /* renamed from: b, reason: collision with root package name */
    private static File f2963b;

    /* renamed from: c, reason: collision with root package name */
    private static File f2964c;
    private static File d;

    public static File a() {
        return f2964c == null ? d(com.apm.insight.h.g()) : f2964c;
    }

    public static File a(@NonNull Context context) {
        return new File(j(context), "apminsight/CrashLogJava");
    }

    public static File a(@NonNull Context context, String str) {
        return new File(j(context) + "/apminsight/CrashCommonLog/" + str);
    }

    public static File a(File file) {
        return new File(file, "flog.txt");
    }

    public static File a(File file, String str) {
        return new File(file, file.getName() + str);
    }

    public static String a(String str) {
        return "dart_" + str;
    }

    public static File b(@NonNull Context context) {
        return new File(j(context), "apminsight/CrashLogSimple");
    }

    public static File b(File file) {
        return new File(file, "tombstone.txt");
    }

    public static File b(String str) {
        return new File(a(com.apm.insight.h.g(), str), "fds.txt");
    }

    public static String b() {
        return "anr_" + com.apm.insight.h.e();
    }

    public static File c(@NonNull Context context) {
        return new File(j(context), "apminsight/RuntimeContext");
    }

    public static File c(File file) {
        return new File(file, "header.bin");
    }

    public static File c(String str) {
        return new File(a(com.apm.insight.h.g(), str), "threads.txt");
    }

    public static String c() {
        return String.format("ensure_%s", com.apm.insight.h.e());
    }

    public static File d(@NonNull Context context) {
        if (f2964c == null) {
            if (context == null) {
                context = com.apm.insight.h.g();
            }
            f2964c = new File(j(context), "apminsight/CrashLogNative");
        }
        return f2964c;
    }

    public static File d(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "maps.txt");
    }

    public static File d(String str) {
        return new File(a(com.apm.insight.h.g(), str), "meminfo.txt");
    }

    public static File e(@NonNull Context context) {
        if (d == null) {
            d = new File(j(context) + "/apminsight/CrashCommonLog/" + com.apm.insight.h.f());
        }
        return d;
    }

    public static File e(File file) {
        return new File(file, "callback.json");
    }

    public static File e(String str) {
        return new File(a(com.apm.insight.h.g(), str), "pthreads.txt");
    }

    public static File f(Context context) {
        return new File(j(context), "apminsight/CrashCommonLog");
    }

    public static File f(File file) {
        return new File(file, "upload.json");
    }

    public static File f(String str) {
        return new File(a(com.apm.insight.h.g(), str), "rountines.txt");
    }

    public static File g(Context context) {
        return new File(j(context), "apminsight/issueCrashTimes");
    }

    public static File g(File file) {
        return new File(file, "javastack.txt");
    }

    public static File g(String str) {
        return new File(a(com.apm.insight.h.g(), str), "leakd_threads.txt");
    }

    public static File h(Context context) {
        return new File(j(context) + "/apminsight/issueCrashTimes/current.times");
    }

    public static File h(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "logcat.txt");
    }

    public static File i(@NonNull Context context) {
        return new File(j(context), "apminsight/alogCrash");
    }

    public static File i(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "fds.txt");
    }

    public static File j(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "threads.txt");
    }

    public static String j(@NonNull Context context) {
        if (TextUtils.isEmpty(f2962a)) {
            try {
                f2962a = context.getFilesDir().getAbsolutePath();
            } catch (Exception e) {
                f2962a = "/sdcard/";
                e.printStackTrace();
            }
        }
        return f2962a;
    }

    public static File k(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "meminfo.txt");
    }

    public static File l(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "pthreads.txt");
    }

    public static File m(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "rountines.txt");
    }

    public static File n(File file) {
        return new File(a(com.apm.insight.h.g(), file.getName()), "leakd_threads.txt");
    }

    public static File o(File file) {
        return new File(file, "abortmsg.txt");
    }
}
