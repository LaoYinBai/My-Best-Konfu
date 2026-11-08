package com.unity3d.services.ads.gmascar.handlers;

import com.unity3d.scar.adapter.common.GMAEvent;
import com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper;
import com.unity3d.scar.adapter.common.scarads.ScarAdMetadata;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.Timer;
import java.util.TimerTask;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ScarInterstitialAdHandler implements IScarInterstitialAdListenerWrapper {
    private ScarAdMetadata _scarAdMetadata;
    private boolean _finishedPlaying = false;
    private boolean _hasSentStartEvents = false;
    private TimerTask _playbackTimerTask = new TimerTask() { // from class: com.unity3d.services.ads.gmascar.handlers.ScarInterstitialAdHandler.1
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            ScarInterstitialAdHandler.this._finishedPlaying = true;
        }
    };
    private Timer _playbackTimer = new Timer();

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdLeftApplication() {
    }

    public ScarInterstitialAdHandler(ScarAdMetadata scarAdMetadata) {
        this._scarAdMetadata = scarAdMetadata;
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdLoaded() {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_LOADED, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId());
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdFailedToLoad(int i, String str) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.LOAD_ERROR, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId(), str, Integer.valueOf(i));
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdOpened() {
        if (!this._hasSentStartEvents) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_STARTED, new Object[0]);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.FIRST_QUARTILE, new Object[0]);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.MIDPOINT, new Object[0]);
            this._hasSentStartEvents = true;
        }
        this._finishedPlaying = false;
        this._playbackTimer.schedule(this._playbackTimerTask, this._scarAdMetadata.getVideoLengthMs().intValue());
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdFailedToShow(int i, String str) {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.INTERSTITIAL_SHOW_ERROR, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId(), str, Integer.valueOf(i));
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdClicked() {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_CLICKED, new Object[0]);
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdClosed() {
        if (!this._finishedPlaying) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_SKIPPED, new Object[0]);
            this._playbackTimer.cancel();
        }
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.AD_CLOSED, new Object[0]);
    }

    @Override // com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper
    public void onAdImpression() {
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.GMA, GMAEvent.INTERSTITIAL_IMPRESSION_RECORDED, this._scarAdMetadata.getPlacementId(), this._scarAdMetadata.getQueryId());
    }
}
