package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LoginQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCaptcha;
    public String mMobile;
    public String mPassword;
    public IBDAccountUserEntity mUserInfo;

    public LoginQueryObj(String str, String str2, String str3) {
        super(7);
        this.mMobile = str;
        this.mPassword = str2;
        this.mCaptcha = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
