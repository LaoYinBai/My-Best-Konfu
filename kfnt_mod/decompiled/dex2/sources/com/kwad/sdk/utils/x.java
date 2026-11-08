package com.kwad.sdk.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.Observable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class x extends Observable {
    private static volatile x a;
    private WeakReference<Activity> b;
    private WeakReference<Activity> c;

    public static x a() {
        if (a == null) {
            synchronized (x.class) {
                if (a == null) {
                    a = new x();
                }
            }
        }
        return a;
    }

    public void a(Context context) {
        Object obj;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            return;
        }
        Application application = null;
        com.kwad.sdk.core.d.a.a("LifecycleHolder", "init appContext: " + applicationContext);
        if (!(applicationContext instanceof Application)) {
            Field[] declaredFields = applicationContext.getClass().getDeclaredFields();
            int length = declaredFields.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Field field = declaredFields[i];
                field.setAccessible(true);
                try {
                    obj = field.get(applicationContext);
                } catch (Throwable th) {
                    com.kwad.sdk.core.d.a.b(th);
                }
                if (obj instanceof Application) {
                    application = (Application) obj;
                    break;
                } else {
                    continue;
                    i++;
                }
            }
        } else {
            application = (Application) applicationContext;
        }
        com.kwad.sdk.core.d.a.a("LifecycleHolder", "init application: " + application);
        if (application != null) {
            application.registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.kwad.sdk.utils.x.1
                private int b = 0;

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(@NonNull Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(@NonNull Activity activity) {
                    if (x.this.b == null || x.this.b.get() == null || !((Activity) x.this.b.get()).equals(activity)) {
                        return;
                    }
                    x.this.b = null;
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(@NonNull Activity activity) {
                    x.this.b = new WeakReference(activity);
                    x.this.c = new WeakReference(activity);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(@NonNull Activity activity) {
                    this.b++;
                    if (this.b == 1) {
                        x.this.e();
                    }
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(@NonNull Activity activity) {
                    this.b--;
                    if (this.b == 0) {
                        x.this.f();
                    }
                    if (x.this.c == null || x.this.c.get() == null || !((Activity) x.this.c.get()).equals(activity)) {
                        return;
                    }
                    x.this.c = null;
                }
            });
        }
    }

    public boolean b() {
        return c() != null;
    }

    @Nullable
    public Activity c() {
        if (this.b == null) {
            return null;
        }
        return this.b.get();
    }

    public int d() {
        Activity activity;
        if (this.c == null || (activity = this.c.get()) == null) {
            return 0;
        }
        return activity.getClass().hashCode();
    }

    protected void e() {
        com.kwad.sdk.core.d.a.a("LifecycleHolder", "onAppBackToForeground");
        setChanged();
        notifyObservers("ACTION_APP_BACK_TO_FOREGROUND");
    }

    protected void f() {
        com.kwad.sdk.core.d.a.a("LifecycleHolder", "onAppGoToBackground");
        setChanged();
        notifyObservers("ACTION_APP_GO_TO_BACKGROUND");
    }
}
