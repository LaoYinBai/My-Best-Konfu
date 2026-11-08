package com.qq.e.ads.nativ.express2;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class VideoOption2 {
    private AutoPlayPolicy a;
    private boolean b;
    private boolean c;
    private int d;
    private int e;

    public enum AutoPlayPolicy {
        WIFI(0),
        ALWAYS(1),
        NEVER(2);

        int a;

        AutoPlayPolicy(int i) {
            this.a = i;
        }

        public int getPolicy() {
            return this.a;
        }
    }

    public static class Builder {
        AutoPlayPolicy a = AutoPlayPolicy.WIFI;
        boolean b = true;
        boolean c = false;
        int d;
        int e;

        public VideoOption2 build() {
            return new VideoOption2(this);
        }

        public Builder setAutoPlayMuted(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setAutoPlayPolicy(AutoPlayPolicy autoPlayPolicy) {
            if (autoPlayPolicy != null) {
                this.a = autoPlayPolicy;
            }
            return this;
        }

        public Builder setDetailPageMuted(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setMaxVideoDuration(int i) {
            this.d = i;
            return this;
        }

        public Builder setMinVideoDuration(int i) {
            this.e = i;
            return this;
        }
    }

    private VideoOption2(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
    }

    public AutoPlayPolicy getAutoPlayPolicy() {
        return this.a;
    }

    public int getMaxVideoDuration() {
        return this.d;
    }

    public int getMinVideoDuration() {
        return this.e;
    }

    public boolean isAutoPlayMuted() {
        return this.b;
    }

    public boolean isDetailPageMuted() {
        return this.c;
    }
}
