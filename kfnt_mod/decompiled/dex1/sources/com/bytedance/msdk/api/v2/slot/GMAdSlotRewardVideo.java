package com.bytedance.msdk.api.v2.slot;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotBaiduOption;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotGDTOption;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdSlotRewardVideo extends GMAdSlotBase {
    private String d;
    private int e;
    private Map<String, String> f;
    private String g;
    private int h;

    public static class Builder extends GMAdSlotBase.Builder {
        private Map<String, String> l;
        private int n;
        private String j = "";
        private int k = 0;
        private String m = "";

        public GMAdSlotRewardVideo build() {
            return new GMAdSlotRewardVideo(this);
        }

        public Builder setBidNotify(boolean z) {
            this.i = z;
            return this;
        }

        public Builder setCustomData(@NonNull Map<String, String> map) {
            this.l = map;
            return this;
        }

        public Builder setDownloadType(int i) {
            this.h = i;
            return this;
        }

        public Builder setExtraObject(String str, Object obj) {
            Map<String, Object> map = this.f;
            if (map != null) {
                map.put(str, obj);
            }
            return this;
        }

        public Builder setGMAdSlotBaiduOption(@NonNull GMAdSlotBaiduOption gMAdSlotBaiduOption) {
            this.e = gMAdSlotBaiduOption;
            return this;
        }

        public Builder setGMAdSlotGDTOption(@NonNull GMAdSlotGDTOption gMAdSlotGDTOption) {
            this.d = gMAdSlotGDTOption;
            return this;
        }

        public Builder setMuted(boolean z) {
            this.f4447a = z;
            return this;
        }

        public Builder setOrientation(int i) {
            this.n = i;
            return this;
        }

        public Builder setRewardAmount(int i) {
            this.k = i;
            return this;
        }

        public Builder setRewardName(@NonNull String str) {
            this.j = str;
            return this;
        }

        @Deprecated
        public Builder setTestSlotId(String str) {
            this.g = str;
            return this;
        }

        public Builder setUseSurfaceView(boolean z) {
            this.f4449c = z;
            return this;
        }

        public Builder setUserID(String str) {
            this.m = str;
            return this;
        }

        public Builder setVolume(float f) {
            this.f4448b = f;
            return this;
        }
    }

    @Nullable
    public Map<String, String> getCustomData() {
        return this.f;
    }

    public int getOrientation() {
        return this.h;
    }

    public int getRewardAmount() {
        return this.e;
    }

    public String getRewardName() {
        return this.d;
    }

    public String getUserID() {
        return this.g;
    }

    private GMAdSlotRewardVideo(Builder builder) {
        super(builder);
        this.d = builder.j;
        this.e = builder.k;
        this.f = builder.l;
        this.g = builder.m;
        this.h = builder.n;
    }
}
