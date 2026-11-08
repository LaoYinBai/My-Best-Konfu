package com.kwad.sdk.core.page;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsRewardVideoAd;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.core.ResContext;
import com.kwad.sdk.api.proxy.app.AdWebViewActivity;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.report.p;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.reward.j;
import org.json.JSONObject;

@KsAdSdkDynamicImpl(AdWebViewActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdRewardPreviewActivityProxy extends com.kwad.sdk.h.a {
    public static final String KEY_TEMPLATE = "key_template_json";
    private static final String KEY_URL = "key_langingpage_url";
    private static final String TAG = "AdRewardPreviewActivityProxy";
    public static KsRewardVideoAd.RewardAdInteractionListener mInteractionListener;
    private AdTemplate mAdTemplate;
    private KsAdWebView mAdWebView;
    private int mCount;
    private TextView mCountDownTips;
    private long mLastDown;
    private String mUrl;
    private ImageView mWebCloseBtn;
    private AdBaseFrameLayout mWebContainer;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private boolean mEnableSkip = false;
    private boolean counterPaused = false;

    static /* synthetic */ int access$410(AdRewardPreviewActivityProxy adRewardPreviewActivityProxy) {
        int i = adRewardPreviewActivityProxy.mCount;
        adRewardPreviewActivityProxy.mCount = i - 1;
        return i;
    }

    private void handlerLandingPageAtEndCard(KsAdWebView ksAdWebView) {
        p.a aVar = new p.a();
        aVar.n = 0;
        ksAdWebView.setClientParams(aVar);
    }

    @SuppressLint({"SetTextI18n"})
    private void initView() {
        KsAdWebView ksAdWebView;
        String strG;
        this.mAdWebView = (KsAdWebView) findViewById(R.id.ksad_video_webview);
        handlerLandingPageAtEndCard(this.mAdWebView);
        this.mAdWebView.setTemplateData(this.mAdTemplate);
        this.mAdWebView.setDeepLinkEnabled(false);
        this.mAdWebView.b();
        this.mWebCloseBtn = (ImageView) findViewById(R.id.ksad_web_close_btn);
        this.mWebContainer = (AdBaseFrameLayout) findViewById(R.id.ksad_landing_page_root);
        this.mWebCloseBtn.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.AdRewardPreviewActivityProxy.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.kwad.sdk.core.report.a.a(AdRewardPreviewActivityProxy.this.mAdTemplate, 1, (JSONObject) null);
                AdRewardPreviewActivityProxy.this.finish();
            }
        });
        this.mCountDownTips = (TextView) findViewById(R.id.ksad_reward_preview_skip_time);
        this.mCountDownTips.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.AdRewardPreviewActivityProxy.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AdRewardPreviewActivityProxy.this.showCloseDialog();
            }
        });
        this.mCountDownTips.setText("激励领取视频还有" + this.mCount + "秒");
        this.mHandler.postDelayed(new Runnable() { // from class: com.kwad.sdk.core.page.AdRewardPreviewActivityProxy.3
            @Override // java.lang.Runnable
            @SuppressLint({"SetTextI18n"})
            public void run() {
                if (AdRewardPreviewActivityProxy.this.counterPaused) {
                    AdRewardPreviewActivityProxy.this.mHandler.postDelayed(this, 400L);
                    return;
                }
                if (AdRewardPreviewActivityProxy.this.mCount <= 0) {
                    AdRewardPreviewActivityProxy.this.mEnableSkip = true;
                    AdRewardPreviewActivityProxy.this.skipToEnd();
                } else {
                    AdRewardPreviewActivityProxy.this.mCountDownTips.setText("激励领取视频还有" + AdRewardPreviewActivityProxy.this.mCount + "秒");
                    AdRewardPreviewActivityProxy.this.mHandler.postDelayed(this, 1000L);
                }
                AdRewardPreviewActivityProxy.access$410(AdRewardPreviewActivityProxy.this);
            }
        }, 1000L);
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
        if (this.mUrl != null) {
            com.kwad.sdk.core.download.a.b bVar = new com.kwad.sdk.core.download.a.b(this.mAdTemplate);
            if (com.kwad.sdk.core.response.a.a.B(adInfoJ) && com.kwad.sdk.core.config.b.Z() && com.ksad.download.c.b.b(getActivity())) {
                bVar.a((Context) getActivity(), false);
            }
            ksAdWebView = this.mAdWebView;
            strG = this.mUrl;
        } else {
            ksAdWebView = this.mAdWebView;
            strG = com.kwad.sdk.core.response.a.a.G(com.kwad.sdk.core.response.a.d.j(this.mAdTemplate));
        }
        ksAdWebView.loadUrl(strG);
        this.mAdWebView.setHttpErrorListener(new KsAdWebView.d() { // from class: com.kwad.sdk.core.page.AdRewardPreviewActivityProxy.4
            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a() {
                com.kwad.sdk.core.report.a.a(AdRewardPreviewActivityProxy.this.mAdTemplate, (JSONObject) null);
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void a(int i, String str, String str2) {
            }

            @Override // com.kwad.sdk.core.page.widget.webview.KsAdWebView.d
            public void b() {
            }
        });
        this.mWebContainer.setDispatchTouchListener(new View.OnTouchListener() { // from class: com.kwad.sdk.core.page.AdRewardPreviewActivityProxy.5
            @Override // android.view.View.OnTouchListener
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getX() > AdRewardPreviewActivityProxy.this.mWebCloseBtn.getX() && motionEvent.getX() - AdRewardPreviewActivityProxy.this.mWebCloseBtn.getX() < AdRewardPreviewActivityProxy.this.mWebCloseBtn.getWidth() && motionEvent.getY() > AdRewardPreviewActivityProxy.this.mWebCloseBtn.getY() && motionEvent.getY() - AdRewardPreviewActivityProxy.this.mWebCloseBtn.getY() < AdRewardPreviewActivityProxy.this.mWebCloseBtn.getHeight()) {
                    com.kwad.sdk.core.d.a.a(AdRewardPreviewActivityProxy.TAG, "onClick backIcon");
                    return false;
                }
                if (motionEvent.getAction() == 0) {
                    AdRewardPreviewActivityProxy.this.mLastDown = SystemClock.elapsedRealtime();
                } else if (motionEvent.getAction() == 1) {
                    long jElapsedRealtime = SystemClock.elapsedRealtime() - AdRewardPreviewActivityProxy.this.mLastDown;
                    if (AdRewardPreviewActivityProxy.this.mLastDown > 0 && jElapsedRealtime > 60 && jElapsedRealtime < 500) {
                        com.kwad.sdk.core.report.a.a(AdRewardPreviewActivityProxy.this.mAdTemplate, 72, AdRewardPreviewActivityProxy.this.mWebContainer.getTouchCoords());
                        if (AdRewardPreviewActivityProxy.mInteractionListener != null) {
                            AdRewardPreviewActivityProxy.mInteractionListener.onAdClicked();
                        }
                    }
                    AdRewardPreviewActivityProxy.this.mLastDown = 0L;
                }
                return false;
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void launch(Context context, AdTemplate adTemplate, String str, KsRewardVideoAd.RewardAdInteractionListener rewardAdInteractionListener) {
        if (context == 0 || adTemplate == null) {
            return;
        }
        mInteractionListener = rewardAdInteractionListener;
        KsAdSDKImpl.putComponentProxy(AdWebViewActivity.class, AdRewardPreviewActivityProxy.class);
        Intent intent = new Intent(context, (Class<?>) AdWebViewActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("key_template_json", adTemplate.toJson().toString());
        intent.putExtra(KEY_URL, str);
        context.startActivity(intent);
        boolean z = context instanceof ResContext;
        Object delegatedContext = context;
        if (z) {
            delegatedContext = ((ResContext) context).getDelegatedContext();
        }
        if (delegatedContext instanceof Activity) {
            ((Activity) delegatedContext).overridePendingTransition(0, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCloseDialog() {
        j.a(getActivity(), this.mAdTemplate, j.a(this.mAdTemplate, "还差" + (this.mCount + 1) + "秒就可以获取奖励", (com.kwad.sdk.reward.b.a.a) null, (com.kwad.sdk.reward.b.kwai.a) null), new j.c() { // from class: com.kwad.sdk.core.page.AdRewardPreviewActivityProxy.6
            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void a() {
                AdRewardPreviewActivityProxy.this.counterPaused = true;
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void b() {
                AdRewardPreviewActivityProxy.this.counterPaused = false;
                AdRewardPreviewActivityProxy.this.finish();
            }

            @Override // com.kwad.sdk.reward.j.c, com.kwad.sdk.reward.j.b
            public void c() {
                AdRewardPreviewActivityProxy.this.counterPaused = false;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void skipToEnd() {
        this.mCountDownTips.setVisibility(8);
        this.mWebCloseBtn.setVisibility(0);
        if (mInteractionListener != null) {
            mInteractionListener.onRewardVerify();
        }
    }

    @Override // com.kwad.sdk.h.a
    protected String getPageName() {
        return TAG;
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onBackPressed() {
        if (this.mAdWebView != null && this.mAdWebView.canGoBack()) {
            this.mAdWebView.goBack();
            com.kwad.sdk.core.report.a.l(this.mAdTemplate);
        } else if (!this.mEnableSkip) {
            showCloseDialog();
        } else {
            super.onBackPressed();
            com.kwad.sdk.core.report.a.a(this.mAdTemplate, 11, (JSONObject) null);
        }
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(Bundle bundle) {
        setContentView(R.layout.ksad_activity_reward_preview);
        String stringExtra = getIntent().getStringExtra("key_template_json");
        this.mUrl = getIntent().getStringExtra(KEY_URL);
        try {
            AdTemplate adTemplate = new AdTemplate();
            adTemplate.parseJson(new JSONObject(stringExtra));
            this.mAdTemplate = adTemplate;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
        if (this.mAdTemplate == null) {
            finish();
        } else {
            this.mCount = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate).adStyleInfo.adBrowseInfo.adBrowseDuration;
            initView();
        }
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    protected void onDestroy() {
        if (mInteractionListener != null) {
            mInteractionListener.onPageDismiss();
        }
        mInteractionListener = null;
        if (this.mAdWebView != null) {
            this.mAdWebView.c();
            this.mAdWebView = null;
        }
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onPause() {
        overridePendingTransition(0, 0);
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onResume() {
        super.onResume();
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onStop() {
        super.onStop();
    }
}
