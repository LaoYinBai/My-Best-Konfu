package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bv implements Thread.UncaughtExceptionHandler {

    /* renamed from: a, reason: collision with root package name */
    public static String f3189a = "";

    /* renamed from: b, reason: collision with root package name */
    private static final String f3190b = "remote";

    /* renamed from: c, reason: collision with root package name */
    private static final String f3191c = "proxy";
    private static final String d = "third-mtj";
    private static final String e = "third-novel";
    private static Thread.UncaughtExceptionHandler f = null;
    private static volatile bv g = null;
    private static final String j = "key_crash_source";
    private static final String k = "key_crash_trace";
    private static final String l = "key_crash_ad";
    private Context h;
    private a i;

    interface a {
        void a(String str);
    }

    public static bv a(Context context) {
        if (g == null) {
            synchronized (bv.class) {
                if (g == null) {
                    g = new bv(context);
                }
            }
        }
        return g;
    }

    private bv(Context context) {
        this.h = context.getApplicationContext();
        f = Thread.getDefaultUncaughtExceptionHandler();
    }

    public void a() {
        am.a().a((h) new bw(this));
    }

    public void b() {
        if (Thread.getDefaultUncaughtExceptionHandler() instanceof bv) {
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            String strA = a(th);
            if (strA != null) {
                a(strA, Log.getStackTraceString(th));
                if (this.i != null) {
                    this.i.a(strA);
                }
            }
            if (f != null) {
                f.uncaughtException(thread, th);
            }
        } catch (Exception e2) {
            bb.a().c(e2);
        }
    }

    public void a(a aVar) {
        this.i = aVar;
    }

    public void c() {
        this.i = null;
    }

    private String a(Throwable th) {
        Throwable cause = th.getCause();
        if (cause != null) {
            th = cause;
        }
        StackTraceElement[] stackTrace = th.getStackTrace();
        if (stackTrace == null || stackTrace.length <= 0) {
            return null;
        }
        List<String> listD = d();
        for (StackTraceElement stackTraceElement : stackTrace) {
            String className = stackTraceElement.getClassName();
            if (className.startsWith("junit.framework")) {
                return null;
            }
            if (className.startsWith(r.aj) || className.startsWith(r.ak) || className.startsWith(r.al)) {
                return f3190b;
            }
            if (className.startsWith(r.am) || className.startsWith(r.an) || className.startsWith(r.ao)) {
                return f3191c;
            }
            if (className.startsWith(r.ap)) {
                return d;
            }
            if (className.startsWith(r.aq) || className.startsWith(r.ar)) {
                if (bu.g.booleanValue()) {
                    return e;
                }
            } else if (a(className, listD)) {
                return f3190b;
            }
        }
        return null;
    }

    private List<String> d() {
        IXAdContainerFactory iXAdContainerFactoryC;
        ArrayList arrayList = new ArrayList();
        try {
            u uVarA = u.a();
            if (uVarA != null && (iXAdContainerFactoryC = uVarA.c()) != null) {
                Object remoteParam = iXAdContainerFactoryC.getRemoteParam("appCommonConfig", "getCrashPackage");
                if (remoteParam instanceof List) {
                    arrayList.addAll((List) remoteParam);
                }
            }
        } catch (Throwable unused) {
        }
        return arrayList;
    }

    private boolean a(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            if (str.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }

    private SharedPreferences e() {
        return this.h.getSharedPreferences("baidu_mobads_crash", 0);
    }

    private SharedPreferences.Editor f() {
        return e().edit();
    }

    public void a(String str, String str2) {
        SharedPreferences.Editor editorF = f();
        String str3 = "crashtime:" + System.currentTimeMillis() + " ";
        editorF.putString(j, str);
        editorF.putString(k, str3 + str2);
        editorF.putString(l, f3189a);
        editorF.commit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String a(String str) {
        return e().getString(str, "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        SharedPreferences.Editor editorF = f();
        editorF.clear();
        if (av.a(this.h).a() >= 9) {
            editorF.apply();
        } else {
            editorF.commit();
        }
    }
}
