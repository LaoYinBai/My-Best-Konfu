package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.load.DecodeFormat;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b {
    private static final int a = 128;
    private static final File b = new File("/proc/self/fd");
    private static final int c = 50;
    private static final int d = 700;
    private static volatile b g;
    private volatile int e;
    private volatile boolean f = true;

    static b a() {
        if (g == null) {
            synchronized (b.class) {
                if (g == null) {
                    g = new b();
                }
            }
        }
        return g;
    }

    private b() {
    }

    @TargetApi(26)
    boolean a(int i, int i2, BitmapFactory.Options options, DecodeFormat decodeFormat, boolean z, boolean z2) {
        if (!z || Build.VERSION.SDK_INT < 26 || z2) {
            return false;
        }
        boolean z3 = i >= a && i2 >= a && b();
        if (z3) {
            options.inPreferredConfig = Bitmap.Config.HARDWARE;
            options.inMutable = false;
        }
        return z3;
    }

    private synchronized boolean b() {
        int i = this.e + 1;
        this.e = i;
        if (i >= 50) {
            this.e = 0;
            int length = b.list().length;
            this.f = length < d;
            if (!this.f && Log.isLoggable("Downsampler", 5)) {
                Log.w("Downsampler", "Excluding HARDWARE bitmap config because we're over the file descriptor limit, file descriptors " + length + ", limit " + d);
            }
        }
        return this.f;
    }
}
