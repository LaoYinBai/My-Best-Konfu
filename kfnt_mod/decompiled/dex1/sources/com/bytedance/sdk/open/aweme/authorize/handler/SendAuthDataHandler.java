package com.bytedance.sdk.open.aweme.authorize.handler;

import android.os.Bundle;
import com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.handler.TikTokDataHandler;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SendAuthDataHandler implements TikTokDataHandler {
    @Override // com.bytedance.sdk.open.aweme.common.handler.TikTokDataHandler
    public boolean handle(int i, Bundle bundle, TikTokApiEventHandler tikTokApiEventHandler) {
        if (bundle == null || tikTokApiEventHandler == null) {
            return false;
        }
        if (i != 1) {
            if (i != 2) {
                return false;
            }
            BaseResp response = new Authorization.Response(bundle);
            if (!response.checkArgs()) {
                return false;
            }
            tikTokApiEventHandler.onResp(response);
            return true;
        }
        Authorization.Request request = new Authorization.Request(bundle);
        if (!request.checkArgs()) {
            return false;
        }
        if (request.scope != null) {
            request.scope = request.scope.replace(" ", "");
        }
        if (request.optionalScope1 != null) {
            request.optionalScope1 = request.optionalScope1.replace(" ", "");
        }
        if (request.optionalScope0 != null) {
            request.optionalScope0 = request.optionalScope0.replace(" ", "");
        }
        tikTokApiEventHandler.onReq(request);
        return true;
    }
}
