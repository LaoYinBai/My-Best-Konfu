package com.bytedance.sdk.open.aweme.api;

import android.content.Intent;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.share.Share;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface TiktokOpenApi {
    boolean authorize(Authorization.Request request);

    boolean handleIntent(Intent intent, TikTokApiEventHandler tikTokApiEventHandler);

    boolean isAppSupportAuthorization();

    boolean isAppSupportShare();

    boolean share(Share.Request request);
}
