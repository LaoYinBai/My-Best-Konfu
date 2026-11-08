package com.qq.e.ads.rewardvideo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ServerSideVerificationOptions {
    public static final String TRANS_ID = "transId";
    private String a;
    private String b;

    public static class Builder {
        private String a;
        private String b;

        public ServerSideVerificationOptions build() {
            return new ServerSideVerificationOptions(this);
        }

        public Builder setCustomData(String str) {
            this.a = str;
            return this;
        }

        public Builder setUserId(String str) {
            this.b = str;
            return this;
        }
    }

    private ServerSideVerificationOptions(Builder builder) {
        this.a = builder.a;
        this.b = builder.b;
    }

    public String getCustomData() {
        return this.a;
    }

    public String getUserId() {
        return this.b;
    }
}
