package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BindLoginObj extends MobileQueryObj implements IUserQueryObj {
    public String mCaptcha;
    public String mCode;
    public Map mExtendInfo;
    public String mMobile;
    public IBDAccountUserEntity mUserInfo;
    public String profile_key;

    public BindLoginObj(String str, String str2, String str3, String str4) {
        super(7);
        this.mMobile = str;
        this.mCode = str2;
        this.mCaptcha = str4;
        this.profile_key = str3;
    }

    public BindLoginObj(String str, String str2, String str3, String str4, Map map) {
        this(str, str2, str3, str4);
        this.mExtendInfo = map;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
