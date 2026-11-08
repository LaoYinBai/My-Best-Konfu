package com.bytedance.sdk.adnet.game.http.req;

import com.bytedance.sdk.adnet.game.http.req.base.NetBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OkTraceBuilder extends NetBuilder<OkTraceBuilder> {
    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    protected void a(Request.Builder builder) {
        builder.method("TRACE", null);
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    public String method() {
        return "TRACE";
    }
}
