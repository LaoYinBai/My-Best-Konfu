package com.apm.insight.b;

import android.app.ActivityManager;
import android.content.Context;
import android.os.FileObserver;
import android.os.Looper;
import android.os.Process;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.apm.insight.l.r;
import com.apm.insight.l.v;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static String f2771a = null;

    /* renamed from: b, reason: collision with root package name */
    private static long f2772b = -1;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f2773c = false;
    private static FileObserver d;
    private static ActivityManager.ProcessErrorStateInfo e;

    static String a(Context context, int i) {
        if (r.a(256)) {
            f2773c = false;
            return "TEST_ANR_INFO";
        }
        if (SystemClock.uptimeMillis() - f2772b < 5000) {
            return null;
        }
        try {
            ActivityManager.ProcessErrorStateInfo processErrorStateInfoA = com.apm.insight.l.a.a(context, i);
            if (processErrorStateInfoA != null && Process.myPid() == processErrorStateInfoA.pid) {
                if (e != null && a.a(e, processErrorStateInfoA)) {
                    return null;
                }
                e = processErrorStateInfoA;
                f2771a = null;
                f2772b = SystemClock.uptimeMillis();
                f2773c = false;
                return a.a(processErrorStateInfoA);
            }
        } catch (Throwable unused) {
        }
        if (f2771a == null) {
            return null;
        }
        String str = f2771a;
        f2773c = true;
        f2771a = null;
        f2772b = SystemClock.uptimeMillis();
        return str;
    }

    public static JSONObject a(boolean z) {
        try {
            StackTraceElement[] stackTrace = Looper.getMainLooper().getThread().getStackTrace();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("thread_number", 1);
            jSONObject.put("mainStackFromTrace", v.a(stackTrace));
            return jSONObject;
        } catch (Throwable th) {
            com.apm.insight.b.a().a("NPTH_CATCH", th);
            return null;
        }
    }

    public static void a(final String str, final com.apm.insight.e eVar) {
        if (d != null) {
            d.stopWatching();
        }
        d = new FileObserver(str, 136) { // from class: com.apm.insight.b.d.1
            @Override // android.os.FileObserver
            public void onEvent(int i, @Nullable String str2) {
                if (TextUtils.isEmpty(str2)) {
                    return;
                }
                try {
                    String unused = d.f2771a = eVar.a(str, str2);
                } catch (Throwable th) {
                    com.apm.insight.b.a().a("NPTH_CATCH", th);
                }
            }
        };
        d.startWatching();
    }

    public static boolean a() {
        return f2773c;
    }

    public static void b() {
        e = null;
    }
}
