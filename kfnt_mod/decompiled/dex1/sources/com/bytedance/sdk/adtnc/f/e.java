package com.bytedance.sdk.adtnc.f;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends a {

    /* renamed from: c, reason: collision with root package name */
    private Handler f5113c;
    private long d;
    private int e;
    private boolean f;

    public e(com.bytedance.sdk.adtnc.sdk.b.a aVar) {
        super(aVar);
        this.f5113c = new Handler(Looper.getMainLooper()) { // from class: com.bytedance.sdk.adtnc.f.e.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                com.bytedance.sdk.adtnc.g.b.a("TimerStrategy", "timerHandler", "strategy trigger timer false");
                e.this.c();
            }
        };
        this.e = 0;
        this.f = false;
        this.d = System.currentTimeMillis();
        a();
        d();
    }

    private void a() {
        ((Application) this.f5108a.a().getApplicationContext()).registerActivityLifecycleCallbacks(new Application.ActivityLifecycleCallbacks() { // from class: com.bytedance.sdk.adtnc.f.e.2
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
                e.b(e.this);
                if (e.this.f) {
                    e.this.b();
                    e.this.f = false;
                }
            }

            @Override // android.app.Application.ActivityLifecycleCallbacks
            public void onActivityStopped(Activity activity) {
                e.e(e.this);
                e.this.f = e.this.e == 0;
            }
        });
    }

    static /* synthetic */ int b(e eVar) {
        int i = eVar.e;
        eVar.e = i + 1;
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        com.bytedance.sdk.adtnc.g.b.a("TimerStrategy", "appReenter", "strategy trigger focus true");
        e();
        a(true);
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (System.currentTimeMillis() - this.d >= 10800000) {
            a(false);
            d();
        }
    }

    private void d() {
        e();
        this.f5113c.sendEmptyMessageDelayed(1, 10801000L);
    }

    static /* synthetic */ int e(e eVar) {
        int i = eVar.e;
        eVar.e = i - 1;
        return i;
    }

    private void e() {
        this.f5113c.removeCallbacksAndMessages(null);
    }
}
