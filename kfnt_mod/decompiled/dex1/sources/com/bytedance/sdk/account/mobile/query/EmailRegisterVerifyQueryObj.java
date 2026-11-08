package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailRegisterVerifyQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCode;
    public String mEmail;
    public Map mExtendInfo;
    public int mType;
    public IBDAccountUserEntity mUserInfo;
    public String mVersion;

    public EmailRegisterVerifyQueryObj(String str, String str2, int i, Map map, String str3) {
        this.mEmail = str;
        this.mCode = str2;
        this.mType = i;
        this.mExtendInfo = map;
        this.mVersion = str3;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
