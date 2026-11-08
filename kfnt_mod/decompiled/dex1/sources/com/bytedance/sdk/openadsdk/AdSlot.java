package com.bytedance.sdk.openadsdk;

import com.bytedance.sdk.openadsdk.api.b.d;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AdSlot implements TTAdSlot {
    private TTAdLoadType A;
    private int B;
    private int C;

    /* renamed from: a, reason: collision with root package name */
    private String f5160a;

    /* renamed from: b, reason: collision with root package name */
    private int f5161b;

    /* renamed from: c, reason: collision with root package name */
    private int f5162c;
    private float d;
    private float e;
    private int f;
    private boolean g;
    private boolean h;
    private String i;
    private int j;
    private String k;
    private String l;
    private int m;
    private int n;
    private int o;
    private int p;
    private boolean q;
    private int[] r;
    private String s;
    private int t;
    private String u;
    private String v;
    private String w;
    private String x;
    private String y;
    private String z;

    private AdSlot() {
        this.m = 2;
        this.q = true;
        this.B = 1;
        this.C = 0;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getAdId() {
        return this.w;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getCreativeId() {
        return this.x;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getExt() {
        return this.y;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getCodeId() {
        return this.f5160a;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public boolean isAutoPlay() {
        return this.q;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getImgAcceptedWidth() {
        return this.f5161b;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getImgAcceptedHeight() {
        return this.f5162c;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public float getExpressViewAcceptedWidth() {
        return this.d;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public float getExpressViewAcceptedHeight() {
        return this.e;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public boolean isSupportDeepLink() {
        return this.g;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public boolean isSupportRenderConrol() {
        return this.h;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getAdCount() {
        return this.f;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public void setAdCount(int i) {
        this.f = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getRewardName() {
        return this.i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getRewardAmount() {
        return this.j;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getMediaExtra() {
        return this.k;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getUserID() {
        return this.l;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getOrientation() {
        return this.m;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getNativeAdType() {
        return this.o;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public void setNativeAdType(int i) {
        this.o = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public void setDurationSlotType(int i) {
        this.p = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getDurationSlotType() {
        return this.p;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int[] getExternalABVid() {
        return this.r;
    }

    public void setExternalABVid(int... iArr) {
        this.r = iArr;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getAdloadSeq() {
        return this.t;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getPrimeRit() {
        return this.u == null ? "" : this.u;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getAdType() {
        return this.n;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getBidAdm() {
        return this.v;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getExtraSmartLookParam() {
        return this.s;
    }

    public void setUserData(String str) {
        this.z = str;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public String getUserData() {
        return this.z;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public TTAdLoadType getAdLoadType() {
        return this.A;
    }

    public void setAdLoadType(TTAdLoadType tTAdLoadType) {
        this.A = tTAdLoadType;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getSplashButtonType() {
        return this.B;
    }

    public void setSplashButtonType(int i) {
        this.B = i;
    }

    @Override // com.bytedance.sdk.openadsdk.TTAdSlot
    public int getDownloadType() {
        return this.C;
    }

    public void setDownloadType(int i) {
        this.C = i;
    }

    public String toString() {
        return "AdSlot{mCodeId='" + this.f5160a + "', mImgAcceptedWidth=" + this.f5161b + ", mImgAcceptedHeight=" + this.f5162c + ", mExpressViewAcceptedWidth=" + this.d + ", mExpressViewAcceptedHeight=" + this.e + ", mAdCount=" + this.f + ", mSupportDeepLink=" + this.g + ", mSupportRenderControl=" + this.h + ", mRewardName='" + this.i + "', mRewardAmount=" + this.j + ", mMediaExtra='" + this.k + "', mUserID='" + this.l + "', mOrientation=" + this.m + ", mNativeAdType=" + this.o + ", mIsAutoPlay=" + this.q + ", mPrimeRit" + this.u + ", mAdloadSeq" + this.t + ", mAdId" + this.w + ", mCreativeId" + this.x + ", mExt" + this.y + ", mUserData" + this.z + ", mAdLoadType" + this.A + ", mSplashButtonType=" + this.B + ", mDownloadType=" + this.C + '}';
    }

    public JSONObject toJsonObj() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("mCodeId", this.f5160a);
            jSONObject.put("mIsAutoPlay", this.q);
            jSONObject.put("mImgAcceptedWidth", this.f5161b);
            jSONObject.put("mImgAcceptedHeight", this.f5162c);
            jSONObject.put("mExpressViewAcceptedWidth", this.d);
            jSONObject.put("mExpressViewAcceptedHeight", this.e);
            jSONObject.put("mAdCount", this.f);
            jSONObject.put("mSupportDeepLink", this.g);
            jSONObject.put("mSupportRenderControl", this.h);
            jSONObject.put("mRewardName", this.i);
            jSONObject.put("mRewardAmount", this.j);
            jSONObject.put("mMediaExtra", this.k);
            jSONObject.put("mUserID", this.l);
            jSONObject.put("mOrientation", this.m);
            jSONObject.put("mNativeAdType", this.o);
            jSONObject.put("mAdloadSeq", this.t);
            jSONObject.put("mPrimeRit", this.u);
            jSONObject.put("mExtraSmartLookParam", this.s);
            jSONObject.put("mAdId", this.w);
            jSONObject.put("mCreativeId", this.x);
            jSONObject.put("mExt", this.y);
            jSONObject.put("mBidAdm", this.v);
            jSONObject.put("mUserData", this.z);
            jSONObject.put("mAdLoadType", this.A);
            jSONObject.put("mSplashButtonType", this.B);
            jSONObject.put("mDownloadType", this.C);
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    public static class Builder {
        private String A;
        private String B;

        /* renamed from: a, reason: collision with root package name */
        private String f5163a;
        private String i;
        private int l;
        private String m;
        private int n;
        private float o;
        private float p;
        private int[] r;
        private int s;
        private String t;
        private String u;
        private String v;
        private String z;

        /* renamed from: b, reason: collision with root package name */
        private int f5164b = 640;

        /* renamed from: c, reason: collision with root package name */
        private int f5165c = 320;
        private boolean d = true;
        private boolean e = false;
        private int f = 1;
        private String g = "";
        private int h = 0;
        private String j = "defaultUser";
        private int k = 2;
        private boolean q = true;
        private int w = 1;
        private int x = 0;
        private TTAdLoadType y = TTAdLoadType.UNKNOWN;

        public Builder setExtraParam(String str) {
            this.m = str;
            return this;
        }

        public Builder setAdType(int i) {
            this.n = i;
            return this;
        }

        public Builder setAdId(String str) {
            this.z = str;
            return this;
        }

        public Builder setCreativeId(String str) {
            this.A = str;
            return this;
        }

        public Builder setExt(String str) {
            this.B = str;
            return this;
        }

        public Builder setIsAutoPlay(boolean z) {
            this.q = z;
            return this;
        }

        public Builder setCodeId(String str) {
            this.f5163a = str;
            return this;
        }

        public Builder setImageAcceptedSize(int i, int i2) {
            this.f5164b = i;
            this.f5165c = i2;
            return this;
        }

        public Builder setExpressViewAcceptedSize(float f, float f2) {
            this.o = f;
            this.p = f2;
            return this;
        }

        public Builder setSupportDeepLink(boolean z) {
            this.d = z;
            return this;
        }

        public Builder supportRenderControl() {
            this.e = true;
            return this;
        }

        public Builder setAdCount(int i) {
            if (i <= 0) {
                i = 1;
                d.b("TT_AD_SDK", "setAdCount: adCount must greater than 0 ");
            }
            if (i > 20) {
                d.b("TT_AD_SDK", "setAdCount: adCount must less than or equal to 20 ");
                i = 20;
            }
            this.f = i;
            return this;
        }

        public Builder setRewardName(String str) {
            this.g = str;
            return this;
        }

        public Builder setRewardAmount(int i) {
            this.h = i;
            return this;
        }

        public Builder setMediaExtra(String str) {
            this.i = str;
            return this;
        }

        public Builder setUserID(String str) {
            this.j = str;
            return this;
        }

        public Builder setOrientation(int i) {
            this.k = i;
            return this;
        }

        public Builder setNativeAdType(int i) {
            this.l = i;
            return this;
        }

        public Builder setAdloadSeq(int i) {
            this.s = i;
            return this;
        }

        public Builder setPrimeRit(String str) {
            this.t = str;
            return this;
        }

        public Builder setExternalABVid(int... iArr) {
            this.r = iArr;
            return this;
        }

        public Builder setUserData(String str) {
            this.v = str;
            return this;
        }

        public Builder setAdLoadType(TTAdLoadType tTAdLoadType) {
            this.y = tTAdLoadType;
            return this;
        }

        public Builder withBid(String str) {
            if (str == null) {
                return this;
            }
            this.u = str;
            return this;
        }

        public Builder setSplashButtonType(int i) {
            if (i != 2) {
                i = 1;
            }
            this.w = i;
            return this;
        }

        public Builder setDownloadType(int i) {
            if (i != 1) {
                i = 0;
            }
            this.x = i;
            return this;
        }

        public AdSlot build() {
            AdSlot adSlot = new AdSlot();
            adSlot.f5160a = this.f5163a;
            adSlot.f = this.f;
            adSlot.g = this.d;
            adSlot.h = this.e;
            adSlot.f5161b = this.f5164b;
            adSlot.f5162c = this.f5165c;
            if (this.o <= 0.0f) {
                adSlot.d = this.f5164b;
                adSlot.e = this.f5165c;
            } else {
                adSlot.d = this.o;
                adSlot.e = this.p;
            }
            adSlot.i = this.g;
            adSlot.j = this.h;
            adSlot.k = this.i;
            adSlot.l = this.j;
            adSlot.m = this.k;
            adSlot.o = this.l;
            adSlot.q = this.q;
            adSlot.r = this.r;
            adSlot.t = this.s;
            adSlot.u = this.t;
            adSlot.s = this.m;
            adSlot.w = this.z;
            adSlot.x = this.A;
            adSlot.y = this.B;
            adSlot.n = this.n;
            adSlot.v = this.u;
            adSlot.z = this.v;
            adSlot.A = this.y;
            adSlot.B = this.w;
            adSlot.C = this.x;
            return adSlot;
        }
    }
}
