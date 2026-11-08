package com.unity3d.scar.adapter.v1920;

import android.content.Context;
import com.unity3d.scar.adapter.common.IAdsErrorHandler;
import com.unity3d.scar.adapter.common.IScarAdapter;
import com.unity3d.scar.adapter.common.IScarInterstitialAdListenerWrapper;
import com.unity3d.scar.adapter.common.IScarRewardedAdListenerWrapper;
import com.unity3d.scar.adapter.common.ScarAdapterBase;
import com.unity3d.scar.adapter.common.Utils;
import com.unity3d.scar.adapter.common.scarads.IScarLoadListener;
import com.unity3d.scar.adapter.common.scarads.ScarAdMetadata;
import com.unity3d.scar.adapter.v1920.scarads.ScarInterstitialAd;
import com.unity3d.scar.adapter.v1920.scarads.ScarRewardedAd;
import com.unity3d.scar.adapter.v1920.signals.SignalsReader;
import com.unity3d.scar.adapter.v1920.signals.SignalsStorage;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ScarAdapter extends ScarAdapterBase implements IScarAdapter {
    private SignalsStorage _scarSignalStorage;

    public ScarAdapter(IAdsErrorHandler iAdsErrorHandler) {
        super(iAdsErrorHandler);
        this._scarSignalStorage = new SignalsStorage();
        this._scarSignalReader = new SignalsReader(this._scarSignalStorage);
    }

    @Override // com.unity3d.scar.adapter.common.IScarAdapter
    public void loadInterstitialAd(Context context, final ScarAdMetadata scarAdMetadata, IScarInterstitialAdListenerWrapper iScarInterstitialAdListenerWrapper) {
        final ScarInterstitialAd scarInterstitialAd = new ScarInterstitialAd(context, this._scarSignalStorage.getQueryInfoMetadata(scarAdMetadata.getPlacementId()), scarAdMetadata, this._adsErrorHandler, iScarInterstitialAdListenerWrapper);
        Utils.runOnUiThread(new Runnable() { // from class: com.unity3d.scar.adapter.v1920.ScarAdapter.1
            @Override // java.lang.Runnable
            public void run() {
                scarInterstitialAd.loadAd(new IScarLoadListener() { // from class: com.unity3d.scar.adapter.v1920.ScarAdapter.1.1
                    @Override // com.unity3d.scar.adapter.common.scarads.IScarLoadListener
                    public void onAdLoaded() {
                        ScarAdapter.this._loadedAds.put(scarAdMetadata.getPlacementId(), scarInterstitialAd);
                    }
                });
            }
        });
    }

    @Override // com.unity3d.scar.adapter.common.IScarAdapter
    public void loadRewardedAd(Context context, final ScarAdMetadata scarAdMetadata, IScarRewardedAdListenerWrapper iScarRewardedAdListenerWrapper) {
        final ScarRewardedAd scarRewardedAd = new ScarRewardedAd(context, this._scarSignalStorage.getQueryInfoMetadata(scarAdMetadata.getPlacementId()), scarAdMetadata, this._adsErrorHandler, iScarRewardedAdListenerWrapper);
        Utils.runOnUiThread(new Runnable() { // from class: com.unity3d.scar.adapter.v1920.ScarAdapter.2
            @Override // java.lang.Runnable
            public void run() {
                scarRewardedAd.loadAd(new IScarLoadListener() { // from class: com.unity3d.scar.adapter.v1920.ScarAdapter.2.1
                    @Override // com.unity3d.scar.adapter.common.scarads.IScarLoadListener
                    public void onAdLoaded() {
                        ScarAdapter.this._loadedAds.put(scarAdMetadata.getPlacementId(), scarRewardedAd);
                    }
                });
            }
        });
    }
}
