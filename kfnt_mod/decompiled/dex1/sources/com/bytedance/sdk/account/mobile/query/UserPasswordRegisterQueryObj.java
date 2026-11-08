package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UserPasswordRegisterQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mPassword;
    public IBDAccountUserEntity mUserInfo;
    public String mUserName;

    public UserPasswordRegisterQueryObj(String str, String str2) {
        super(3);
        this.mPassword = str2;
        this.mUserName = str;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
