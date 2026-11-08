package com.ss.union.game.sdk.common.net.http.base.in;

import com.umeng.message.util.HttpRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class ACoreRequestHead implements ICoreNetRequestGet<ACoreRequestHead> {
    @Override // com.ss.union.game.sdk.common.net.http.base.in.ICoreNetRequest
    public String method() {
        return HttpRequest.METHOD_HEAD;
    }
}
