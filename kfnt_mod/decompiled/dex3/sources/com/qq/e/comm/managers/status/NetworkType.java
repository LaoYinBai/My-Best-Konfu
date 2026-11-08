package com.qq.e.comm.managers.status;

import com.taobao.accs.utl.UtilityImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum NetworkType {
    UNKNOWN(0, 1, UtilityImpl.NET_TYPE_UNKNOWN),
    WIFI(1, 2, UtilityImpl.NET_TYPE_WIFI),
    NET_2G(2, 4, UtilityImpl.NET_TYPE_2G),
    NET_3G(3, 8, UtilityImpl.NET_TYPE_3G),
    NET_4G(4, 16, UtilityImpl.NET_TYPE_4G);

    private int a;
    private int b;
    private String c;

    NetworkType(int i, int i2, String str) {
        this.a = i;
        this.b = i2;
        this.c = str;
    }

    public int getConnValue() {
        return this.a;
    }

    public String getNameValue() {
        return this.c;
    }

    public int getPermValue() {
        return this.b;
    }
}
