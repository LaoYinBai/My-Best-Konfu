package com.bytedance.msdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GMAdEcpmInfo {

    /* renamed from: a, reason: collision with root package name */
    private int f4286a;

    /* renamed from: b, reason: collision with root package name */
    private String f4287b;

    /* renamed from: c, reason: collision with root package name */
    private String f4288c;
    private String d;
    private int e;
    private String f;

    public int getAdNetworkPlatformId() {
        return this.f4286a;
    }

    public String getAdNetworkRitId() {
        return this.f4287b;
    }

    public String getErrorMsg() {
        return this.f;
    }

    public String getLevelTag() {
        return this.f4288c;
    }

    public String getPreEcpm() {
        return this.d;
    }

    public int getReqBiddingType() {
        return this.e;
    }

    public void setAdNetworkPlatformId(int i) {
        this.f4286a = i;
    }

    public void setAdNetworkRitId(String str) {
        this.f4287b = str;
    }

    public void setErrorMsg(String str) {
        this.f = str;
    }

    public void setLevelTag(String str) {
        this.f4288c = str;
    }

    public void setPreEcpm(String str) {
        this.d = str;
    }

    public void setReqBiddingType(int i) {
        this.e = i;
    }

    public String toString() {
        return "{mSdkNum='" + this.f4286a + "', mSlotId='" + this.f4287b + "', mLevelTag='" + this.f4288c + "', mEcpm=" + this.d + ", mReqBiddingType=" + this.e + '}';
    }
}
