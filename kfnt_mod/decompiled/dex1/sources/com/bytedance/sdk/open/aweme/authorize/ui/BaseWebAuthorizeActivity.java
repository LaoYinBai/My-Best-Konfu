package com.bytedance.sdk.open.aweme.authorize.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler;
import com.bytedance.sdk.open.aweme.authorize.WebViewHelper;
import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import com.bytedance.sdk.open.aweme.common.model.BaseReq;
import com.bytedance.sdk.open.aweme.common.model.BaseResp;
import com.bytedance.sdk.open.aweme.utils.AppUtil;
import com.bytedance.sdk.open.aweme.utils.OpenUtils;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class BaseWebAuthorizeActivity extends Activity implements TikTokApiEventHandler {
    protected static final String LOCAL_ENTRY_ACTIVITY = "tiktokapi.TikTokEntryActivity";
    private static final int MSG_LOADING_TIME_OUT = 100;
    private static final String RES_ID = "id";
    private static final String RES_LAYOUT = "layout";
    private static final String RES_STRING = "string";
    protected static final String WAP_AUTHORIZE_URL = "wap_authorize_url";
    protected Authorization.Request mAuthRequest;
    protected AlertDialog mBaseErrorDialog;
    protected RelativeLayout mContainer;
    protected WebView mContentWebView;
    private Context mContext;
    MyHandler mHandler;
    protected boolean mHasExecutingRequest;
    protected RelativeLayout mHeaderView;
    private int mLastErrorCode;
    protected FrameLayout mLoadingLayout;
    int OP_ERROR_NO_CONNECTION = -12;
    int OP_ERROR_CONNECT_TIMEOUT = -13;
    int OP_ERROR_NETWORK_ERROR = -15;
    protected boolean mStatusDestroyed = false;
    protected boolean isShowNetworkError = false;

    protected abstract String errorCode2Message(int i);

    protected abstract String getAuthPath();

    protected abstract String getDomain();

    protected View getHeaderView(ViewGroup viewGroup) {
        return null;
    }

    protected abstract String getHost();

    protected View getLoadingView(ViewGroup viewGroup) {
        return null;
    }

    protected abstract boolean handleIntent(Intent intent, TikTokApiEventHandler tikTokApiEventHandler);

    @CallSuper
    protected void initActions() {
    }

    protected abstract boolean isNetworkAvailable();

    @Override // com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler
    public void onErrorIntent(@Nullable Intent intent) {
    }

    @Override // com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler
    public void onResp(BaseResp baseResp) {
    }

    protected abstract void sendInnerResponse(Authorization.Request request, BaseResp baseResp);

    private static class MyHandler extends Handler {
        private final WeakReference<BaseWebAuthorizeActivity> mActivty;

        public MyHandler(BaseWebAuthorizeActivity baseWebAuthorizeActivity) {
            this.mActivty = new WeakReference<>(baseWebAuthorizeActivity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            BaseWebAuthorizeActivity baseWebAuthorizeActivity;
            super.handleMessage(message);
            if (message.what == 100 && (baseWebAuthorizeActivity = this.mActivty.get()) != null) {
                baseWebAuthorizeActivity.handleLoadingTimeout();
            }
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mHandler = new MyHandler(this);
        handleIntent(getIntent(), this);
        setContentView(getResources().getIdentifier("tiktok_layout_open_web_authorize", RES_LAYOUT, getPackageName()));
        initView();
        initActions();
        handleRequestIntent();
    }

    @Override // com.bytedance.sdk.open.aweme.api.TikTokApiEventHandler
    public void onReq(BaseReq baseReq) {
        if (baseReq instanceof Authorization.Request) {
            this.mAuthRequest = (Authorization.Request) baseReq;
            this.mAuthRequest.redirectUri = "https://" + getDomain() + ParamKeyConstants.REDIRECT_URL_PATH;
            setRequestedOrientation(-1);
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        redirectToClientApp("", -2);
    }

    public final void handleRequestIntent() {
        Authorization.Request request = this.mAuthRequest;
        if (request == null) {
            finish();
            return;
        }
        if (!isNetworkAvailable()) {
            this.isShowNetworkError = true;
            showNetworkErrorDialog(this.OP_ERROR_NO_CONNECTION);
        } else {
            this.mHandler.sendEmptyMessageDelayed(100, 8000L);
            startLoading();
            this.mContentWebView.setWebViewClient(new AuthWebViewClient());
            this.mContentWebView.loadUrl(WebViewHelper.getLoadUrl(this, request, getHost(), getAuthPath()));
        }
    }

    private void redirectToClientApp(String str, int i) {
        redirectToClientApp(str, null, i);
    }

    private void redirectToClientApp(String str, String str2, int i) {
        Authorization.Response response = new Authorization.Response();
        response.authCode = str;
        response.errorCode = i;
        response.state = str2;
        sendInnerResponse(this.mAuthRequest, response);
        finish();
    }

    private void redirectToClientApp(String str, String str2, String str3, int i) {
        Authorization.Response response = new Authorization.Response();
        response.authCode = str;
        response.errorCode = i;
        response.state = str2;
        response.grantedPermissions = str3;
        sendInnerResponse(this.mAuthRequest, response);
        finish();
    }

    public boolean sendInnerResponse(String str, Authorization.Request request, BaseResp baseResp) {
        if (baseResp == null || this.mContext == null || !baseResp.checkArgs()) {
            return false;
        }
        Bundle bundle = new Bundle();
        baseResp.toBundle(bundle);
        String packageName = this.mContext.getPackageName();
        String strBuildComponentClassName = TextUtils.isEmpty(request.callerLocalEntry) ? AppUtil.buildComponentClassName(packageName, str) : request.callerLocalEntry;
        Intent intent = new Intent();
        intent.setComponent(new ComponentName(packageName, strBuildComponentClassName));
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

    private void initView() {
        this.mContainer = (RelativeLayout) findViewById(getResources().getIdentifier("tiktok_open_rl_container", "id", getPackageName()));
        int identifier = getResources().getIdentifier("tiktok_open_header_view", "id", getPackageName());
        this.mHeaderView = (RelativeLayout) findViewById(identifier);
        setContainerViewBgColor();
        View headerView = getHeaderView(this.mHeaderView);
        if (headerView != null) {
            this.mHeaderView.removeAllViews();
            this.mHeaderView.addView(headerView);
        }
        this.mLoadingLayout = (FrameLayout) findViewById(getResources().getIdentifier("tiktok_open_loading_group", "id", getPackageName()));
        View loadingView = getLoadingView(this.mLoadingLayout);
        if (loadingView != null) {
            this.mLoadingLayout.removeAllViews();
            this.mLoadingLayout.addView(loadingView);
        }
        initWebView(this);
        if (this.mContentWebView.getParent() != null) {
            ((ViewGroup) this.mContentWebView.getParent()).removeView(this.mContentWebView);
        }
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mContentWebView.getLayoutParams();
        layoutParams.addRule(3, identifier);
        this.mContentWebView.setLayoutParams(layoutParams);
        this.mContentWebView.setVisibility(4);
        this.mContainer.addView(this.mContentWebView);
    }

    public void initWebView(Context context) {
        this.mContentWebView = new WebView(context);
        this.mContentWebView.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        WebSettings settings = this.mContentWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setCacheMode(-1);
    }

    public final class AuthWebViewClient extends WebViewClient {
        public AuthWebViewClient() {
        }

        @Override // android.webkit.WebViewClient
        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (BaseWebAuthorizeActivity.this.isNetworkAvailable()) {
                if (BaseWebAuthorizeActivity.this.handleRedirect(str)) {
                    return true;
                }
                BaseWebAuthorizeActivity.this.mContentWebView.loadUrl(str);
            } else {
                BaseWebAuthorizeActivity.this.showNetworkErrorDialog(BaseWebAuthorizeActivity.this.OP_ERROR_NO_CONNECTION);
            }
            return true;
        }

        @Override // android.webkit.WebViewClient
        public void onPageFinished(WebView webView, String str) {
            BaseWebAuthorizeActivity.this.mHasExecutingRequest = false;
            if (BaseWebAuthorizeActivity.this.mContentWebView == null || BaseWebAuthorizeActivity.this.mContentWebView.getProgress() != 100) {
                return;
            }
            BaseWebAuthorizeActivity.this.stopLoading();
            if (BaseWebAuthorizeActivity.this.mLastErrorCode == 0 && !BaseWebAuthorizeActivity.this.isShowNetworkError) {
                OpenUtils.setViewVisibility(BaseWebAuthorizeActivity.this.mContentWebView, 0);
            }
            if (BaseWebAuthorizeActivity.this.mHandler != null) {
                BaseWebAuthorizeActivity.this.mHandler.removeMessages(100);
            }
        }

        @Override // android.webkit.WebViewClient
        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (BaseWebAuthorizeActivity.this.mHasExecutingRequest) {
                return;
            }
            BaseWebAuthorizeActivity.this.mLastErrorCode = 0;
            BaseWebAuthorizeActivity.this.mHasExecutingRequest = true;
            BaseWebAuthorizeActivity.this.startLoading();
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedError(WebView webView, int i, String str, String str2) {
            BaseWebAuthorizeActivity.this.mLastErrorCode = i;
            BaseWebAuthorizeActivity.this.showNetworkErrorDialog(BaseWebAuthorizeActivity.this.OP_ERROR_NETWORK_ERROR);
            BaseWebAuthorizeActivity.this.isShowNetworkError = true;
        }

        @Override // android.webkit.WebViewClient
        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (BaseWebAuthorizeActivity.this.mHandler != null) {
                BaseWebAuthorizeActivity.this.mHandler.removeMessages(100);
            }
            BaseWebAuthorizeActivity.this.showSslErrorDialog(sslErrorHandler, sslError);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean handleRedirect(String str) throws NumberFormatException {
        Authorization.Request request;
        int i;
        if (TextUtils.isEmpty(str) || (request = this.mAuthRequest) == null || request.redirectUri == null || !str.startsWith(request.redirectUri)) {
            return false;
        }
        Uri uri = Uri.parse(str);
        String queryParameter = uri.getQueryParameter("code");
        String queryParameter2 = uri.getQueryParameter("state");
        String queryParameter3 = uri.getQueryParameter(ParamKeyConstants.WebViewConstants.REDIRECT_QUERY_SCOPE);
        if (TextUtils.isEmpty(queryParameter)) {
            String queryParameter4 = uri.getQueryParameter("errCode");
            if (TextUtils.isEmpty(queryParameter4)) {
                i = -1;
            } else {
                try {
                    i = Integer.parseInt(queryParameter4);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            redirectToClientApp("", i);
            return false;
        }
        redirectToClientApp(queryParameter, queryParameter2, queryParameter3, 0);
        return true;
    }

    protected void setContainerViewBgColor() {
        if (this.mContainer != null) {
            this.mContainer.setBackgroundColor(Color.parseColor("#ffffff"));
        }
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mStatusDestroyed = true;
        if (this.mContentWebView != null) {
            ViewParent parent = this.mContentWebView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeView(this.mContentWebView);
            }
            this.mContentWebView.stopLoading();
            this.mContentWebView.setWebViewClient(null);
        }
        if (this.mHandler != null) {
            this.mHandler.removeMessages(100);
        }
    }

    protected void startLoading() {
        OpenUtils.setViewVisibility(this.mLoadingLayout, 0);
    }

    protected void stopLoading() {
        OpenUtils.setViewVisibility(this.mLoadingLayout, 8);
    }

    protected void onCancel(int i) {
        redirectToClientApp("", i);
    }

    protected void handleLoadingTimeout() {
        if (isFinishing() || isDestroyed()) {
            return;
        }
        stopLoading();
        if (this.mContentWebView == null || this.mContentWebView.getVisibility() != 0) {
            this.isShowNetworkError = true;
            showNetworkErrorDialog(this.OP_ERROR_CONNECT_TIMEOUT);
        }
    }

    @Override // android.app.Activity
    public boolean isDestroyed() {
        if (Build.VERSION.SDK_INT < 17) {
            return this.mStatusDestroyed;
        }
        try {
            return super.isDestroyed();
        } catch (Throwable unused) {
            return this.mStatusDestroyed;
        }
    }

    protected void showSslErrorDialog(final SslErrorHandler sslErrorHandler, SslError sslError) {
        try {
            AlertDialog alertDialogCreate = new AlertDialog.Builder(this.mContext).create();
            String string = this.mContext.getString(getResources().getIdentifier("tiktok_open_ssl_error", RES_STRING, getPackageName()));
            switch (sslError.getPrimaryError()) {
                case 0:
                    string = this.mContext.getString(getResources().getIdentifier("tiktok_open_ssl_notyetvalid", RES_STRING, getPackageName()));
                    break;
                case 1:
                    string = this.mContext.getString(getResources().getIdentifier("tiktok_open_ssl_expired", RES_STRING, getPackageName()));
                    break;
                case 2:
                    string = this.mContext.getString(getResources().getIdentifier("tiktok_open_ssl_mismatched", RES_STRING, getPackageName()));
                    break;
                case 3:
                    string = this.mContext.getString(getResources().getIdentifier("tiktok_open_ssl_untrusted", RES_STRING, getPackageName()));
                    break;
            }
            String str = string + this.mContext.getString(getResources().getIdentifier("tiktok_open_ssl_continue", RES_STRING, getPackageName()));
            alertDialogCreate.setTitle(getResources().getIdentifier("tiktok_open_ssl_warning", RES_STRING, getPackageName()));
            alertDialogCreate.setTitle(str);
            int identifier = getResources().getIdentifier("tiktok_open_ssl_ok", RES_STRING, getPackageName());
            int identifier2 = getResources().getIdentifier("tiktok_open_ssl_cancel", RES_STRING, getPackageName());
            alertDialogCreate.setButton(-1, this.mContext.getString(identifier), new DialogInterface.OnClickListener() { // from class: com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    BaseWebAuthorizeActivity.this.proceedLoad(sslErrorHandler);
                }
            });
            alertDialogCreate.setButton(-2, this.mContext.getString(identifier2), new DialogInterface.OnClickListener() { // from class: com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    BaseWebAuthorizeActivity.this.cancelLoad(sslErrorHandler);
                }
            });
            alertDialogCreate.setCanceledOnTouchOutside(false);
            alertDialogCreate.show();
        } catch (Exception unused) {
            proceedLoad(sslErrorHandler);
        }
    }

    protected void proceedLoad(SslErrorHandler sslErrorHandler) {
        if (sslErrorHandler != null) {
            this.mHandler.sendEmptyMessageDelayed(100, 8000L);
            sslErrorHandler.proceed();
        }
    }

    protected void cancelLoad(SslErrorHandler sslErrorHandler) {
        if (sslErrorHandler != null) {
            sslErrorHandler.cancel();
        }
        showNetworkErrorDialog(this.OP_ERROR_NETWORK_ERROR);
        this.isShowNetworkError = true;
    }

    protected void showNetworkErrorDialog(final int i) {
        if (this.mBaseErrorDialog == null || !this.mBaseErrorDialog.isShowing()) {
            if (this.mBaseErrorDialog == null) {
                View viewInflate = LayoutInflater.from(this).inflate(getResources().getIdentifier("tiktok_layout_open_network_error_dialog", RES_LAYOUT, getPackageName()), (ViewGroup) null, false);
                viewInflate.findViewById(getResources().getIdentifier("tiktok_open_auth_tv_confirm", "id", getPackageName())).setOnClickListener(new View.OnClickListener() { // from class: com.bytedance.sdk.open.aweme.authorize.ui.BaseWebAuthorizeActivity.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        BaseWebAuthorizeActivity.this.onCancel(i);
                    }
                });
                this.mBaseErrorDialog = new AlertDialog.Builder(this).setCancelable(false).setView(viewInflate).create();
            }
            this.mBaseErrorDialog.show();
        }
    }
}
