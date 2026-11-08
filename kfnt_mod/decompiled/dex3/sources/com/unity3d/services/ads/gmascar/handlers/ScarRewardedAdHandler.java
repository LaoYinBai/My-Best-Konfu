package com.unity3d.services.ads.gmascar.handlers;

import com.unity3d.scar.adapter.common.GMAEvent;
import com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper;
import com.unity3d.scar.adapter.common.scarads.ScarAdMetadata;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ScarRewardedAdHandler implements IScarRewardedAdListenerWrapper {
    private ScarAdMetadata _scarAdMetadata;
    private boolean _finishedPlaying = false;
    private boolean _hasRewarded = false;
    private boolean _hasSentStartEvents = false;
    private TimerTask _playbackTimerTask = new TimerTask() { // from class: com.unity3d.services.ads.gmascar.handlers.ScarRewardedAdHandler.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ScarRewardedAdHandler.this._finishedPlaying = true;
        }
    };
    private Timer _playbackTimer = new Timer();

    public ScarRewardedAdHandler(ScarAdMetadata scarAdMetadata) {
        this._scarAdMetadata = scarAdMetadata;
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onRewardedAdLoaded() {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_LOADED, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId());
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onRewardedAdFailedToLoad(int i, String str) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.LOAD_ERROR, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId(), str, Integer.valueOf(i));
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onRewardedAdOpened() {
        if (!this._hasSentStartEvents) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_STARTED, new Object[0]);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.FIRST_QUARTILE, new Object[0]);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.MIDPOINT, new Object[0]);
            this._hasSentStartEvents = true;
        }
        if (!this._hasRewarded) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_EARNED_REWARD, new Object[0]);
            this._hasRewarded = true;
        }
        this._finishedPlaying = false;
        this._playbackTimer.schedule(this._playbackTimerTask, this._scarAdMetadata.getVideoLengthMs().intValue());
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onRewardedAdFailedToShow(int i, String str) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.REWARDED_SHOW_ERROR, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId(), str, Integer.valueOf(i));
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onUserEarnedReward() {
        if (this._hasRewarded) {
            return;
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_EARNED_REWARD, new Object[0]);
        this._hasRewarded = true;
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onRewardedAdClosed() {
        if (!this._finishedPlaying) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_SKIPPED, new Object[0]);
            this._playbackTimer.cancel();
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_CLOSED, new Object[0]);
    }

    @Override // com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper
    public void onAdImpression() {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.REWARDED_IMPRESSION_RECORDED, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId());
    }
}
