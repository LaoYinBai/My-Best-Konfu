package b.b.a.c;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import b.b.a.j.j;
import java.util.HashMap;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public static int f1046a;

    /* renamed from: b, reason: collision with root package name */
    public static j f1047b;

    /* renamed from: c, reason: collision with root package name */
    public static j f1048c;
    public static long d;
    public static String e;
    public static final HashSet<Integer> f;

    static {
        new HashMap();
        f = new HashSet<>(8);
    }

    public static j a() {
        j jVar = f1047b;
        j jVar2 = f1048c;
        if (jVar2 != null) {
            return jVar2;
        }
        if (jVar != null) {
            return jVar;
        }
        return null;
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
        j jVar = f1048c;
        j jVar2 = f1047b;
        if (jVar2 != null) {
            e = jVar2.n;
            d = System.currentTimeMillis();
            j jVar3 = f1047b;
            long j = d;
            j jVar4 = (j) jVar3.clone();
            jVar4.a(j);
            long j2 = j - jVar3.f1110c;
            if (j2 <= 0) {
                j2 = 1000;
            }
            jVar4.l = j2;
            com.apm.applog.a.a(jVar4);
            f1047b = null;
            if (activity != null) {
                activity.isChild();
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        f1047b = a(activity.getClass().getName(), "", System.currentTimeMillis(), e);
        f1047b.o = !f.remove(Integer.valueOf(activity.hashCode())) ? 1 : 0;
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
        f1046a++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (e != null) {
            f1046a--;
            if (f1046a <= 0) {
                e = null;
                d = 0L;
            }
        }
    }

    public void a(String str, int i) {
        f1047b = a(str, "", System.currentTimeMillis(), e);
        f1047b.o = !f.remove(Integer.valueOf(i)) ? 1 : 0;
    }

    public static j a(String str, String str2, long j, String str3) {
        j jVar = new j();
        if (!TextUtils.isEmpty(str2)) {
            jVar.n = str + ":" + str2;
        } else {
            jVar.n = str;
        }
        jVar.a(j);
        jVar.l = -1L;
        if (str3 == null) {
            str3 = "";
        }
        jVar.m = str3;
        com.apm.applog.a.a(jVar);
        return jVar;
    }
}
