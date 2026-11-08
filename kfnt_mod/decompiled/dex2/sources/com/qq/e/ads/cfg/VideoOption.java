package com.qq.e.ads.cfg;

import com.qq.e.comm.util.GDTLogger;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class VideoOption {
    private final boolean a;
    private final int b;
    private final boolean c;
    private final boolean d;
    private final boolean e;
    private final boolean f;
    private final boolean g;

    public static final class AutoPlayPolicy {
        public static final int ALWAYS = 1;
        public static final int NEVER = 2;
        public static final int WIFI = 0;
    }

    public static final class Builder {
        private boolean a = true;
        private int b = 1;
        private boolean c = true;
        private boolean d = true;
        private boolean e = true;
        private boolean f = false;
        private boolean g = false;

        public VideoOption build() {
            return new VideoOption(this);
        }

        public Builder setAutoPlayMuted(boolean z) {
            this.a = z;
            return this;
        }

        public Builder setAutoPlayPolicy(int i) {
            if (i < 0 || i > 2) {
                i = 1;
                GDTLogger.e("setAutoPlayPolicy 设置失败，值只能为0到2之间的数值, 重置为 : 1");
            }
            this.b = i;
            return this;
        }

        public Builder setDetailPageMuted(boolean z) {
            this.g = z;
            return this;
        }

        public Builder setEnableDetailPage(boolean z) {
            this.e = z;
            return this;
        }

        public Builder setEnableUserControl(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setNeedCoverImage(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setNeedProgressBar(boolean z) {
            this.c = z;
            return this;
        }
    }

    @Deprecated
    public static final class VideoADContainerRender {
        public static final int DEV = 2;
        public static final int SDK = 1;
        public static final int UNKNOWN = 0;
    }

    @Deprecated
    public static final class VideoPlayPolicy {
        public static final int AUTO = 1;
        public static final int MANUAL = 2;
        public static final int UNKNOWN = 0;
    }

    private VideoOption(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
    }

    public boolean getAutoPlayMuted() {
        return this.a;
    }

    public int getAutoPlayPolicy() {
        return this.b;
    }

    public JSONObject getOptions() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.putOpt("autoPlayMuted", Boolean.valueOf(this.a));
            jSONObject.putOpt("autoPlayPolicy", Integer.valueOf(this.b));
            jSONObject.putOpt("detailPageMuted", Boolean.valueOf(this.g));
        } catch (Exception e) {
            GDTLogger.d("Get video options error: " + e.getMessage());
        }
        return jSONObject;
    }

    public boolean isDetailPageMuted() {
        return this.g;
    }

    public boolean isEnableDetailPage() {
        return this.e;
    }

    public boolean isEnableUserControl() {
        return this.f;
    }

    public boolean isNeedCoverImage() {
        return this.d;
    }

    public boolean isNeedProgressBar() {
        return this.c;
    }
}
