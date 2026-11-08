package com.bytedance.sdk.adnet.game.http.req;

import anet.channel.request.Request;
import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OkHeadBuilder extends NetBuilder<OkHeadBuilder> {
    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    protected void a(Request.Builder builder) {
        builder.head();
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    public String method() {
        return Request.Method.HEAD;
    }
}
