package com.bytedance.msdk.api.nativeAd;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder;
import java.util.HashMap;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TTViewBinder extends GMViewBinder {

    public static class Builder extends GMViewBinder.Builder {
        public Builder(int i) {
            super(i);
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public /* bridge */ /* synthetic */ GMViewBinder.Builder addExtras(Map map) {
            return addExtras((Map<String, Integer>) map);
        }

        @Deprecated
        public final Builder decriptionTextId(int i) {
            this.f4436c = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder addExtra(String str, int i) {
            this.m.put(str, Integer.valueOf(i));
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder addExtras(Map<String, Integer> map) {
            this.m = new HashMap(map);
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public TTViewBinder build() {
            return new TTViewBinder(this);
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder callToActionId(int i) {
            this.d = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public Builder descriptionTextId(int i) {
            this.f4436c = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public Builder groupImage1Id(int i) {
            this.j = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder groupImage2Id(int i) {
            this.k = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder groupImage3Id(int i) {
            this.l = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder iconImageId(int i) {
            this.e = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public Builder logoLayoutId(int i) {
            this.i = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder mainImageId(int i) {
            this.f = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder mediaViewIdId(int i) {
            this.g = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public final Builder sourceId(int i) {
            this.h = i;
            return this;
        }

        @Override // com.bytedance.msdk.api.v2.ad.nativeAd.GMViewBinder.Builder
        @NonNull
        public Builder titleId(int i) {
            this.f4435b = i;
            return this;
        }
    }

    private TTViewBinder(@NonNull Builder builder) {
        super(builder);
    }
}
