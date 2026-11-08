package com.bytedance.gameprotect;

import android.content.Context;
import androidx.annotation.NonNull;
import c.a.a.a.a.ah;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
abstract class b extends Thread {

    /* renamed from: a, reason: collision with root package name */
    protected final UserConfig f3864a;

    /* renamed from: b, reason: collision with root package name */
    protected final Context f3865b;

    static class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            ah.a().a(k.b());
        }
    }

    public b(@NonNull Context context, @NonNull UserConfig userConfig) {
        this.f3865b = context.getApplicationContext();
        this.f3864a = userConfig;
        a(userConfig);
        a(this.f3865b);
        v vVarA = v.a();
        Context context2 = this.f3865b;
        UserConfig userConfig2 = this.f3864a;
        vVarA.a(context2, userConfig2.appId, userConfig2.region);
    }

    static void b() {
        AppLifecycleListener.a();
    }

    static void c() {
        k.a(new a());
    }

    public abstract void a();

    public abstract void a(Context context);

    public abstract void a(@NonNull UserConfig userConfig);

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        b();
        c();
        a();
        p.a(this.f3865b.getAssets());
        IlIlllIiI.a();
    }
}
