package com.bytedance.sdk.account.mobile.query;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MergeCheckObj extends MobileQueryObj {
    public String mCaptcha;
    public int mIsLoginPrimary;
    public String mToken;

    public MergeCheckObj(String str, int i, String str2) {
        this.mToken = str;
        this.mIsLoginPrimary = i;
        this.mCaptcha = str2;
    }
}
