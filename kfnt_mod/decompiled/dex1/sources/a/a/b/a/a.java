package a.a.b.a;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.Window;
import androidx.annotation.NonNull;
import com.cgs.out.NativeLib;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a implements Application.ActivityLifecycleCallbacks {

    /* renamed from: a, reason: collision with root package name */
    public static volatile a f327a;

    /* renamed from: b, reason: collision with root package name */
    @NonNull
    public final c<Activity> f328b = new c<>();

    public static a a() {
        if (f327a == null) {
            synchronized (a.class) {
                if (f327a == null) {
                    f327a = new a();
                    NativeLib.setALC(true);
                }
            }
        }
        return f327a;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        if (!this.f328b.contains(activity)) {
            synchronized (this.f328b) {
                try {
                    Window window = activity.getWindow();
                    if (window != null) {
                        window.setCallback(new b(window.getCallback()));
                    }
                } catch (Throwable unused) {
                }
            }
        }
        this.f328b.f330a.add(new WeakReference<>(activity));
        try {
            NativeLib.native_bridge(16777228, null, activity);
        } catch (Throwable unused2) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        synchronized (this.f328b) {
            this.f328b.remove(activity);
            this.f328b.notify();
        }
        try {
            NativeLib.native_bridge(16777229, null, activity);
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        new WeakReference(activity);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }
}
