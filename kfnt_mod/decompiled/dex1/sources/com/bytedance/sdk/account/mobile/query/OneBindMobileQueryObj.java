package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OneBindMobileQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mConfirmSwitchBindTips;
    public String mConfirmSwitchBindUrl;
    public String mFrom;
    public String mPassword;
    public String mToken;
    public int mUnbindExist;
    public IBDAccountUserEntity mUserInfo;

    public OneBindMobileQueryObj(String str, String str2, String str3, int i) {
        super(10);
        this.mToken = str;
        this.mFrom = str2;
        this.mPassword = str3;
        this.mUnbindExist = i;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
