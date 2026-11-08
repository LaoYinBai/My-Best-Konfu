package bykvm_19do.bykvm_19do.bykvm_19do;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;
import java.util.HashSet;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class j0 implements Application.ActivityLifecycleCallbacks {

    /* renamed from: b, reason: collision with root package name */
    private static int f1350b;

    /* renamed from: c, reason: collision with root package name */
    private static b0 f1351c;
    private static b0 d;
    private static long e;
    private static String f;
    private static Object g;
    private static final HashSet<Integer> h = new HashSet<>(8);

    /* renamed from: a, reason: collision with root package name */
    private final f f1352a;

    public j0(f fVar) {
        this.f1352a = fVar;
    }

    public static b0 a(String str, String str2, long j, String str3) {
        b0 b0Var = new b0();
        if (TextUtils.isEmpty(str2)) {
            b0Var.k = str;
        } else {
            b0Var.k = str + ":" + str2;
        }
        b0Var.f1385a = j;
        b0Var.i = -1L;
        if (str3 == null) {
            str3 = "";
        }
        b0Var.j = str3;
        u1.a(b0Var);
        return b0Var;
    }

    public static void a(Object obj) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        h.add(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        h.remove(Integer.valueOf(activity.hashCode()));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (d != null) {
            a(g);
        }
        b0 b0Var = f1351c;
        if (b0Var != null) {
            f = b0Var.k;
            e = System.currentTimeMillis();
            a(f1351c, e);
            f1351c = null;
            activity.isChild();
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        f1351c = a(activity.getClass().getName(), "", System.currentTimeMillis(), f);
        f1351c.l = !h.remove(Integer.valueOf(activity.hashCode())) ? 1 : 0;
        if (activity.isChild()) {
            return;
        }
        try {
            activity.getWindow().getDecorView().hashCode();
        } catch (Exception e2) {
            l0.a(e2);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        f fVar;
        f1350b++;
        if (f1350b != 1 || (fVar = this.f1352a) == null) {
            return;
        }
        fVar.a(true);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        if (f != null) {
            f1350b--;
            if (f1350b <= 0) {
                f = null;
                e = 0L;
                f fVar = this.f1352a;
                if (fVar != null) {
                    fVar.a(false);
                }
            }
        }
    }

    public static b0 a(b0 b0Var, long j) {
        b0 b0Var2 = (b0) b0Var.clone();
        b0Var2.f1385a = j;
        long j2 = j - b0Var.f1385a;
        if (j2 >= 0) {
            b0Var2.i = j2;
        } else {
            l0.a(null);
        }
        u1.a(b0Var2);
        return b0Var2;
    }
}
