package com.bytedance.msdk.api;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GDTExtraOption {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4280a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4281b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4282c;
    private boolean d;
    private int e;
    private final int f;
    private final int g;
    private final int h;
    private final int i;
    private final int j;
    private final int k;
    private int l;
    private boolean m;

    public static final class AutoPlayPolicy {
        public static final int ALWAYS = 1;
        public static final int NEVER = 2;
        public static final int WIFI = 0;
    }

    public static final class BrowserType {
        public static final int TYPE_DEFAULT = 0;
        public static final int TYPE_INNER = 1;
        public static final int TYPE_SYS = 2;
    }

    public static final class Builder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4283a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4284b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4285c;
        private boolean d;
        private int e;
        private int f;
        private int g;
        private int h;
        private int i;
        private int j;
        private int k;
        private int l = 1;
        private boolean m;

        public final GDTExtraOption build() {
            return new GDTExtraOption(this);
        }

        public Builder setAutoPlayPolicy(int i) {
            this.g = i;
            return this;
        }

        public Builder setBrowserType(int i) {
            this.h = i;
            return this;
        }

        public Builder setDownAPPConfirmPolicy(int i) {
            this.i = i;
            return this;
        }

        public Builder setFeedExpressType(int i) {
            this.l = i;
            return this;
        }

        public Builder setGDTAutoPlayMuted(boolean z) {
            this.f4284b = z;
            return this;
        }

        public Builder setGDTDetailPageMuted(boolean z) {
            this.f4285c = z;
            return this;
        }

        public Builder setGDTEnableDetailPage(boolean z) {
            this.f4283a = z;
            return this;
        }

        public Builder setGDTEnableUserControl(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setGDTMaxVideoDuration(int i) {
            this.f = i;
            return this;
        }

        public Builder setGDTMinVideoDuration(int i) {
            this.e = i;
            return this;
        }

        public Builder setHeight(int i) {
            this.k = i;
            return this;
        }

        public Builder setSplashPreLoad(boolean z) {
            this.m = z;
            return this;
        }

        public Builder setWidth(int i) {
            this.j = i;
            return this;
        }
    }

    public static final class DownAPPConfirmPolicy {
        public static final int TYPE_DEFAULT = 0;
        public static final int TYPE_NO_CONFIRM = 1;
    }

    public static final class FeedExpressType {
        public static final int FEED_EXPRESS_TYPE_1 = 1;
        public static final int FEED_EXPRESS_TYPE_2 = 2;
    }

    public static final class VideoPlayPolicy {
        public static final int AUTO = 1;
        public static final int MANUAL = 2;
        public static final int UNKNOWN = 0;
    }

    public int getBrowserType() {
        return this.h;
    }

    public int getDownAPPConfirmPolicy() {
        return this.i;
    }

    public int getFeedExpressType() {
        return this.l;
    }

    public int getGDTAutoPlayPolicy() {
        return this.e;
    }

    public int getGDTMaxVideoDuration() {
        return this.g;
    }

    public int getGDTMinVideoDuration() {
        return this.f;
    }

    public int getHeight() {
        return this.k;
    }

    public int getWidth() {
        return this.j;
    }

    public boolean isGDTAutoPlayMuted() {
        return this.f4281b;
    }

    public boolean isGDTDetailPageMuted() {
        return this.f4282c;
    }

    public boolean isGDTEnableDetailPage() {
        return this.f4280a;
    }

    public boolean isGDTEnableUserControl() {
        return this.d;
    }

    public boolean isSplashPreLoad() {
        return this.m;
    }

    private GDTExtraOption(Builder builder) {
        this.f4280a = true;
        this.f4281b = true;
        this.f4282c = false;
        this.d = false;
        this.e = 0;
        this.l = 1;
        this.f4280a = builder.f4283a;
        this.f4281b = builder.f4284b;
        this.f4282c = builder.f4285c;
        this.d = builder.d;
        this.f = builder.e;
        this.g = builder.f;
        this.e = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
        this.l = builder.l;
        this.m = builder.m;
    }
}
