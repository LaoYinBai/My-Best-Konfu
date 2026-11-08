package com.kwad.sdk.fullscreen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.kwad.sdk.KsAdSDKImpl;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsFullScreenVideoAd;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.api.proxy.app.FeedDownloadActivity;
import com.kwad.sdk.api.proxy.app.KsFullScreenLandScapeVideoActivity;
import com.kwad.sdk.api.proxy.app.KsFullScreenVideoActivity;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.reward.a.c;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl;
import com.kwad.sdk.utils.m;
import com.kwad.sdk.utils.t;
import java.io.File;
import java.io.Serializable;
import org.json.JSONObject;

@KsAdSdkDynamicImpl(KsFullScreenVideoActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsFullScreenVideoActivityProxy extends com.kwad.sdk.core.e.a<com.kwad.sdk.reward.a> {
    public static final String KEY_TEMPLATE = "key_template_json";
    public static final String KEY_VIDEO_PLAY_CONFIG = "key_video_play_config";
    private static final String TAG = "FullScreenVideo";
    public static KsFullScreenVideoAd.FullScreenVideoAdInteractionListener mInteractionListener;
    private AdInfo mAdInfo;
    private AdTemplate mAdTemplate;
    private Context mContext;
    private DetailVideoView mDetailVideoView;
    private boolean mIsBackEnable;
    private boolean mPageDismissCalled;
    private JSONObject mReportExtData;
    private AdBaseFrameLayout mRootContainer;
    public int mScreenOrientation;
    private KsVideoPlayConfig mVideoPlayConfig;
    private f mPlayEndPageListener = new f() { // from class: com.kwad.sdk.fullscreen.KsFullScreenVideoActivityProxy.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            KsFullScreenVideoActivityProxy.this.mIsBackEnable = true;
        }
    };
    private com.kwad.sdk.reward.a.b mAdOpenInteractionListener = new c() { // from class: com.kwad.sdk.fullscreen.KsFullScreenVideoActivityProxy.2
        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a() {
            if (KsFullScreenVideoActivityProxy.mInteractionListener != null) {
                KsFullScreenVideoActivityProxy.mInteractionListener.onAdClicked();
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a(int i, int i2) {
            if (KsFullScreenVideoActivityProxy.mInteractionListener != null) {
                KsFullScreenVideoActivityProxy.mInteractionListener.onVideoPlayError(i, i2);
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a(boolean z) {
            KsFullScreenVideoActivityProxy.this.notifyPageDismiss();
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void b() {
            if (KsFullScreenVideoActivityProxy.mInteractionListener != null) {
                KsFullScreenVideoActivityProxy.mInteractionListener.onSkippedVideo();
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void c() {
            if (KsFullScreenVideoActivityProxy.mInteractionListener != null) {
                KsFullScreenVideoActivityProxy.mInteractionListener.onVideoPlayStart();
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void d() {
            if (KsFullScreenVideoActivityProxy.mInteractionListener != null) {
                KsFullScreenVideoActivityProxy.mInteractionListener.onVideoPlayEnd();
            }
        }
    };

    private boolean initData() {
        File fileB;
        Serializable serializableExtra = getIntent().getSerializableExtra("key_video_play_config");
        if (!(serializableExtra instanceof KsVideoPlayConfig)) {
            com.kwad.sdk.core.d.a.e(TAG, "data is not instanceof VideoPlayConfigImpl:" + serializableExtra);
            return false;
        }
        String stringExtra = getIntent().getStringExtra("key_template_json");
        try {
            AdTemplate adTemplate = new AdTemplate();
            adTemplate.parseJson(new JSONObject(stringExtra));
            this.mAdTemplate = adTemplate;
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.a(th);
        }
        if (this.mAdTemplate == null) {
            com.kwad.sdk.core.d.a.e(TAG, "data is null:" + stringExtra);
            return false;
        }
        this.mAdInfo = d.j(this.mAdTemplate);
        String strA = com.kwad.sdk.core.response.a.a.a(this.mAdInfo);
        if (com.kwad.sdk.core.config.b.C() < 0 && ((fileB = com.kwad.sdk.core.diskcache.a.a.a().b(strA)) == null || !fileB.exists())) {
            return false;
        }
        this.mVideoPlayConfig = (KsVideoPlayConfig) serializableExtra;
        this.mScreenOrientation = this.mVideoPlayConfig.isShowLandscape() ? 1 : 0;
        this.mAdTemplate.mInitVoiceStatus = this.mVideoPlayConfig.isVideoSoundEnable() ? 2 : 1;
        initVideoPlayConfig(this.mVideoPlayConfig);
        return true;
    }

    private void initVideoPlayConfig(@NonNull KsVideoPlayConfig ksVideoPlayConfig) {
        getActivity().setRequestedOrientation(!ksVideoPlayConfig.isShowLandscape() ? 1 : 0);
        if (TextUtils.isEmpty(ksVideoPlayConfig.getShowScene())) {
            return;
        }
        this.mReportExtData = null;
        this.mReportExtData = new JSONObject();
        t.a(this.mReportExtData, "ext_showscene", ksVideoPlayConfig.getShowScene());
    }

    private void initView() {
        this.mRootContainer = (AdBaseFrameLayout) findViewById(R.id.ksad_root_container);
        this.mDetailVideoView = (DetailVideoView) this.mRootContainer.findViewById(R.id.ksad_video_player);
        this.mDetailVideoView.setAd(true);
    }

    public static void launch(Context context, @NonNull AdTemplate adTemplate, @NonNull KsVideoPlayConfig ksVideoPlayConfig, KsFullScreenVideoAd.FullScreenVideoAdInteractionListener fullScreenVideoAdInteractionListener) {
        Class cls;
        Class cls2;
        m.b(adTemplate);
        if (ksVideoPlayConfig.isShowLandscape()) {
            cls = KsFullScreenLandScapeVideoActivity.class;
            cls2 = a.class;
        } else {
            cls = FeedDownloadActivity.class;
            cls2 = KsFullScreenVideoActivityProxy.class;
        }
        KsAdSDKImpl.putComponentProxy(cls, cls2);
        Intent intent = new Intent(context, (Class<?>) (ksVideoPlayConfig.isShowLandscape() ? KsFullScreenLandScapeVideoActivity.class : FeedDownloadActivity.class));
        intent.setFlags(268435456);
        intent.putExtra("key_template_json", adTemplate.toJson().toString());
        intent.putExtra("key_video_play_config", ksVideoPlayConfig);
        mInteractionListener = fullScreenVideoAdInteractionListener;
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPageDismiss() {
        if (this.mPageDismissCalled) {
            return;
        }
        this.mPageDismissCalled = true;
        com.kwad.sdk.core.report.a.a(this.mAdTemplate, 6, this.mReportExtData);
        if (mInteractionListener != null) {
            mInteractionListener.onPageDismiss();
        }
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void finish() {
        notifyPageDismiss();
        getActivity().finish();
    }

    @Override // com.kwad.sdk.h.a
    protected String getPageName() {
        return "KsFullScreenVideoActivityProxy";
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onBackPressed() {
        if (this.mIsBackEnable) {
            super.onBackPressed();
        }
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (!initData()) {
            finish();
            return;
        }
        this.mContext = Wrapper.wrapContextIfNeed(getActivity());
        setContentView(R.layout.ksad_activity_fullscreen_video);
        initView();
        onActivityCreated(this.mRootContainer);
    }

    @Override // com.kwad.sdk.core.e.a
    protected com.kwad.sdk.reward.a onCreateCallerContext() {
        com.kwad.sdk.reward.a aVar = new com.kwad.sdk.reward.a();
        aVar.h = getActivity();
        aVar.i = this.mContext;
        aVar.b = this.mAdOpenInteractionListener;
        aVar.f = this.mScreenOrientation;
        aVar.d = this.mVideoPlayConfig;
        aVar.e = this.mReportExtData;
        aVar.j = this.mRootContainer;
        aVar.g = this.mAdTemplate;
        com.kwad.sdk.reward.c.a aVar2 = new com.kwad.sdk.reward.c.a(this.mAdTemplate, this.mDetailVideoView, this.mVideoPlayConfig);
        aVar.k = aVar2;
        aVar.a.add(aVar2);
        if (com.kwad.sdk.core.response.a.a.B(this.mAdInfo)) {
            aVar.l = new com.kwad.sdk.core.download.a.b(this.mAdTemplate, this.mReportExtData);
        }
        aVar.n = new RewardActionBarControl(this.mContext, this.mAdTemplate);
        aVar.a(this.mPlayEndPageListener);
        if (com.kwad.sdk.core.response.a.b.h(this.mAdTemplate)) {
            aVar.o = new com.kwad.sdk.i.b(this.mReportExtData);
        }
        if (d.s(this.mAdTemplate)) {
            aVar.p = new com.kwad.sdk.i.a().a(false);
        }
        aVar.r = false;
        return aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x00ae  */
    @Override // com.kwad.sdk.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.kwad.sdk.mvp.Presenter onCreatePresenter() {
        /*
            r4 = this;
            com.kwad.sdk.mvp.Presenter r0 = new com.kwad.sdk.mvp.Presenter
            r0.<init>()
            com.kwad.sdk.reward.presenter.e r1 = new com.kwad.sdk.reward.presenter.e
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.core.response.model.AdInfo r1 = r4.mAdInfo
            boolean r1 = com.kwad.sdk.core.response.a.a.B(r1)
            if (r1 == 0) goto L1d
            com.kwad.sdk.reward.presenter.platdetail.b r1 = new com.kwad.sdk.reward.presenter.platdetail.b
            r1.<init>()
            r0.a(r1)
        L1d:
            java.lang.Class<com.kwad.sdk.plugin.g> r1 = com.kwad.sdk.plugin.g.class
            com.kwad.sdk.plugin.d r1 = com.kwad.sdk.plugin.f.a(r1)
            com.kwad.sdk.plugin.g r1 = (com.kwad.sdk.plugin.g) r1
            com.kwad.sdk.core.response.model.AdInfo r2 = r4.mAdInfo
            boolean r2 = com.kwad.sdk.core.response.a.a.aG(r2)
            if (r2 != 0) goto L67
            if (r1 == 0) goto L67
            boolean r1 = r1.b()
            if (r1 == 0) goto L67
            T extends com.kwad.sdk.reward.a r1 = r4.mCallerContext
            r2 = 1
            r1.t = r2
            com.kwad.sdk.reward.presenter.b.b r1 = new com.kwad.sdk.reward.presenter.b.b
            r1.<init>()
            r0.a(r1)
            android.content.Context r1 = r4.mContext
            com.kwad.sdk.core.response.model.AdTemplate r2 = r4.mAdTemplate
            boolean r1 = com.kwad.sdk.core.response.a.b.a(r1, r2)
            if (r1 == 0) goto L55
            com.kwad.sdk.reward.presenter.b.d r1 = new com.kwad.sdk.reward.presenter.b.d
            r1.<init>()
        L51:
            r0.a(r1)
            goto L61
        L55:
            com.kwad.sdk.reward.presenter.b.c r1 = new com.kwad.sdk.reward.presenter.b.c
            com.kwad.sdk.core.response.model.AdTemplate r2 = r4.mAdTemplate
            boolean r2 = com.kwad.sdk.core.response.a.b.d(r2)
            r1.<init>(r2)
            goto L51
        L61:
            com.kwad.sdk.fullscreen.kwai.a r1 = new com.kwad.sdk.fullscreen.kwai.a
            r1.<init>()
            goto L8c
        L67:
            com.kwad.sdk.fullscreen.kwai.a r1 = new com.kwad.sdk.fullscreen.kwai.a
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.fullscreen.kwai.kwai.c r1 = new com.kwad.sdk.fullscreen.kwai.kwai.c
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.fullscreen.kwai.a.a r1 = new com.kwad.sdk.fullscreen.kwai.a.a
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.core.response.model.AdInfo r1 = r4.mAdInfo
            boolean r1 = com.kwad.sdk.core.response.a.a.T(r1)
            if (r1 == 0) goto L90
            com.kwad.sdk.reward.presenter.platdetail.a r1 = new com.kwad.sdk.reward.presenter.platdetail.a
            r1.<init>()
        L8c:
            r0.a(r1)
            goto La6
        L90:
            com.kwad.sdk.reward.presenter.platdetail.actionbar.a r1 = new com.kwad.sdk.reward.presenter.platdetail.actionbar.a
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.core.response.model.AdTemplate r1 = r4.mAdTemplate
            boolean r1 = com.kwad.sdk.core.response.a.b.d(r1)
            if (r1 == 0) goto La6
            com.kwad.sdk.reward.presenter.platdetail.actionbar.b r1 = new com.kwad.sdk.reward.presenter.platdetail.actionbar.b
            r1.<init>()
            goto L8c
        La6:
            com.kwad.sdk.core.response.model.AdInfo r1 = r4.mAdInfo
            boolean r1 = com.kwad.sdk.core.response.a.a.D(r1)
            if (r1 == 0) goto Lb6
            com.kwad.sdk.reward.presenter.b r1 = new com.kwad.sdk.reward.presenter.b
            r1.<init>()
            r0.a(r1)
        Lb6:
            com.kwad.sdk.reward.presenter.a.a r1 = new com.kwad.sdk.reward.presenter.a.a
            com.kwad.sdk.core.response.model.AdTemplate r2 = r4.mAdTemplate
            r3 = 0
            r1.<init>(r4, r2, r3)
            r0.a(r1)
            com.kwad.sdk.reward.presenter.kwai.a r1 = new com.kwad.sdk.reward.presenter.kwai.a
            r1.<init>()
            r0.a(r1)
            com.kwad.sdk.reward.presenter.c r1 = new com.kwad.sdk.reward.presenter.c
            com.kwad.sdk.core.response.model.AdInfo r2 = r4.mAdInfo
            com.kwad.sdk.core.view.AdBaseFrameLayout r3 = r4.mRootContainer
            r1.<init>(r2, r3)
            r0.a(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.fullscreen.KsFullScreenVideoActivityProxy.onCreatePresenter():com.kwad.sdk.mvp.Presenter");
    }

    @Override // com.kwad.sdk.core.e.a, com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onDestroy() {
        super.onDestroy();
        notifyPageDismiss();
        if (this.mAdInfo != null) {
            com.kwad.sdk.core.videocache.b.a.a(this.mContext.getApplicationContext()).c(com.kwad.sdk.core.response.a.a.a(this.mAdInfo));
        }
        mInteractionListener = null;
    }
}
