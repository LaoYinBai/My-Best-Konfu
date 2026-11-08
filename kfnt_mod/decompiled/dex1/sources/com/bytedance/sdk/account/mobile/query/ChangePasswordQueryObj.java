package com.bytedance.sdk.account.mobile.query;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ChangePasswordQueryObj extends MobileQueryObj {
    public String mCaptcha;
    public String mCode;
    public String mPassword;

    public ChangePasswordQueryObj(String str, String str2, String str3) {
        super(15);
        this.mCode = str;
        this.mPassword = str2;
        this.mCaptcha = str3;
    }
}
