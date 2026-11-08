package com.kwad.sdk.core.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.api.proxy.app.AdWebViewActivity;
import com.kwad.sdk.api.proxy.app.KsFullScreenLandScapeVideoActivity;
import com.kwad.sdk.compliance.widget.ComplianceTextView;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.imageloader.core.DisplayImageOptionsCompat;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener;
import com.kwad.sdk.core.imageloader.utils.BlurUtils;
import com.kwad.sdk.core.page.kwai.d;
import com.kwad.sdk.core.page.widget.RoundAngleImageView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.widget.DownloadProgressBar;
import com.kwad.sdk.widget.KSLinearLayout;
import com.kwad.sdk.widget.KSRelativeLayout;
import com.kwad.sdk.widget.KsLogoView;
import java.io.InputStream;
import org.json.JSONObject;

@KsAdSdkDynamicImpl(AdWebViewActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class AdWebViewLandPageActivityProxy extends com.kwad.sdk.core.e.a<com.kwad.sdk.reward.a> {
    public static final String KEY_NEED_CLOSE_REWARD = "key_close_reward";
    public static final String KEY_TEMPLATE = "key_template_json";
    private static final String TAG = "AdWebViewLandPageActivityProxy";
    public static boolean showingAdWebViewLandPage = false;
    private AdInfo mAdInfo;
    private AdTemplate mAdTemplate;
    private com.kwad.sdk.core.download.a.b mApkDownloadHelper;
    private ImageView mBackIcon;
    private ImageView mCloseIcon;
    private Context mContext;
    private DetailVideoView mDetailVideoView;
    private KSRelativeLayout mKsadVideoContainer;
    private boolean mNeedCloseReward;
    private com.kwad.sdk.reward.c.a mRewardPlayModule;
    private KSRelativeLayout mRootContainer;
    private ImageView mVideoBlurBg;
    private ImageView mVideoCover;
    private com.kwad.sdk.contentalliance.detail.video.d mVideoPlayStateListener;

    private void buildView(ComplianceTextView complianceTextView, KSLinearLayout kSLinearLayout, KsLogoView ksLogoView) {
        if (com.kwad.sdk.core.response.a.a.P(com.kwad.sdk.core.response.a.d.j(this.mAdTemplate))) {
            return;
        }
        ((FrameLayout.LayoutParams) complianceTextView.getLayoutParams()).gravity = 80;
        if (!af.e(this.mContext)) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ksLogoView.getLayoutParams();
            marginLayoutParams.setMargins(0, 0, 0, com.kwad.sdk.a.kwai.a.a(this.mContext, 100.0f));
            ksLogoView.setLayoutParams(marginLayoutParams);
        }
        ksLogoView.setVisibility(0);
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) kSLinearLayout.getLayoutParams();
        layoutParams.gravity = 17;
        kSLinearLayout.setLayoutParams(layoutParams);
    }

    private com.kwad.sdk.contentalliance.detail.video.d getVideoPlayStateListener() {
        this.mVideoPlayStateListener = new e() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.6
            @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
            public void a(int i, int i2) {
            }

            @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
            public void c() {
                AdWebViewLandPageActivityProxy.this.mVideoCover.setVisibility(8);
            }

            @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
            public void f() {
                AdWebViewLandPageActivityProxy.this.mVideoCover.setVisibility(0);
            }
        };
        return this.mVideoPlayStateListener;
    }

    @NonNull
    private d.a getWebViewStateListener() {
        return new d.a() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.3
            @Override // com.kwad.sdk.core.page.kwai.d.a
            public void a(int i) {
                if (i != 1) {
                    AdWebViewLandPageActivityProxy.this.initNativeLandPage();
                }
            }
        };
    }

    private void handleCloseBtn() {
        long jT = com.kwad.sdk.core.config.b.t() * 1000;
        if (jT == 0) {
            this.mCloseIcon.setVisibility(0);
        } else {
            this.mCloseIcon.postDelayed(new Runnable() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.7
                @Override // java.lang.Runnable
                public void run() {
                    if (AdWebViewLandPageActivityProxy.this.getActivity() == null || AdWebViewLandPageActivityProxy.this.getActivity().isFinishing()) {
                        return;
                    }
                    AdWebViewLandPageActivityProxy.this.mCloseIcon.setVisibility(0);
                    AdWebViewLandPageActivityProxy.this.mCloseIcon.setAlpha(0.0f);
                    AdWebViewLandPageActivityProxy.this.mCloseIcon.animate().alpha(1.0f).setDuration(500L).start();
                }
            }, jT);
        }
        this.mCloseIcon.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.kwad.sdk.reward.b.a().b();
                AdWebViewLandPageActivityProxy.this.finish();
            }
        });
    }

    private boolean initData() {
        String stringExtra = getIntent().getStringExtra("key_template_json");
        this.mNeedCloseReward = getIntent().getBooleanExtra(KEY_NEED_CLOSE_REWARD, false);
        try {
            AdTemplate adTemplate = new AdTemplate();
            if (stringExtra != null) {
                adTemplate.parseJson(new JSONObject(stringExtra));
            }
            this.mAdTemplate = adTemplate;
            this.mAdInfo = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
            return true;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNativeLandPage() {
        findViewById(R.id.ksad_ad_land_page_native).setVisibility(0);
        ComplianceTextView complianceTextView = (ComplianceTextView) findViewById(R.id.ksad_compliance_view);
        complianceTextView.setVisibility(0);
        complianceTextView.setAdTemplate(this.mAdTemplate);
        RoundAngleImageView roundAngleImageView = (RoundAngleImageView) findViewById(R.id.ksad_ad_cover);
        TextView textView = (TextView) findViewById(R.id.ksad_ad_title);
        TextView textView2 = (TextView) findViewById(R.id.ksad_ad_info);
        KSLinearLayout kSLinearLayout = (KSLinearLayout) findViewById(R.id.ksad_info_container);
        KsLogoView ksLogoView = (KsLogoView) findViewById(R.id.ksad_land_page_logo);
        DownloadProgressBar downloadProgressBar = (DownloadProgressBar) findViewById(R.id.ksad_web_download_progress);
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
        setAdTitle(textView, adInfoJ);
        textView2.setText(adInfoJ.adBaseInfo.adDescription);
        roundAngleImageView.setRadius(32.0f);
        if (!TextUtils.isEmpty(com.kwad.sdk.core.response.a.a.au(adInfoJ))) {
            KSImageLoader.loadImage(roundAngleImageView, com.kwad.sdk.core.response.a.a.au(adInfoJ), this.mAdTemplate);
        }
        setAdkDownload(downloadProgressBar, adInfoJ);
        buildView(complianceTextView, kSLinearLayout, ksLogoView);
    }

    private void initView() {
        this.mRootContainer = (KSRelativeLayout) findViewById(R.id.ksad_root_container);
        this.mKsadVideoContainer = (KSRelativeLayout) findViewById(R.id.ksad_video_container);
        this.mDetailVideoView = (DetailVideoView) this.mRootContainer.findViewById(R.id.ksad_video_player);
        this.mVideoBlurBg = (ImageView) this.mRootContainer.findViewById(R.id.ksad_video_blur_bg);
        this.mBackIcon = (ImageView) this.mRootContainer.findViewById(R.id.ksad_back_icon);
        this.mCloseIcon = (ImageView) this.mRootContainer.findViewById(R.id.ksad_right_close);
        this.mVideoCover = (ImageView) this.mRootContainer.findViewById(R.id.ksad_video_cover);
        if (this.mAdTemplate.adInfoList.size() > 0) {
            loadBlurImage(com.kwad.sdk.core.response.a.a.h(this.mAdTemplate.adInfoList.get(0)), this.mVideoBlurBg);
        }
        this.mBackIcon.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                com.kwad.sdk.core.report.a.f(AdWebViewLandPageActivityProxy.this.mAdTemplate, 153);
                AdWebViewLandPageActivityProxy.this.finish();
            }
        });
        if (com.kwad.sdk.core.response.a.a.P(com.kwad.sdk.core.response.a.d.j(this.mAdTemplate))) {
            setVideoData();
        }
        if (this.mNeedCloseReward) {
            handleCloseBtn();
        }
    }

    public static void launch(@NonNull Context context, @NonNull AdTemplate adTemplate) {
        launch(context, adTemplate, false);
    }

    public static void launch(@NonNull Context context, @NonNull AdTemplate adTemplate, boolean z) {
        Class cls = af.e(context) ? AdWebViewActivity.class : KsFullScreenLandScapeVideoActivity.class;
        KsAdSDKImpl.putComponentProxy(cls, AdWebViewLandPageActivityProxy.class);
        Intent intent = new Intent(context, (Class<?>) cls);
        intent.addFlags(268435456);
        intent.putExtra("key_template_json", adTemplate.toJson().toString());
        intent.putExtra(KEY_NEED_CLOSE_REWARD, z);
        context.startActivity(intent);
    }

    private void loadBlurImage(String str, ImageView imageView) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        imageView.setVisibility(0);
        KSImageLoader.loadImage(imageView, str, this.mAdTemplate, new DisplayImageOptionsCompat.Builder().setBlurRadius(50).build(), new SimpleImageLoadingListener() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.2
            @Override // com.kwad.sdk.core.imageloader.core.listener.SimpleImageLoadingListener, com.kwad.sdk.core.imageloader.core.listener.ImageLoadingListener
            public boolean onDecode(String str2, InputStream inputStream, DecodedResult decodedResult) {
                decodedResult.mBitmap = BlurUtils.stackBlur(BitmapFactory.decodeStream(inputStream), 50, false);
                return true;
            }
        });
    }

    private void setAdTitle(TextView textView, AdInfo adInfo) {
        String str;
        if (!TextUtils.isEmpty(adInfo.adBaseInfo.appName)) {
            str = adInfo.adBaseInfo.appName;
        } else if (TextUtils.isEmpty(adInfo.advertiserInfo.rawUserName)) {
            return;
        } else {
            str = adInfo.advertiserInfo.rawUserName;
        }
        textView.setText(str);
    }

    private void setAdkDownload(final DownloadProgressBar downloadProgressBar, final AdInfo adInfo) {
        if (com.kwad.sdk.core.response.a.a.B(adInfo)) {
            this.mApkDownloadHelper = new com.kwad.sdk.core.download.a.b(this.mAdTemplate, new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.4
                @Override // com.kwad.sdk.core.download.a.c
                public void a(int i) {
                    downloadProgressBar.setProgress(i);
                    downloadProgressBar.setText(com.kwad.sdk.core.response.a.a.b(i));
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFailed() {
                    downloadProgressBar.setProgress(100.0f);
                    downloadProgressBar.setText(com.kwad.sdk.core.response.a.a.A(adInfo));
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onDownloadFinished() {
                    downloadProgressBar.setProgress(100.0f);
                    downloadProgressBar.setText(com.kwad.sdk.core.response.a.a.a(AdWebViewLandPageActivityProxy.this.mAdTemplate));
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onIdle() {
                    downloadProgressBar.setProgress(100.0f);
                    downloadProgressBar.setText(com.kwad.sdk.core.response.a.a.A(adInfo));
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onInstalled() {
                    downloadProgressBar.setProgress(100.0f);
                    downloadProgressBar.setText(com.kwad.sdk.core.response.a.a.m(adInfo));
                }

                @Override // com.kwad.sdk.api.KsAppDownloadListener
                public void onProgressUpdate(int i) {
                    downloadProgressBar.setProgress(i);
                    downloadProgressBar.setText(com.kwad.sdk.core.response.a.a.a(i));
                }
            });
            downloadProgressBar.setOnClickListener(new View.OnClickListener() { // from class: com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    com.kwad.sdk.core.report.a.f(AdWebViewLandPageActivityProxy.this.mAdTemplate, 152);
                    AdWebViewLandPageActivityProxy.this.mApkDownloadHelper.a(AdWebViewLandPageActivityProxy.this.mContext, true);
                }
            });
        }
    }

    private void setVideoData() {
        ViewGroup.LayoutParams layoutParams = this.mKsadVideoContainer.getLayoutParams();
        if (af.e(this.mContext)) {
            layoutParams.height = (com.kwad.sdk.a.kwai.a.c(this.mContext) * 9) / 16;
            layoutParams.width = -1;
            this.mDetailVideoView.a(true);
        } else {
            layoutParams.width = com.kwad.sdk.a.kwai.a.c(this.mContext) / 2;
            layoutParams.height = -1;
            this.mDetailVideoView.setHorizontalVideo(true);
        }
        this.mKsadVideoContainer.setLayoutParams(layoutParams);
        this.mKsadVideoContainer.setVisibility(0);
        KSImageLoader.loadImage(this.mVideoCover, com.kwad.sdk.core.response.a.a.aa(this.mAdInfo).a(), this.mAdTemplate);
    }

    @Override // com.kwad.sdk.h.a
    protected String getPageName() {
        return TAG;
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = Wrapper.wrapContextIfNeed(getActivity());
        showingAdWebViewLandPage = true;
        setContentView(af.e(this.mContext) ? R.layout.ksad_activity_ad_land_page : R.layout.ksad_activity_land_page_horizontal);
        if (!initData()) {
            finish();
        } else {
            initView();
            onActivityCreated(this.mRootContainer);
        }
    }

    @Override // com.kwad.sdk.core.e.a
    protected com.kwad.sdk.reward.a onCreateCallerContext() {
        com.kwad.sdk.reward.a aVar = new com.kwad.sdk.reward.a();
        aVar.h = getActivity();
        aVar.i = this.mContext;
        aVar.g = this.mAdTemplate;
        aVar.r = false;
        if (com.kwad.sdk.core.response.a.a.P(com.kwad.sdk.core.response.a.d.j(this.mAdTemplate))) {
            com.kwad.sdk.utils.b.a(this.mContext).a(true);
            this.mRewardPlayModule = new com.kwad.sdk.reward.c.a(this.mAdTemplate, this.mDetailVideoView, new KsVideoPlayConfig.Builder().videoSoundEnable(true).build());
            aVar.k = this.mRewardPlayModule;
            this.mRewardPlayModule.a(getVideoPlayStateListener());
            aVar.a.add(this.mRewardPlayModule);
        }
        return aVar;
    }

    @Override // com.kwad.sdk.core.e.a
    protected Presenter onCreatePresenter() {
        Presenter presenter = new Presenter();
        if (com.kwad.sdk.core.response.a.a.P(com.kwad.sdk.core.response.a.d.j(this.mAdTemplate))) {
            presenter.a((Presenter) new com.kwad.sdk.core.page.kwai.c());
        }
        presenter.a((Presenter) new com.kwad.sdk.core.page.kwai.d(getWebViewStateListener()));
        return presenter;
    }

    @Override // com.kwad.sdk.core.e.a, com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onDestroy() {
        super.onDestroy();
        showingAdWebViewLandPage = false;
        if (this.mApkDownloadHelper != null) {
            this.mApkDownloadHelper.i();
        }
        if (this.mRewardPlayModule != null) {
            this.mRewardPlayModule.e();
        }
    }
}
