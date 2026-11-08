package com.bytedance.sdk.account.mobile.query;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailSendCodeQueryObj extends MobileQueryObj {
    public String mCaptcha;
    public String mEmail;
    public Map mExtendInfo;
    public String mNext;
    public String mPassWord;
    public int mType;
    public String mVersion;

    public EmailSendCodeQueryObj(String str, String str2, String str3, int i, String str4, Map map, String str5) {
        this.mEmail = str;
        this.mCaptcha = str2;
        this.mPassWord = str3;
        this.mType = i;
        this.mNext = str4;
        this.mExtendInfo = map;
        this.mVersion = str5;
    }
}
