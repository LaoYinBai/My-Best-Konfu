package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ResetPasswordQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCaptcha;
    public String mCode;
    public String mMobile;
    public String mPassword;
    public IBDAccountUserEntity mUserInfo;

    public ResetPasswordQueryObj(String str, String str2, String str3, String str4) {
        super(6);
        this.mMobile = str;
        this.mCode = str2;
        this.mPassword = str3;
        this.mCaptcha = str4;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
