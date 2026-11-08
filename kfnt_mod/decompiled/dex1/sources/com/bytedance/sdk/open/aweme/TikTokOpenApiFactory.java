package com.bytedance.sdk.open.aweme;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.open.aweme.api.TiktokOpenApi;
import com.bytedance.sdk.open.aweme.authorize.AuthImpl;
import com.bytedance.sdk.open.aweme.impl.TikTokOpenApiImpl;
import com.bytedance.sdk.open.aweme.share.ShareImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TikTokOpenApiFactory {
    private static TikTokOpenConfig sConfig;

    public static boolean init(TikTokOpenConfig tikTokOpenConfig) {
        if (tikTokOpenConfig == null || TextUtils.isEmpty(tikTokOpenConfig.clientKey)) {
            return false;
        }
        sConfig = tikTokOpenConfig;
        return true;
    }

    public static TiktokOpenApi create(Context context) {
        return new TikTokOpenApiImpl(context, new AuthImpl(context, sConfig), new ShareImpl(context, sConfig), 2);
    }

    public static TiktokOpenApi create(Context context, int i) {
        return new TikTokOpenApiImpl(context, new AuthImpl(context, sConfig), new ShareImpl(context, sConfig), i);
    }
}
