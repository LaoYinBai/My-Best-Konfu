package com.unity3d.services.ads.gmascar;

import com.unity3d.scar.adapter.common.GMAAdsError;
import com.unity3d.scar.adapter.common.GMAEvent;
import com.unity3d.scar.adapter.common.IScarAdapter;
import com.unity3d.scar.adapter.common.WebViewAdsError;
import com.unity3d.scar.adapter.common.scarads.ScarAdMetadata;
import com.unity3d.services.ads.gmascar.adapters.ScarAdapterFactory;
import com.unity3d.services.ads.gmascar.bridges.AdapterStatusBridge;
import com.unity3d.services.ads.gmascar.bridges.InitializationStatusBridge;
import com.unity3d.services.ads.gmascar.bridges.InitializeListenerBridge;
import com.unity3d.services.ads.gmascar.bridges.MobileAdsBridge;
import com.unity3d.services.ads.gmascar.finder.GMAInitializer;
import com.unity3d.services.ads.gmascar.finder.PresenceDetector;
import com.unity3d.services.ads.gmascar.finder.ScarVersionFinder;
import com.unity3d.services.ads.gmascar.handlers.ScarInterstitialAdHandler;
import com.unity3d.services.ads.gmascar.handlers.ScarRewardedAdHandler;
import com.unity3d.services.ads.gmascar.handlers.SignalsHandler;
import com.unity3d.services.ads.gmascar.handlers.WebViewErrorHandler;
import com.unity3d.services.core.properties.ClientProperties;
import java.lang.reflect.InvocationTargetException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GMAScarAdapterBridge {
    private IScarAdapter _scarAdapter;
    private MobileAdsBridge _mobileAdsBridge = new MobileAdsBridge();
    private InitializationStatusBridge _initializationStatusBridge = new InitializationStatusBridge();
    private InitializeListenerBridge _initializationListenerBridge = new InitializeListenerBridge();
    private AdapterStatusBridge _adapterStatusBridge = new AdapterStatusBridge();
    private WebViewErrorHandler _webViewErrorHandler = new WebViewErrorHandler();
    private ScarAdapterFactory _scarAdapterFactory = new ScarAdapterFactory();
    private PresenceDetector _presenceDetector = new PresenceDetector(this._mobileAdsBridge, this._initializationListenerBridge, this._initializationStatusBridge, this._adapterStatusBridge);
    private GMAInitializer _gmaInitializer = new GMAInitializer(this._mobileAdsBridge, this._initializationListenerBridge, this._initializationStatusBridge, this._adapterStatusBridge);
    private ScarVersionFinder _scarVersionFinder = new ScarVersionFinder(this._mobileAdsBridge, this._presenceDetector, this._gmaInitializer);

    public void initializeScar() {
        if (this._presenceDetector.areGMAClassesPresent()) {
            this._gmaInitializer.initializeGMA();
        } else {
            this._webViewErrorHandler.handleError((WebViewAdsError) new GMAAdsError(GMAEvent.INIT_ERROR, new Object[0]));
        }
    }

    public boolean isInitialized() {
        return this._gmaInitializer.isInitialized();
    }

    public void getVersion() throws IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException {
        this._scarVersionFinder.getVersion();
    }

    public void getSCARSignals(String[] strArr, String[] strArr2) {
        this._scarAdapter = getScarAdapterObject();
        SignalsHandler signalsHandler = new SignalsHandler();
        if (this._scarAdapter != null) {
            this._scarAdapter.getSCARSignals(ClientProperties.getApplicationContext(), strArr, strArr2, signalsHandler);
        } else {
            this._webViewErrorHandler.handleError((WebViewAdsError) GMAAdsError.InternalSignalsError("Could not create SCAR adapter object"));
        }
    }

    public void load(boolean z, String str, String str2, String str3, String str4, int i) {
        ScarAdMetadata scarAdMetadata = new ScarAdMetadata(str, str2, str4, str3, Integer.valueOf(i));
        this._scarAdapter = getScarAdapterObject();
        if (this._scarAdapter == null) {
            this._webViewErrorHandler.handleError((WebViewAdsError) GMAAdsError.InternalLoadError(scarAdMetadata, "Scar Adapter object is null"));
        } else if (z) {
            loadInterstitialAd(scarAdMetadata);
        } else {
            loadRewardedAd(scarAdMetadata);
        }
    }

    private void loadInterstitialAd(ScarAdMetadata scarAdMetadata) {
        this._scarAdapter.loadInterstitialAd(ClientProperties.getApplicationContext(), scarAdMetadata, new ScarInterstitialAdHandler(scarAdMetadata));
    }

    private void loadRewardedAd(ScarAdMetadata scarAdMetadata) {
        this._scarAdapter.loadRewardedAd(ClientProperties.getApplicationContext(), scarAdMetadata, new ScarRewardedAdHandler(scarAdMetadata));
    }

    public void show(String str, String str2, boolean z) {
        ScarAdMetadata scarAdMetadata = new ScarAdMetadata(str, str2);
        this._scarAdapter = getScarAdapterObject();
        if (this._scarAdapter != null) {
            this._scarAdapter.show(ClientProperties.getActivity(), str2, str);
        } else {
            this._webViewErrorHandler.handleError((WebViewAdsError) GMAAdsError.InternalShowError(scarAdMetadata, "Scar Adapter object is null"));
        }
    }

    private IScarAdapter getScarAdapterObject() {
        if (this._scarAdapter == null) {
            this._scarAdapter = this._scarAdapterFactory.createScarAdapter(this._scarVersionFinder.getGoogleSdkVersionCode(), this._webViewErrorHandler);
        }
        return this._scarAdapter;
    }
}
