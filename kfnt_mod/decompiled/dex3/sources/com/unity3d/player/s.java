package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import com.unity3d.player.r;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class s {
    private UnityPlayer a;
    private a c;
    private Context b = null;
    private final Semaphore d = new Semaphore(0);
    private final Lock e = new ReentrantLock();
    private r f = null;
    private int g = 2;
    private boolean h = false;
    private boolean i = false;

    /* renamed from: com.unity3d.player.s$1, reason: invalid class name */
    final class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ boolean e;
        final /* synthetic */ long f;
        final /* synthetic */ long g;

        AnonymousClass1(String str, int i, int i2, int i3, boolean z, long j, long j2) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = z;
            this.f = j;
            this.g = j2;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (s.this.f != null) {
                g.Log(5, "Video already playing");
                s.this.g = 2;
                s.this.d.release();
            } else {
                s.this.f = new r(s.this.b, this.a, this.b, this.c, this.d, this.e, this.f, this.g, new r.a() { // from class: com.unity3d.player.s.1.1
                    @Override // com.unity3d.player.r.a
                    public final void a(int i) {
                        s.this.e.lock();
                        s.this.g = i;
                        if (i == 3 && s.this.i) {
                            s.this.runOnUiThread(new Runnable() { // from class: com.unity3d.player.s.1.1.1
                                @Override // java.lang.Runnable
                                public final void run() throws IllegalStateException {
                                    s.this.d();
                                    s.this.a.resume();
                                }
                            });
                        }
                        if (i != 0) {
                            s.this.d.release();
                        }
                        s.this.e.unlock();
                    }
                });
                if (s.this.f != null) {
                    s.this.a.addView(s.this.f);
                }
            }
        }
    }

    public interface a {
        void a();
    }

    s(UnityPlayer unityPlayer) {
        this.a = null;
        this.a = unityPlayer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() throws IllegalStateException {
        if (this.f != null) {
            this.a.removeViewFromPlayer(this.f);
            this.i = false;
            this.f.destroyPlayer();
            this.f = null;
            if (this.c != null) {
                this.c.a();
            }
        }
    }

    static /* synthetic */ boolean h(s sVar) {
        sVar.i = true;
        return true;
    }

    public final void a() {
        this.e.lock();
        if (this.f != null) {
            if (this.g == 0) {
                this.f.CancelOnPrepare();
            } else if (this.i) {
                this.h = this.f.a();
                if (!this.h) {
                    this.f.pause();
                }
            }
        }
        this.e.unlock();
    }

    public final boolean a(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, a aVar) {
        this.e.lock();
        this.c = aVar;
        this.b = context;
        this.d.drainPermits();
        this.g = 2;
        runOnUiThread(new AnonymousClass1(str, i, i2, i3, z, j, j2));
        boolean z2 = false;
        try {
            this.e.unlock();
            this.d.acquire();
            this.e.lock();
            if (this.g != 2) {
                z2 = true;
            }
        } catch (InterruptedException unused) {
        }
        runOnUiThread(new Runnable() { // from class: com.unity3d.player.s.2
            @Override // java.lang.Runnable
            public final void run() {
                s.this.a.pause();
            }
        });
        runOnUiThread((!z2 || this.g == 3) ? new Runnable() { // from class: com.unity3d.player.s.4
            @Override // java.lang.Runnable
            public final void run() throws IllegalStateException {
                s.this.d();
                s.this.a.resume();
            }
        } : new Runnable() { // from class: com.unity3d.player.s.3
            @Override // java.lang.Runnable
            public final void run() {
                if (s.this.f != null) {
                    s.this.a.addViewToPlayer(s.this.f, true);
                    s.h(s.this);
                    s.this.f.requestFocus();
                }
            }
        });
        this.e.unlock();
        return z2;
    }

    public final void b() {
        this.e.lock();
        if (this.f != null && this.i && !this.h) {
            this.f.start();
        }
        this.e.unlock();
    }

    public final void c() {
        this.e.lock();
        if (this.f != null) {
            this.f.updateVideoLayout();
        }
        this.e.unlock();
    }

    protected final void runOnUiThread(Runnable runnable) {
        if (this.b instanceof Activity) {
            ((Activity) this.b).runOnUiThread(runnable);
        } else {
            g.Log(5, "Not running from an Activity; Ignoring execution request...");
        }
    }
}
