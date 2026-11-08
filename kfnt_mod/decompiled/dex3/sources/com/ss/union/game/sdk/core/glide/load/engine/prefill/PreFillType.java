package com.ss.union.game.sdk.core.glide.load.engine.prefill;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class PreFillType {
    static final Bitmap.Config a = Bitmap.Config.RGB_565;
    private final int b;
    private final int c;
    private final Bitmap.Config d;
    private final int e;

    PreFillType(int i, int i2, Bitmap.Config config, int i3) {
        this.d = (Bitmap.Config) Preconditions.checkNotNull(config, "Config must not be null");
        this.b = i;
        this.c = i2;
        this.e = i3;
    }

    int a() {
        return this.b;
    }

    int b() {
        return this.c;
    }

    Bitmap.Config c() {
        return this.d;
    }

    int d() {
        return this.e;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PreFillType)) {
            return false;
        }
        PreFillType preFillType = (PreFillType) obj;
        return this.c == preFillType.c && this.b == preFillType.b && this.e == preFillType.e && this.d == preFillType.d;
    }

    public int hashCode() {
        return (((((this.b * 31) + this.c) * 31) + this.d.hashCode()) * 31) + this.e;
    }

    public String toString() {
        return "PreFillSize{width=" + this.b + ", height=" + this.c + ", config=" + this.d + ", weight=" + this.e + '}';
    }

    public static class Builder {
        private final int a;
        private final int b;
        private Bitmap.Config c;
        private int d;

        public Builder(int i) {
            this(i, i);
        }

        public Builder(int i, int i2) {
            this.d = 1;
            if (i <= 0) {
                throw new IllegalArgumentException("Width must be > 0");
            }
            if (i2 <= 0) {
                throw new IllegalArgumentException("Height must be > 0");
            }
            this.a = i;
            this.b = i2;
        }

        public Builder setConfig(Bitmap.Config config) {
            this.c = config;
            return this;
        }

        Bitmap.Config a() {
            return this.c;
        }

        public Builder setWeight(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("Weight must be > 0");
            }
            this.d = i;
            return this;
        }

        PreFillType b() {
            return new PreFillType(this.a, this.b, this.c, this.d);
        }
    }
}
