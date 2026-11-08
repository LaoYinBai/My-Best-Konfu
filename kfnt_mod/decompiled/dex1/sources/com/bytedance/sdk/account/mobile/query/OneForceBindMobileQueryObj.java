package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OneForceBindMobileQueryObj extends MobileQueryObj implements IUserQueryObj {
    public int mBindLogicType;
    public String mFrom;
    public String mProfileKey;
    public String mResultCode;
    public String mToken;
    public IBDAccountUserEntity mUserInfo;

    public OneForceBindMobileQueryObj(String str, String str2, String str3, int i) {
        super(10);
        this.mToken = str;
        this.mFrom = str2;
        this.mProfileKey = str3;
        this.mBindLogicType = i;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
