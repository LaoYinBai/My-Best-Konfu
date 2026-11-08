package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailLoginQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCaptcha;
    public String mEmail;
    public String mPassword;
    public IBDAccountUserEntity mUserInfo;

    public EmailLoginQueryObj(String str, String str2, String str3) {
        this.mEmail = str;
        this.mPassword = str2;
        this.mCaptcha = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
