package com.bytedance.sdk.adnet.game.http.req;

import com.bytedance.sdk.adnet.game.http.req.base.NetBodyBuilder;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.Request;
import com.bytedance.sdk.adnet.game.ok3.okhttp3.RequestBody;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OkPostBuilder extends NetBodyBuilder<OkPostBuilder> {
    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBodyBuilder
    protected void a(Request.Builder builder, RequestBody requestBody) {
        builder.post(requestBody);
    }

    @Override // com.bytedance.sdk.adnet.game.http.req.base.NetBuilder
    public String method() {
        return "POST";
    }
}
