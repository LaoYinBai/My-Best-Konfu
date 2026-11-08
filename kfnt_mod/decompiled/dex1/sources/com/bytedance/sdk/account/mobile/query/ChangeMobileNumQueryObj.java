package com.bytedance.sdk.account.mobile.query;

import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ChangeMobileNumQueryObj extends MobileQueryObj implements IUserQueryObj {
    public String mCaptcha;
    public String mCode;
    public Map mExtendInfo;
    public String mMobile;
    public String mTicket;
    public IBDAccountUserEntity mUserInfo;

    public ChangeMobileNumQueryObj(String str, String str2, String str3) {
        super(20);
        this.mMobile = str;
        this.mCaptcha = str3;
        this.mCode = str2;
    }

    public ChangeMobileNumQueryObj(String str, String str2, String str3, String str4) {
        super(20);
        this.mMobile = str;
        this.mCaptcha = str3;
        this.mCode = str2;
        this.mTicket = str4;
    }

    public ChangeMobileNumQueryObj(String str, String str2, String str3, String str4, Map map) {
        this(str, str2, str3, str4);
        this.mExtendInfo = map;
    }

    @Override // com.bytedance.sdk.account.mobile.query.IUserQueryObj
    public IBDAccountUserEntity getUserInfo() {
        return this.mUserInfo;
    }
}
