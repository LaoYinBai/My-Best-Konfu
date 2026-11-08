package c;

import android.support.v4.media.session.PlaybackStateCompat;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class v {

    /* renamed from: a, reason: collision with root package name */
    static final long f2185a = 65536;

    /* renamed from: b, reason: collision with root package name */
    @Nullable
    static u f2186b;

    /* renamed from: c, reason: collision with root package name */
    static long f2187c;

    private v() {
    }

    static u a() {
        synchronized (v.class) {
            if (f2186b != null) {
                u uVar = f2186b;
                f2186b = uVar.h;
                uVar.h = null;
                f2187c -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                return uVar;
            }
            return new u();
        }
    }

    static void a(u uVar) {
        if (uVar.h != null || uVar.i != null) {
            throw new IllegalArgumentException();
        }
        if (uVar.f) {
            return;
        }
        synchronized (v.class) {
            if (f2187c + PlaybackStateCompat.ACTION_PLAY_FROM_URI > 65536) {
                return;
            }
            f2187c += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            uVar.h = f2186b;
            uVar.e = 0;
            uVar.d = 0;
            f2186b = uVar;
        }
    }
}
