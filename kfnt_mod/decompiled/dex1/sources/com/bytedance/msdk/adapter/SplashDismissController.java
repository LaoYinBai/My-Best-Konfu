package com.bytedance.msdk.adapter;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SplashDismissController {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<Activity> f3909a;
    private CallBack d;

    /* renamed from: b, reason: collision with root package name */
    private boolean f3910b = false;

    /* renamed from: c, reason: collision with root package name */
    private boolean f3911c = false;
    private boolean e = false;

    public interface CallBack {
        void onResume();
    }

    public SplashDismissController(Activity activity) {
        this.f3909a = new WeakReference<>(activity);
        a();
    }

    public boolean isCallDismiss() {
        return this.e;
    }

    public boolean jumpToAdPage() {
        return this.f3910b && this.f3911c;
    }

    public void setCallBack(CallBack callBack) {
        this.d = callBack;
    }

    public void setCallDismiss(boolean z) {
        this.e = z;
    }

    public void setClick(boolean z) {
        this.f3910b = z;
    }

    private void a() {
        Activity activity = this.f3909a.get();
        if (activity != null) {
            activity.getApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.msdk.adapter.SplashDismissController.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityCreated(Activity activity2, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityDestroyed(Activity activity2) {
                    SplashDismissController.this.d = null;
                    if (SplashDismissController.this.f3909a == null || SplashDismissController.this.f3909a.get() != activity2) {
                        return;
                    }
                    activity2.getApplication().unregisterActivityLifecycleCallbacks(this);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityPaused(Activity activity2) {
                    if (SplashDismissController.this.f3909a == null || SplashDismissController.this.f3909a.get() != activity2) {
                        return;
                    }
                    SplashDismissController.this.f3911c = true;
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityResumed(Activity activity2) {
                    if (SplashDismissController.this.f3909a == null || SplashDismissController.this.f3909a.get() != activity2 || SplashDismissController.this.d == null) {
                        return;
                    }
                    SplashDismissController.this.d.onResume();
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivitySaveInstanceState(Activity activity2, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStarted(Activity activity2) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public void onActivityStopped(Activity activity2) {
                }
            });
        }
    }
}
