package com.bytedance.msdk.api.v2.slot.paltform;

import android.widget.FrameLayout;
import com.bytedance.msdk.api.GDTExtraOption;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdSlotGDTOption {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4456a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f4457b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4458c;
    private boolean d;
    private int e;
    private final int f;
    private final int g;
    private final int h;
    private FrameLayout.LayoutParams i;

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
        private boolean f4459a;

        /* renamed from: b, reason: collision with root package name */
        private boolean f4460b;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4461c;
        private boolean d;
        private int e;
        private int f;
        private int g;
        private int h;
        private FrameLayout.LayoutParams i;

        public final GMAdSlotGDTOption build() {
            return new GMAdSlotGDTOption(this);
        }

        public Builder setAutoPlayPolicy(int i) {
            this.g = i;
            return this;
        }

        public Builder setDownAPPConfirmPolicy(int i) {
            this.h = i;
            return this;
        }

        public Builder setGDTAutoPlayMuted(boolean z) {
            this.f4460b = z;
            return this;
        }

        public Builder setGDTDetailPageMuted(boolean z) {
            this.f4461c = z;
            return this;
        }

        public Builder setGDTEnableDetailPage(boolean z) {
            this.f4459a = z;
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

        public Builder setNativeAdLogoParams(FrameLayout.LayoutParams layoutParams) {
            this.i = layoutParams;
            return this;
        }
    }

    public static final class DownAPPConfirmPolicy {
        public static final int TYPE_DEFAULT = 0;
        public static final int TYPE_NO_CONFIRM = 1;
    }

    public static final class VideoPlayPolicy {
        public static final int AUTO = 1;
        public static final int MANUAL = 2;
        public static final int UNKNOWN = 0;
    }

    public int getDownAPPConfirmPolicy() {
        return this.h;
    }

    public int getGDTAutoPlayPolicy() {
        return this.e;
    }

    public GDTExtraOption getGDTExtraOption(boolean z) {
        GDTExtraOption.Builder builder = new GDTExtraOption.Builder();
        builder.setAutoPlayPolicy(getGDTAutoPlayPolicy());
        builder.setDownAPPConfirmPolicy(getDownAPPConfirmPolicy());
        builder.setGDTAutoPlayMuted(isGDTAutoPlayMuted());
        builder.setGDTDetailPageMuted(isGDTDetailPageMuted());
        builder.setGDTEnableDetailPage(isGDTEnableDetailPage());
        builder.setGDTEnableUserControl(isGDTEnableUserControl());
        builder.setGDTMaxVideoDuration(getGDTMaxVideoDuration());
        builder.setGDTMinVideoDuration(getGDTMinVideoDuration());
        builder.setSplashPreLoad(z);
        return builder.build();
    }

    public int getGDTMaxVideoDuration() {
        return this.g;
    }

    public int getGDTMinVideoDuration() {
        return this.f;
    }

    public FrameLayout.LayoutParams getNativeAdLogoParams() {
        return this.i;
    }

    public boolean isGDTAutoPlayMuted() {
        return this.f4457b;
    }

    public boolean isGDTDetailPageMuted() {
        return this.f4458c;
    }

    public boolean isGDTEnableDetailPage() {
        return this.f4456a;
    }

    public boolean isGDTEnableUserControl() {
        return this.d;
    }

    private GMAdSlotGDTOption(Builder builder) {
        this.f4456a = true;
        this.f4457b = true;
        this.f4458c = false;
        this.d = false;
        this.e = 0;
        this.f4456a = builder.f4459a;
        this.f4457b = builder.f4460b;
        this.f4458c = builder.f4461c;
        this.d = builder.d;
        this.f = builder.e;
        this.g = builder.f;
        this.e = builder.g;
        this.h = builder.h;
        this.i = builder.i;
    }
}
