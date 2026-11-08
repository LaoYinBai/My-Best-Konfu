package com.bytedance.msdk.api.v2.slot;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotBaiduOption;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotGDTOption;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdSlotFullVideo extends GMAdSlotBase {
    private String d;
    private int e;

    public static class Builder extends GMAdSlotBase.Builder {
        private String j;
        private int k;

        public GMAdSlotFullVideo build() {
            return new GMAdSlotFullVideo(this);
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

        public Builder setMuted(boolean z) {
            this.f4447a = z;
            return this;
        }

        public Builder setOrientation(int i) {
            this.k = i;
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
            this.j = str;
            return this;
        }

        public Builder setVolume(float f) {
            this.f4448b = f;
            return this;
        }
    }

    public int getOrientation() {
        return this.e;
    }

    public String getUserID() {
        return this.d;
    }

    private GMAdSlotFullVideo(Builder builder) {
        super(builder);
        this.d = builder.j;
        this.e = builder.k;
    }
}
