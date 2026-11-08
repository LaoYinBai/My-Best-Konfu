package cn.com.chinatelecom.account.api.c;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class e implements Runnable {

    /* renamed from: c, reason: collision with root package name */
    private static Handler f2254c = new Handler(Looper.getMainLooper());

    /* renamed from: a, reason: collision with root package name */
    private boolean f2255a = false;

    /* renamed from: b, reason: collision with root package name */
    private long f2256b;
    private a d;

    private static class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private e f2257a;

        public a(e eVar) {
            this.f2257a = eVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.f2257a != null) {
                this.f2257a.b();
            }
        }
    }

    public e() {
    }

    public e(long j) {
        this.f2256b = j;
    }

    private void e() {
        this.d = new a(this);
        f2254c.postDelayed(this.d, this.f2256b);
    }

    public abstract void a();

    public void a(boolean z) {
        this.f2255a = z;
    }

    public void b() {
    }

    public boolean c() {
        return this.f2255a;
    }

    public void d() {
        try {
            if (this.d != null) {
                f2254c.removeCallbacks(this.d);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f2256b > 0) {
            e();
        }
        a();
    }
}
