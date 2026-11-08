package com.alipay.sdk.protocol;

import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum a {
    None(com.baidu.mobads.sdk.internal.a.f3089a),
    WapPay("js://wappay"),
    Update("js://update"),
    OpenWeb("loc:openweb"),
    SetResult("loc:setResult"),
    Exit("loc:exit");

    private String g;

    a(String str) {
        this.g = str;
    }

    public static a a(String str) {
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        a aVar = None;
        for (a aVar2 : values()) {
            if (str.startsWith(aVar2.g)) {
                return aVar2;
            }
        }
        return aVar;
    }
}
