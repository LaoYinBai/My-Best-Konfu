package com.bytedance.sdk.adnet.game.http.req;

import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OkGetBuilder extends NetBuilder<OkGetBuilder> {
    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    protected void a(Request.Builder builder) {
        builder.get();
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    public String method() {
        return "GET";
    }
}
