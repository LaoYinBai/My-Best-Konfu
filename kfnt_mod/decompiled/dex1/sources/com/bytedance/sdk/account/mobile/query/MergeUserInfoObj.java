package com.bytedance.sdk.account.mobile.query;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MergeUserInfoObj extends MobileQueryObj {
    public String mAvatarUrl;
    public String mCaptcha;
    public String mMobile;
    public String mScreenName;
    public String mToken;

    public MergeUserInfoObj(String str, String str2) {
        this.mToken = str;
        this.mCaptcha = str2;
    }
}
