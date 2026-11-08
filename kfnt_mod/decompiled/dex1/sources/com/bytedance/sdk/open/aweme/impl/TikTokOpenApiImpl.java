package com.bytedance.sdk.open.aweme.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler;
import com.bytedance.sdk.open.aweme.api.TiktokOpenApi;
import com.bytedance.sdk.open.aweme.authorize.AuthImpl;
import com.bytedance.sdk.open.aweme.authorize.handler.SendAuthDataHandler;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.authorize.ui.AwemeWebAuthorizeActivity;
import com.bytedance.sdk.open.aweme.authorize.ui.TikTokWebAuthorizeActivity;
import com.bytedance.sdk.open.aweme.base.IAPPCheckHelper;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.common.handler.TikTokDataHandler;
import com.bytedance.sdk.open.aweme.common.impl.AwemeCheckHelperImpl;
import com.bytedance.sdk.open.aweme.common.impl.MusicallyCheckHelperImpl;
import com.bytedance.sdk.open.aweme.common.impl.TiktokCheckHelperImpl;
import com.bytedance.sdk.open.aweme.share.Share;
import com.bytedance.sdk.open.aweme.share.ShareDataHandler;
import com.bytedance.sdk.open.aweme.share.ShareImpl;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TikTokOpenApiImpl implements TiktokOpenApi {
    static final int API_TYPE_LOGIN = 0;
    static final int API_TYPE_SHARE = 1;
    static final String LOCAL_ENTRY_ACTIVITY = "tiktokapi.TikTokEntryActivity";
    static final String REMOTE_SHARE_ACTIVITY = "share.SystemShareActivity";
    private static final int TYPE_AUTH_HANDLER = 1;
    private static final int TYPE_SHARE_HANDLER = 2;
    private AuthImpl authImpl;
    private Map<Integer, TikTokDataHandler> handlerMap = new HashMap(2);
    private final IAPPCheckHelper[] mAuthcheckApis;
    private Context mContext;
    private final IAPPCheckHelper[] mSharecheckApis;
    private int mTargetApp;
    private ShareImpl shareImpl;

    public TikTokOpenApiImpl(Context context, AuthImpl authImpl, ShareImpl shareImpl, int i) {
        this.mContext = context;
        this.shareImpl = shareImpl;
        this.authImpl = authImpl;
        this.handlerMap.put(1, new SendAuthDataHandler());
        this.handlerMap.put(2, new ShareDataHandler());
        this.mAuthcheckApis = new IAPPCheckHelper[]{new MusicallyCheckHelperImpl(context), new TiktokCheckHelperImpl(context)};
        this.mSharecheckApis = new IAPPCheckHelper[]{new MusicallyCheckHelperImpl(context), new TiktokCheckHelperImpl(context)};
        this.mTargetApp = i;
    }

    @Override // com.bytedance.sdk.open.aweme.api.TiktokOpenApi
    public boolean handleIntent(Intent intent, TikTokApiEventHandler tikTokApiEventHandler) {
        if (tikTokApiEventHandler == null) {
            return false;
        }
        if (intent == null) {
            tikTokApiEventHandler.onErrorIntent(intent);
            return false;
        }
        Bundle extras = intent.getExtras();
        if (extras == null) {
            tikTokApiEventHandler.onErrorIntent(intent);
            return false;
        }
        int i = extras.getInt(ParamKeyConstants.BaseParams.TYPE);
        if (i == 0) {
            i = extras.getInt(ParamKeyConstants.ShareParams.TYPE);
        }
        switch (i) {
        }
        return false;
    }

    @Override // com.bytedance.sdk.open.aweme.api.TiktokOpenApi
    public boolean isAppSupportAuthorization() {
        if (this.mTargetApp == 1) {
            return new AwemeCheckHelperImpl(this.mContext).isAppSupportAuthorization();
        }
        return getSupportApiAppInfo(0) != null;
    }

    @Override // com.bytedance.sdk.open.aweme.api.TiktokOpenApi
    public boolean isAppSupportShare() {
        if (this.mTargetApp == 1) {
            return new AwemeCheckHelperImpl(this.mContext).isAppSupportShare();
        }
        return getSupportApiAppInfo(1) != null;
    }

    @Override // com.bytedance.sdk.open.aweme.api.TiktokOpenApi
    public boolean authorize(Authorization.Request request) {
        IAPPCheckHelper supportApiAppInfo;
        if (this.mTargetApp == 1) {
            supportApiAppInfo = new AwemeCheckHelperImpl(this.mContext);
            if (!supportApiAppInfo.isAppSupportAuthorization()) {
                supportApiAppInfo = null;
            }
        } else if (this.mTargetApp == 2) {
            supportApiAppInfo = getSupportApiAppInfo(0);
        } else {
            throw new IllegalArgumentException("We only support AWEME And TIKTOK for authorization.");
        }
        if (supportApiAppInfo == null || !this.authImpl.authorizeNative(request, supportApiAppInfo.getPackageName(), supportApiAppInfo.getRemoteAuthEntryActivity(), LOCAL_ENTRY_ACTIVITY)) {
            return sendWebAuthRequest(request);
        }
        return true;
    }

    @Override // com.bytedance.sdk.open.aweme.api.TiktokOpenApi
    public boolean share(Share.Request request) {
        if (request == null) {
            return false;
        }
        if (this.mTargetApp == 1) {
            AwemeCheckHelperImpl awemeCheckHelperImpl = new AwemeCheckHelperImpl(this.mContext);
            if (this.mContext != null && awemeCheckHelperImpl.isAppSupportShare()) {
                return this.shareImpl.share(LOCAL_ENTRY_ACTIVITY, awemeCheckHelperImpl.getPackageName(), REMOTE_SHARE_ACTIVITY, request, awemeCheckHelperImpl.getRemoteAuthEntryActivity());
            }
        } else if (isAppSupportShare()) {
            return this.shareImpl.share(LOCAL_ENTRY_ACTIVITY, getSupportApiAppInfo(1).getPackageName(), REMOTE_SHARE_ACTIVITY, request, getSupportApiAppInfo(1).getRemoteAuthEntryActivity());
        }
        return false;
    }

    private boolean sendWebAuthRequest(Authorization.Request request) {
        if (this.mTargetApp == 2) {
            return this.authImpl.authorizeWeb(TikTokWebAuthorizeActivity.class, request);
        }
        if (this.mTargetApp == 1) {
            return this.authImpl.authorizeWeb(AwemeWebAuthorizeActivity.class, request);
        }
        throw new IllegalArgumentException("We only support AWEME And TIKTOK for authorization.");
    }

    @Nullable
    private IAPPCheckHelper getSupportApiAppInfo(int i) {
        int i2 = 0;
        switch (i) {
            case 0:
                IAPPCheckHelper[] iAPPCheckHelperArr = this.mAuthcheckApis;
                int length = iAPPCheckHelperArr.length;
                while (i2 < length) {
                    IAPPCheckHelper iAPPCheckHelper = iAPPCheckHelperArr[i2];
                    if (iAPPCheckHelper.isAppSupportAuthorization()) {
                        return iAPPCheckHelper;
                    }
                    i2++;
                }
                return null;
            case 1:
                IAPPCheckHelper[] iAPPCheckHelperArr2 = this.mSharecheckApis;
                int length2 = iAPPCheckHelperArr2.length;
                while (i2 < length2) {
                    IAPPCheckHelper iAPPCheckHelper2 = iAPPCheckHelperArr2[i2];
                    if (iAPPCheckHelper2.isAppSupportShare()) {
                        return iAPPCheckHelper2;
                    }
                    i2++;
                }
                return null;
            default:
                return null;
        }
    }
}
