package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.support.v4.media.session.PlaybackStateCompat;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class p {

    /* renamed from: a, reason: collision with root package name */
    static o f1490a;

    /* renamed from: b, reason: collision with root package name */
    static long f1491b;

    private p() {
    }

    static o a() {
        synchronized (p.class) {
            if (f1490a == null) {
                return new o();
            }
            o oVar = f1490a;
            f1490a = oVar.f;
            oVar.f = null;
            f1491b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            return oVar;
        }
    }

    static void a(o oVar) {
        if (oVar.f == null && oVar.g == null) {
            if (oVar.d) {
                return;
            }
            synchronized (p.class) {
                if (f1491b + PlaybackStateCompat.ACTION_PLAY_FROM_URI > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    return;
                }
                f1491b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                oVar.f = f1490a;
                oVar.f1489c = 0;
                oVar.f1488b = 0;
                f1490a = oVar;
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
