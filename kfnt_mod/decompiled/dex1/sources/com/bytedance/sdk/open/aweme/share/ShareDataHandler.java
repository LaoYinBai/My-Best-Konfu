package com.bytedance.sdk.open.aweme.share;

import android.os.Bundle;
import com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler;
import com.bytedance.sdk.open.aweme.common.handler.TikTokDataHandler;
import com.bytedance.sdk.open.aweme.share.Share;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ShareDataHandler implements TikTokDataHandler {
    @Override // com.bytedance.sdk.open.aweme.common.handler.TikTokDataHandler
    public boolean handle(int i, Bundle bundle, TikTokApiEventHandler tikTokApiEventHandler) {
        if (bundle == null || tikTokApiEventHandler == null) {
            return false;
        }
        if (i == 3) {
            Share.Request request = new Share.Request(bundle);
            if (!request.checkArgs()) {
                return false;
            }
            tikTokApiEventHandler.onReq(request);
            return true;
        }
        if (i != 4) {
            return false;
        }
        Share.Response response = new Share.Response(bundle);
        if (!response.checkArgs()) {
            return false;
        }
        tikTokApiEventHandler.onResp(response);
        return true;
    }
}
