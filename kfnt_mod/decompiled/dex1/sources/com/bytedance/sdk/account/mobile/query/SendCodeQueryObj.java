package com.bytedance.sdk.account.mobile.query;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SendCodeQueryObj extends MobileQueryObj {
    public int checkRegister;
    public String mAuthToken;
    public int mAutoread;
    public String mCaptcha;
    public String mConfirmSwitchBindTips;
    public String mConfirmSwitchBindUrl;
    public Map mExtendInfo;
    public String mMobile;
    public String mOldMobile;
    public int mResendTime;
    public String mSharkTicket;
    public String mTicket;
    public int mType;
    public int mUnbindExist;
    public String mUnusableMobileTicket;

    public SendCodeQueryObj(String str, String str2, int i) {
        super(i);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i;
        this.mResendTime = 30;
        this.mOldMobile = "";
    }

    public SendCodeQueryObj(String str, String str2, int i, int i2, int i3) {
        super(i);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i;
        this.mResendTime = 30;
        this.mOldMobile = "";
        this.mUnbindExist = i2;
        this.checkRegister = i3;
    }

    public SendCodeQueryObj(String str, String str2, String str3, int i) {
        super(i);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str3;
        this.mType = i;
        this.mResendTime = 30;
        this.mOldMobile = str2;
    }

    public SendCodeQueryObj(String str, String str2, int i, int i2, String str3, int i3, int i4) {
        super(i);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i;
        this.mResendTime = 30;
        this.mOldMobile = "";
        this.mUnbindExist = i2;
        this.mTicket = str3;
        this.mAutoread = i4;
        this.checkRegister = i3;
    }

    public SendCodeQueryObj(String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5) {
        super(i);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mCaptcha = str2;
        this.mType = i;
        this.mResendTime = 30;
        this.mOldMobile = "";
        this.mUnbindExist = i2;
        this.mTicket = str3;
        this.mAutoread = i4;
        this.mSharkTicket = str4;
        this.mUnusableMobileTicket = str5;
        this.checkRegister = i3;
    }

    public SendCodeQueryObj(String str, String str2, int i, int i2, String str3, int i3, int i4, String str4, String str5, Map map) {
        this(str, str2, i, i2, str3, i3, i4, str4, str5);
        this.mExtendInfo = map;
    }

    public SendCodeQueryObj(int i, String str, String str2, int i2, int i3, String str3, String str4, String str5) {
        super(i);
        this.checkRegister = -1;
        this.mMobile = str;
        this.mOldMobile = str2;
        this.checkRegister = i2;
        this.mUnbindExist = i3;
        this.mTicket = str3;
        this.mAuthToken = str4;
        this.mUnusableMobileTicket = str5;
    }
}
