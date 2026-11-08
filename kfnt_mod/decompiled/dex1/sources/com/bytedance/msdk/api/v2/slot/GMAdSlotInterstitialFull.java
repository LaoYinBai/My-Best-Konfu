package com.bytedance.msdk.api.v2.slot;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.v2.slot.GMAdSlotBase;
import com.bytedance.msdk.api.v2.slot.GMAdSlotFullVideo;
import com.bytedance.msdk.api.v2.slot.GMAdSlotInterstitial;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotBaiduOption;
import com.bytedance.msdk.api.v2.slot.paltform.GMAdSlotGDTOption;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdSlotInterstitialFull extends GMAdSlotBase {
    private int d;
    private int e;
    private String f;
    private int g;

    public static class Builder extends GMAdSlotBase.Builder {
        private int j = 640;
        private int k = 320;
        private String l;
        private int m;

        public GMAdSlotInterstitialFull build() {
            return new GMAdSlotInterstitialFull(this);
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

        public Builder setOrientation(int i) {
            this.m = i;
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
            this.l = str;
            return this;
        }

        public Builder setVolume(float f) {
            this.f4448b = f;
            return this;
        }
    }

    public GMAdSlotFullVideo getGMAdSlotFullVideo() {
        GMAdSlotFullVideo gMAdSlotFullVideoBuild = new GMAdSlotFullVideo.Builder().setUserID(this.f).setOrientation(this.g).setMuted(isMuted()).setVolume(getVolume()).setUseSurfaceView(isUseSurfaceView()).setGMAdSlotGDTOption(this.f4444a).setGMAdSlotBaiduOption(this.f4445b).setTestSlotId(getTestSlotId()).setDownloadType(getDownloadType()).setBidNotify(isBidNotify()).build();
        if (gMAdSlotFullVideoBuild.getParams() != null && getParams() != null) {
            gMAdSlotFullVideoBuild.getParams().putAll(getParams());
        }
        return gMAdSlotFullVideoBuild;
    }

    public GMAdSlotInterstitial getGMAdSlotInterstitial() {
        GMAdSlotInterstitial gMAdSlotInterstitialBuild = new GMAdSlotInterstitial.Builder().setImageAdSize(getWidth(), getHeight()).setMuted(isMuted()).setVolume(getVolume()).setUseSurfaceView(isUseSurfaceView()).setGMAdSlotGDTOption(this.f4444a).setGMAdSlotBaiduOption(this.f4445b).setTestSlotId(getTestSlotId()).setDownloadType(getDownloadType()).setBidNotify(isBidNotify()).build();
        if (gMAdSlotInterstitialBuild.getParams() != null && getParams() != null) {
            gMAdSlotInterstitialBuild.getParams().putAll(getParams());
        }
        return gMAdSlotInterstitialBuild;
    }

    public int getHeight() {
        return this.e;
    }

    public int getOrientation() {
        return this.g;
    }

    public String getUserID() {
        return this.f;
    }

    public int getWidth() {
        return this.d;
    }

    private GMAdSlotInterstitialFull(Builder builder) {
        super(builder);
        this.d = builder.j;
        this.e = builder.k;
        this.f = builder.l;
        this.g = builder.m;
    }
}
