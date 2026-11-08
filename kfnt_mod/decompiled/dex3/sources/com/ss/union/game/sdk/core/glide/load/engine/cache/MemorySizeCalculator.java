package com.ss.union.game.sdk.core.glide.load.engine.cache;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.feedback.picture.select.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class MemorySizeCalculator {
    static final int a = 4;
    private static final String b = "MemorySizeCalculator";
    private static final int c = 2;
    private final int d;
    private final int e;
    private final Context f;
    private final int g;

    interface b {
        int a();

        int b();
    }

    MemorySizeCalculator(Builder builder) {
        this.f = builder.f;
        this.g = a(builder.g) ? builder.m / 2 : builder.m;
        int iA = a(builder.g, builder.k, builder.l);
        float fA = builder.h.a() * builder.h.b() * 4;
        int iRound = Math.round(builder.j * fA);
        int iRound2 = Math.round(fA * builder.i);
        int i = iA - this.g;
        int i2 = iRound2 + iRound;
        if (i2 <= i) {
            this.e = iRound2;
            this.d = iRound;
        } else {
            float f = i / (builder.j + builder.i);
            this.e = Math.round(builder.i * f);
            this.d = Math.round(f * builder.j);
        }
        if (Log.isLoggable(b, 3)) {
            StringBuilder sb = new StringBuilder();
            sb.append("Calculation complete, Calculated memory cache size: ");
            sb.append(a(this.e));
            sb.append(", pool size: ");
            sb.append(a(this.d));
            sb.append(", byte array size: ");
            sb.append(a(this.g));
            sb.append(", memory class limited? ");
            sb.append(i2 > iA);
            sb.append(", max size: ");
            sb.append(a(iA));
            sb.append(", memoryClass: ");
            sb.append(builder.g.getMemoryClass());
            sb.append(", isLowMemoryDevice: ");
            sb.append(a(builder.g));
            Log.d(b, sb.toString());
        }
    }

    public int getMemoryCacheSize() {
        return this.e;
    }

    public int getBitmapPoolSize() {
        return this.d;
    }

    public int getArrayPoolSizeInBytes() {
        return this.g;
    }

    private static int a(ActivityManager activityManager, float f, float f2) {
        float memoryClass = activityManager.getMemoryClass() * d.d * d.d;
        if (a(activityManager)) {
            f = f2;
        }
        return Math.round(memoryClass * f);
    }

    private String a(int i) {
        return Formatter.formatFileSize(this.f, i);
    }

    @TargetApi(19)
    static boolean a(ActivityManager activityManager) {
        if (Build.VERSION.SDK_INT >= 19) {
            return activityManager.isLowRamDevice();
        }
        return true;
    }

    public static final class Builder {
        static final int a = 2;
        static final int b;
        static final float c = 0.4f;
        static final float d = 0.33f;
        static final int e = 4194304;
        final Context f;
        ActivityManager g;
        b h;
        float j;
        float i = 2.0f;
        float k = c;
        float l = d;
        int m = 4194304;

        static {
            b = Build.VERSION.SDK_INT < 26 ? 4 : 1;
        }

        public Builder(Context context) {
            this.j = b;
            this.f = context;
            this.g = (ActivityManager) context.getSystemService("activity");
            this.h = new a(context.getResources().getDisplayMetrics());
            if (Build.VERSION.SDK_INT < 26 || !MemorySizeCalculator.a(this.g)) {
                return;
            }
            this.j = 0.0f;
        }

        public Builder setMemoryCacheScreens(float f) {
            Preconditions.checkArgument(f >= 0.0f, "Memory cache screens must be greater than or equal to 0");
            this.i = f;
            return this;
        }

        public Builder setBitmapPoolScreens(float f) {
            Preconditions.checkArgument(f >= 0.0f, "Bitmap pool screens must be greater than or equal to 0");
            this.j = f;
            return this;
        }

        public Builder setMaxSizeMultiplier(float f) {
            Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Size multiplier must be between 0 and 1");
            this.k = f;
            return this;
        }

        public Builder setLowMemoryMaxSizeMultiplier(float f) {
            Preconditions.checkArgument(f >= 0.0f && f <= 1.0f, "Low memory max size multiplier must be between 0 and 1");
            this.l = f;
            return this;
        }

        public Builder setArrayPoolSize(int i) {
            this.m = i;
            return this;
        }

        Builder a(ActivityManager activityManager) {
            this.g = activityManager;
            return this;
        }

        Builder a(b bVar) {
            this.h = bVar;
            return this;
        }

        public MemorySizeCalculator build() {
            return new MemorySizeCalculator(this);
        }
    }

    private static final class a implements b {
        private final DisplayMetrics a;

        a(DisplayMetrics displayMetrics) {
            this.a = displayMetrics;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemorySizeCalculator.b
        public int a() {
            return this.a.widthPixels;
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemorySizeCalculator.b
        public int b() {
            return this.a.heightPixels;
        }
    }
}
