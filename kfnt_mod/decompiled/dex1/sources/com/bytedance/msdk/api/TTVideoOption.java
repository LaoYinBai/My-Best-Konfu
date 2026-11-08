package com.bytedance.msdk.api;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTVideoOption {

    /* renamed from: a, reason: collision with root package name */
    private final boolean f4300a;

    /* renamed from: b, reason: collision with root package name */
    private final boolean f4301b;

    /* renamed from: c, reason: collision with root package name */
    private float f4302c;
    private GDTExtraOption d;
    private BaiduExtraOptions e;

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        @Deprecated
        private boolean f4303a = true;

        /* renamed from: b, reason: collision with root package name */
        @Deprecated
        private float f4304b;

        /* renamed from: c, reason: collision with root package name */
        @Deprecated
        private GDTExtraOption f4305c;

        @Deprecated
        private boolean d;

        @Deprecated
        private BaiduExtraOptions e;

        public final TTVideoOption build() {
            return new TTVideoOption(this);
        }

        @Deprecated
        public Builder setAdmobAppVolume(float f) {
            if (f > 1.0f) {
                f = 1.0f;
            } else if (f < 0.0f) {
                f = 0.0f;
            }
            this.f4304b = f;
            return this;
        }

        @Deprecated
        public Builder setBaiduExtraOption(BaiduExtraOptions baiduExtraOptions) {
            this.e = baiduExtraOptions;
            return this;
        }

        @Deprecated
        public Builder setGDTExtraOption(GDTExtraOption gDTExtraOption) {
            this.f4305c = gDTExtraOption;
            return this;
        }

        @Deprecated
        public final Builder setMuted(boolean z) {
            this.f4303a = z;
            return this;
        }

        @Deprecated
        public final Builder useSurfaceView(boolean z) {
            this.d = z;
            return this;
        }
    }

    public float getAdmobAppVolume() {
        return this.f4302c;
    }

    public BaiduExtraOptions getBaiduExtraOption() {
        return this.e;
    }

    public GDTExtraOption getGDTExtraOption() {
        return this.d;
    }

    public boolean isMuted() {
        return this.f4300a;
    }

    public boolean useSurfaceView() {
        return this.f4301b;
    }

    private TTVideoOption(Builder builder) {
        this.f4300a = builder.f4303a;
        this.f4302c = builder.f4304b;
        this.d = builder.f4305c;
        this.f4301b = builder.d;
        this.e = builder.e;
    }
}
