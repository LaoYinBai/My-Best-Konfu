package com.bytedance.sdk.open.aweme.api;

import android.content.Intent;
import androidx.annotation.Nullable;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface TikTokApiEventHandler {
    void onErrorIntent(@Nullable Intent intent);

    void onReq(BaseReq baseReq);

    void onResp(BaseResp baseResp);
}
