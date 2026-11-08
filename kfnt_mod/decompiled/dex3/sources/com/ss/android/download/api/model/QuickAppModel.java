package com.ss.android.download.api.model;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class QuickAppModel {
    private String mExtraData;
    private String mOpenUrl;

    public QuickAppModel(Builder builder) {
        this.mOpenUrl = builder.mOpenUrl;
        this.mExtraData = builder.mExtraData;
    }

    public String getExtData() {
        return this.mExtraData;
    }

    public String getQuickOpenUrl() {
        return this.mOpenUrl;
    }

    public static class Builder {
        private String mExtraData;
        private String mOpenUrl;

        public Builder setOpenUrl(String str) {
            this.mOpenUrl = str;
            return this;
        }

        public Builder setExtraData(String str) {
            this.mExtraData = str;
            return this;
        }

        public QuickAppModel build() {
            return new QuickAppModel(this);
        }
    }
}
