package com.bytedance.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.applog.IPicker;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ch implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public static int f3468a;

    /* renamed from: b, reason: collision with root package name */
    public static di f3469b;

    /* renamed from: c, reason: collision with root package name */
    public static di f3470c;
    public static long d;
    public static String e;
    public static final HashSet<Integer> f;

    static {
        new HashMap();
        f = new HashSet<>(8);
    }

    public ch(IPicker iPicker) {
    }

    public static di a(String str, String str2, long j, String str3) {
        di diVar = new di();
        if (!TextUtils.isEmpty(str2)) {
            str = str + ":" + str2;
        }
        diVar.o = str;
        diVar.a(j);
        diVar.m = -1L;
        if (str3 == null) {
            str3 = "";
        }
        diVar.n = str3;
        dg.a(diVar);
        return diVar;
    }

    public static void a(boolean z) {
    }

    public void a(String str, int i) {
        f3469b = a(str, "", System.currentTimeMillis(), e);
        f3469b.p = !f.remove(Integer.valueOf(i)) ? 1 : 0;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        f.add(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        f.remove(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        di diVar = f3470c;
        di diVar2 = f3469b;
        if (diVar2 != null) {
            e = diVar2.o;
            d = System.currentTimeMillis();
            di diVar3 = f3469b;
            long j = d;
            di diVar4 = (di) diVar3.clone();
            diVar4.a(j);
            long j2 = j - diVar3.f3434c;
            if (j2 <= 0) {
                j2 = 1000;
            }
            diVar4.m = j2;
            dg.a(diVar4);
            f3469b = null;
            if (activity != null) {
                activity.isChild();
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        f3469b = a(activity.getClass().getName(), "", System.currentTimeMillis(), e);
        f3469b.p = !f.remove(Integer.valueOf(activity.hashCode())) ? 1 : 0;
        if (activity.isChild()) {
            return;
        }
        activity.getWindow().getDecorView().hashCode();
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        f3468a++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (e != null) {
            f3468a--;
            if (f3468a <= 0) {
                e = null;
                d = 0L;
            }
        }
    }
}
