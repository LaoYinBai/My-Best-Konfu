package com.kwad.sdk.core.report;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.annotation.WorkerThread;
import java.util.UUID;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n {
    private static String a = f();
    private static long b = 0;
    private static Context c;

    public static String a() {
        com.kwad.sdk.core.d.a.a("ReportIdManager", ">> updateSessionId");
        a = f();
        return a;
    }

    public static void a(Context context) {
        c = context;
    }

    @WorkerThread
    private static boolean a(Context context, long j) {
        if (context == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("ksadsdk_seq", 0).edit();
        editorEdit.putLong("seq", j);
        return editorEdit.commit();
    }

    @WorkerThread
    private static long b(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getSharedPreferences("ksadsdk_seq", 0)) == null) {
            return 0L;
        }
        return sharedPreferences.getLong("seq", 1L);
    }

    public static String b() {
        return a;
    }

    @WorkerThread
    private static boolean b(Context context, long j) {
        if (context == null) {
            return false;
        }
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("ksadsdk_mplogseq", 0).edit();
        editorEdit.putLong("seq", j);
        return editorEdit.commit();
    }

    @WorkerThread
    public static long c() {
        long jB = b(c);
        a(c, 1 + jB);
        return jB;
    }

    @WorkerThread
    private static long c(Context context) {
        SharedPreferences sharedPreferences;
        if (context == null || (sharedPreferences = context.getSharedPreferences("ksadsdk_mplogseq", 0)) == null) {
            return 0L;
        }
        return sharedPreferences.getLong("seq", 1L);
    }

    @WorkerThread
    public static synchronized long d() {
        long jC;
        jC = c(c);
        b(c, 1 + jC);
        return jC;
    }

    public static long e() {
        return b;
    }

    private static String f() {
        return UUID.randomUUID().toString();
    }
}
