package com.ss.android.downloadlib.addownload.compliance;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import com.sigmob.sdk.common.Constants;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.android.downloadlib.addownload.GlobalInfo;
import com.ss.android.downloadlib.addownload.model.AppInfoX;
import com.ss.android.downloadlib.constants.DownloadSettingKeys;
import com.ss.android.downloadlib.constants.EventConstants;
import com.ss.android.socialbase.appdownloader.AppDownloadUtils;
import com.ss.union.game.sdk.core.base.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppPrivacyPolicyActivity extends Activity {
    private long cid;
    private long infoId;
    private ImageView ivBack;
    private String privacyPolicyUrl;
    private WebView webView;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.ttdownloader_activity_app_privacy_policy);
        if (initData()) {
            initView();
        } else {
            AppDownloadUtils.safeFinish(this);
        }
    }

    public static void start(Activity activity, long j) {
        Intent intent = new Intent(activity, (Class<?>) AppPrivacyPolicyActivity.class);
        intent.putExtra(TTDelegateActivity.APP_INFO_ID, j);
        activity.startActivity(intent);
    }

    private boolean initData() {
        this.infoId = getIntent().getLongExtra(TTDelegateActivity.APP_INFO_ID, 0L);
        AppInfoX appInfoX = AppInfoCache.getInstance().getAppInfoX(this.infoId);
        if (appInfoX == null) {
            return false;
        }
        this.cid = appInfoX.cid;
        this.privacyPolicyUrl = appInfoX.privacyPolicyUrl;
        if (!TextUtils.isEmpty(this.privacyPolicyUrl)) {
            return true;
        }
        this.privacyPolicyUrl = GlobalInfo.getDownloadSettings().optString(DownloadSettingKeys.AD_PRIVACY_BACKUP_URL, "https://sf6-ttcdn-tos.pstatp.com/obj/ad-tetris-site/personal-privacy-page.html");
        return true;
    }

    private void initView() {
        this.ivBack = (ImageView) findViewById(R.id.iv_privacy_back);
        this.webView = (WebView) findViewById(R.id.privacy_webview);
        this.ivBack.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppPrivacyPolicyActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_PRIVACY_CLICK_CLOSE, AppPrivacyPolicyActivity.this.cid);
                AppPrivacyPolicyActivity.this.finish();
            }
        });
        WebSettings settings = this.webView.getSettings();
        settings.setDefaultFontSize(16);
        settings.setCacheMode(-1);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(0);
        }
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setSavePassword(false);
        settings.setAllowFileAccess(false);
        this.webView.setWebViewClient(new WebViewClient() { // from class: com.ss.android.downloadlib.addownload.compliance.AppPrivacyPolicyActivity.2
            @Override // android.webkit.WebViewClient
            @TargetApi(21)
            public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
                return shouldOverrideUrlLoading(webResourceRequest.getUrl());
            }

            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView webView, String str) {
                return shouldOverrideUrlLoading(Uri.parse(str));
            }

            private boolean shouldOverrideUrlLoading(Uri uri) {
                String scheme = uri.getScheme();
                return (Constants.HTTP.equals(scheme) || Constants.HTTPS.equals(scheme)) ? false : true;
            }
        });
        removeJavascriptInterfacesSafe(this.webView);
        this.webView.setScrollBarStyle(0);
        this.webView.loadUrl(this.privacyPolicyUrl);
    }

    private void removeJavascriptInterfacesSafe(WebView webView) {
        try {
            webView.removeJavascriptInterface("searchBoxJavaBridge_");
            webView.removeJavascriptInterface("accessibility");
            webView.removeJavascriptInterface("accessibilityTraversal");
        } catch (Throwable unused) {
        }
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        EventSender.sendDialogClickEvent(EventConstants.Refer.LP_APP_PRIVACY_CLICK_CLOSE, this.cid);
        super.onBackPressed();
    }
}
