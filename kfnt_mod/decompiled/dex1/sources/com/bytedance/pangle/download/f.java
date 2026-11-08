package com.bytedance.pangle.download;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import com.bytedance.pangle.Zeus;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: a, reason: collision with root package name */
    private static volatile f f4578a;

    public static f a() {
        if (f4578a == null) {
            synchronized (f.class) {
                if (f4578a == null) {
                    f4578a = new f();
                }
            }
        }
        return f4578a;
    }

    public static void b() {
        if (com.bytedance.pangle.helper.c.b(Zeus.getAppApplication())) {
            g gVarA = g.a();
            b bVarA = b.a();
            bVarA.f4562b.add(new a() { // from class: com.bytedance.pangle.download.g.1
                AnonymousClass1() {
                }

                @Override // com.bytedance.pangle.download.a
                public final void a() {
                    g.this.b();
                }

                @Override // com.bytedance.pangle.download.a
                public final void b() {
                    g.a(g.this);
                }
            });
            Zeus.getAppApplication().registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.pangle.download.b.1
                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityCreated(Activity activity, Bundle bundle) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityDestroyed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityPaused(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityResumed(Activity activity) {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
                }

                AnonymousClass1() {
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityStarted(Activity activity) {
                    if (b.this.e == 0) {
                        b.this.f4561a = false;
                        if (!b.this.f4563c) {
                            Iterator it = b.this.f4562b.iterator();
                            while (it.hasNext()) {
                                ((a) it.next()).b();
                            }
                            b.this.f4563c = true;
                        }
                        Iterator it2 = b.this.f4562b.iterator();
                        while (it2.hasNext()) {
                            ((a) it2.next()).a();
                        }
                    }
                    b.c(b.this);
                }

                @Override // android.app.Application.ActivityLifecycleCallbacks
                public final void onActivityStopped(Activity activity) {
                    b.d(b.this);
                    if (b.this.e == 0) {
                        b.this.f4561a = true;
                        Iterator it = b.this.f4562b.iterator();
                        while (it.hasNext()) {
                            it.next();
                        }
                    }
                }
            });
        }
    }
}
