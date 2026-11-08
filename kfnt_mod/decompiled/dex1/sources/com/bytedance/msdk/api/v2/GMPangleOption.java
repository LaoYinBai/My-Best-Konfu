package com.bytedance.msdk.api.v2;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMPangleOption {

    /* renamed from: a, reason: collision with root package name */
    private boolean f4350a;

    /* renamed from: b, reason: collision with root package name */
    private int f4351b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f4352c;
    private boolean d;
    private int[] e;
    private boolean f;
    private String[] g;
    private String h;
    private Map<String, String> i;
    private String j;
    private int k;

    public static class Builder {

        /* renamed from: a, reason: collision with root package name */
        private boolean f4353a = false;

        /* renamed from: b, reason: collision with root package name */
        private int f4354b = 0;

        /* renamed from: c, reason: collision with root package name */
        private boolean f4355c = true;
        private boolean d = false;
        private int[] e = {4, 3, 5};
        private boolean f = false;
        private String[] g = new String[0];
        private String h = "";
        private final Map<String, String> i = new HashMap();
        private String j = "";
        private int k = 2;

        public GMPangleOption build() {
            return new GMPangleOption(this);
        }

        public Builder setAllowShowNotify(boolean z) {
            this.f4355c = z;
            return this;
        }

        public Builder setAllowShowPageWhenScreenLock(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setData(@NonNull String str) {
            this.h = str;
            return this;
        }

        public Builder setDirectDownloadNetworkType(@NonNull int... iArr) {
            this.e = iArr;
            return this;
        }

        public Builder setIsPaid(boolean z) {
            this.f4353a = z;
            return this;
        }

        public Builder setIsUseTextureView(boolean z) {
            this.f = z;
            return this;
        }

        public Builder setKeywords(@NonNull String str) {
            this.j = str;
            return this;
        }

        public Builder setNeedClearTaskReset(@NonNull String... strArr) {
            this.g = strArr;
            return this;
        }

        public Builder setTitleBarTheme(int i) {
            this.f4354b = i;
            return this;
        }

        public Builder setData(@NonNull String str, @NonNull String str2) {
            this.i.put(str, str2);
            return this;
        }

        public Builder setData(@NonNull Map<String, String> map) {
            this.i.putAll(map);
            return this;
        }
    }

    public String getData() {
        return this.h;
    }

    public int[] getDirectDownloadNetworkType() {
        return this.e;
    }

    @NonNull
    public Map<String, String> getExtraData() {
        return this.i;
    }

    public String getKeywords() {
        return this.j;
    }

    public String[] getNeedClearTaskReset() {
        return this.g;
    }

    public int getPluginUpdateConfig() {
        return this.k;
    }

    public int getTitleBarTheme() {
        return this.f4351b;
    }

    public boolean isAllowShowNotify() {
        return this.f4352c;
    }

    public boolean isAllowShowPageWhenScreenLock() {
        return this.d;
    }

    public boolean isIsUseTextureView() {
        return this.f;
    }

    public boolean isPaid() {
        return this.f4350a;
    }

    private GMPangleOption(Builder builder) {
        this.f4350a = builder.f4353a;
        this.f4351b = builder.f4354b;
        this.f4352c = builder.f4355c;
        this.d = builder.d;
        this.e = builder.e;
        this.f = builder.f;
        this.g = builder.g;
        this.h = builder.h;
        this.i = builder.i;
        this.j = builder.j;
        this.k = builder.k;
    }
}
