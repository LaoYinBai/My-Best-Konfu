package com.bytedance.sdk.account.mobile.query;

import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailCheckRegisterQueryObj extends MobileQueryObj {
    public int is_registered;
    public String mEmail;
    public Map mExtendInfo;
    public String mVersion;

    public EmailCheckRegisterQueryObj(String str, Map map, String str2) {
        this.mEmail = str;
        this.mExtendInfo = map;
        this.mVersion = str2;
    }

    public boolean isEmailRegister() {
        return this.is_registered == 1;
    }
}
