package com.bytedance.sdk.open.aweme.authorize;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.sdk.open.aweme.TikTokOpenConfig;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.utils.AppUtil;
import com.bytedance.sdk.open.aweme.utils.OpenUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthImpl {
    private Context mContext;
    private TikTokOpenConfig openConfig;

    public AuthImpl(Context context, TikTokOpenConfig tikTokOpenConfig) {
        this.mContext = context;
        this.openConfig = tikTokOpenConfig;
    }

    public boolean authorizeWeb(Class cls, Authorization.Request request) {
        if (request == null || this.mContext == null || !request.checkArgs()) {
            return false;
        }
        OpenUtils.handleRequestScope(request);
        Bundle bundle = new Bundle();
        request.toBundle(bundle);
        bundle.putString(ParamKeyConstants.AuthParams.CLIENT_KEY, this.openConfig.clientKey);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_PKG, this.mContext.getPackageName());
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_VERSION, "1");
        Intent intent = new Intent(this.mContext, (Class<?>) cls);
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

    public boolean authorizeNative(Authorization.Request request, String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || request == null || this.mContext == null || !request.checkArgs()) {
            return false;
        }
        if (request instanceof Authorization.Request) {
            OpenUtils.handleRequestScope(request);
        }
        Bundle bundle = new Bundle();
        request.toBundle(bundle);
        bundle.putString(ParamKeyConstants.AuthParams.CLIENT_KEY, this.openConfig.clientKey);
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_PKG, this.mContext.getPackageName());
        bundle.putString(ParamKeyConstants.BaseParams.CALLER_BASE_OPEN_VERSION, "1");
        if (TextUtils.isEmpty(request.callerLocalEntry)) {
            bundle.putString(ParamKeyConstants.BaseParams.FROM_ENTRY, AppUtil.buildComponentClassName(this.mContext.getPackageName(), str3));
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(str, AppUtil.buildComponentClassName(str, str2)));
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
}
