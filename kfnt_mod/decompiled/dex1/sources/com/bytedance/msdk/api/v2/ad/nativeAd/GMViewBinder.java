package com.bytedance.msdk.api.v2.ad.nativeAd;

import androidx.annotation.NonNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMViewBinder {
    public final int callToActionId;
    public final int decriptionTextId;

    @NonNull
    public final Map<String, Integer> extras;
    public final int groupImage1Id;
    public final int groupImage2Id;
    public final int groupImage3Id;
    public final int iconImageId;
    public final int layoutId;
    public final int logoLayoutId;
    public final int mainImageId;
    public final int mediaViewId;
    public final int sourceId;
    public final int titleId;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        protected int f4434a;

        /* renamed from: b, reason: collision with root package name */
        protected int f4435b;

        /* renamed from: c, reason: collision with root package name */
        protected int f4436c;
        protected int d;
        protected int e;
        protected int f;
        protected int g;
        protected int h;
        protected int i;
        protected int j;
        protected int k;
        protected int l;

        @NonNull
        protected Map<String, Integer> m;

        public Builder(int i) {
            this.m = Collections.emptyMap();
            this.f4434a = i;
            this.m = new HashMap();
        }

        @NonNull
        public Builder addExtra(String str, int i) {
            this.m.put(str, Integer.valueOf(i));
            return this;
        }

        @NonNull
        public Builder addExtras(Map<String, Integer> map) {
            this.m = new HashMap(map);
            return this;
        }

        @NonNull
        public GMViewBinder build() {
            return new GMViewBinder(this);
        }

        @NonNull
        public Builder callToActionId(int i) {
            this.d = i;
            return this;
        }

        @NonNull
        public Builder descriptionTextId(int i) {
            this.f4436c = i;
            return this;
        }

        @NonNull
        public Builder groupImage1Id(int i) {
            this.j = i;
            return this;
        }

        @NonNull
        public Builder groupImage2Id(int i) {
            this.k = i;
            return this;
        }

        @NonNull
        public Builder groupImage3Id(int i) {
            this.l = i;
            return this;
        }

        @NonNull
        public Builder iconImageId(int i) {
            this.e = i;
            return this;
        }

        @NonNull
        public Builder logoLayoutId(int i) {
            this.i = i;
            return this;
        }

        @NonNull
        public Builder mainImageId(int i) {
            this.f = i;
            return this;
        }

        @NonNull
        public Builder mediaViewIdId(int i) {
            this.g = i;
            return this;
        }

        @NonNull
        public Builder sourceId(int i) {
            this.h = i;
            return this;
        }

        @NonNull
        public Builder titleId(int i) {
            this.f4435b = i;
            return this;
        }
    }

    protected GMViewBinder(@NonNull Builder builder) {
        this.layoutId = builder.f4434a;
        this.titleId = builder.f4435b;
        this.decriptionTextId = builder.f4436c;
        this.callToActionId = builder.d;
        this.iconImageId = builder.e;
        this.mainImageId = builder.f;
        this.mediaViewId = builder.g;
        this.sourceId = builder.h;
        this.extras = builder.m;
        this.groupImage1Id = builder.j;
        this.groupImage2Id = builder.k;
        this.groupImage3Id = builder.l;
        this.logoLayoutId = builder.i;
    }
}
