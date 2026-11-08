package com.baidu.mobads.sdk.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mobads.sdk.api.IXAdContainerFactory;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class u {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3249a = "LoadRemoteDex";
    private static u i;

    /* renamed from: b, reason: collision with root package name */
    private IXAdContainerFactory f3250b;

    /* renamed from: c, reason: collision with root package name */
    private ax f3251c;
    private Runnable f;
    private Context g;
    private boolean k;
    private int d = 5000;
    private Handler e = new Handler(Looper.getMainLooper());
    private bb h = bb.a();
    private AtomicBoolean j = new AtomicBoolean(false);

    public interface a {

        /* renamed from: a, reason: collision with root package name */
        public static final int f3252a = 1;

        /* renamed from: b, reason: collision with root package name */
        public static final int f3253b = 2;

        void onFailure();

        void onSuccess();
    }

    public static u a() {
        if (i == null) {
            synchronized (u.class) {
                if (i == null) {
                    i = new u();
                }
            }
        }
        return i;
    }

    private u() {
    }

    public void a(Context context, a aVar) {
        if (context == null) {
            this.h.c(f3249a, "init Context is null,error");
            return;
        }
        this.g = context.getApplicationContext();
        k.a().a(aVar);
        if (this.f3250b != null) {
            k();
        } else {
            if (this.j.get()) {
                return;
            }
            f();
        }
    }

    public Context b() {
        return this.g;
    }

    public IXAdContainerFactory c() {
        if (this.g == null) {
            return null;
        }
        if (this.f3250b == null && !this.j.get()) {
            f();
        }
        return this.f3250b;
    }

    private void f() {
        this.j.set(true);
        if (af.a()) {
            h();
        } else {
            g();
        }
    }

    private void g() {
        synchronized (u.class) {
            try {
                this.f3251c = new ax(Class.forName(r.av, true, getClass().getClassLoader()), this.g);
                this.f3250b = this.f3251c.a();
                k();
            } catch (Exception unused) {
                a("反射调用remote失败");
            }
        }
    }

    private void h() {
        this.f = new v(this);
        j();
        if (f.f3227a == null) {
            synchronized (bh.class) {
                if (f.f3227a == null) {
                    f.f3227a = new bh(this.g);
                }
            }
        }
        if (this.f3250b != null) {
            k();
        } else if (f.f3227a != null) {
            this.h.a(f3249a, "start load apk");
            f.f3227a.a(new w(this));
        } else {
            this.h.a(f3249a, "BaiduXAdSDKContext.mApkLoader == null,not load apk");
        }
    }

    private void i() {
        if (this.f != null) {
            this.e.removeCallbacks(this.f);
        }
        this.f = null;
    }

    private void j() {
        if (this.f != null) {
            this.e.postDelayed(this.f, this.d);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        this.j.set(false);
        av.a(this.g);
        i();
        k.a().a(1);
        bv.a(this.g).b();
        bv.a(this.g).a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        this.h.a(f3249a, "加载dex失败原因=" + str);
        this.j.set(false);
        i();
        k.a().a(2);
    }

    public String d() {
        if (this.f3250b == null) {
            return "";
        }
        return "_" + this.f3250b.getRemoteVersion();
    }

    public boolean e() {
        return this.k;
    }
}
