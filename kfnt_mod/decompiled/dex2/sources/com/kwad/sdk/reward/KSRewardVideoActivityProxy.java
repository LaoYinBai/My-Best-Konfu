package com.kwad.sdk.reward;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsRewardVideoAd;
import com.kwad.sdk.api.KsVideoPlayConfig;
import com.kwad.sdk.api.core.KsAdSdkDynamicImpl;
import com.kwad.sdk.api.loader.Wrapper;
import com.kwad.sdk.api.proxy.app.KSRewardLandScapeVideoActivity;
import com.kwad.sdk.api.proxy.app.KsRewardVideoActivity;
import com.kwad.sdk.contentalliance.detail.video.DetailVideoView;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.reward.b;
import com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.t;
import java.io.File;
import java.io.Serializable;
import org.json.JSONException;
import org.json.JSONObject;

@KsAdSdkDynamicImpl(KsRewardVideoActivity.class)
@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KSRewardVideoActivityProxy extends com.kwad.sdk.core.e.a<a> implements b.InterfaceC0068b {
    public static final String KEY_REWARD_TYPE = "key_template_reward_type";
    public static final String KEY_TEMPLATE = "key_template_json";
    public static final String KEY_VIDEO_PLAY_CONFIG = "key_video_play_config";
    private static final String TAG = "RewardVideo";
    public static KsRewardVideoAd.RewardAdInteractionListener mInteractionListener;
    private AdInfo mAdInfo;
    private AdTemplate mAdTemplate;
    private Context mContext;
    private DetailVideoView mDetailVideoView;
    private boolean mIsBackEnable;
    private boolean mPageDismissCalled;
    private JSONObject mReportExtData;
    private boolean mRewardTopBarNewStyle;
    private AdBaseFrameLayout mRootContainer;
    public int mScreenOrientation;
    private KsVideoPlayConfig mVideoPlayConfig;
    private com.kwad.sdk.reward.presenter.j rewardRefluxPresenter;
    private int rewardType = 1;
    private final com.kwad.sdk.reward.a.h mRewardVerifyListener = new com.kwad.sdk.reward.a.h() { // from class: com.kwad.sdk.reward.KSRewardVideoActivityProxy.1
        @Override // com.kwad.sdk.reward.a.h
        public void a() {
            if (KSRewardVideoActivityProxy.this.isRewardLandPageOpenTask()) {
                KSRewardVideoActivityProxy.this.markOpenNsCompleted();
                KSRewardVideoActivityProxy.this.notifyRewardVerify();
                KSRewardVideoActivityProxy.this.notifyRewardVerifyStepByStep();
            }
        }
    };
    private com.kwad.sdk.reward.a.f mPlayEndPageListener = new com.kwad.sdk.reward.a.f() { // from class: com.kwad.sdk.reward.KSRewardVideoActivityProxy.3
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            KSRewardVideoActivityProxy.this.mIsBackEnable = true;
        }
    };
    private com.kwad.sdk.reward.a.d mAdRewardStepListener = new com.kwad.sdk.reward.a.d() { // from class: com.kwad.sdk.reward.KSRewardVideoActivityProxy.4
        @Override // com.kwad.sdk.reward.a.d
        public void a() {
            KSRewardVideoActivityProxy.this.notifyRewardVerifyStepByStep();
        }
    };
    private com.kwad.sdk.reward.a.b mAdOpenInteractionListener = new com.kwad.sdk.reward.a.c() { // from class: com.kwad.sdk.reward.KSRewardVideoActivityProxy.5
        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a() {
            if (KSRewardVideoActivityProxy.mInteractionListener != null) {
                KSRewardVideoActivityProxy.mInteractionListener.onAdClicked();
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a(int i, int i2) {
            if (KSRewardVideoActivityProxy.mInteractionListener != null) {
                KSRewardVideoActivityProxy.mInteractionListener.onVideoPlayError(i, i2);
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a(long j) {
            try {
                if (KSRewardVideoActivityProxy.mInteractionListener != null) {
                    KSRewardVideoActivityProxy.mInteractionListener.onVideoSkipToEnd(j);
                }
            } catch (Throwable unused) {
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void a(boolean z) {
            KSRewardVideoActivityProxy.this.notifyPageDismiss(z);
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void c() {
            if (KSRewardVideoActivityProxy.mInteractionListener != null) {
                KSRewardVideoActivityProxy.mInteractionListener.onVideoPlayStart();
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void d() {
            if (KSRewardVideoActivityProxy.mInteractionListener != null) {
                KSRewardVideoActivityProxy.mInteractionListener.onVideoPlayEnd();
            }
        }

        @Override // com.kwad.sdk.reward.a.c, com.kwad.sdk.reward.a.b
        public void e() {
            KSRewardVideoActivityProxy.this.notifyRewardVerify();
            KSRewardVideoActivityProxy.this.notifyRewardVerifyStepByStep();
        }
    };

    private void handleNotifyVerify() {
        this.mCallerContext.v = true;
        this.mAdTemplate.mRewardVerifyCalled = true;
        h.a().a(this.mAdTemplate);
        if (mInteractionListener != null) {
            mInteractionListener.onRewardVerify();
        }
    }

    private boolean initData() {
        File fileB;
        Serializable serializableExtra = getIntent().getSerializableExtra("key_video_play_config");
        if (!(serializableExtra instanceof KsVideoPlayConfig)) {
            com.kwad.sdk.core.d.a.e(TAG, "data is not instanceof VideoPlayConfigImpl:" + serializableExtra);
            return false;
        }
        this.rewardType = getIntent().getIntExtra(KEY_REWARD_TYPE, 1);
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
        this.mAdInfo = com.kwad.sdk.core.response.a.d.j(this.mAdTemplate);
        String strA = com.kwad.sdk.core.response.a.a.a(this.mAdInfo);
        if (com.kwad.sdk.core.config.b.C() < 0 && ((fileB = com.kwad.sdk.core.diskcache.a.a.a().b(strA)) == null || !fileB.exists())) {
            return false;
        }
        this.mVideoPlayConfig = (KsVideoPlayConfig) serializableExtra;
        this.mScreenOrientation = this.mVideoPlayConfig.isShowLandscape() ? 1 : 0;
        this.mAdTemplate.mInitVoiceStatus = this.mVideoPlayConfig.isVideoSoundEnable() ? 2 : 1;
        initVideoPlayConfig(this.mVideoPlayConfig);
        b.a().a(this);
        this.mRewardTopBarNewStyle = com.kwad.sdk.core.config.b.k();
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
        boolean z = !af.e(this.mContext);
        if ((isRewardLaunchAppTask() || isRewardLandPageOpenTask()) && z) {
            this.mDetailVideoView.setForce(true);
        }
        this.mDetailVideoView.setAd(true);
    }

    private boolean isLaunchTaskCompleted() {
        return this.mCallerContext.y != null && this.mCallerContext.y.d();
    }

    public static void launch(Context context, @NonNull AdTemplate adTemplate, @NonNull KsVideoPlayConfig ksVideoPlayConfig, KsRewardVideoAd.RewardAdInteractionListener rewardAdInteractionListener, int i) {
        com.kwad.sdk.utils.m.b(adTemplate);
        Intent intent = new Intent(context, (Class<?>) (ksVideoPlayConfig.isShowLandscape() ? KSRewardLandScapeVideoActivity.class : KsRewardVideoActivity.class));
        intent.setFlags(268435456);
        intent.putExtra("key_template_json", adTemplate.toJson().toString());
        intent.putExtra("key_video_play_config", ksVideoPlayConfig);
        intent.putExtra(KEY_REWARD_TYPE, i);
        mInteractionListener = rewardAdInteractionListener;
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markOpenNsCompleted() {
        if (this.mCallerContext.z != null) {
            this.mCallerContext.z.h();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPageDismiss(boolean z) {
        AdTemplate adTemplate;
        JSONObject jSONObject;
        if (this.mPageDismissCalled) {
            return;
        }
        int i = 1;
        this.mPageDismissCalled = true;
        if (z) {
            adTemplate = this.mAdTemplate;
            jSONObject = this.mCallerContext.e;
        } else {
            adTemplate = this.mAdTemplate;
            i = 6;
            jSONObject = this.mReportExtData;
        }
        com.kwad.sdk.core.report.a.a(adTemplate, i, jSONObject);
        if (mInteractionListener != null) {
            mInteractionListener.onPageDismiss();
        }
    }

    private void notifyRearwdVerifySteped(int i, int i2) {
        if (this.mCallerContext.w.contains(Integer.valueOf(i2))) {
            return;
        }
        this.mCallerContext.w.add(Integer.valueOf(i2));
        try {
            mInteractionListener.onRewardStepVerify(i, i2);
        } catch (Throwable th) {
            com.kwad.sdk.core.d.a.b(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRewardVerify() {
        if (this.mCallerContext.v) {
            return;
        }
        if (isRewardLaunchAppTask()) {
            if (!(this.mCallerContext.y != null && this.mCallerContext.y.d())) {
                return;
            }
        } else if (isRewardLandPageOpenTask()) {
            if (!(this.mCallerContext.z != null && this.mCallerContext.z.d())) {
                return;
            }
        }
        handleNotifyVerify();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRewardVerifyStepByStep() {
        if (mInteractionListener == null) {
            return;
        }
        if (isRewardLaunchAppTask()) {
            notifyRearwdVerifySteped(2, 0);
            if (isLaunchTaskCompleted()) {
                notifyRearwdVerifySteped(2, 2);
                return;
            }
            return;
        }
        if (!isRewardLandPageOpenTask()) {
            notifyRearwdVerifySteped(0, 0);
            return;
        }
        boolean z = this.mCallerContext.z != null && this.mCallerContext.z.d();
        notifyRearwdVerifySteped(1, 0);
        if (z) {
            notifyRearwdVerifySteped(1, 1);
        }
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void finish() {
        notifyPageDismiss(false);
        getActivity().finish();
    }

    @Override // com.kwad.sdk.h.a
    protected String getPageName() {
        return "KSRewardLandScapeVideoActivityProxy";
    }

    public boolean isRefluxVisible() {
        return this.rewardRefluxPresenter != null && this.rewardRefluxPresenter.r();
    }

    public boolean isRewardLandPageOpenTask() {
        return com.kwad.sdk.core.response.a.d.v(this.mAdTemplate);
    }

    public boolean isRewardLaunchAppTask() {
        return com.kwad.sdk.core.response.a.d.u(this.mAdTemplate);
    }

    @Override // com.kwad.sdk.api.proxy.IActivityProxy
    public void onBackPressed() {
        if (this.rewardRefluxPresenter != null) {
            int i = this.rewardRefluxPresenter.i();
            if (i == 2) {
                return;
            }
            if (i == 3) {
                super.onBackPressed();
                return;
            }
        }
        if (this.mIsBackEnable) {
            super.onBackPressed();
        }
    }

    @Override // com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        if (!initData()) {
            finish();
            return;
        }
        this.mContext = Wrapper.wrapContextIfNeed(getActivity());
        setContentView(R.layout.ksad_activity_reward_video);
        initView();
        onActivityCreated(this.mRootContainer);
        d.a().a(this.mRewardVerifyListener);
    }

    @Override // com.kwad.sdk.core.e.a
    protected a onCreateCallerContext() {
        a aVar = new a();
        aVar.h = getActivity();
        aVar.i = this.mContext;
        aVar.b = this.mAdOpenInteractionListener;
        aVar.c = this.mAdRewardStepListener;
        aVar.f = this.mScreenOrientation;
        aVar.d = this.mVideoPlayConfig;
        aVar.e = this.mReportExtData;
        aVar.j = this.mRootContainer;
        aVar.g = this.mAdTemplate;
        com.kwad.sdk.reward.c.a aVar2 = new com.kwad.sdk.reward.c.a(this.mAdTemplate, this.mDetailVideoView, this.mVideoPlayConfig, this.rewardType == 2);
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
        if (com.kwad.sdk.core.response.a.a.q(this.mAdInfo)) {
            aVar.p = new com.kwad.sdk.i.a().a(true);
        }
        aVar.r = true;
        aVar.s = this.mRewardTopBarNewStyle;
        if (com.kwad.sdk.core.response.a.a.an(this.mAdInfo)) {
            aVar.m = new com.kwad.sdk.widget.e((KsAdWebView) findViewById(R.id.ksad_playable_webview));
        }
        return aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00de  */
    @Override // com.kwad.sdk.core.e.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected com.kwad.sdk.mvp.Presenter onCreatePresenter() {
        /*
            Method dump skipped, instructions count: 289
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.reward.KSRewardVideoActivityProxy.onCreatePresenter():com.kwad.sdk.mvp.Presenter");
    }

    @Override // com.kwad.sdk.core.e.a, com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onDestroy() {
        d.a().b(this.mRewardVerifyListener);
        super.onDestroy();
        notifyPageDismiss(false);
        if (this.mAdInfo != null) {
            com.kwad.sdk.core.videocache.b.a.a(this.mContext.getApplicationContext()).c(com.kwad.sdk.core.response.a.a.a(this.mAdInfo));
        }
        mInteractionListener = null;
        b.a().b(this);
        h.a().b();
    }

    @Override // com.kwad.sdk.reward.b.InterfaceC0068b
    public void onPageClose() {
        finish();
    }

    @Override // com.kwad.sdk.core.e.a, com.kwad.sdk.h.a, com.kwad.sdk.api.proxy.IActivityProxy
    public void onResume() throws JSONException {
        super.onResume();
        com.kwad.sdk.core.c.a.a().a(this.mAdTemplate);
    }
}
