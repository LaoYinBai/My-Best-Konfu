package com.bytedance.sdk.open.aweme.authorize.ui;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.bytedance.sdk.open.aweme.TikTokOpenApiFactory;
import com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler;
import com.bytedance.sdk.open.aweme.api.TiktokOpenApi;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;
import com.bytedance.sdk.open.aweme.utils.ViewUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TikTokWebAuthorizeActivity extends BaseWebAuthorizeActivity {
    public static final String AUTH_HOST = "open-api.musical.ly";
    public static final String AUTH_PATH = "/platform/oauth/connect/";
    public static final String DOMAIN = "api.snssdk.com";
    private TiktokOpenApi ttOpenApi;

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected String errorCode2Message(int i) {
        return "";
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected String getAuthPath() {
        return "/platform/oauth/connect/";
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected String getDomain() {
        return "api.snssdk.com";
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected String getHost() {
        return AUTH_HOST;
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected boolean isNetworkAvailable() {
        return true;
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.ttOpenApi = TikTokOpenApiFactory.create(this, 2);
        super.onCreate(bundle);
        ViewUtils.setStatusBarColor(this, 0);
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected View getLoadingView(ViewGroup viewGroup) {
        return LayoutInflater.from(this).inflate(getResources().getIdentifier("tiktok_layout_open_loading_view", "layout", getPackageName()), viewGroup, false);
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected View getHeaderView(ViewGroup viewGroup) {
        View viewInflate = LayoutInflater.from(this).inflate(getResources().getIdentifier("tiktok_layout_open_web_header_view", "layout", getPackageName()), viewGroup, false);
        ImageView imageView = (ImageView) viewInflate.findViewById(getResources().getIdentifier("tiktok_cancel", "id", getPackageName()));
        Drawable drawable = ContextCompat.getDrawable(this, getResources().getIdentifier("tiktok_selector_web_authorize_titlebar_back", "drawable", getPackageName()));
        if (Build.VERSION.SDK_INT >= 19 && drawable != null) {
            drawable.setAutoMirrored(true);
        }
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.sdk.open.aweme.authorize.ui.TikTokWebAuthorizeActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                TikTokWebAuthorizeActivity.this.onCancel(-2);
            }
        });
        return viewInflate;
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected boolean handleIntent(Intent intent, TikTokApiEventHandler tikTokApiEventHandler) {
        return this.ttOpenApi.handleIntent(intent, tikTokApiEventHandler);
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected void sendInnerResponse(Authorization.Request request, BaseResp baseResp) {
        if (baseResp != null && this.mContentWebView != null) {
            if (baseResp.extras == null) {
                baseResp.extras = new Bundle();
            }
            baseResp.extras.putString("wap_authorize_url", this.mContentWebView.getUrl());
        }
        sendInnerResponse("tiktokapi.TikTokEntryActivity", request, baseResp);
    }

    @Override // com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity
    protected void setContainerViewBgColor() {
        if (this.mContainer != null) {
            this.mContainer.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
    }
}
