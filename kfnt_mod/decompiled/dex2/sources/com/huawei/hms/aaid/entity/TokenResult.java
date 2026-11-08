package com.huawei.hms.aaid.entity;

import com.huawei.hms.core.aidl.annotation.Packed;
import com.huawei.hms.support.api.client.Result;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TokenResult extends Result {

    @Packed
    private String belongId;

    @Packed
    private String token = "";

    @Packed
    private int retCode = 0;

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getBelongId() {
        return this.belongId;
    }

    public void setBelongId(String str) {
        this.belongId = str;
    }

    public int getRetCode() {
        return this.retCode;
    }

    public void setRetCode(int i) {
        this.retCode = i;
    }
}
