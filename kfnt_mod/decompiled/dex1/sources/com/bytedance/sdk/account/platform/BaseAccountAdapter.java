package com.bytedance.sdk.account.platform;

import android.content.Context;
import com.bytedance.b.a;
import com.bytedance.sdk.account.api.IBDAccountPlatformAPI;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.impl.BDAccountDelegate;
import com.bytedance.sdk.account.platform.DouyinPlatformDelegate;
import com.bytedance.sdk.account.platform.FacebookPlatformDelegate;
import com.bytedance.sdk.account.platform.GooglePlatformDelegate;
import com.bytedance.sdk.account.platform.KakaoPlatformDelegate;
import com.bytedance.sdk.account.platform.LinePlatformDelegate;
import com.bytedance.sdk.account.platform.PlatformDelegate;
import com.bytedance.sdk.account.platform.QQPlatformDelegate;
import com.bytedance.sdk.account.platform.TouTiaoPlatformDelegate;
import com.bytedance.sdk.account.platform.TwitterPlatformDelegate;
import com.bytedance.sdk.account.platform.VKPlatformDelegate;
import com.bytedance.sdk.account.platform.WeiboPlatformDelegate;
import com.bytedance.sdk.account.platform.WeixinPlatformDelegate;
import com.bytedance.sdk.account.platform.base.AuthorizeCallback;
import com.bytedance.sdk.account.platform.base.AuthorizeErrorResponse;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class BaseAccountAdapter implements AuthorizeCallback {
    static Map<String, PlatformDelegate.IFactory> delegateMap = new HashMap();
    protected IBDAccountPlatformAPI api;
    protected String platform;
    protected String platformId;

    static {
        delegateMap.put(a.ar, new WeixinPlatformDelegate.Factory());
        delegateMap.put(a.an, new QQPlatformDelegate.QQLoginFactor());
        delegateMap.put(a.ai, new WeiboPlatformDelegate.Factory());
        delegateMap.put("google", new GooglePlatformDelegate.Factory());
        delegateMap.put("facebook", new FacebookPlatformDelegate.Factory());
        delegateMap.put("twitter", new TwitterPlatformDelegate.Factory());
        delegateMap.put("line", new LinePlatformDelegate.Factory());
        delegateMap.put("kakaotalk", new KakaoPlatformDelegate.Factory());
        delegateMap.put(a.aq, new DouyinPlatformDelegate.Factory());
        delegateMap.put("toutiao", new TouTiaoPlatformDelegate.Factory());
        delegateMap.put("vk", new VKPlatformDelegate.Factory());
    }

    BaseAccountAdapter(Context context, String str, String str2) {
        this.api = BDAccountDelegate.createPlatformAPI(context.getApplicationContext());
        this.platformId = str;
        this.platform = str2;
    }

    public static class ErrorResponse {
        public final UserApiResponse apiResponse;
        public final AuthorizeErrorResponse authorizeErrorResponse;

        ErrorResponse(UserApiResponse userApiResponse) {
            this.apiResponse = userApiResponse;
            this.authorizeErrorResponse = null;
        }

        ErrorResponse(AuthorizeErrorResponse authorizeErrorResponse) {
            this.authorizeErrorResponse = authorizeErrorResponse;
            this.apiResponse = null;
        }
    }
}
