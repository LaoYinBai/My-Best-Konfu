package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailRegisterQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCode;
    public String mEmail;
    public String mPassword;
    public String mRecaptchaToken;
    public IBDAccountUserEntity mUserInfo;

    public EmailRegisterQueryObj(String str, String str2, String str3, String str4) {
        this.mEmail = str;
        this.mPassword = str2;
        this.mCode = str3;
        this.mRecaptchaToken = str4;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
