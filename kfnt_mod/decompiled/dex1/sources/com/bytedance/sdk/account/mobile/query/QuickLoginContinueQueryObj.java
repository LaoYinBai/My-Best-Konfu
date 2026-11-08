package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class QuickLoginContinueQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mMobile;
    public String mSmsCodekey;
    public IBDAccountUserEntity mUserInfo;

    public QuickLoginContinueQueryObj(String str, String str2) {
        super(24);
        this.mMobile = str;
        this.mSmsCodekey = str2;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
