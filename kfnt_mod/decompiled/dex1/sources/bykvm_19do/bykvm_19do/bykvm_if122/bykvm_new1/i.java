package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private b f2010a;

    /* renamed from: b, reason: collision with root package name */
    private final Application.ActivityLifecycleCallbacks f2011b = new a();

    class a implements Application.ActivityLifecycleCallbacks {

        /* renamed from: a, reason: collision with root package name */
        private int f2012a = 0;

        a() {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityDestroyed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityPaused(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityResumed(Activity activity) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStarted(Activity activity) {
            this.f2012a++;
            if (this.f2012a != 1 || i.this.f2010a == null) {
                return;
            }
            i.this.f2010a.a();
        }

        @Override // android.app.Application.ActivityLifecycleCallbacks
        public void onActivityStopped(Activity activity) {
            this.f2012a--;
            if (this.f2012a != 0 || i.this.f2010a == null) {
                return;
            }
            i.this.f2010a.b();
        }
    }

    public interface b {
        void a();

        void b();
    }

    public void a(Application application, b bVar) {
        if (application != null) {
            this.f2010a = bVar;
            application.registerActivityLifecycleCallbacks(this.f2011b);
        }
    }

    public void a(Application application) {
        if (application != null) {
            application.unregisterActivityLifecycleCallbacks(this.f2011b);
        }
    }
}
