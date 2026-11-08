package com.bytedance.sdk.account.mobile.query;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MobilePassAuthObj extends MobileQueryObj {
    public String mCaptcha;
    public String mMobile;
    public String mPassword;
    public String mToken;

    public MobilePassAuthObj(String str, String str2, String str3) {
        super(7);
        this.mMobile = str;
        this.mPassword = str2;
        this.mCaptcha = str3;
    }

    public String getToken() {
        return this.mToken;
    }
}
