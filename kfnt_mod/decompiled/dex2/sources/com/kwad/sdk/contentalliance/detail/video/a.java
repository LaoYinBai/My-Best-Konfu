package com.kwad.sdk.contentalliance.detail.video;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Surface;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.core.video.kwai.c;
import com.kwad.sdk.utils.ai;
import com.kwad.sdk.utils.i;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static int b = -1;
    private static boolean c = false;
    private static final AtomicInteger d = new AtomicInteger(0);
    private com.kwad.sdk.core.video.kwai.c f;
    private int g;
    private long h;
    private Timer i;
    private TimerTask j;
    private Context l;
    private DetailVideoView m;
    private int n;
    private int o;
    private com.kwad.sdk.contentalliance.detail.video.c q;
    private String a = "MediaPlayerImpl";
    private volatile int e = 0;
    private Handler k = new Handler(Looper.getMainLooper());
    private long p = 0;
    private int r = 0;
    private List<c.d> s = new CopyOnWriteArrayList();
    private final AtomicBoolean t = new AtomicBoolean(false);
    private boolean u = false;
    private volatile List<c.e> v = new CopyOnWriteArrayList();
    private c.e w = new c.e() { // from class: com.kwad.sdk.contentalliance.detail.video.a.1
        @Override // com.kwad.sdk.core.video.kwai.c.e
        public void a(com.kwad.sdk.core.video.kwai.c cVar) {
            com.kwad.sdk.core.d.a.c(a.this.a, "onPrepared:" + a.this.b(a.this.e) + "->STATE_PREPARED");
            a.this.e = 2;
            a.this.a(a.this.e);
            Iterator it = a.this.v.iterator();
            while (it.hasNext()) {
                ((c.e) it.next()).a(a.this.f);
            }
        }
    };
    private c.h x = new c.h() { // from class: com.kwad.sdk.contentalliance.detail.video.a.2
        @Override // com.kwad.sdk.core.video.kwai.c.h
        public void a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
            if (a.this.m != null) {
                a.this.m.a(i, i2);
            }
            com.kwad.sdk.core.d.a.c(a.this.a, "onVideoSizeChanged ——> width：" + i + "， height：" + i2);
        }
    };
    private c.b y = new c.b() { // from class: com.kwad.sdk.contentalliance.detail.video.a.3
        @Override // com.kwad.sdk.core.video.kwai.c.b
        public void a(com.kwad.sdk.core.video.kwai.c cVar) {
            a.this.e = 9;
            a.this.a(a.this.e);
            com.kwad.sdk.core.d.a.c(a.this.a, "onCompletion ——> STATE_COMPLETED");
        }
    };
    private c.InterfaceC0046c z = new c.InterfaceC0046c() { // from class: com.kwad.sdk.contentalliance.detail.video.a.4
        @Override // com.kwad.sdk.core.video.kwai.c.InterfaceC0046c
        public boolean a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
            if (i == -38) {
                return true;
            }
            a.this.e = -1;
            a.this.n = i;
            a.this.o = i2;
            a.this.a(a.this.e);
            com.kwad.sdk.core.d.a.c(a.this.a, "onError ——> STATE_ERROR ———— what：" + i + ", extra: " + i2);
            return true;
        }
    };
    private c.d A = new c.d() { // from class: com.kwad.sdk.contentalliance.detail.video.a.5
        @Override // com.kwad.sdk.core.video.kwai.c.d
        public boolean a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
            String str;
            StringBuilder sb;
            String string;
            String str2;
            String str3;
            if (i != 3) {
                if (i == 701) {
                    if (a.this.e == 5 || a.this.e == 7) {
                        a.this.e = 7;
                        str2 = a.this.a;
                        str3 = "onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PAUSED";
                    } else {
                        a.this.e = 6;
                        str2 = a.this.a;
                        str3 = "onInfo ——> MEDIA_INFO_BUFFERING_START：STATE_BUFFERING_PLAYING";
                    }
                    com.kwad.sdk.core.d.a.c(str2, str3);
                    a.this.a(a.this.e);
                } else if (i == 702) {
                    if (a.this.e == 6) {
                        a.this.e = 4;
                        a.this.a(a.this.e);
                        com.kwad.sdk.core.d.a.c(a.this.a, "onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PLAYING");
                    }
                    if (a.this.e == 7) {
                        a.this.e = 5;
                        a.this.a(a.this.e);
                        str = a.this.a;
                        string = "onInfo ——> MEDIA_INFO_BUFFERING_END： STATE_PAUSED";
                        com.kwad.sdk.core.d.a.c(str, string);
                    }
                } else if (i != 10001) {
                    if (i == 801) {
                        str = a.this.a;
                        string = "视频不能seekTo，为直播视频";
                        com.kwad.sdk.core.d.a.c(str, string);
                    } else {
                        str = a.this.a;
                        sb = new StringBuilder();
                        sb.append("onInfo ——> what：");
                        sb.append(i);
                    }
                }
                a.this.a(a.this.f, i, i2);
                return true;
            }
            a.this.e = 4;
            a.this.a(a.this.e);
            str = a.this.a;
            sb = new StringBuilder();
            sb.append("onInfo ——> MEDIA_INFO_VIDEO_RENDERING_START：STATE_PLAYING, TIME ELAPSED: ");
            sb.append(System.currentTimeMillis() - a.this.p);
            string = sb.toString();
            com.kwad.sdk.core.d.a.c(str, string);
            a.this.a(a.this.f, i, i2);
            return true;
        }
    };
    private c.a B = new c.a() { // from class: com.kwad.sdk.contentalliance.detail.video.a.6
        @Override // com.kwad.sdk.core.video.kwai.c.a
        public void a(com.kwad.sdk.core.video.kwai.c cVar, int i) {
            a.this.g = i;
        }
    };
    private volatile List<d> C = new CopyOnWriteArrayList();

    /* renamed from: com.kwad.sdk.contentalliance.detail.video.a$a, reason: collision with other inner class name */
    private static class C0031a extends TimerTask {
        private WeakReference<a> a;

        C0031a(a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            a aVar = this.a.get();
            if (aVar != null) {
                aVar.s();
            }
        }
    }

    public interface b {
        @WorkerThread
        void a();
    }

    private static class c implements Runnable {
        private WeakReference<a> a;

        c(a aVar) {
            this.a = new WeakReference<>(aVar);
        }

        @Override // java.lang.Runnable
        public void run() {
            a aVar = this.a.get();
            if (aVar != null) {
                long jM = aVar.m();
                long jK = aVar.k();
                Iterator it = aVar.C.iterator();
                while (it.hasNext()) {
                    ((d) it.next()).a(jK, jM);
                }
            }
        }
    }

    public a(@Nullable DetailVideoView detailVideoView) {
        this.m = detailVideoView;
        if (detailVideoView != null) {
            this.l = detailVideoView.getContext().getApplicationContext();
        }
    }

    public static com.kwad.sdk.core.video.kwai.c a(@NonNull Context context, boolean z) {
        com.kwad.sdk.core.video.kwai.c bVar;
        boolean z2;
        try {
            if (com.kwad.sdk.core.video.kwai.e.b() && com.kwad.sdk.core.config.b.h() && com.kwad.sdk.core.video.kwai.e.a()) {
                com.kwad.sdk.core.d.a.c("MediaPlayerImpl", "constructPlayer KwaiMediaPlayer");
                bVar = new com.kwad.sdk.core.video.kwai.d(context);
                ((com.kwad.sdk.core.video.kwai.d) bVar).b(z);
            } else {
                com.kwad.sdk.core.d.a.c("MediaPlayerImpl", "constructPlayer AndroidMediaPlayer");
                bVar = new com.kwad.sdk.core.video.kwai.b();
            }
            z2 = false;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a("MediaPlayerImpl", "constructPlayer exception, using AndroidMediaPlayer", th);
            if (!c) {
                c = true;
                com.kwad.sdk.crash.a.a(th);
            }
            bVar = new com.kwad.sdk.core.video.kwai.b();
            z2 = true;
        }
        int iA = ai.a(com.kwad.sdk.core.video.kwai.e.b(), KsAdSDKImpl.get().getIsExternal(), com.kwad.sdk.core.config.b.h(), com.kwad.sdk.core.video.kwai.e.a(), z2, com.kwad.sdk.core.config.b.G(), bVar.o());
        com.kwad.sdk.core.d.a.a("player v=" + Integer.toBinaryString(iA));
        if (b != iA) {
            b = iA;
            com.kwad.sdk.core.report.d.c(iA);
        }
        return bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(com.kwad.sdk.core.video.kwai.c cVar, b bVar) {
        if (cVar == null) {
            return;
        }
        try {
            cVar.l();
            if (bVar != null) {
                bVar.a();
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    private void b(boolean z) {
        if (this.m != null) {
            this.m.setKeepScreenOn(z);
        }
    }

    private void c(int i) {
        if (this.q == null || this.q.d == null) {
            return;
        }
        this.q.d.mVideoPlayerType = i;
    }

    private void o() {
        if (this.f == null) {
            return;
        }
        this.f.a((c.InterfaceC0046c) null);
        this.f.a((c.b) null);
        this.f.a((c.e) null);
        this.f.a((c.h) null);
        this.f.a((c.d) null);
        this.f.a((c.f) null);
        this.f.a((c.a) null);
    }

    private void p() {
        com.kwad.sdk.core.d.a.c(this.a, "reset:" + b(this.e) + "->STATE_IDLE");
        this.f.m();
        this.e = 0;
    }

    private void q() {
        r();
        if (this.i == null) {
            this.i = new Timer("ksad-DetailPlayer-timer" + d.getAndIncrement());
        }
        if (this.j == null) {
            this.j = new C0031a(this);
            this.i.schedule(this.j, 0L, 500L);
        }
    }

    private void r() {
        if (this.i != null) {
            this.i.cancel();
            this.i = null;
        }
        if (this.j != null) {
            this.j.cancel();
            this.j = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        this.k.post(new c(this));
    }

    public com.kwad.sdk.core.video.kwai.c a() {
        return this.f;
    }

    public void a(float f, float f2) {
        if (this.f == null) {
            return;
        }
        this.f.a(f, f2);
    }

    public void a(int i) {
        if (this.C == null) {
            return;
        }
        for (d dVar : this.C) {
            if (dVar != null) {
                if (i == -1) {
                    b(false);
                    r();
                    dVar.a(this.n, this.o);
                } else if (i != 9) {
                    switch (i) {
                        case 1:
                            dVar.a();
                            break;
                        case 2:
                            dVar.b();
                            break;
                        case 3:
                            b(true);
                            dVar.c();
                            break;
                        case 4:
                            b(true);
                            dVar.d();
                            break;
                        case 5:
                            b(false);
                            dVar.e();
                            break;
                        case 6:
                            dVar.g();
                            break;
                        case 7:
                            dVar.h();
                            break;
                    }
                } else {
                    if (this.f != null && !this.f.n()) {
                        b(false);
                        r();
                    }
                    dVar.f();
                }
            }
        }
    }

    public void a(long j) {
        if (this.f != null) {
            this.f.a(j);
        }
    }

    public void a(Surface surface) {
        if (this.f != null) {
            this.f.a(surface);
        }
    }

    public void a(b bVar) {
        a(bVar, true);
    }

    public void a(final b bVar, boolean z) {
        if (this.f == null) {
            return;
        }
        b(false);
        this.k.removeCallbacksAndMessages(null);
        r();
        o();
        this.m = null;
        if (this.f != null) {
            if (z) {
                final com.kwad.sdk.core.video.kwai.c cVar = this.f;
                i.a(new Runnable() { // from class: com.kwad.sdk.contentalliance.detail.video.a.9
                    @Override // java.lang.Runnable
                    public void run() {
                        a.this.a(cVar, bVar);
                    }
                });
            } else {
                a(this.f, bVar);
            }
            this.f = null;
        }
        com.kwad.sdk.core.d.a.c(this.a, "release:" + b(this.e) + "->STATE_IDLE");
        this.e = 0;
        this.r = 0;
    }

    public void a(com.kwad.sdk.contentalliance.detail.video.b bVar) {
        if (this.f instanceof com.kwad.sdk.core.video.kwai.d) {
            ((com.kwad.sdk.core.video.kwai.d) this.f).a(bVar);
        }
    }

    public void a(@NonNull com.kwad.sdk.contentalliance.detail.video.c cVar) {
        try {
            if (TextUtils.isEmpty(cVar.b)) {
                com.kwad.sdk.core.d.a.e(this.a, "videoUrl is null");
            } else {
                com.kwad.sdk.core.d.a.e(this.a, "videoUrl=" + cVar.b);
                this.f.a(cVar);
            }
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    public void a(@NonNull com.kwad.sdk.contentalliance.detail.video.c cVar, @NonNull DetailVideoView detailVideoView) {
        a(cVar, true, false, detailVideoView);
    }

    public void a(@NonNull com.kwad.sdk.contentalliance.detail.video.c cVar, boolean z, @NonNull DetailVideoView detailVideoView, @NonNull com.kwad.sdk.core.video.kwai.c cVar2) {
        com.kwad.sdk.core.d.a.c(this.a, "initMediaPlayer");
        if (cVar == null || detailVideoView == null || cVar2 == null) {
            return;
        }
        if (this.l == null) {
            this.l = detailVideoView.getContext().getApplicationContext();
        }
        this.u = z;
        this.q = cVar;
        DetailVideoView detailVideoView2 = this.m;
        if (detailVideoView2 != detailVideoView) {
            com.kwad.sdk.core.d.a.c(this.a, "initMediaPlayer videoView changed");
            if (detailVideoView2 != null) {
                detailVideoView2.setMediaPlayer(null);
                detailVideoView.setKeepScreenOn(detailVideoView2.getKeepScreenOn());
                detailVideoView2.setKeepScreenOn(false);
            }
            this.m = detailVideoView;
        }
        detailVideoView.setMediaPlayer(this);
        if (this.f != cVar2) {
            com.kwad.sdk.core.d.a.c(this.a, "initMediaPlayer mediaPlayer changed");
            if (this.f != null) {
                cVar2.a(this.f.n());
                o();
                this.f.l();
            }
            this.f = cVar2;
            p();
            c();
            cVar2.b(3);
        } else {
            com.kwad.sdk.core.d.a.c(this.a, "initMediaPlayer mediaPlayer not changed");
            p();
            o();
            c();
        }
        this.f.a(detailVideoView.b);
        if (this.f instanceof com.kwad.sdk.core.video.kwai.d) {
            ((com.kwad.sdk.core.video.kwai.d) this.f).a(detailVideoView);
        }
    }

    public void a(@NonNull com.kwad.sdk.contentalliance.detail.video.c cVar, boolean z, boolean z2, @NonNull DetailVideoView detailVideoView) {
        com.kwad.sdk.core.d.a.c(this.a, "initMediaPlayer enablePreLoad:" + z);
        if (cVar == null || detailVideoView == null) {
            return;
        }
        com.kwad.sdk.core.video.kwai.c cVarA = a(this.l, z);
        cVarA.a(false);
        a(cVar, z2, detailVideoView, cVarA);
    }

    public void a(d dVar) {
        this.C.add(dVar);
    }

    public void a(c.e eVar) {
        this.v.add(eVar);
    }

    public void a(com.kwad.sdk.core.video.kwai.c cVar, int i, int i2) {
        Iterator<c.d> it = this.s.iterator();
        while (it.hasNext()) {
            c.d next = it.next();
            if (next == null) {
                it.remove();
            } else {
                next.a(cVar, i, i2);
            }
        }
    }

    public void a(boolean z) {
        float f = z ? 1.0f : 0.0f;
        a(f, f);
    }

    public int b() {
        if (this.f != null) {
            return this.f.o();
        }
        return 0;
    }

    public String b(int i) {
        switch (i) {
            case -1:
                return "STATE_ERROR";
            case 0:
                return "STATE_IDLE";
            case 1:
                return "STATE_PREPARING";
            case 2:
                return "STATE_PREPARED";
            case 3:
                return "STATE_STARTED";
            case 4:
                return "STATE_PLAYING";
            case 5:
                return "STATE_PAUSED";
            case 6:
                return "STATE_BUFFERING_PLAYING";
            case 7:
                return "STATE_BUFFERING_PAUSED";
            case 8:
                return "PLAYER_STATE_STOPPED";
            case 9:
                return "STATE_COMPLETED";
            default:
                return "STATE_UNKNOWN";
        }
    }

    public void b(com.kwad.sdk.contentalliance.detail.video.c cVar) {
        if (this.f == null) {
            com.kwad.sdk.core.d.a.d("resetAndPlay", "mMediaPlayer is null");
            return;
        }
        if (this.e == 2 || this.e == 3 || this.e == 4 || this.e == 5) {
            com.kwad.sdk.core.d.a.d("resetAndPlay", "can not resetAndPlay in sate:" + this.e);
            return;
        }
        p();
        o();
        c();
        d();
    }

    public void b(d dVar) {
        this.C.remove(dVar);
    }

    protected void c() {
        this.f.a(this.w);
        this.f.a(this.x);
        this.f.a(this.y);
        this.f.a(this.z);
        this.f.a(this.A);
        this.f.a(this.B);
    }

    public void d() {
        if (this.u) {
            if (this.t.compareAndSet(false, true)) {
                i.a(new Runnable() { // from class: com.kwad.sdk.contentalliance.detail.video.a.7
                    @Override // java.lang.Runnable
                    public void run() {
                        boolean zE;
                        com.kwad.sdk.core.d.a.c(a.this.a, "prepareAsync now:" + a.this.b(a.this.e));
                        if (a.this.f == null) {
                            return;
                        }
                        try {
                            try {
                                synchronized (a.this.f) {
                                    a.this.a(a.this.q);
                                    zE = a.this.f.e();
                                    com.kwad.sdk.core.d.a.c(a.this.a, "prepareAsync:" + a.this.b(a.this.e) + "->STATE_PREPARING, success: " + zE);
                                    a.this.e = 1;
                                }
                                if (zE) {
                                    a.this.k.post(new Runnable() { // from class: com.kwad.sdk.contentalliance.detail.video.a.7.1
                                        @Override // java.lang.Runnable
                                        public void run() {
                                            a.this.a(a.this.e);
                                        }
                                    });
                                } else {
                                    com.kwad.sdk.core.d.a.e(a.this.a, "prepareAsync failed");
                                }
                            } finally {
                                a.this.t.set(false);
                            }
                        } catch (Throwable th) {
                            com.kwad.sdk.core.d.a.a(a.this.a, "prepareAsync Exception:" + a.this.b(a.this.e), th);
                        }
                        try {
                            synchronized (a.this.t) {
                                a.this.t.notifyAll();
                            }
                        } catch (Exception e) {
                            com.kwad.sdk.core.d.a.a(e);
                        }
                    }
                });
                return;
            }
            return;
        }
        try {
            a(this.q);
            if (this.f.e()) {
                com.kwad.sdk.core.d.a.c(this.a, "prepareAsync:" + b(this.e) + "->STATE_PREPARING");
                this.e = 1;
                a(this.e);
            } else {
                com.kwad.sdk.core.d.a.e(this.a, "prepareAsync failed");
            }
        } catch (Throwable th) {
            if (b() != 2) {
                int i = this.r;
                this.r = i + 1;
                if (i <= 4) {
                    b(this.q);
                }
            }
            com.kwad.sdk.core.d.a.c(this.a, "prepareAsync Exception:" + b(this.e));
            com.kwad.sdk.core.d.a.a(th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00af  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void e() {
        /*
            r7 = this;
            java.lang.String r0 = r7.a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "start mCurrentState: "
            r1.append(r2)
            int r2 = r7.e
            java.lang.String r2 = r7.b(r2)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.kwad.sdk.core.d.a.c(r0, r1)
            int r0 = r7.e
            r1 = 2
            if (r0 != 0) goto L4c
            java.lang.String r0 = r7.a
            java.lang.String r2 = "start, still not prepared well, prepare again"
            com.kwad.sdk.core.d.a.c(r0, r2)
            r7.d()
            int r0 = r7.b()
            if (r0 != r1) goto L4b
            boolean r0 = r7.u
            if (r0 == 0) goto L46
            java.util.concurrent.atomic.AtomicBoolean r0 = r7.t
            boolean r0 = r0.get()
            if (r0 == 0) goto L46
            com.kwad.sdk.contentalliance.detail.video.a$8 r0 = new com.kwad.sdk.contentalliance.detail.video.a$8
            r0.<init>()
            com.kwad.sdk.utils.i.a(r0)
            goto L4b
        L46:
            com.kwad.sdk.core.video.kwai.c r0 = r7.f
            r0.f()
        L4b:
            return
        L4c:
            int r0 = r7.e
            r2 = 9
            if (r0 == r1) goto L56
            int r0 = r7.e
            if (r0 != r2) goto Lc2
        L56:
            long r3 = java.lang.System.currentTimeMillis()
            r7.p = r3
            com.kwad.sdk.core.video.kwai.c r0 = r7.f
            r0.f()
            long r3 = r7.h
            r5 = 0
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 == 0) goto L72
            com.kwad.sdk.core.video.kwai.c r0 = r7.f
            long r3 = r7.h
            int r3 = (int) r3
            long r3 = (long) r3
            r0.a(r3)
        L72:
            java.lang.String r0 = r7.a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "start:"
            r3.append(r4)
            int r4 = r7.e
            java.lang.String r4 = r7.b(r4)
            r3.append(r4)
            java.lang.String r4 = "->STATE_STARTED"
            r3.append(r4)
            java.lang.String r3 = r3.toString()
            com.kwad.sdk.core.d.a.c(r0, r3)
            com.kwad.sdk.contentalliance.detail.video.c r0 = r7.q
            r3 = 3
            if (r0 == 0) goto Lb8
            com.kwad.sdk.contentalliance.detail.video.c r0 = r7.q
            com.kwad.sdk.core.response.model.VideoPlayerStatus r0 = r0.d
            if (r0 == 0) goto Lb8
            int r0 = r7.e
            if (r0 != r1) goto Lb3
            com.kwad.sdk.contentalliance.detail.video.c r0 = r7.q
            com.kwad.sdk.core.response.model.VideoPlayerStatus r0 = r0.d
            int r0 = r0.mVideoPlayerType
            if (r0 != 0) goto Laf
            r0 = 1
            r7.c(r0)
            goto Lb8
        Laf:
            r7.c(r3)
            goto Lb8
        Lb3:
            int r0 = r7.e
            if (r0 != r2) goto Lb8
            goto Laf
        Lb8:
            r7.e = r3
            int r0 = r7.e
            r7.a(r0)
            r7.q()
        Lc2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.contentalliance.detail.video.a.e():void");
    }

    public void f() {
        if (this.f == null) {
            com.kwad.sdk.core.d.a.e(this.a, "resume but mMediaPlayer is null");
            return;
        }
        com.kwad.sdk.core.d.a.c(this.a, "resume mCurrentState: " + b(this.e));
        if (this.e == 2 || this.e == 0) {
            com.kwad.sdk.core.d.a.c(this.a, "resume:" + b(this.e) + "->start()");
            e();
            return;
        }
        if (this.e == 5) {
            this.f.f();
            com.kwad.sdk.core.d.a.c(this.a, "resume:" + b(this.e) + "->STATE_PLAYING");
            this.e = 4;
            a(this.e);
            c(2);
            return;
        }
        if (this.e != 7) {
            com.kwad.sdk.core.d.a.e(this.a, "resume: " + b(this.e) + " 此时不能调用resume()方法.");
            return;
        }
        this.f.f();
        com.kwad.sdk.core.d.a.c(this.a, "resume:" + b(this.e) + "->STATE_BUFFERING_PLAYING");
        this.e = 6;
        a(this.e);
    }

    public boolean g() {
        boolean z;
        com.kwad.sdk.core.d.a.c(this.a, "pause mCurrentState: " + b(this.e));
        if (this.e == 4) {
            this.f.g();
            com.kwad.sdk.core.d.a.c(this.a, "pause STATE_PLAYING->STATE_PAUSED");
            this.e = 5;
            a(this.e);
            z = true;
        } else {
            z = false;
        }
        if (this.e == 6) {
            this.f.g();
            com.kwad.sdk.core.d.a.c(this.a, "pause STATE_BUFFERING_PLAYING->STATE_PAUSED");
            this.e = 7;
            a(this.e);
            z = true;
        }
        if (this.e == 3) {
            this.f.g();
            com.kwad.sdk.core.d.a.c(this.a, "pause STATE_STARTED->STATE_PAUSED");
            this.e = 5;
            a(this.e);
            z = true;
        }
        if (this.e != 9 || !this.f.n()) {
            return z;
        }
        this.f.g();
        com.kwad.sdk.core.d.a.c(this.a, "pause " + b(this.e) + "->STATE_PAUSED");
        this.e = 5;
        a(this.e);
        return true;
    }

    public void h() {
        a((b) null);
    }

    public int i() {
        if (this.f != null) {
            return this.f.h();
        }
        return 0;
    }

    public int j() {
        if (this.f != null) {
            return this.f.i();
        }
        return 0;
    }

    public long k() {
        if (this.f != null) {
            return this.f.k();
        }
        return 0L;
    }

    public long l() {
        if (this.f != null) {
            return this.f.j();
        }
        return 0L;
    }

    public long m() {
        if (this.f != null) {
            return this.f.j();
        }
        return 0L;
    }

    public void n() {
        this.C.clear();
    }
}
