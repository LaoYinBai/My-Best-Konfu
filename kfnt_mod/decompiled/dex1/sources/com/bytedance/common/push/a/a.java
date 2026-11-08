package com.bytedance.common.push.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Message;
import com.bytedance.b.a.a.c.g;
import com.bytedance.common.push.utility.a.c;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends Observable implements Application.ActivityLifecycleCallbacks, c.a {

    /* renamed from: a, reason: collision with root package name */
    private static a f3816a = null;
    private static int f = 0;
    private static boolean g = true;

    /* renamed from: b, reason: collision with root package name */
    private WeakReference<Activity> f3817b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3818c;
    private InterfaceC0108a e;
    private Runnable d = new Runnable() { // from class: com.bytedance.common.push.a.a.1
        @Override // java.lang.Runnable
        public void run() {
            if (a.this.f3818c) {
                a.this.f3818c = false;
                com.bytedance.common.push.utility.a.b("ActivityLifecycleObserver", "sAppAlive = false");
                if (a.this.e != null) {
                    a.this.e.b();
                }
            }
        }
    };
    private c h = new c(this);
    private final List<Application.ActivityLifecycleCallbacks> i = new ArrayList();

    /* renamed from: com.bytedance.common.push.a.a$a, reason: collision with other inner class name */
    public interface InterfaceC0108a {
        void a();

        void b();
    }

    public static synchronized a a() {
        if (f3816a == null) {
            f3816a = new a();
        }
        return f3816a;
    }

    private a() {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        g = false;
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityCreated(activity, bundle);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityStarted(activity);
            }
        }
        this.h.removeMessages(1);
        if (f == 0) {
            g = false;
        }
        f++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        this.f3817b = new WeakReference<>(activity);
        if (!this.f3818c) {
            this.f3818c = true;
            com.bytedance.common.push.utility.a.b("ActivityLifecycleObserver", "onResume sAppAlive = true");
            if (this.e != null) {
                this.e.a();
            }
        }
        this.h.removeCallbacks(this.d);
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityResumed(activity);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        if (this.f3818c) {
            this.h.postDelayed(this.d, g.e);
        }
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityPaused(activity);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityStopped(activity);
            }
        }
        f--;
        if (f == 0) {
            g = true;
            this.h.sendEmptyMessageDelayed(1, g.e);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivitySaveInstanceState(activity, bundle);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Object[] objArrD = d();
        if (objArrD != null) {
            for (Object obj : objArrD) {
                ((Application.ActivityLifecycleCallbacks) obj).onActivityDestroyed(activity);
            }
        }
    }

    public boolean b() {
        return g;
    }

    public boolean c() {
        return this.f3818c;
    }

    @Override // com.bytedance.common.push.utility.a.c.a
    public void handleMsg(Message message) {
        if (message.what == 1 && g) {
            setChanged();
            notifyObservers(Boolean.valueOf(g));
        }
    }

    private Object[] d() {
        Object[] array;
        synchronized (this.i) {
            array = this.i.size() > 0 ? this.i.toArray() : null;
        }
        return array;
    }
}
