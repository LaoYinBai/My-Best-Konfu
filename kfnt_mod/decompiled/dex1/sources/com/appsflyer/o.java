package com.appsflyer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import java.lang.ref.WeakReference;
import java.util.concurrent.RejectedExecutionException;

@RequiresApi(api = 14)
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class o implements Application.ActivityLifecycleCallbacks {

    /* renamed from: ˏ, reason: contains not printable characters */
    private static o f245;

    /* renamed from: ˋ, reason: contains not printable characters */
    private boolean f247 = false;

    /* renamed from: ॱ, reason: contains not printable characters */
    private boolean f248 = true;

    /* renamed from: ˊ, reason: contains not printable characters */
    private d f246 = null;

    interface d {
        /* renamed from: ˊ */
        void mo117(Activity activity);

        /* renamed from: ˊ */
        void mo118(WeakReference<Context> weakReference);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityDestroyed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityStopped(Activity activity) {
    }

    o() {
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    static /* synthetic */ boolean m197(o oVar) {
        oVar.f247 = false;
        return false;
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    static o m193() {
        if (f245 == null) {
            f245 = new o();
        }
        return f245;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    public static o m196() {
        if (f245 == null) {
            throw new IllegalStateException("Foreground is not initialised - invoke at least once with parameter init/get");
        }
        return f245;
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    public final void m199(Application application, d dVar) {
        this.f246 = dVar;
        if (Build.VERSION.SDK_INT >= 14) {
            application.registerActivityLifecycleCallbacks(f245);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityResumed(Activity activity) {
        this.f248 = false;
        boolean z = !this.f247;
        this.f247 = true;
        if (z) {
            try {
                this.f246.mo117(activity);
            } catch (Exception e2) {
                AFLogger.afErrorLog("Listener threw exception! ", e2);
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityPaused(Activity activity) {
        this.f248 = true;
        try {
            new e(new WeakReference(activity.getApplicationContext())).executeOnExecutor(AFExecutor.getInstance().getThreadPoolExecutor(), new Void[0]);
        } catch (RejectedExecutionException e2) {
            AFLogger.afErrorLog("backgroundTask.executeOnExecutor failed with RejectedExecutionException Exception", e2);
        } catch (Throwable th) {
            AFLogger.afErrorLog("backgroundTask.executeOnExecutor failed with Exception", th);
        }
    }

    class e extends AsyncTask<Void, Void, Void> {

        /* renamed from: ˋ, reason: contains not printable characters */
        private WeakReference<Context> f249;

        @Override // android.os.AsyncTask
        protected final /* synthetic */ Void doInBackground(Void[] voidArr) {
            return m200();
        }

        public e(WeakReference<Context> weakReference) {
            this.f249 = weakReference;
        }

        /* renamed from: ॱ, reason: contains not printable characters */
        private Void m200() throws InterruptedException {
            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                AFLogger.afErrorLog("Sleeping attempt failed (essential for background state verification)\n", e);
            }
            if (o.this.f247 && o.this.f248) {
                o.m197(o.this);
                try {
                    o.this.f246.mo118(this.f249);
                } catch (Exception e2) {
                    AFLogger.afErrorLog("Listener threw exception! ", e2);
                    cancel(true);
                }
            }
            this.f249.clear();
            return null;
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public final void onActivityCreated(Activity activity, Bundle bundle) {
        AFDeepLinkManager.getInstance().collectIntentsFromActivities(activity.getIntent());
    }
}
