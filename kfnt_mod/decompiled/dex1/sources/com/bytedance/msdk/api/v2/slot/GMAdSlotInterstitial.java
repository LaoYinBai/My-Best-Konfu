package com.bytedance.msdk.api.v2.slot;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotBaiduOption;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotGDTOption;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdSlotInterstitial extends GMAdSlotBase {
    private int d;
    private int e;

    public static class Builder extends GMAdSlotBase.Builder {
        private int j = 640;
        private int k = 320;

        public GMAdSlotInterstitial build() {
            return new GMAdSlotInterstitial(this);
        }

        public Builder setBidNotify(boolean z) {
            this.i = z;
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

        public Builder setImageAdSize(int i, int i2) {
            this.j = i;
            this.k = i2;
            return this;
        }

        public Builder setMuted(boolean z) {
            this.f4447a = z;
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

        public Builder setVolume(float f) {
            this.f4448b = f;
            return this;
        }
    }

    public int getHeight() {
        return this.e;
    }

    public int getWidth() {
        return this.d;
    }

    private GMAdSlotInterstitial(Builder builder) {
        super(builder);
        this.d = builder.j;
        this.e = builder.k;
    }
}
