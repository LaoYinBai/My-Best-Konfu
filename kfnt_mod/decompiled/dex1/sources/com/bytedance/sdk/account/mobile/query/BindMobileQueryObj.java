package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BindMobileQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCaptcha;
    public String mCode;
    public String mMobile;
    public String mPassword;
    public int mUnbindExist;
    public IBDAccountUserEntity mUserInfo;

    public BindMobileQueryObj(String str, String str2, String str3, String str4) {
        super(10);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str4;
        this.mPassword = str3;
    }

    public BindMobileQueryObj(String str, String str2, String str3, String str4, int i) {
        super(10);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str4;
        this.mPassword = str3;
        this.mUnbindExist = i;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
