package com.bytedance.sdk.account.mobile.query;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckUserNameQueryObj extends MobileQueryObj {
    public String mAvailableName;
    public String mUserName;

    public CheckUserNameQueryObj(String str, int i) {
        super(i);
        this.mUserName = str;
    }
}
