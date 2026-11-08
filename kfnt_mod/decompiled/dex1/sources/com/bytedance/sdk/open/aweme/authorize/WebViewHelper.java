package com.bytedance.sdk.open.aweme.authorize;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.api.IWeiboService;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.utils.SignatureUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class WebViewHelper {
    public static String getLoadUrl(Context context, Authorization.Request request, String str, String str2) throws PackageManager.NameNotFoundException {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(request.optionalScope1)) {
            for (String str3 : request.optionalScope1.split(IWeiboService.Scope.EMPTY_SCOPE)) {
                if (sb.length() > 0) {
                    sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                }
                sb.append(str3 + ",1");
            }
        }
        if (!TextUtils.isEmpty(request.optionalScope0)) {
            for (String str4 : request.optionalScope0.split(IWeiboService.Scope.EMPTY_SCOPE)) {
                if (sb.length() > 0) {
                    sb.append(IWeiboService.Scope.EMPTY_SCOPE);
                }
                sb.append(str4 + ",0");
            }
        }
        return new Uri.Builder().scheme("https").authority(str).path(str2).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_RESPONSE_TYPE, "code").appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_REDIRECT_URI, request.redirectUri).appendQueryParameter("client_key", request.getClientKey()).appendQueryParameter("state", request.state).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_FROM, ParamKeyConstants.WebViewConstants.VALUE_FROM_OPENSDK).appendQueryParameter("scope", request.scope).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_OPTIONAL_SCOPE, sb.toString()).appendQueryParameter(ParamKeyConstants.WebViewConstants.QUERY_SIGNATURE, SignatureUtils.packageSignature(SignatureUtils.getMd5Signs(context, request.getCallerPackage()))).build().toString();
    }
}
