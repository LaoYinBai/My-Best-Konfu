package com.vivo.push.ups;

import com.vivo.push.NoPorGuard;

@NoPorGuard
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TokenResult extends CodeResult {
    String token;

    public TokenResult(int i, String str) {
        super(i);
        this.token = str;
    }

    public String getToken() {
        return this.token;
    }
}
