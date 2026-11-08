package com.bytedance.sdk.open.aweme.share;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.open.aweme.TikTokOpenConfig;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.common.handler.TikTokDataHandler;
import com.bytedance.sdk.open.aweme.share.Share;
import com.bytedance.sdk.open.aweme.utils.AppUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ShareImpl {
    private List<TikTokDataHandler> handlers = new ArrayList();
    private Context mContext;
    private TikTokOpenConfig openConfig;

    public ShareImpl(Context context, TikTokOpenConfig tikTokOpenConfig) {
        this.mContext = context;
        this.openConfig = tikTokOpenConfig;
        this.handlers.add(new ShareDataHandler());
    }

    public boolean share(String str, String str2, String str3, Share.Request request, String str4) {
        if (TextUtils.isEmpty(str2) || request == null || this.mContext == null || !request.checkArgs()) {
            return false;
        }
        Bundle bundle = new Bundle();
        if (AppUtil.getPlatformSDKVersion(this.mContext, str2, str4) >= 3) {
            request.toBundle(bundle);
        } else {
            request.toBundleForOldVersion(bundle);
        }
        bundle.putString(ParamKeyConstants.ShareParams.CLIENT_KEY, this.openConfig.clientKey);
        bundle.putString(ParamKeyConstants.ShareParams.CALLER_PKG, this.mContext.getPackageName());
        bundle.putString(ParamKeyConstants.ShareParams.CALLER_SDK_VERSION, "1");
        if (TextUtils.isEmpty(request.callerLocalEntry)) {
            bundle.putString(ParamKeyConstants.ShareParams.CALLER_LOCAL_ENTRY, this.mContext.getPackageName() + "." + str);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str2, buildComponentClassName(str2, str3)));
        intent.putExtras(bundle);
        if (Build.VERSION.SDK_INT >= 11) {
            intent.addFlags(32768);
        }
        if (Build.VERSION.SDK_INT >= 21) {
            intent.addFlags(67108864);
            intent.addFlags(536870912);
        }
        try {
            this.mContext.startActivity(intent);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    private String buildComponentClassName(String str, String str2) {
        return "com.ss.android.ugc.aweme." + str2;
    }
}
