package com.huawei.hms.push.ups.entity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TokenResult extends CodeResult {
    private String a;

    public TokenResult() {
    }

    public TokenResult(int i) {
        super(i);
    }

    public TokenResult(int i, String str) {
        super(i, str);
    }

    public TokenResult(String str) {
        this.a = str;
    }

    public String getToken() {
        return this.a;
    }

    public void setToken(String str) {
        this.a = str;
    }
}
